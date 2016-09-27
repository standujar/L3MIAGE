package Model;

import java.awt.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Cette fonction sert à définir un motif en générale pour un dessin.
 */
public abstract class MonMotif
{	
 
//============================ Variables ===============================//
 
	
	protected int x; // coordonnée x
	protected int y; // coordonnée y
	
	protected Color couleur; // couleur du motif
	protected float lOpaciter = 1.0f; // opacité du motif
	
    protected boolean selectionne = false; // cette variable permet de sélectionner le motif
    
	protected int poigneeSelectionne = 0; // cette variable permet de savoir quelle poignée est sélectionnée
	
	protected static final int HANDLE_SIZE = 8; // définit la taille des poignées
	
	/* Définition des différentes poignées */
	public final static int PAS_POIGNEE = 0;
	public final static int LEFT_TOP_POIGNEE = 1;
	public final static int RIGHT_TOP_POIGNEE = 2;
	public final static int LEFT_BOT_POIGNEE = 3;
	public final static int RIGHT_BOT_POIGNEE = 4;
	public final static int GAUCHE_POIGNEE = 5;
	public final static int HAUT_POIGNEE = 6;
	public final static int DROITE_POIGNEE = 7;
	public final static int BAS_POIGNEE = 8;
	
	
 
//========================== Constructeurs =============================//
 
	
	/**
	 * Crée une nouvelle instance de <i>MonMotif</i> par défaut.
	 */
	public MonMotif()
	{
		this.x = -1;
		this.y = -1;
	}
	
	
	/**
	 * Crée une nouvelle instance de <i>MonMotif</i> qui sera un Monclone de <i>motif</i>.
	 * 
	 * @param motif MonMotif
	 */
	public MonMotif(MonMotif motif)
	{	
		this.x = motif.x; 
		this.y = motif.y;
				
		this.lOpaciter = motif.lOpaciter;
		
		this.couleur = motif.couleur;
		selectionne = motif.selectionne;
	}
	
	
 
//======================== Méthodes abstraites =========================//
 

