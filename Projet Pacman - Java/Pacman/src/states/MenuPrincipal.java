/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import constantes.Constantes;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import menuItems.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class MenuPrincipal extends BasicGameState{
    
    private ArrayList<Item> alListeItem;
    private int iItemFocused;
    private UnicodeFont uFont=getNewFont("Arial",80);
    
    @Override
    /**
     * @return ID de l'Etat 
     */
    public int getID() 
    {
        return Constantes.iID_ETAT_MENU_PRINCIPAL;
    }

    @Override
    public void init(GameContainer gcPar, StateBasedGame sbgPar) throws SlickException 
    {
        
        this.alListeItem = new ArrayList();
        
        // liste des labels des choix possible pour la difficultÃ© du jeu
        ArrayList<String> alChoixDifficulte = new ArrayList<>();
        alChoixDifficulte.add(Constantes.iID_LABEL_LENT, Constantes.sLABEL_LENT);
        alChoixDifficulte.add(Constantes.iID_LABEL_NORMAL, Constantes.sLABEL_NORMAL);
        alChoixDifficulte.add(Constantes.iID_LABEL_RAPIDE, Constantes.sLABEL_RAPIDE);
        
        
        // CrÃ©ation des items du menu principal
        this.alListeItem.add(new BasicMenuItem(Constantes.iID_NOUVELLE_PARTIE, Constantes.sLABEL_NOUVELLE_PARTIE, Color.blue, Color.orange, Color.white, Color.orange, 340, 200, 200, 50, true));
        this.alListeItem.add(new BasicMenuItem(Constantes.iID_MEILLEURS_SCORES, Constantes.sLABEL_MEILLEURS_SCORE, Color.blue, Color.orange, Color.white, Color.orange, 340, 200+50+5, 200, 50, false));
        this.alListeItem.add(new MenuItemChoixMultiple(Constantes.iID_DIFFICULTE, alChoixDifficulte, Constantes.iID_LABEL_NORMAL, Color.blue, Color.orange, Color.white, Color.orange, 340, 200+2*50+10, 200, 50, false));
        this.alListeItem.add(new BasicMenuItem(Constantes.iID_QUITTER, Constantes.sLABEL_QUTTER, Color.blue, Color.orange, Color.white, Color.orange, 340, 200+3*50+15, 200, 50, false));
        
        // l'item focus au dÃ©part est le premier item, ici l'item de nouvelle partie
        this.iItemFocused = Constantes.iID_NOUVELLE_PARTIE;
        
        //Load tableau de score
        this.loadTableauScore();
        uFont.loadGlyphs();
    }
    
    @Override
    public void enter(GameContainer gcPar, StateBasedGame sbgPar) throws SlickException 
    {
        Constantes.iPause = Constantes.iID_ETAT_MENU_PAUSE;
    }
    
    @Override
    public void render(GameContainer gcPar, StateBasedGame sbgPar, Graphics grphcsPar) throws SlickException 
    {
        for(Item elt : this.alListeItem)
        {
            elt.render(grphcsPar);
        }
        
        grphcsPar.setBackground(Color.blue);
        
        grphcsPar.setFont(uFont);
        grphcsPar.setColor(Color.orange);
        grphcsPar.drawString("PACMAN" , 270, 70);
    }  
    
    @Override
    public void update(GameContainer gcPar, StateBasedGame sbgPar, int iParDeltaTimer) throws SlickException 
    {
        if(gcPar.getInput().isKeyPressed(Input.KEY_DOWN))
        {
            this.selectionItemSuivant();
        }
        
        if(gcPar.getInput().isKeyPressed(Input.KEY_UP))
        {
            this.selectionItemPrecedent();
        }
        
        if((this.alListeItem.get(this.iItemFocused) instanceof MenuItemChoixMultiple) && !(this.alListeItem.get(this.iItemFocused) instanceof MenuItemChoixBinaire))
        {
            MenuItemChoixMultiple micmTemporaire = (MenuItemChoixMultiple)this.alListeItem.get(this.iItemFocused);
            
            if(gcPar.getInput().isKeyPressed(Input.KEY_LEFT))
            {
                micmTemporaire.changeChoixFocus(micmTemporaire.getChoixFocus()-1);
            }

            if(gcPar.getInput().isKeyPressed(Input.KEY_RIGHT))
            {
                micmTemporaire.changeChoixFocus(micmTemporaire.getChoixFocus()+1);
            }
        }
        
        if(gcPar.getInput().isKeyPressed(Input.KEY_ENTER))
        {
            if(this.alListeItem.get(this.iItemFocused) instanceof MenuItemChoixBinaire)
            {
                    MenuItemChoixMultiple micmTemporaire = (MenuItemChoixMultiple)this.alListeItem.get(this.iItemFocused);
                    if(micmTemporaire.getChoixFocus() == 0)
                        micmTemporaire.changeChoixFocus(1);
                    else 
                        micmTemporaire.changeChoixFocus(0);
            }
            else
            {
                if(iItemFocused == Constantes.iID_NOUVELLE_PARTIE)
                    sbgPar.enterState(Constantes.iID_ETAT_JEU);
                if(iItemFocused == 1)
                    sbgPar.enterState(Constantes.iID_ETAT_TABLEAU_SCORE);
                if(iItemFocused == Constantes.iID_QUITTER)
                    exit(1);
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
            // si l'item a  le focus, il perd le focus et le donne a  l'item suivant s'il existe
            if(elt.hasFocus())
            {
                // on verifie si l'item n'est pas le premier de la liste
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

   public UnicodeFont getNewFont(String fontName , int fontSize)
    {
        UnicodeFont returnFont = new UnicodeFont(new Font(fontName , Font.PLAIN , fontSize));
        returnFont.addAsciiGlyphs();
        returnFont.getEffects().add(new ColorEffect(java.awt.Color.white));
        return (returnFont);
    }
    
}
