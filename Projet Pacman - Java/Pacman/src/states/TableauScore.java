package states;

import constantes.Constantes;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import menuItems.BasicMenuItem;
import menuItems.Item;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TableauScore extends BasicGameState{
    
    private UnicodeFont uFont=getNewFont("Arial", 40);
    private ArrayList<Item> alListeItem;
    private int iItemFocused;
    
    @Override
    // @return : ID de l'Etat 
    public int getID() 
    {
        return Constantes.iID_ETAT_TABLEAU_SCORE;
    }

    @Override
    public void init(GameContainer gcPar, StateBasedGame sbgPar) throws SlickException 
    {
        uFont.loadGlyphs();
        
        //Load tableau de scores 
        this.loadTableauScore();
        
        this.alListeItem = new ArrayList();
        this.alListeItem.add(new BasicMenuItem(0, "Back", Color.blue, Color.orange, Color.white, Color.orange, 340, 650, 200, 50, true));
        this.iItemFocused = 0;
    }

    @Override
    public void render(GameContainer gcPar, StateBasedGame sbgPar, Graphics grphcsPar) throws SlickException 
    {
        for(Item elt : this.alListeItem)
        {
            elt.render(grphcsPar);
        }
        
        //Afficher les scores
        grphcsPar.setFont(uFont);
        grphcsPar.setColor(Color.white);
        grphcsPar.drawString("LES MEILLEURS 5 SCORES" , 170, 100);
        grphcsPar.drawString("PSEUDO" , 180, 200);
        grphcsPar.drawString("SCORE" , 450, 200);
        for(int i=0;i<5;i++)
        {
            grphcsPar.drawString((i+1) + "." , 130, 270+i*60);
            grphcsPar.drawString(Constantes.iTabNomScore[i] , 210, 270+i*60);
            grphcsPar.drawString(Constantes.iTabScore[i] +"" , 450, 270+i*60);
        }
    }

    @Override
    public void update(GameContainer gcPar, StateBasedGame sbgPar, int iParDeltaTimer) throws SlickException 
    {
        if(gcPar.getInput().isKeyPressed(Input.KEY_ENTER))
        {
            sbgPar.enterState(Constantes.iID_ETAT_MENU_PRINCIPAL);
        }
        
        if(gcPar.getInput().isKeyPressed(Input.KEY_ESCAPE))
        {
            exit(1);
        }
    }
    
    public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        UnicodeFont returnFont = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        returnFont.addAsciiGlyphs();
        returnFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (returnFont);
    }
    
    public void loadTableauScore()
    {
        String fileName = "highScore.txt";
        
        String line = null;
        String[] s = new String[2];
        
        int i=0;
        
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null &&  i<5) {
                s = line.split("-");
                Constantes.iTabNomScore[i] = s[0];
                Constantes.iTabScore[i] = Integer.valueOf(s[1]);
                i++;
                //System.out.println(line);
            }    

            bufferedReader.close();            
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'"); 
        }
        
    }
    
}
