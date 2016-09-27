package Model;

import java.awt.*;

/**
 * Cette fonction sert à définir un motif de type rectangulaire, c'est à dire un motif ayant une maLargeur et une Mahauteur.
 */
public abstract class MotifRectangulaire extends MonMotif 
{	
 
//============================ Variables ===============================//
 
	
	protected int maLargeur; // maLargeur du motif
	protected int Mahauteur; // Mahauteur du motif


 
//========================== Constructeurs =============================//
 
	
	/**
	 * Crée une nouvelle instance de <i>MotifRectangulaire</i> par défaut.
	 */
	public MotifRectangulaire()
	{
	}
	
	
	/**
	 * Crée une nouvelle instance de <i>MotifRectangulaire</i> qui sera un Monclone de <i>motif</i>.
	 */
	public MotifRectangulaire(MonMotif motif)
	{	
		super(motif);
		MotifRectangulaire m = (MotifRectangulaire) motif;
		
		this.maLargeur = m.maLargeur;
		this.Mahauteur = m.Mahauteur;
	}
		
		
 
//==================== Écriture méthodes abstraites ====================//
 
	
	@Override
	public void dessPoign(Graphics2D g)
	{
		g.setStroke(new BasicStroke(1));
		
		g.setColor(Color.white);
		g.fillRect(this.x, this.y, HANDLE_SIZE, HANDLE_SIZE); // left top
		g.fillRect(this.x + this.maLargeur - HANDLE_SIZE, this.y, HANDLE_SIZE, HANDLE_SIZE); // right top
		g.fillRect(this.x, this.y + this.Mahauteur - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE); // left bottom
		g.fillRect(this.x + this.maLargeur - HANDLE_SIZE, this.y + this.Mahauteur - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE);// right bottom

		g.fillRect(this.x, this.y + (this.Mahauteur - HANDLE_SIZE) / 2, HANDLE_SIZE, HANDLE_SIZE); // gauche
		g.fillRect(this.x + (this.maLargeur - HANDLE_SIZE) / 2, this.y, HANDLE_SIZE, HANDLE_SIZE); // haut
		g.fillRect(this.x + this.maLargeur - HANDLE_SIZE, this.y + (this.Mahauteur - HANDLE_SIZE) / 2, HANDLE_SIZE, HANDLE_SIZE); // droite
		g.fillRect(this.x + (this.maLargeur - HANDLE_SIZE) / 2, this.y + this.Mahauteur - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE); // bas
		
		g.setColor(Color.gray);
		g.drawRect(this.x , this.y, HANDLE_SIZE, HANDLE_SIZE); // left top
		g.drawRect(this.x + this.maLargeur - HANDLE_SIZE, this.y, HANDLE_SIZE, HANDLE_SIZE); // right top
		g.drawRect(this.x, this.y + this.Mahauteur - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE); // left bottom
		g.drawRect(this.x + this.maLargeur - HANDLE_SIZE, this.y + this.Mahauteur - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE);// right bottom

		g.drawRect(this.x, this.y + (this.Mahauteur - HANDLE_SIZE) / 2, HANDLE_SIZE, HANDLE_SIZE); // gauche
		g.drawRect(this.x + (this.maLargeur - HANDLE_SIZE) / 2, this.y, HANDLE_SIZE, HANDLE_SIZE); // haut
		g.drawRect(this.x + this.maLargeur - HANDLE_SIZE, this.y + (this.Mahauteur - HANDLE_SIZE) / 2, HANDLE_SIZE, HANDLE_SIZE); // droite
		g.drawRect(this.x + (this.maLargeur - HANDLE_SIZE) / 2, this.y + this.Mahauteur - HANDLE_SIZE, HANDLE_SIZE, HANDLE_SIZE); // bas
		
		float[] dash3 = { 4f, 0f, 2f };
		g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash3, 2f));
		g.drawRect(this.x, this.y, this.maLargeur, this.Mahauteur);
		
		g.setColor(this.couleur);
	}
	
	
	@Override
	public void deplacer(int x, int y)
    {    	
    	this.x += x;
    	this.y += y;
    }
	
	
	@Override
	public void redim(int x1, int x2, int y1, int y2)
    {
    	int ecartX = x1 - x2;
    	int ecartY = y1 - y2;
    	int x = this.x, y = this.y;
  
		switch(this.poigneeSelectionne)
		{
			case PAS_POIGNEE : 
				this.x += ecartX; 
				this.y += ecartY; 
				break;
			case LEFT_TOP_POIGNEE : 
				this.x += ecartX; 
				this.y += ecartY;
				this.Mahauteur -= ecartY;
				this.maLargeur -= ecartX;
				break;
			case RIGHT_TOP_POIGNEE : 
				this.y += ecartY;
				this.Mahauteur -= ecartY;
				this.maLargeur += ecartX; 
				break;
			case LEFT_BOT_POIGNEE : 
				this.x += ecartX;
				this.Mahauteur += ecartY;
				this.maLargeur -= ecartX;
				break;
			case RIGHT_BOT_POIGNEE : 
				this.Mahauteur += ecartY; 
				this.maLargeur += ecartX; 
				break;
			case GAUCHE_POIGNEE : 
				this.x += ecartX; 
				this.maLargeur -= ecartX; 
				break;
			case DROITE_POIGNEE : 
				this.maLargeur += ecartX; 
				break;
			case HAUT_POIGNEE : 
				this.y += ecartY; 
				this.Mahauteur -= ecartY;
				break;
			case BAS_POIGNEE : 
				this.Mahauteur += ecartY;
				break;
			default : 
				break;
		}
    	
		if (this.Mahauteur < 0)
		{
			this.Mahauteur = 0;
			
			if(y != this.y)
				this.y = y;
		}
		
		if (this.maLargeur <0)
		{
			this.maLargeur = 0;
			
			if (x != this.x)
				this.x = x;
		}
    }
	
	
	@Override
	public boolean contient(Point point)
	{
        return (this.x <= point.x) && (this.y <= point.y) && (point.x <= this.x + this.maLargeur) && (point.y <= this.y + this.Mahauteur);
	}
	
	
	@Override
	public void aFaireCorrespondre(int ox, int oy, int x, int y) 
	{
		int xt = x - ox;
		int yt = y - oy;
		
		if (xt < 0 && yt < 0)
		{
			this.x = ox + xt;
			this.y = oy + yt;
			this.maLargeur = -xt;
			this.Mahauteur = -yt;
		}
		else if (xt < 0 && yt > 0)
		{
			this.x = ox + xt;
			this.y = oy;
			this.maLargeur = -xt;
			this.Mahauteur = yt;
		}
		else if(xt > 0 && yt < 0)
		{
			this.x = ox;
			this.y = oy + yt;
			this.maLargeur = xt;
			this.Mahauteur = -yt;
		}
		else
		{
			this.x = ox;
			this.y = oy;
			this.maLargeur = xt;
			this.Mahauteur = yt;
		}
	}
	
	
	@Override
	public void touverHandles(Point point)
	{
		this.poigneeSelectionne = getPoign(point.x, point.y);
	}
	
	
	@Override
 	public int getPoign(int x, int y) 
	{
		int s = HANDLE_SIZE;
		
		if ((x >= this.x) && (y >= this.y) && (x <= this.x + s) && (y <= this.y + s)) 
			return LEFT_TOP_POIGNEE;
		else if ((x >= this.x + this.maLargeur - s) && (y >= this.y) && (x <= this.x + this.maLargeur) && (y <= this.y + s))
			return RIGHT_TOP_POIGNEE;
		else if ((x >= this.x) && (y >= this.y + this.Mahauteur - s) && (x <= this.x + s) && (y <= this.y + this.Mahauteur)) 
			return LEFT_BOT_POIGNEE;
		else if ((x >= this.x + this.maLargeur - s) && (y >= this.y + this.Mahauteur - s) && (x <= this.x + this.maLargeur) && (y <= this.y + this.Mahauteur)) 
			return RIGHT_BOT_POIGNEE;

		else if ((x >= this.x) && (y >= this.y + (this.Mahauteur - s) / 2) && (x <= this.x + s) && (y <= this.y + s + (this.Mahauteur - s) / 2)) 
			return GAUCHE_POIGNEE;
		else if ((x >= this.x + (this.maLargeur - s) / 2) && (y >= this.y) && (x <= this.x + s + (this.maLargeur - s) / 2) && (y <= this.y + s)) 
			return HAUT_POIGNEE;
		else if ((x >= this.x + this.maLargeur - s) && (y >= this.y + (this.Mahauteur - s) / 2) && (x <= this.x + this.maLargeur) && (y <= this.y + s + (this.Mahauteur - s) / 2)) 
			return DROITE_POIGNEE;
		else if ((x >= this.x + (this.maLargeur - s) / 2) && (y >= this.y + this.Mahauteur - s) && (x <= this.x + s + (this.maLargeur - s) / 2) && (y <= this.y + this.Mahauteur)) 
			return BAS_POIGNEE;
		else 
			return PAS_POIGNEE;
	}
		
	
 
