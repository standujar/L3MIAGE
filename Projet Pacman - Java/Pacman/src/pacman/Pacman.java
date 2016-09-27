package pacman;

import constantes.Constantes;
import labyrinthe.Labyrinthe;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Pacman 
{
    private Image imgGauche;
    private Image imgDroite;
    private Image imgHaut;
    private Image imgBas;
    private Image imgRender;
    
    private float posColonne;
    private float posLigne;
    
    private int iDirection;
    
    private float fVitesse;
    
    private Labyrinthe labyrinthe;
    
    private int iScore;
    
    private int iPacGommeMange;
    
    private int iVie;
    private boolean bEaten;
    
    private boolean bSuperMode;
    private int iNbFantomeEatenWhileSuperMode;
    
    public Pacman(Labyrinthe labPar)
    {
        try {
            this.imgGauche = new Image(Constantes.sCHEMIN_TEXTURE_PACMAN_GAUCHE);
            this.imgDroite = new Image(Constantes.sCHEMIN_TEXTURE_PACMAN_DROITE);
            this.imgHaut = new Image(Constantes.sCHEMIN_TEXTURE_PACMAN_HAUT);
            this.imgBas = new Image(Constantes.sCHEMIN_TEXTURE_PACMAN_BAS);
            
            this.iDirection = Constantes.iGAUCHE;
            this.imgRender = this.imgGauche;
        } catch (SlickException ex) {}   
        
        posColonne = 14*Constantes.iTAILLE_TILE_WIDTH;
        posLigne = 23*Constantes.iTAILLE_TILE_HEIGHT-8;
        
        this.fVitesse = Constantes.fVITESSE_NORMALE;
        
        this.labyrinthe = labPar;
        
        this.iPacGommeMange = 0;
        
        this.iVie = 3;
        this.bEaten = false;
        
        this.bSuperMode = false;
        this.iNbFantomeEatenWhileSuperMode = 0;
    }
    
    /**
     * retourne le score en fonction du nombre de pacgomme mangé  
     * 
     * @return le score
     */
    public int getScore()
    {
        return this.iScore;
    }  
       
    public float getPosColonne()
    {
        return posColonne;
    }    
    
    public float getPosLigne()
    {
        return posLigne;
    }   
    
    public float getVitesse()
    {
        return this.fVitesse;
    }
    
    public int getDirection()
    {
        return this.iDirection;
    }
   
    public int getPacGommeMange()
    {
        return this.iPacGommeMange;
    }
    
    public boolean isEaten()
    {
        return this.bEaten;
    }
    
    public boolean isSuperMode()
    {
        return this.bSuperMode;
    }
    
    public void setSuperModeOff()
    {
        this.bSuperMode = false;
    }
    
    public void setLabyrinthe(Labyrinthe labPar)
    {
        this.labyrinthe = labPar;
    }
    
    public void reset()
    {
        this.iDirection = Constantes.iGAUCHE;
        this.imgRender  = this.imgGauche;
        this.posColonne = 14*Constantes.iTAILLE_TILE_WIDTH;
        this.posLigne   = 23*Constantes.iTAILLE_TILE_HEIGHT-8;
        this.bEaten     = false;
    }
    
    public void fantomTouchedMe()
    {
        if( iVie > 0 )
        {
           iVie--;
           this.bEaten = true;
        }
    }
    
    public void resetNbFantomeEaterWhileSuperMode()
    {
        this.iNbFantomeEatenWhileSuperMode = 0;
    }
    
    public void mangeFantome()
    {
        if(this.iNbFantomeEatenWhileSuperMode <= 4)
        {
            this.iNbFantomeEatenWhileSuperMode++;
            this.iScore += (Math.pow(2, this.iNbFantomeEatenWhileSuperMode) * 10 * 10);
        }
        else 
        {
            this.iNbFantomeEatenWhileSuperMode = 1;   
        }
    }
    
    public int getVie()
    {
        return iVie;
    }
    
    /**
     * Le but de cette fonction est de regarder si le Pacman va pouvoir se déplacer : on test le déplacement, si avec le déplacement le Pacman touche un mur alors il y a collision.
     * 
     * @return true si il y a collision, false s'il n'y a pas de colllision
     * 
     */
    public boolean testCollision()
    {
        int iTypeCase = -1;
        int iColonne = 0;
        int iLigne = 0;

        if(this.iDirection == Constantes.iGAUCHE)
        {
            iColonne    = (int)((posColonne-this.fVitesse)/Constantes.iTAILLE_TILE_WIDTH);
            iLigne      = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
        }
        else if(this.iDirection == Constantes.iDROITE)
        {
            iColonne    = (int)((posColonne+this.fVitesse+Constantes.iTAILLE_PACMAN_WIDTH)/Constantes.iTAILLE_TILE_WIDTH);
            iLigne      = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
        }
        else if(this.iDirection == Constantes.iHAUT)
        {
            iColonne    = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
            iLigne      = (int)((posLigne-this.fVitesse)/Constantes.iTAILLE_TILE_HEIGHT);
        }
        else if(this.iDirection == Constantes.iBAS)
        {
            iColonne    = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
            iLigne      = (int)((posLigne+this.fVitesse+Constantes.iTAILLE_PACMAN_HEIGHT)/Constantes.iTAILLE_TILE_HEIGHT);
        }
        
        if ( iColonne < 0 )
        {
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, 0);
        }
        else if ( iColonne > 27 )
        {
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, 27);
        }
        else
        {
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, iColonne);
        }
         
        if( iTypeCase != Constantes.iPACGOMME && iTypeCase != Constantes.iINTERSECTION && iTypeCase != Constantes.iTUNNEL && iTypeCase != Constantes.iPACGOMME_SPECIAL)        
        {
            return true;
        }
        else
            return false;
    }
    
    
    /**
     * 
     * @param iDirection test la collision par rapport à la direction demandé
     * 
     * @return true si il y a collision, false s'il n'y a pas de colllision
     */
    public boolean testCollision(int iDirection)
    {
        
        int iTypeCase = -1;
        //System.out.println(iDirection);
        if(iDirection == Constantes.iGAUCHE)
        {
            int iColonne    = (int)((posColonne-this.fVitesse)/Constantes.iTAILLE_TILE_WIDTH);
            int iLigne      = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
            iTypeCase       = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne,iColonne);
        }
        else if(iDirection == Constantes.iDROITE)
        {
            int iColonne    = (int)((posColonne+this.fVitesse+Constantes.iTAILLE_PACMAN_WIDTH)/Constantes.iTAILLE_TILE_WIDTH);
            int iLigne      = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
            iTypeCase       = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, iColonne);
        }
        else if(iDirection == Constantes.iHAUT)
        {
            int iColonne    = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
            int iLigne      = (int)((posLigne-this.fVitesse)/Constantes.iTAILLE_TILE_HEIGHT);
            iTypeCase       = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, iColonne);
        }
        else if(iDirection == Constantes.iBAS)
        {
            int iColonne    = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
            int iLigne      = (int)((posLigne+this.fVitesse+Constantes.iTAILLE_PACMAN_HEIGHT)/Constantes.iTAILLE_TILE_HEIGHT);
            iTypeCase       = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, iColonne);
        }
        
         
        if(iTypeCase != Constantes.iPACGOMME && iTypeCase!=Constantes.iINTERSECTION && iTypeCase != Constantes.iTUNNEL && iTypeCase != Constantes.iPACGOMME_SPECIAL)        
        {
            return true;
        }
        else
            return false;
    }
    
    
    public void update(GameContainer gcPar, int iParDeltaTimer)
    {
        
        int iTypeCase = -1;
        int iColonne = 0;
        int iLigne = 0;

        if(this.iDirection == Constantes.iHAUT)
        {
            iColonne    = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
            iLigne      = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2)+11)/Constantes.iTAILLE_TILE_HEIGHT);
        }
        if(this.iDirection == Constantes.iBAS)
        {
            iColonne    = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
            iLigne      = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2)-10)/Constantes.iTAILLE_TILE_HEIGHT);
        }
        if(this.iDirection == Constantes.iGAUCHE)
        {
            iColonne    = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2)+13)/Constantes.iTAILLE_TILE_WIDTH);
            iLigne      = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
        }
        if(this.iDirection == Constantes.iDROITE)
        {
            iColonne    = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2)-13)/Constantes.iTAILLE_TILE_WIDTH);
            iLigne      = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
        }
        
        if ( iColonne < 0 )
        {
            iColonne = 0;
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, iColonne);
        }
        else if ( iColonne > 27 )
        {
            iColonne = 27;
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, iColonne);
        }
        else
        {
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, iColonne);
        }
        
        
        
        if( (iTypeCase == Constantes.iINTERSECTION && !this.testCollision(Constantes.iBAS) && gcPar.getInput().isKeyDown(Input.KEY_DOWN))
                || (gcPar.getInput().isKeyDown(Input.KEY_DOWN) && (this.iDirection == Constantes.iHAUT)) )
        {
            this.iDirection = Constantes.iBAS;
        }
        else if( (iTypeCase == Constantes.iINTERSECTION && !this.testCollision(Constantes.iHAUT) && gcPar.getInput().isKeyDown(Input.KEY_UP))
                || (gcPar.getInput().isKeyDown(Input.KEY_UP) && (this.iDirection == Constantes.iBAS)) )
        { 
            this.iDirection = Constantes.iHAUT;
        }
        else if( (iTypeCase == Constantes.iINTERSECTION && !this.testCollision(Constantes.iDROITE) && gcPar.getInput().isKeyDown(Input.KEY_RIGHT)) 
                || (gcPar.getInput().isKeyDown(Input.KEY_RIGHT) && (this.iDirection == Constantes.iGAUCHE)) )
        {
            this.iDirection = Constantes.iDROITE;
        }
        else if( (iTypeCase == Constantes.iINTERSECTION && !this.testCollision(Constantes.iGAUCHE) && gcPar.getInput().isKeyDown(Input.KEY_LEFT)) 
                || (gcPar.getInput().isKeyDown(Input.KEY_LEFT) && (this.iDirection == Constantes.iDROITE)) )
        {
            this.iDirection = Constantes.iGAUCHE;
        }
        
        
        // GESTION TUNNEL : le pacman se tÃ©lÃ©porte lorsqu'il dÃ©passe les limites du labyrinthe
        if((int)this.posColonne < Constantes.iCOORDONNE_LIMITE_LABYRINTHE_GAUCHE)
        {
            int iColonneMax = Constantes.iTAILLE_LABYRINTHE_COLONNE - 1;
            this.posColonne =  iColonneMax * Constantes.iTAILLE_TILE_WIDTH;
        }
        else if((int)this.posColonne > Constantes.iCOORDONNE_LIMITE_LABYRINTHE_DROITE)
        {
            this.posColonne = 0;
        }
        
        if(this.testCollision() == false)
        {
            int iCase = this.labyrinthe.getTypeCaseLabyrintheRender(iLigne, iColonne);
            if( iCase == Constantes.iINTERSECTION || iCase == Constantes.iTUNNEL || iCase == Constantes.iPACGOMME  || iCase == Constantes.iPACGOMME_SPECIAL)
            {
                this.labyrinthe.setTypeCaseLabyrintheRender(iLigne, iColonne, Constantes.iVIDE);
                if(iCase == Constantes.iPACGOMME)
                {
                    this.iScore += 10;
                    this.iPacGommeMange++;
                }
                if(iCase == Constantes.iPACGOMME_SPECIAL)
                {
                    this.iScore += 50;
                    this.iPacGommeMange++;
                    this.bSuperMode = true;
                }
            }
            
            if(this.iDirection == Constantes.iGAUCHE)
            {
                this.posColonne -= this.fVitesse*iParDeltaTimer;
                this.imgRender  = this.imgGauche;
            }
            else if(this.iDirection == Constantes.iDROITE)
            {
                this.posColonne += this.fVitesse*iParDeltaTimer;
                this.imgRender  = this.imgDroite;
            }
            else if(this.iDirection == Constantes.iHAUT)
            {
                this.posLigne   -= this.fVitesse*iParDeltaTimer;
                this.imgRender  = this.imgHaut;
            }
            else
            {
                this.posLigne   += this.fVitesse*iParDeltaTimer;
                this.imgRender  = this.imgBas;
            }
        }
        else
        {
            if(this.iDirection == Constantes.iGAUCHE)
            {
                int fPosPacmanColonne   = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
                float fPosIntersectionColonne = (fPosPacmanColonne * Constantes.iTAILLE_TILE_WIDTH) + (Constantes.iTAILLE_TILE_WIDTH/2);
                float fPosCentrePacmanColonne = posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2);

                if(fPosCentrePacmanColonne > fPosIntersectionColonne)
                    this.posColonne -= this.fVitesse*iParDeltaTimer;
            }
            else if(this.iDirection == Constantes.iDROITE)
            {
                int fPosPacmanColonne   = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
                float fPosIntersectionColonne = (fPosPacmanColonne * Constantes.iTAILLE_TILE_WIDTH) + (Constantes.iTAILLE_TILE_WIDTH/2);
                float fPosCentrePacmanColonne = posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2);

                if(fPosCentrePacmanColonne < fPosIntersectionColonne)
                    this.posColonne += this.fVitesse*iParDeltaTimer;
            }
            else if(this.iDirection == Constantes.iHAUT)
            {
                int fPosPacmanLigne     = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
                float fPosIntersectionLigne = (fPosPacmanLigne * Constantes.iTAILLE_TILE_HEIGHT) + (Constantes.iTAILLE_TILE_HEIGHT/2);
                float fPosCentrePacmanLigne = posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2);

                if(fPosCentrePacmanLigne > fPosIntersectionLigne)
                    this.posLigne -= this.fVitesse*iParDeltaTimer;
            }
            else if(this.iDirection == Constantes.iBAS)
            {
                int fPosPacmanLigne     = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
                float fPosIntersectionLigne = (fPosPacmanLigne * Constantes.iTAILLE_TILE_HEIGHT) + (Constantes.iTAILLE_TILE_HEIGHT/2);
                float fPosCentrePacmanLigne = posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2);

                if(fPosCentrePacmanLigne < fPosIntersectionLigne)
                    this.posLigne += this.fVitesse*iParDeltaTimer;
            }
        } 
    }
    
    public void render(Graphics grphcsPar)
    {
        grphcsPar.setAntiAlias(true);
        grphcsPar.drawImage(this.imgRender, this.posColonne, this.posLigne);
    }
}
