package Model;

import java.awt.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/** 
 *  L'élément 'line' définit un segment de droite qui commence à un point et finit à un autre.
 */
public class Ligne extends MonMotif 
{
 
//============================ Variables ===============================//
 
	
	private int x2; // coordonnées de l'axe des abscisses du deuxième point
	private int y2; // coordonnées de l'axe des ordonnées du deuxième point

	
 
//========================== Constructeurs =============================//
 
	
	/**
	 * Crée une nouvelle instance de <i>Ligne</i> par défaut.
	 */
	public Ligne()
	{
	}
	
	
	/**
	 * Crée une nouvelle instance de <i>Ligne</i> qui sera un Monclone de <i>motif</i>.
	 * 
	 * @param motif MonMotif
	 */
	public Ligne(MonMotif motif)
	{	
		super(motif);
		Ligne m = (Ligne) motif;
		
		this.x2 = m.x2;
		this.y2 = m.y2;
	}
	
	
 
//==================== Écriture méthodes abstraites ====================//
 
	
	@Override
	public void dessinerMotif(Graphics2D g) 
	{
		g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, this.lOpaciter));	
		g.setColor(couleur);
		
		g.drawLine(this.x, this.y, this.x2, this.y2);
		g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));	
	}

	@Override
	public void dessPoign(Graphics2D g)
	{
	
		
		if(this.x <= this.x2 && this.y <= this.y2)
		{
			g.setStroke(new BasicStroke(1));
			g.setColor(Color.white);
			g.fillRect(this.x, this.y, HANDLE_SIZE, HANDLE_SIZE); // left top
			g.fillRect(this.x2 - HANDLE_SIZE, this.y2 - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE); // right top
			g.setColor(Color.gray);
			g.drawRect(this.x, this.y, HANDLE_SIZE, HANDLE_SIZE); // left top
			g.drawRect(this.x2 - HANDLE_SIZE, this.y2 - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE); // right top
		}
		else if(this.x >= this.x2 && this.y >= this.y2)
		{
			g.setStroke(new BasicStroke(1));
			g.setColor(Color.white);
			g.fillRect(this.x - HANDLE_SIZE, this.y - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE); // left top
			g.fillRect(this.x2, this.y2, HANDLE_SIZE, HANDLE_SIZE); // right top
			g.setColor(Color.gray);
			g.drawRect(this.x - HANDLE_SIZE, this.y - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE); // left top
			g.drawRect(this.x2, this.y2, HANDLE_SIZE, HANDLE_SIZE); // right top
		}
		else if(this.x >= this.x2 && this.y <= this.y2)
		{
			g.setStroke(new BasicStroke(1));
			g.setColor(Color.white);
			g.fillRect(this.x - HANDLE_SIZE, this.y, HANDLE_SIZE, HANDLE_SIZE); // left top
			g.fillRect(this.x2, this.y2 - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE); // right top
			g.setColor(Color.gray);
			g.drawRect(this.x - HANDLE_SIZE, this.y, HANDLE_SIZE, HANDLE_SIZE); // left top
			g.drawRect(this.x2, this.y2 - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE); // right top
		}
		else if(this.x <= this.x2 && this.y >= this.y2)
		{
			g.setStroke(new BasicStroke(1));
			g.setColor(Color.white);
			g.fillRect(this.x, this.y - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE); // left top
			g.fillRect(this.x2 - HANDLE_SIZE, this.y2, HANDLE_SIZE, HANDLE_SIZE); // right top
			g.setColor(Color.gray);
			g.drawRect(this.x, this.y - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE); // left top
			g.drawRect(this.x2 - HANDLE_SIZE, this.y2, HANDLE_SIZE, HANDLE_SIZE); // right top
		}
	}
	
	
	@Override
	public void deplacer(int x, int y)
    {    	
    	this.x += x;
    	this.y += y;
    	this.x2 += x;
    	this.y2 += y;
    }
	
	
	@Override
	public void redim(int x1, int x2, int y1, int y2)
    {
		switch(this.poigneeSelectionne)
		{
			case MonMotif.PAS_POIGNEE : 
				this.x += x1; 
				this.y += y1; 
				break;
			case MonMotif.LEFT_TOP_POIGNEE : 
				this.x = x1; 
				this.y = y1; 
				break;
			case MonMotif.RIGHT_TOP_POIGNEE :
				this.x2 = x1; 
				this.y2 = y1; 
				break;
			default : 
				break;	
		}
    }
	
	
	@Override
	public boolean contient(Point point)
	{		
		if(this.x <= this.x2 && this.y <= this.y2)
			return ((point.x >= this.x) && (point.y >= this.y) && (point.x <= this.x2) && (point.y <= this.y2));
		else if(this.x >= this.x2 && this.y >= this.y2)
			return ((point.x <= this.x) && (point.y<= this.y) && (point.x >=this.x2) && (point.y >=this.y2));
		else if(this.x <= this.x2 && this.y >= this.y2)
			return ((point.x >= this.x) && (point.y<= this.y) && (point.x <=this.x2) && (point.y >=this.y2));
		else if(this.x >= this.x2 && this.y <= this.y2)
			return ((point.x <= this.x) && (point.y>= this.y) && (point.x >=this.x2) && (point.y <=this.y2));
		else
			return false;
	} 
	 
	
	@Override
	public void aFaireCorrespondre(int x1, int y1, int x2, int y2) 
	{
		this.x = x1;
		this.y = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	 
	
	@Override
	public void touverHandles(Point point)
	{
		this.poigneeSelectionne = getPoign(point.x, point.y);
	}
	
	
	
	@Override
 	public int getPoign(int x, int y) 
	{
		if(this.x <= this.x2 && this.y <= this.y2)
		{
			if ((x >= this.x) && (y >= this.y) && (x <= this.x + HANDLE_SIZE) && (y <= this.y + HANDLE_SIZE)) 
				return LEFT_TOP_POIGNEE;
			else if ((x >= this.x2 - HANDLE_SIZE) && (y >= this.y2 - HANDLE_SIZE) && (x <= this.x2) && (y <= this.y2)) 
				return RIGHT_TOP_POIGNEE;
		}
		else if(this.x >= this.x2 && this.y >= this.y2)
		{
			if ((x <= this.x) && (y <= this.y) && (x >= this.x - HANDLE_SIZE) && (y >= this.y - HANDLE_SIZE)) 
				return LEFT_TOP_POIGNEE;
			else if ((x >= this.x2) && (y >= this.y2) && (x <= this.x2 + HANDLE_SIZE) && (y <= this.y2 + HANDLE_SIZE)) 
				return RIGHT_TOP_POIGNEE;
		}
		else if(this.x >= this.x2 && this.y <= this.y2)
		{
			if ((x <= this.x) && (y >= this.y) && (x >= this.x - HANDLE_SIZE) && (y <= this.y + HANDLE_SIZE)) 
				return LEFT_TOP_POIGNEE;
			else if ((x >= this.x2) && (y >= this.y2 - HANDLE_SIZE) && (x <= this.x2 + HANDLE_SIZE) && (y <= this.y2)) 
				return RIGHT_TOP_POIGNEE;
		}
		else if(this.x <= this.x2 && this.y >= this.y2)
		{
			if ((x >= this.x) && (y <= this.y) && (x <= this.x + HANDLE_SIZE) && (y >= this.y - HANDLE_SIZE)) 
				return LEFT_TOP_POIGNEE;
			else if ((x >= this.x2 - HANDLE_SIZE) && (y >= this.y2) && (x <= this.x2) && (y <= this.y2 + HANDLE_SIZE)) 
				return RIGHT_TOP_POIGNEE;
		}
		
		return PAS_POIGNEE;
	}

	
	@Override
	public MonMotif newInstance() 
	{
		Ligne maLigne = new Ligne();
		maLigne.setCouleur(couleur);
		
		return maLigne;
	}

	
	@Override
	public MonMotif Monclone()
	{
		return new Ligne(this);
	}
	
 
//============================= Méthodes ===============================//
  	
	
	@Override
	public boolean equals(Object o)
 	{
		if(this.getClass() == o.getClass())
		{
			Ligne maLigne = (Ligne) o;
			boolean egale = true;
			
			egale &= this.x2 == maLigne.x2;
			egale &= this.y2 == maLigne.y2;
			
	 		return super.equals(maLigne) && egale;
		}
		else
			return false;
 	}
	
	
	@Override
	public String toString()
	{
		return super.toString()+"\nx2: "+this.x2+"\ny2: "+this.y2;
	}
	
	
 
//========================= Getters et Setters =========================//
 	
	
 	/**
	 * Retourne les coordonnées de l'abscisses du premier point de la Ligne.
	 */
	public int getX1()
	{
		return x;
	}
	

	/**
	 * Cette fonction sert à modifier les coordonnées de l'abscisse du premier point de la Ligne.
	 */
	public void setX1(int x1)
	{
		this.x = x1;
	}
	

	/**
	 * Retourne l'ordonnée du premier point de la Ligne.
	 */
	public int getY1()
	{
		return y;
	}
	

	/**
	 * Cette fonction sert à modifier les coordonnées de l'ordonnée du premier point de la Ligne.
	 */
	public void setY1(int y1)
	{
		this.y = y1;
	}
	

	/**
	 * Retourne les coordonnées de l'abscisse du deuxième point de la Ligne.
	 */
	public int getX2()
	{
		return x2;
	}
	

	/**
	 * Cette fonction sert à modifier les coordonnées de l'abscisse du deuxième point de la Ligne.
	 */
	public void setX2(int x2)
	{
		this.x2 = x2;
	}
	

	/**
	 * Retourne les coordonnées des ordonnées du deuxième point de la Ligne.
	 */
	public int getY2()
	{
		return y2;
	}
	

	/**
	 * Cette fonction sert à modifier les coordonnées de l'ordonnée du deuxième point de la Ligne.
	 */
	public void setY2(int y2)
	{
		this.y2 = y2;
	}
	
}
