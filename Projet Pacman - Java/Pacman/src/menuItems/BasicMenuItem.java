package menuItems;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class BasicMenuItem implements Item
{
    
    private final String sLabel;
    
    private final Color cCouleurContourParDefaut;
    private final Color cCouleurContourWhenfocus;
    private final Color cCouleurLabelParDefaut;
    private final Color cCouleurLabelWhenfocus;
    
    private final int iID;
    
    private float fHeight;
    private float fWidth;
    private float fX;
    private float fY;
    
    private boolean bHasFocus;
    
    
    /**
     * Créer un BasicMenuItem avec l'id, le tire, la couleur et les coordonnées données
     * 
     * @param iParID id de l'item
     * @param sParLabel label de l'item
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
    public BasicMenuItem(int iParID, String sParLabel, Color cParCouleurContourParDefaut, Color cParCouleurContourWhenFocus, 
                            Color cParCouleurLabelParDefaut, Color cParCouleurLabelWhenFocus, float fParX, float fParY, float fParWidth, float fParHeight,
                            boolean bParHasFocus)
    {
        this.iID = iParID;
        
        this.cCouleurContourParDefaut = cParCouleurContourParDefaut;
        this.cCouleurContourWhenfocus = cParCouleurContourWhenFocus;
        this.cCouleurLabelParDefaut = cParCouleurLabelParDefaut;
        this.cCouleurLabelWhenfocus = cParCouleurLabelWhenFocus;
        
        this.sLabel = sParLabel;
        
        this.fX = fParX;
        this.fY = fParY;
        this.fWidth = fParWidth;
        this.fHeight = fParHeight;
        
        this.bHasFocus = bParHasFocus;
    }
    
    
    /**
     * renvoie l'ID de l'item
     * @return ID de l'item
     */
    public int getID()
    {
        return this.iID;
    }
    
    public void SetX(float fParX)
    {
        this.fX = fParX;
    }
    
    public float getX()
    {
        return this.fX;
    }
    
    public void SetY(float fParY)
    {
        this.fY = fParY;
    }
    
    public float getY()
    {
        return this.fY;
    }
    
    /**
     * @param fParHeight : hauteur de l'item
     * Change la hauteur de l'item
     */
    public void SetHeight(float fParHeight)
    {
        this.fHeight = fParHeight;
    }

    /**
     * Permet de connaître la hauteur de l'item
     * @return hauteur de l'item
     */
    public float getHeight()
    {
        return this.fY;
    }
    
    
    /**
     * Change la largeur de l'item
     * @param fParWidth largeur de l'item
     */
    public void SetWidth(float fParWidth)
    {
        this.fWidth = fParWidth;
    }
    
    
    /**
     * sert à connaître la largeur de l'item
     * @return largeur de l'item
     */
    public float getWidth()
    {
        return this.fY;
    }
    
    
    /**
     * appeler cette fonction pour Selectionner/Déselectionner l'item
     * @param bParFocus true si focus, false sinon
     */
    public void setFocus(boolean bParFocus)
    {
        this.bHasFocus = bParFocus;
    }
    
    /**
     * l'item est t-il focus ? (est t-il sélectionné)
     * 
     * @return true si l'item a le focus, false si l'item n'a pas le focus
     */
    public boolean hasFocus()
    {
        return this.bHasFocus;
    }
    
    
    /**
     * Dessine/Affiche l'item à l'écran 
     * 
     * @param grphcsPar contexte graphique avec lequel on dessine
     */
    @Override
    public void render(Graphics grphcsPar)
    {
        // Calcul des coordonnées du text à afficher pour le centrer
        // coordonnée en x
            float fCoordCenteredLabelX = this.fX + this.fWidth/2 - grphcsPar.getFont().getWidth(sLabel)/2;
        // coordonnée en y
            float fCoordCenteredLabelY = (float)(this.fY+(0.5*this.fHeight)-10);
                
        // si l'item a le focus
        if(this.bHasFocus == false)
        {
            grphcsPar.setColor(this.cCouleurContourParDefaut);
            grphcsPar.setLineWidth(3);
            grphcsPar.setAntiAlias(true);
            grphcsPar.drawRoundRect(this.fX, this.fY, this.fWidth, this.fHeight, 5);

            grphcsPar.setColor(cCouleurLabelParDefaut);
  
            grphcsPar.drawString(this.sLabel, fCoordCenteredLabelX, fCoordCenteredLabelY);
        }
        
        // si l'item n'a pas le focus
        if(this.bHasFocus == true)
        {
            grphcsPar.setColor(this.cCouleurContourWhenfocus);
            grphcsPar.setLineWidth(3);
            grphcsPar.setAntiAlias(true);
            grphcsPar.drawRoundRect(this.fX, this.fY, this.fWidth, this.fHeight, 5);

            grphcsPar.setColor(cCouleurLabelWhenfocus);
            grphcsPar.drawString(this.sLabel, fCoordCenteredLabelX, fCoordCenteredLabelY);
        }
    }
    
}

