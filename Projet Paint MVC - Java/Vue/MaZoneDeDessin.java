package Vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Controleur.MonControleur;
import Model.MonMotif;

/**
 * Cette fonction sert à créer et gérer la zone de dessin, c'est là où l'ulisateur dessinera les motifs d'un dessin.
 */
public class MaZoneDeDessin extends JPanel implements MouseListener, MouseMotionListener
{	
//============================ Variables ===============================//
	
	private MonControleur controleur;
	
	private Point poDebutFigure = new Point();
	private Point posPrecedente = new Point();
	
	
//========================== Constructeurs =============================//
	
	/**
	 * Crée une nouvelle instance de <i>MaZoneDeDessin</i>.
	 */
	public MaZoneDeDessin(MonControleur controleur, int maLargeur, int Mahauteur)
    {
    	this.controleur = controleur;
    	this.setPreferredSize(new Dimension(maLargeur, Mahauteur));
		this.setBackground(Color.white);
    	this.revalidate();
    	
    	this.addMouseListener(this);
    	this.addMouseMotionListener(this);
    }
	
//============================= Méthodes ===============================//

	/**
	 * Cette fonction sert à changer la maDimension de la zone de dessin.
	 */
    public void ModiferDimensionPaneau(Dimension maDimension)
    {
    	this.setPreferredSize(maDimension);
    	this.revalidate();
    }
    
    
    /**
     * Cette fonction sert à dessiner tous les motifs du dessin et le motif courant dans la zone de dessin.
     */
    public void paintAvecMotifCourant(Graphics g)
    {
    	this.paint(g);    	
    	this.controleur.dessiner(g, false);
    }
    
    
    @Override
    public void paint(Graphics g)
    {
    	super.paint(g);
    	this.controleur.dessiner(g, true);
    }

   
//====================== Méthodes Implémentées= ========================//
    
	@Override
	public void mouseClicked(MouseEvent e)
	{			
		Point positionSouris = new Point(e.getX(), e.getY());
		int mode = this.controleur.getMode();
		Graphics g = this.getGraphics();
		
		if(e.getClickCount()>=2)
		{
			this.controleur.maZoneDeTexte(g);
		}
		else
		{
			if(mode == MonControleur.MODEDE_GOMME)
			{
				if(this.controleur.effacerMonMotif(positionSouris))
					this.repaint();
			}
			else if(controleur.motifCourant() != null && controleur.motifCourant().getClass().getCanonicalName() == "Model.Texte" && controleur.getMode() == controleur.MODEDE_CREATION)
			{
				this.controleur.CorrespondreMotif(this.poDebutFigure, positionSouris);
				this.controleur.maZoneDeTexte(g);
				this.controleur.miseAJourDuMenu(false);
			}
		}
	}

	
	@Override
	public void mouseEntered(MouseEvent e){ }

	
	@Override
	public void mouseExited(MouseEvent e){ }

	
	@Override
	public void mousePressed(MouseEvent e)
	{  
		Point positionSouris = new Point(e.getX(), e.getY());
		int mode = this.controleur.getMode();
		Graphics g = this.getGraphics();

		if(mode == MonControleur.MODEDE_SOURIS)
		{
			this.controleur.selectionneMonMotif(positionSouris, g);
			this.repaint();
		}
		else if(mode == MonControleur.MODEDE_CREATION)
		{
			this.controleur.newInstanceMotif();
		}
		
		this.poDebutFigure.x = positionSouris.x;
		this.poDebutFigure.y = positionSouris.y;
		
		this.posPrecedente.x = this.poDebutFigure.x;
		this.posPrecedente.y = this.poDebutFigure.y;
	}

	
	@Override
	public void mouseReleased(MouseEvent e)
	{		
		MonMotif motifSelectionne = this.controleur.motifCourant();
		MonMotif motif = this.controleur.getMonMotif();
		Point positionSouris = new Point(e.getX(), e.getY());
		int mode = this.controleur.getMode();
		Graphics g = this.getGraphics();
		
		if(motifSelectionne != null)
		{
			if(mode == MonControleur.MODEDE_CREATION)
			{
				if(!this.poDebutFigure.equals(positionSouris) && motifSelectionne.getClass().getCanonicalName() != "Model.Texte" )
				{
					this.controleur.ajouteMotif();
				}
			}
			else if(mode == MonControleur.MODEDE_SOURIS && motifSelectionne.getSelectionner() && motif != null)
			{
				if(!motif.equals(motifSelectionne))
				{
					this.controleur.modifieMotif(true);
				}
			}
		}
	}

	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		MonMotif motifSelectionne = this.controleur.motifCourant();
		Point positionSouris = new Point(e.getX(), e.getY());
		int mode = this.controleur.getMode();
		Graphics g = this.getGraphics();
		
		if(mode == MonControleur.MODEDE_CREATION && motifSelectionne != null && motifSelectionne.getClass().getCanonicalName() != "Model.Texte")
		{
			this.controleur.CorrespondreMotif(this.poDebutFigure, positionSouris);
			this.controleur.miseAJourDuMenu(false);
			
			this.paintAvecMotifCourant(g);
		}
		else if(mode == MonControleur.MODEDE_SOURIS && motifSelectionne != null)
		{
			if(this.controleur.getSelHandles() == 0)
				this.controleur.motifDeplacer(positionSouris.x - this.posPrecedente.x, positionSouris.y - this.posPrecedente.y);
			else
				this.controleur.motifRedimentionner(positionSouris, this.posPrecedente);
			
			this.repaint();
			this.posPrecedente.x = positionSouris.x;
			this.posPrecedente.y = positionSouris.y;
			g.setPaintMode();
		}
		
		this.controleur.affPositionSouris(positionSouris.x, positionSouris.y);
	}

	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		this.controleur.affPositionSouris(e.getX(), e.getY());
	}
}
