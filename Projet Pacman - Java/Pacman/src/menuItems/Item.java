package menuItems;

import org.newdawn.slick.Graphics;

public interface Item 
{
    
    /**
     * Dessine/Affiche l'item à l'écran 
     * @param grphcsPar contexte graphique avec lequel on dessine
     */
    public void render(Graphics grphcsPar);
    
    /**
     * l'item est t-il focus ? (est t-il sélectionné)
     * @return true si l'item a le focus, false si l'item n'a pas le focus
     */
    public boolean hasFocus();
    
    /**
     * appeler cette fonction pour Selectionner/Déselectionner l'item
     * @param bHasFocus true si focus, false sinon
     */
    public void setFocus(boolean bParFocus);
    
}
