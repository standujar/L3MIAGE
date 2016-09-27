package states;

import constantes.Constantes;
import java.awt.Font;
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

public class MenuPause extends BasicGameState{
    
    private ArrayList<Item> alListeItem;
    private int iItemFocused;
    private UnicodeFont uFont=getNewFont("Arial",40);
    
    @Override
    // @return : ID de l'Etat 
    public int getID() 
    {
        return Constantes.iID_ETAT_MENU_PAUSE;
    }

    @Override
    public void init(GameContainer gcPar, StateBasedGame sbgPar) throws SlickException 
    {
        this.alListeItem = new ArrayList();
        this.alListeItem.add(new BasicMenuItem(0, "Reprendre la partie", Color.blue, Color.orange, Color.white, Color.orange, 340, 200, 200, 50, true));
        this.alListeItem.add(new BasicMenuItem(Constantes.iID_NOUVELLE_PARTIE, Constantes.sLABEL_NOUVELLE_PARTIE, Color.blue, Color.orange, Color.white, Color.orange, 340, 200+50+5, 200, 50, false));
        this.alListeItem.add(new BasicMenuItem(Constantes.iID_ETAT_MENU_PRINCIPAL, Constantes.sLABEL_MENU_PRINCIPAL, Color.blue, Color.orange, Color.white, Color.orange, 340, 200+2*50+10, 200, 50, false));
        this.alListeItem.add(new BasicMenuItem(Constantes.iID_QUITTER, Constantes.sLABEL_QUTTER, Color.blue, Color.orange, Color.white, Color.orange, 340, 200+3*50+15, 200, 50, false));
        this.iItemFocused = 0;
        
        uFont.loadGlyphs();
    }
    
    @Override
    public void enter(GameContainer gcPar, StateBasedGame sbgPar) throws SlickException 
    {
        this.init(gcPar,sbgPar);
    }
   
    @Override
    public void render(GameContainer gcPar, StateBasedGame sbgPar, Graphics grphcsPar) throws SlickException 
    {
        for(Item elt : this.alListeItem)
        {
            elt.render(grphcsPar);
        }
        
        grphcsPar.setFont(uFont);
        grphcsPar.setColor(Color.orange);
        grphcsPar.drawString("PAUSE" , 375, 100);
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
        
        if(gcPar.getInput().isKeyPressed(Input.KEY_ENTER))
        {
            if(iItemFocused == 0)
                sbgPar.enterState(Constantes.iID_ETAT_JEU);
            else if(iItemFocused == 1)
            {
                Constantes.iPause = Constantes.iID_ETAT_MENU_PAUSE;
                sbgPar.enterState(Constantes.iID_ETAT_JEU);
            }   
            else if(iItemFocused == 2)
                sbgPar.enterState(Constantes.iID_ETAT_MENU_PRINCIPAL);
            else if(iItemFocused == 3)
                exit(1);
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
