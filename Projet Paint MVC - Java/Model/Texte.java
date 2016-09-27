package Model;


import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/** 
 *  L'élément 'text' définit un élément graphique qui consiste à écrire du txt dans un dessin.
 */
 
public class Texte extends MotifRectangulaire
{
//============================ Variables ===============================//
	
	private String maPolice = "Verdana"; // maPolice du txt
	private int tailleDePolice = 14; // taille du txt
	private String Letxt = ""; // valeur du txt
	private int effect = Font.PLAIN; // effect du txt
	private int xSelec = 0; // abscisse du point en haut a gauche du txt
	private int ySelec = 0; // ordonné du point en haut a gauche du txt
	
	private JTextArea MazoneTexte = new JTextArea();
	
//========================== Constructeurs =============================//
 
	/**
	 * Crée une nouvelle instance de <i>Texte</i> par default.
	 */
	public Texte()
	{
	}
	
	
	/**
	 * Crée une nouvelle instance de <i>Texte</i> qui sera un Monclone de <i>motif</i>.
	 */
	public Texte(MonMotif motif)
	{	
		super(motif);
		Texte m = (Texte) motif;
		
		this.Letxt = m.Letxt;
		this.maLargeur = m.maLargeur;
		this.Mahauteur = m.Mahauteur;
		
		this.maPolice = m.maPolice;
		this.tailleDePolice = m.tailleDePolice;
		this.effect = m.effect;
	}
			
 
//==================== Écriture méthodes abstraites ====================//
 
	
	@Override
	public MonMotif newInstance() 
	{
		Texte Letxt = new Texte();
		Letxt.setCouleur(this.couleur);

		
		return Letxt;
	}
	
	
	@Override
	public boolean equals(Object o)
	{
		if(this.getClass() == o.getClass())
		{
			Texte Letxt = (Texte) o;
			boolean egale = true;
			
			egale &= this.x == Letxt.x;
			egale &= this.y == Letxt.y;
			egale &= this.lOpaciter == Letxt.lOpaciter;
			egale &= this.couleur == Letxt.couleur;
			egale &= this.maPolice == Letxt.maPolice;
			egale &= this.tailleDePolice == Letxt.tailleDePolice;
			egale &= this.effect == Letxt.effect;
			egale &= this.Letxt == Letxt.Letxt;
			
			return egale; 
		}
		else
			return false;
	}
	

	@Override
	public void dessinerMotif(Graphics2D g) 
	{
		g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, this.lOpaciter));
		
		
		g.setFont(new Font(maPolice, effect, tailleDePolice));
		FontMetrics fontMetrics = g.getFontMetrics();
			if (fontMetrics != null)
			{
				if(Letxt != null)
				{
					g.setColor(couleur);
					g.drawString(Letxt, x, y);	
					
					this.recalculerLesDimension(g);
					g.setColor(Color.black);
				}
			}
			
		g.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));	
	}
	
	public void recalculerLesDimension(Graphics2D g)
	{
		g.setFont(new Font(maPolice, effect, tailleDePolice));
		FontMetrics fontMetrics = g.getFontMetrics();
		Rectangle2D l = fontMetrics.getStringBounds(Letxt, g);
		maLargeur = (int) l.getWidth();
		Mahauteur = fontMetrics.getAscent()+fontMetrics.getDescent();
		xSelec = this.x;
		ySelec = y - fontMetrics.getAscent();
	}
		
	@Override
	public void dessPoign(Graphics2D g)
	{
		int y = this.y - g.getFontMetrics().getAscent();
		
		float[] dash3 = { 4f, 0f, 2f };
		g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash3, 2f));
		g.drawRect(this.x, y, this.maLargeur, this.Mahauteur);
		
		g.setColor(this.couleur);
	}
	
	@Override
	public void redim(int x1, int x2, int y1, int y2)
    {
    	int ecartX = x1 - x2;
    	int ecartY = y1 - y2;
    	int x = this.x, y = this.y;
  
		switch (poigneeSelectionne)
		{
			case PAS_POIGNEE : 
				this.x += ecartX; 
				this.y += ecartY; 
				break;
			case LEFT_TOP_POIGNEE : 
				this.y += ecartY;
				this.x += ecartX;
				this.tailleDePolice += -ecartY;
				break;
			case RIGHT_TOP_POIGNEE : 
				this.y += ecartY;
				this.tailleDePolice += -ecartY; 
				break;
			case LEFT_BOT_POIGNEE : 
				this.x += ecartX;
				this.tailleDePolice += ecartY;
				break;
			case RIGHT_BOT_POIGNEE : 
				this.tailleDePolice += ecartY;
				break;
			default : 
				break;
		}
    }
	
	@Override
 	public int getPoign(int x, int y) 
	{
		//Pas de poignnées pour un objet de type Letxt
			return PAS_POIGNEE;
	}
 	

	@Override
	public void aFaireCorrespondre(int ox, int oy, int x, int y) 
	{
		int xt = x - ox;
		int yt = y - oy;

		if (xt < 0 && yt < 0)
		{
			this.x = x;
			this.y = y;
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
	public boolean contient(Point point)
	{
        return (point.x >= this.x-HANDLE_SIZE ) && (point.y >= this.ySelec-HANDLE_SIZE) && (point.x <= this.x + this.maLargeur+HANDLE_SIZE) && (point.y <= this.ySelec +Mahauteur+HANDLE_SIZE);
	}

	@Override
	public MonMotif Monclone()
	{
		return new Texte(this);
	}
	
 
//============================= Méthodes ===============================//
 
	@Override
	public String toString()
	{
		String Letxt = super.toString();
		Letxt += "Letxt: "+this.Letxt+"\n";
		Letxt += "maPolice: "+this.maPolice+"\n";
		Letxt += "taille maPolice: "+this.tailleDePolice+"\n";
		Letxt += "effect: "+this.effect+"\n";
		
		return Letxt;
	}
	
	public JTextArea getMaZoneTxt() 
	{
		return MazoneTexte;
	}

	public void miseAJourText(Graphics2D g) 
	{
		if (MazoneTexte != null)
		{
			this.Letxt = MazoneTexte.getText();
			this.recalculerLesDimension(g);
		}
	}
	
	public void modEnGras()
	{
		if(effect == Font.BOLD)
			effect = Font.PLAIN;
		else
			effect = Font.BOLD;
		
		MazoneTexte.setFont(new Font(maPolice, effect, tailleDePolice));
		MazoneTexte.setForeground(couleur);
	}

	public void modEnItalique()
	{
		if(effect == Font.ITALIC)
			effect=Font.PLAIN;
		else
			effect = Font.ITALIC;
		MazoneTexte.setFont(new Font(maPolice, effect, tailleDePolice));
		MazoneTexte.setForeground(couleur);
	}
	
	public void setPolice(Object selectedItem) 
	{
		maPolice = (String) selectedItem;
		MazoneTexte.setFont(new Font(maPolice, effect, tailleDePolice));
		MazoneTexte.setForeground(couleur);
	}

 
//========================= Getters et Setters =========================//
 	
	
	public String getPolice()
	{
		return maPolice;
	}

	public void setPolice(String maPolice)
	{
		this.maPolice = maPolice;
	}


	public int getTailleDePolice()
	{
		return tailleDePolice;
	}

	public void setTailleDeLaPolice(int tailleDePolice)
	{
		this.tailleDePolice = tailleDePolice;
		MazoneTexte.setFont(new Font(maPolice, effect, tailleDePolice));
	}

	public String getTexte()
	{
		return Letxt;
	}


	public void setTexte(String Letxt)
	{
		this.Letxt = Letxt;
	}
}