//============================= Méthodes ===============================//
  	
 	
	@Override
	public boolean equals(Object o)
 	{
		if(this.getClass() == o.getClass())
		{
			MotifRectangulaire motifRectangulaire = (MotifRectangulaire) o;
			boolean egale = true;
			
			egale &= this.maLargeur == motifRectangulaire.maLargeur;
			egale &= this.Mahauteur == motifRectangulaire.Mahauteur;
			
	 		return super.equals(motifRectangulaire) && egale;
		}
		else
			return false;
 	}
 	
 	
 	@Override
	public String toString()
	{
		String Letxt = super.toString();
		Letxt += "lenght: "+ this.maLargeur +"\n";
		Letxt += "width: "+ this.Mahauteur +"\n";
		
		return Letxt;
	}
	
	
 
//========================= Getters et Setters =========================//
 	

	/**
	 * Retourne la largeur du motif.
	 */
	public int getLargeur()
	{
		return maLargeur;
	}

	/**
	 * Cette fonction sert à modifier la largeur du motif.
	 */
	public void setLargeur(int maLargeur)
	{
		this.maLargeur = maLargeur;
	}

	/**
	 * Retourne la hauteur du motif.
	 */
	public int getHauteur()
	{
		return Mahauteur;
	}

	/**
	 * Cette fonction sert à modifier la hauteur du motif.
	 */
	public void setHauteur(int Mahauteur)
	{
		this.Mahauteur = Mahauteur;
	}
}