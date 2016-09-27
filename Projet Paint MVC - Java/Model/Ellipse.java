package Model;

import java.awt.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/** 
 *  L'élément 'Ellipse' définit une ellipse, alignée sur les axes du système de coordonnées utilisateur et basée sur un point central et deux rayons.
 */
public class Ellipse extends MotifRectangulaire 
{
	
 
//========================== Constructeurs =============================//
 
	
	/**
	 * Crée une nouvelle instance de <i>Ellipse</i> par défaut.
	 */
	public Ellipse()
	{
	}
	
	
	/**
	 * Crée une nouvelle instance de <i>Ellipse</i> qui sera un Monclone de <i>motif</i>.
	 */
	public Ellipse(MonMotif motif)
	{	
		super(motif);
	}
	
 
//==================== Écriture méthodes abstraites ====================//
 
	
	@Override
	public void dessinerMotif(Graphics2D g) 
	{
		g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, this.lOpaciter));	
		g.setColor(this.couleur);
		g.fillOval(this.x, this.y, this.maLargeur, this.Mahauteur);

		g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));	
	}


	@Override
	public MonMotif newInstance() 
	{
		Ellipse monEllipse = new Ellipse();
		monEllipse.setCouleur(couleur);
		
		return monEllipse;
	}
	
	
	@Override
	public MonMotif Monclone()
	{
		return new Ellipse(this);
	}
}