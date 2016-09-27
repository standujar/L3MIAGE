package states;

import constantes.Constantes;
import fantome.Blinky;
import fantome.Clyde;
import fantome.Inky;
import fantome.Pinky;
import java.io.FileNotFoundException;
import labyrinthe.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import pacman.Pacman;

public class Jeu extends BasicGameState
{
    
    private Labyrinthe  laby1;
    private Pacman      pacman;
    private Image       imgViePacman;
    private Blinky      blinky;
    private Pinky       pinky;
    private Inky        inky;
    private Clyde       clyde;
    
    private int iPosScoreX;
    private int iPosScoreY;
    
    private int level;
    
    @Override
    // @return : ID de l'état 
    public int getID() 
    {
        return Constantes.iID_ETAT_JEU;
    }

    @Override
    public void init(GameContainer gcPar, StateBasedGame sbgPar) throws SlickException 
    {
        try {
            this.laby1 = new Labyrinthe(Constantes.sCHEMIN_LABYRINTHE_1, Constantes.sCHEMIN_TEXTURE_LABYRINTHE_1);
        } catch (FileNotFoundException ex) {}
        
        this.pacman = new Pacman(this.laby1);
        this.blinky = new Blinky(this.laby1, this.pacman);
        this.pinky  = new Pinky(this.laby1, this.pacman);
        this.inky   = new Inky(this.laby1, this.pacman, this.blinky);
        this.clyde  = new Clyde(this.laby1, this.pacman);
        
        this.imgViePacman = new Image(Constantes.sCHEMIN_TEXTURE_PACMAN_DROITE);
        
        this.iPosScoreX = 790;
        this.iPosScoreY = 30; 
        
        this.level = 1;
    }
    
    @Override
    public void enter(GameContainer gcPar, StateBasedGame sbgPar) throws SlickException 
    {
        //Valeur 0 pour indiquer la partie n'est pas en pause
        if( Constantes.iPause == Constantes.iID_ETAT_MENU_PAUSE )
        {
            this.init(gcPar,sbgPar);
        }
    }

    @Override
    public void render(GameContainer gcPar, StateBasedGame sbgPar, Graphics grphcsPar) throws SlickException 
    {
        this.laby1.render(grphcsPar);
        this.pacman.render(grphcsPar);
        this.blinky.render(grphcsPar);
        this.pinky.render(grphcsPar);
        this.inky.render(grphcsPar);
        this.clyde.render(grphcsPar);
        
        // Affichage du score
        grphcsPar.drawString("Score : \n" + String.valueOf(this.pacman.getScore()), iPosScoreX, iPosScoreY);
        
        for(int i=0; i<(pacman.getVie()-1); i++)
        {
            grphcsPar.drawImage(this.imgViePacman, i*(Constantes.iTAILLE_PACMAN_WIDTH + 10), Constantes.iTAILLE_TILE_HEIGHT * Constantes.iTAILLE_LABYRINTHE_LIGNE + 10);
        }
        
    }

    @Override
    public void update(GameContainer gcPar, StateBasedGame sbgPar, int iParDeltaTimer) throws SlickException 
    {
        
        if(gcPar.getInput().isKeyPressed(Input.KEY_ESCAPE))
        {
            Constantes.iPause = Constantes.iID_ETAT_JEU;
            sbgPar.enterState(Constantes.iID_ETAT_MENU_PAUSE);
        }
        
        this.pacman.update(gcPar, iParDeltaTimer);
        this.blinky.update(gcPar, iParDeltaTimer);
        this.pinky.update(gcPar, iParDeltaTimer);
        
        if( this.pacman.getPacGommeMange() == 29 )
        {
            this.inky.setBehaviorChase();
            this.inky.update(gcPar, iParDeltaTimer);
        }
        else if (this.pacman.getPacGommeMange() < 29)
        {
            this.inky.setBehaviorChase();
        }
        else
        {
            this.inky.update(gcPar, iParDeltaTimer);
        }
            
        if( this.pacman.getPacGommeMange() == 89 )
        {
            this.clyde.setBehaviorChase();
            this.clyde.update(gcPar, iParDeltaTimer);
        }
        else if(this.pacman.getPacGommeMange() < 89)
        {
            this.clyde.setBehaviorChase();
        }
        else
        {
            this.clyde.update(gcPar, iParDeltaTimer);
        }
        
        if ( this.pacman.isEaten() )
        {
            if( this.pacman.getVie() == 0 )
            {
                Constantes.iScore = pacman.getScore();
                if(Constantes.iTabScore[4] < pacman.getScore())
                {
                    sbgPar.enterState(Constantes.iID_ETAT_AJOUT_PSEUDO,new FadeOutTransition(), new FadeInTransition());
                }
                else
                {
                    sbgPar.enterState(Constantes.iID_ETAT_FIN_PARTIE,new FadeOutTransition(), new FadeInTransition());
                }
            }
               
            this.pacman.reset();
            this.blinky.initialisation();
            this.pinky.initialisation();
            this.inky.initialisation();
            this.clyde.initialisation();
        }
        
        if( this.pacman.isSuperMode() )
        {
            this.pacman.setSuperModeOff();
            this.blinky.setBehaviorFrightened();
            this.inky.setBehaviorFrightened();
            this.clyde.setBehaviorFrightened();
            this.pinky.setBehaviorFrightened();
        }
        
        
        if(this.pacman.getPacGommeMange() == Constantes.iMAX_PACGOMME_PAR_LEVEL * this.level)
        {
            this.level++;
            this.resetLevel();
        }
        
        
        System.out.println(this.pacman.getPacGommeMange());
    }

    private void resetLevel() throws SlickException 
    {
        try 
        {
            this.laby1 = new Labyrinthe(Constantes.sCHEMIN_LABYRINTHE_1, Constantes.sCHEMIN_TEXTURE_LABYRINTHE_1);
            this.pacman.setLabyrinthe(laby1);
        } catch (FileNotFoundException ex) {}
        
        this.pacman.reset();
        this.blinky.initialisation();
        this.pinky.initialisation();
        this.inky.initialisation();
        this.clyde.initialisation();

    }
    
}
