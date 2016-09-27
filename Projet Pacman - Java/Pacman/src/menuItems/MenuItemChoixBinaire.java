package menuItems;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import org.newdawn.slick.SlickException;


public class MenuItemChoixBinaire extends MenuItemChoixMultiple
{
    private Image imgPremierChoix;
    private Image imgSecondChoix;
    private final String sLabel;
    
    public MenuItemChoixBinaire(int iParID, String sParLabel, int iParChoixFocus, String sParImgPremierChoix, String sParImgSecondChoix,
                                Color cParCouleurContourParDefaut, Color cParCouleurContourWhenFocus, Color cParCouleurLabelParDefaut, Color cParCouleurLabelWhenFocus, 
                                float fParX, float fParY, float fParWidth, float fParHeight, boolean bParHasFocus) 
    {
        super(iParID, new ArrayList<>(), iParChoixFocus, cParCouleurContourParDefaut, cParCouleurContourWhenFocus, cParCouleurLabelParDefaut, cParCouleurLabelWhenFocus, fParX, fParY, fParWidth, fParHeight, bParHasFocus);
        this.sLabel = sParLabel;
        
        try {
            this.imgPremierChoix = new Image(sParImgPremierChoix);
            this.imgSecondChoix = new Image(sParImgSecondChoix);
        } catch (SlickException ex) 
        {
            System.out.println("Erreur lors du chargement image menu item choix binaire");
        }
    }

    @Override
    public void changeChoixFocus(int iParChoixFocus)
    {
        
        // on vérifie la validité du paramètre iParChoixFocus
        if(iParChoixFocus == 0 || iParChoixFocus == 1)
        {
            this.iChoixFocus = iParChoixFocus;
        }
    }
    
    @Override
    public void render(Graphics grphcsPar) 
    {
        // Calcul des coordonnées du text à afficher pour le centrer
        // coordonnée en x
            float fCoordCenteredLabelX = this.fX + this.fWidth/2 - grphcsPar.getFont().getWidth(sLabel)/2;
        // coordonnée en y
            float fCoordCenteredLabelY = (float)(this.fY+(0.5*this.fHeight)-10);
        
        if(this.iChoixFocus == 0)
            grphcsPar.drawImage(this.imgPremierChoix, this.fX+this.fWidth-this.imgPremierChoix.getWidth()-15, (float)(this.fY+(0.5*this.fHeight)-15));
        else
            grphcsPar.drawImage(this.imgSecondChoix, this.fX+this.fWidth-this.imgSecondChoix.getWidth()-15, (float)(this.fY+(0.5*this.fHeight)-15));
        
        // si l'item a le focus
        if(this.hasFocus() == false)
        {
            grphcsPar.setColor(this.cCouleurContourParDefaut);
            grphcsPar.setLineWidth(3);
            grphcsPar.setAntiAlias(true);
            grphcsPar.drawRoundRect(this.fX, this.fY, this.fWidth, this.fHeight, 5);

            grphcsPar.setColor(this.cCouleurLabelParDefaut);
            grphcsPar.drawString(this.sLabel, fCoordCenteredLabelX, fCoordCenteredLabelY);
        }
        
        // si l'item n'a pas le focus
        if(this.hasFocus() == true)
        {
            grphcsPar.setColor(this.cCouleurContourWhenfocus);
            grphcsPar.setLineWidth(3);
            grphcsPar.setAntiAlias(true);
            grphcsPar.drawRoundRect(this.fX, this.fY, this.fWidth, this.fHeight, 5);

            grphcsPar.setColor(this.cCouleurLabelWhenfocus);
            grphcsPar.drawString(this.sLabel, fCoordCenteredLabelX, fCoordCenteredLabelY);
        }
    }
    
}