	/**
	 * Cette fonction sert à dessiner un motif dans la fenêtre grâce à <i>g</i>.
	 */
 	public abstract void dessinerMotif(Graphics2D g);
 	
 	
 	/**
 	 * Cette fonction sert à dessiner les poignées d'un motif dans la fenêtre grâce à <i>g</i>.
 	 */
    public abstract void dessPoign(Graphics2D g);
    
    
    /**
     * Cette fonction nous permet appliquer les modifications nécessaires lors du déplacement d'un motif par l'utilisateur.
     */
    public abstract void deplacer(int x, int y);
    
    
    /**
     * Cette fonction nous permet appliquer les modifications nécessaires lors du redim d'un motif par l'utilisateur.
     */
 	public abstract void redim(int x1, int x2, int y1, int y2);
    
 	
 	/**
 	 * Cette fonction sert à savoir si le <i>point</i> est dans le motif.
 	 */
	public abstract boolean contient(Point point);
	
	
	/**
	 * Cette fonction sert à faire correspondre les valeurs des Lesmotifs dessinés par l'utilisateur lors de la création de ce dernier au valeur de ce motif. 
	 */
 	public abstract void aFaireCorrespondre(int x1, int y1, int x2, int y2);
 	
 	
 	/**
 	 * Cette fonction sert à savoir dans quel poignée l'utilisateur a cliqué lors du redimensionnement du motif.
 	 */
 	public abstract void touverHandles(Point point);
 	

	
	/**
	 * Cette fonction sert à retourner la poignée que l'utilisateur s'est servi pour redim le motif.
	 */
 	public abstract int getPoign(int x, int y);
 	
	
	/**
	 * Cette fonction sert à cloner le motif.
	 */
	public abstract MonMotif Monclone();
	
	
	/**
	 * Cette fonction sert à créer un nouveau <i>MonMotif</i> avec la couleur du motif et de la bordure aux couleurs courantes.
	 */
	public abstract MonMotif newInstance();
	
	
 
//============================= Méthodes ===============================//
 
	
	/**
	 * Cette fonction sert à vérifier si deux Lesmotifs sont égaux.
	 */
	public boolean equals(Object o)
	{
		MonMotif motif = (MonMotif) o;
		
		if(this.getClass() == motif.getClass())
		{
			boolean egale = true;
			
			egale &= this.x == motif.x;
			egale &= this.y == motif.y;
			egale &= this.lOpaciter == motif.lOpaciter;
			egale &= this.couleur == motif.couleur;
			
			return egale;
		}
		else
			return false;
	}
	
	
	/** 
	 * Cette fonction sert à décrire un objet <i>MonMotif</i>.
	 */
	public String toString()
	{
		String Letxt = super.toString()+"\n";
		Letxt += "x: "+ this.x +"\n";
		Letxt += "y: "+ this.y +"\n";
		Letxt += "lOpaciter couleur: "+this.lOpaciter+"\n";
		Letxt += "couleur: "+ this.couleur +"\n";
		Letxt += "selectionne: "+ this.selectionne +"\n";
		
		return Letxt;
	}

	
	/**
	 * Cette fonction sert à dessiner un <i>MonMotif</i> dans la fenêtre.
	 */
	public void dessiner(Graphics2D g) 
	{
		dessinerMotif(g);
		
		if (this.getSelectionner())
			this.dessPoign(g);
	}
	
	
	/**
	 * Cette fonction viens convertir un <i>String</i> en <i>Color</i>.  
	 */
	public Color StringToCouleur(String colorRGB)
	{
		Color couleur = null;		
		colorRGB.replaceAll(" ", "");
		
		if(colorRGB.matches("rgb\\([0-9]{1,3},[0-9]{1,3},[0-9]{1,3}\\)"))
		{
			String rgb[];	
			colorRGB = colorRGB.substring(4, colorRGB.length() - 1);
			rgb = colorRGB.split(",");
			
			couleur = new Color(Integer.valueOf(rgb[0]), Integer.valueOf(rgb[1]), Integer.valueOf(rgb[2]));
		}
		else
			System.out.println("Erreur");
		
		return couleur;
	}
	
	
 
//========================= Getters et Setters =========================//
 
	
	/**
	 * Retourne les coordonnées x du motif.
	 */
	public int getX()
	{
		return x;
	}
	
	
	/**
	 * Cette fonction sert à modifier les coordonnées x du motif.
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	
	/**
	 * Retourne les coordonnées y du motif.
	 */
	public int getY()
	{
		return y;
	}
	
	
	/**
	 * Cette fonction sert à modifier les coordonnées y du motif.
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	

	/**
	 * Retourne la couleur du motif.
	 */
	public Color getCouleur()
	{
		return couleur;
	}
	
	
	/**
	 * Cette fonction sert à modifier la couleur du motif.
	 */
	public void setCouleur(Color couleur)
	{
		this.couleur = couleur;
	}
	
	
	/**
	 * Retourne l'opacité du motif.
	 */
	public float getOpaCouleur()
	{
		return lOpaciter;
	}
	
	
	/**
	 * Cette fonction sert à modifier l'opacité du motif.
	 */
	public void setOpaCouleur(float opaciteCouleur)
	{
		this.lOpaciter = opaciteCouleur;
	}
	

	/**
	 * Retourne vrai si le motif est sélectionné, sinon faux.
	 * 
	 * @return Vrai si le motif est sélectionné, sinon faux.
	 */
	public boolean getSelectionner()
	{
		return selectionne;
	}
	
	
	/**
	 * Cette fonction sert à modifier si le motif est sélectionné ou non.
	 */
	public void setSelectionner(boolean selectionne)
	{
		this.selectionne = selectionne;
	}
	
	
	/**
	 * Retourne la poignée que l'utilisateur s'est servi pour redim le motif.
	 */
	public int getSelHandle()
	{
		return poigneeSelectionne;
	}
	
	
	/**
	 * Cette fonction sert à modifier la poignée que l'utilisateur s'est servi pour redim le motif.
	 */
	public void setSelectedHandle(int selectedHandle)
	{
		this.poigneeSelectionne = selectedHandle;
	}
}	