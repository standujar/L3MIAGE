package Model;

import java.awt.*;
import javax.swing.ImageIcon;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/** 
 * L'élément 'rect' définit un Lerectangle qui s'aligne selon l'axe du système de coordonnées utilisateur.
 */
public class MonRectangle extends MotifRectangulaire 
{	
 
//========================== Constructeurs =============================//
 
	
	/**
	 * Crée une nouvelle instance de <i>MonRectangle</i> par défaut.
	 */
	public MonRectangle()
	{
	}
	
	
	/**
	 * Crée une nouvelle instance de <i>MonRectangle</i> qui sera un Monclone de <i>motif</i>.
	 */
	public MonRectangle(MonMotif motif)
	{	
		super(motif);
	}
		
 
//==================== Écriture méthodes abstraites ====================//
 
	
	@Override
	public void dessinerMotif(Graphics2D g)
	{		
		g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, this.lOpaciter));		
		g.setColor(this.couleur);
		g.fillRect(this.x, this.y, this.maLargeur, this.Mahauteur);
		
		g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));	
	}
	
		
	@Override
	public MonMotif newInstance() 
	{
		MonRectangle Lerectangle = new MonRectangle();
		Lerectangle.setCouleur(this.couleur);
		
		return Lerectangle;
	}
	

	@Override
	public MonMotif Monclone()
	{
		return new MonRectangle(this);
	}
}