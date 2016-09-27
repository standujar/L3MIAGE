package menuItems;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import constantes.Constantes;
import org.newdawn.slick.SlickException;

public class MenuItemChoixMultiple implements Item
{

    
    protected final ArrayList<String> alChoix;
    
    protected final Color cCouleurContourParDefaut;
    protected final Color cCouleurContourWhenfocus;
    protected final Color cCouleurLabelParDefaut;
    protected final Color cCouleurLabelWhenfocus;
    
    private final int iID;
    
    protected float fHeight;
    protected float fWidth;
    protected float fX;
    protected float fY;
    
    private boolean bHasFocus;
    
    private Image imgFlecheDroite;
    private Image imgFlecheGauche;
    
    protected int iChoixFocus;
    
    
    
    /**
     * Créer un item de menu à choix multiple
     * 
     * @param iParID id de l'item
     * @param alParChoix liste des labels des choix possible
     * @param iParChoixFocus ID du choix focus
     * @param cParCouleurContourParDefaut couleur du contour de l'item par défaut (quand il n'est pas focus)
     * @param cParCouleurContourWhenFocus couleur du contour de l'item quand il est focus
     * @param cParCouleurLabelParDefaut couleur du label de l'item par défaut (quand il n'est pas focus)
     * @param cParCouleurLabelWhenFocus couleur du label de l'item quand il est focus
     * @param fParX coordonnée sur l'axe des abscisses de l'item 
     * @param fParY coordonnée sur l'axe des ordonnées de l'item 
     * @param fParWidth longueur de l'item
     * @param fParHeight hauteur de l'item
     * @param bParHasFocus true si l'item est focus, false sinon
     * 
     */
    public MenuItemChoixMultiple(int iParID, ArrayList<String> alParChoix, int iParChoixFocus, Color cParCouleurContourParDefaut, Color cParCouleurContourWhenFocus, 
                            Color cParCouleurLabelParDefaut, Color cParCouleurLabelWhenFocus, float fParX, float fParY, float fParWidth, float fParHeight,
                            boolean bParHasFocus)
    {
        this.iID = iParID;
        
        this.cCouleurContourParDefaut = cParCouleurContourParDefaut;
        this.cCouleurContourWhenfocus = cParCouleurContourWhenFocus;
        this.cCouleurLabelParDefaut = cParCouleurLabelParDefaut;
        this.cCouleurLabelWhenfocus = cParCouleurLabelWhenFocus;
        
        this.alChoix = alParChoix;
        this.iChoixFocus = iParChoixFocus;
        
        this.fX = fParX;
        this.fY = fParY;
        this.fWidth = fParWidth;
        this.fHeight = fParHeight;
        
        this.bHasFocus = bParHasFocus;
        
        
        try 
        {
            this.imgFlecheGauche = new Image(Constantes.sCHEMIN_FLECHE_GAUCHE_ITEM_CHOIX_MULTIPLE);
            this.imgFlecheDroite = new Image(Constantes.sCHEMIN_FLECHE_DROITE_ITEM_CHOIX_MULTIPLE);
        } catch (SlickException ex) 
        {
            System.out.println("Erreur lors du chargements de la flèche droite de l'item");
        }
        
    }
    
    /**
     * Appeler cette fonction pour changer le choix
     * 
     * @param iParChoixFocus ID du choix qui est focus (l'id ne peut pas être inférieur à l'id du premier élément de la liste des choix, ni supérieur au dernier).
     * Augmenter l'ID pour naviguer vers la droite, diminuer l'ID pour aller vers la gauche
     */
    public void changeChoixFocus(int iParChoixFocus)
    {
        // on vérifie la validité du paramètre iParChoixFocus
        if(iParChoixFocus >= 0 && iParChoixFocus < this.alChoix.size())
        {
            this.iChoixFocus = iParChoixFocus;
        }
    }
    
    /**
     * Appeler cette fonction pour changer le choix
     * 
     * @return ID du choix qui est focus
     */
    public int getChoixFocus()
    {
            return this.iChoixFocus;
    }
    
    /**
     * Récupére le label du choix focus 
     * 
     * @return le label du choix focus
     */
    public String getLabelChoixFocus()
    {
        return this.alChoix.get(this.iChoixFocus);
    }
    
    @Override
    public boolean hasFocus() 
    {
        return this.bHasFocus;
    }

    @Override
    public void setFocus(boolean bParFocus) 
    {
        this.bHasFocus = bParFocus;
    }
    
    @Override
    public void render(Graphics grphcsPar) 
    {    
        // Calcul des coordonnées du text à afficher pour le centrer
        // coordonnée en x
            float fCoordCenteredLabelX = this.fX + this.fWidth/2 - grphcsPar.getFont().getWidth(this.alChoix.get(this.iChoixFocus))/2;
        // coordonnée en y
            float fCoordCenteredLabelY = (float)(this.fY+(0.5*this.fHeight)-10);
        
       
        if(this.iChoixFocus == 0)
            grphcsPar.drawImage(this.imgFlecheDroite, fX+this.fWidth-this.imgFlecheDroite.getWidth()-5, (float)(this.fY+(0.5*this.fHeight)-23));
        else if(this.iChoixFocus == this.alChoix.size()-1)
            grphcsPar.drawImage(this.imgFlecheGauche, fX+5, (float)(this.fY+(0.5*this.fHeight)-23));
        else
        {
            grphcsPar.drawImage(this.imgFlecheDroite, fX+this.fWidth-this.imgFlecheDroite.getWidth()-5, (float)(this.fY+(0.5*this.fHeight)-23));
            grphcsPar.drawImage(this.imgFlecheGauche, fX+5, (float)(this.fY+(0.5*this.fHeight)-23));
        }
        
        // si l'item a le focus
        if(this.bHasFocus == false)
        {
            grphcsPar.setColor(this.cCouleurContourParDefaut);
            grphcsPar.setLineWidth(3);
            grphcsPar.setAntiAlias(true);
            grphcsPar.drawRoundRect(this.fX, this.fY, this.fWidth, this.fHeight, 5);

            grphcsPar.setColor(this.cCouleurLabelParDefaut);
            grphcsPar.drawString(this.alChoix.get(this.iChoixFocus), fCoordCenteredLabelX, fCoordCenteredLabelY);
        }
        
        // si l'item n'a pas le focus
        if(this.bHasFocus == true)
        {
            grphcsPar.setColor(this.cCouleurContourWhenfocus);
            grphcsPar.setLineWidth(3);
            grphcsPar.setAntiAlias(true);
            grphcsPar.drawRoundRect(this.fX, this.fY, this.fWidth, this.fHeight, 5);

            grphcsPar.setColor(this.cCouleurLabelWhenfocus);
            grphcsPar.drawString(this.alChoix.get(this.iChoixFocus), fCoordCenteredLabelX, fCoordCenteredLabelY);
        }
    }
    
}
