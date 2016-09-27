package states;

import constantes.Constantes;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import menuItems.BasicMenuItem;
import menuItems.Item;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class AjoutPseudo extends BasicGameState{
    
    private ArrayList<Item> alListeItem;
    private int iItemFocused;
    private Image imgGameOver;
    private TextField textPseudo;
    private UnicodeFont uFont=getNewFont("Arial",16);
    
    @Override
    // @return : ID de l'Etat 
    public int getID() 
    {
        return Constantes.iID_ETAT_AJOUT_PSEUDO;
    }

    @Override
    public void init(GameContainer gcPar, StateBasedGame sbgPar) throws SlickException 
    {
        this.imgGameOver = new Image(Constantes.sCHEMIN_GAME_OVER);
        this.alListeItem = new ArrayList();
        this.alListeItem.add(new BasicMenuItem(0, "Continue", Color.blue, Color.orange, Color.white, Color.orange, 340, 200+2*50+20, 200, 50, true));
        this.iItemFocused = 0;
        
        
        uFont.loadGlyphs();
        textPseudo = new TextField(gcPar, uFont, 540, 275, 80, 30);
        textPseudo.setMaxLength(3);
        textPseudo.setBorderColor(Color.orange);
        textPseudo.setFocus(true);
        
    }
    
     public void enter(GameContainer gcPar, StateBasedGame sbgPar) throws SlickException 
    {
        this.init(gcPar,sbgPar);
        
    }
     
    public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        UnicodeFont returnFont = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        returnFont.addAsciiGlyphs();
        returnFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (returnFont);
    }
    
    @Override
    public void render(GameContainer gcPar, StateBasedGame sbgPar, Graphics grphcsPar) throws SlickException 
    {
        grphcsPar.setFont(uFont);
        grphcsPar.setColor(Color.white);
        grphcsPar.drawImage(this.imgGameOver, 115, 0);
        grphcsPar.drawString("Votre Score de " + String.valueOf(Constantes.iScore) + " est l'un de meilleur score!", 240, 250);
        grphcsPar.drawString("Veuillez entrez votre pseudo : " , 270, 280);
        
        textPseudo.render(gcPar, grphcsPar);
        
        for(Item elt : this.alListeItem)
        {
            elt.render(grphcsPar);
        }
    }

    @Override
    public void update(GameContainer gcPar, StateBasedGame sbgPar, int iParDeltaTimer) throws SlickException 
    {
        //Mettre le text en majuscule
        textPseudo.setText(textPseudo.getText().toUpperCase());
                
        if(gcPar.getInput().isKeyPressed(Input.KEY_DOWN))
        {
            this.selectionItemSuivant();
        }
        
        if(gcPar.getInput().isKeyPressed(Input.KEY_UP))
        {
            this.selectionItemPrecedent();
        }
        
        if(gcPar.getInput().isKeyPressed(Input.KEY_ENTER))
        {
            if( iItemFocused == 0 && (textPseudo.getText().length() != 0) )
            {
                //Classer les nouveaux scores
                for(int i=0;i<5;i++)
                {
                    if( Constantes.iScore > Constantes.iTabScore[i] )
                    {
                        if( i < 4 )
                        {
                            for(int j=i; j<4; j++)
                            {
                                Constantes.iTabScore[j] = Constantes.iTabScore[j+1];
                                Constantes.iTabNomScore[j] = Constantes.iTabNomScore[j+1];
                            }
                        }
                       Constantes.iTabScore[i] = Constantes.iScore;
                       Constantes.iTabNomScore[i] = textPseudo.getText();
                       break; //Sort de boucle apres maj
                    }
                }
                
                
                 String fileName = "highScore.txt";

                try{
                    FileWriter fileWriter = new FileWriter(fileName);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    for(int i=0;i<5;i++)
                    {
                        bufferedWriter.write(Constantes.iTabNomScore[i] + "-" + Constantes.iTabScore[i]);
                        bufferedWriter.newLine();
                    }
                    
                    bufferedWriter.close();
                 }catch(IOException ex) {
                     System.out.println("Error writing to file '"+ fileName + "'");
                 }
    
                //Apres sauvegarde sur le fichier highScore.txt, passe a etat tableau score
                sbgPar.enterState(Constantes.iID_ETAT_TABLEAU_SCORE);
            }
        }
        
        if(gcPar.getInput().isKeyPressed(Input.KEY_ESCAPE))
        {
            exit(1);
        }
    }
    
    public void selectionItemSuivant()
    {
        // index de l'item de la liste courant lors du parcour de la liste d'item
        int i = 0;
        for(Item elt : this.alListeItem)
        {
            // si l'item Ã  le focus, il perd le focus et le donne Ã  l'item suivant s'il existe
            if(elt.hasFocus())
            {
                // on vÃ©rifie si l'item n'est pas le dernier de la liste
                if(this.alListeItem.size() > i+1)
                {
                    // changement du focus 
                    elt.setFocus(false);
                    this.alListeItem.get(i+1).setFocus(true);
                    this.iItemFocused = i+1;
                }
                // pas besoin de continuer Ã  parcourir la liste des items
                break;
            }
            i++;
        }
    }
    
    public void selectionItemPrecedent()
    {
        // index de l'item de la liste courant lors du parcour de la liste d'item
        int i = 0;
        for(Item elt : this.alListeItem)
        {
            // si l'item Ã  le focus, il perd le focus et le donne Ã  l'item suivant s'il existe
            if(elt.hasFocus())
            {
                // on vÃ©rifie si l'item n'est pas le premier de la liste
                if(i > 0)
                {
                    // changement du focus 
                    elt.setFocus(false);
                    this.alListeItem.get(i-1).setFocus(true);
                    this.iItemFocused = i-1;
                }
                // pas besoin de continuer Ã  parcourir la liste des items
                break;
            }
            i++;
        }
    }
    
}
