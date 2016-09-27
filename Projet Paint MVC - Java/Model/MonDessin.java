package Model;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.awt.print.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.*;
import org.w3c.dom.*;
import Controleur.MonControleur;
import Model.Evenement.*;


public class MonDessin
{
 
//============================ Variables ===============================//
 
	
	private MonMotif motif; // motif courant
	private MonMotif pressePapier; // contient un motif si l'utilisateur en a copié un auparavent
	
	private int maLargeur = 800; // maLargeur du dessin
	private int Mahauteur = 600; // Mahauteur du dessin
	
	private Vector<MonMotif> Lesmotifs = new Vector(); // vecteur de tous les motifs du dessin
	
	private Historique historique = new Historique(); // historique du dessin
	
	private String nom = ""; // nom du dessin
	private float monZoom = 1.0f; // monZoom du dessin
	
	private Color couleur = Color.blue; // couleur courante du dessin
	


 
//========================== Constructeurs =============================//
 
	
	/**
	 * nouvelle instance de <i>MonDessin</i> par défaut.
	 */
	public MonDessin()
	{
	}
	
	/**
	 * nouvelle instance de <i>MonDessin</i> avec les valeurs nom, maLargeur et Mahauteur.
	 */
	public MonDessin(String nom, int maLargeur, int Mahauteur)
	{
		this.nom = nom;
		this.maLargeur = maLargeur;
		this.Mahauteur = Mahauteur;
	}
	
	
	/**
	 * Nouvelle instance de <i>MonDessin</i> avec le vecteur de Lesmotifs.
	 */
	public MonDessin(Vector<MonMotif> Lesmotifs)
	{
		this.Lesmotifs = Lesmotifs;
		
		for(int i = 0; i < this.Lesmotifs.size(); i++)
			this.Lesmotifs.get(i).setSelectionner(false);
	}
	
	
 
//============================= Méthodes ===============================//
 
	
	/**
	 * Cette fonction sert à créer une nouvelle instance du motif courant en fonction de la demande de l'utilisateur.
	 */
	public boolean creationObjet(String name)
	{
		try
		{
			if(this.motif != null)
				this.motif.setSelectionner(false);
			
			Class c = Class.forName("Model."+name);
			this.motif = (MonMotif) c.newInstance();
			this.motif.setCouleur(this.couleur);
		
			
			return true;
		}
		catch(ClassNotFoundException e) {}
		catch(InstantiationException e) {}
		catch(IllegalAccessException e) {}
		
		return false;
	}
	
	
	/**
	 * Cette fonction sert à savoir si un <i>point</i> est dans un des Lesmotifs du dessin.
	 */
	public boolean find(Point point) 
	{
		MonMotif motif;
		int i = Lesmotifs.size() - 1;
		boolean find = false;
		
		while(i >= 0 && !find)
		{
			motif = Lesmotifs.get(i);
			
			if (motif.contient(point))
			{
				this.motif = motif;
				this.motif.setSelectionner(true);
				
				find = true;
			}
			
			i--;
		}
		
		if(!find)
			this.motif = null;

		return find;
	}
	
	
	/**
	 * Cette fonction sert à supprimer une motif du dessin, si le <i>point</i> est dans un des Lesmotifs du dessin.
	 */
	public MonMotif effacerMonMotif(Point point)
	{
		MonMotif motif = null;
		int i = this.Lesmotifs.size() - 1;
		boolean find = false;
		
		while(i >= 0 && !find)
		{
			motif = this.Lesmotifs.get(i);
			
			if(this.Lesmotifs.get(i).contient(point))
			{
				motif = this.Lesmotifs.get(i);	
				this.Lesmotifs.remove(i);
				find = true;
			}
			
			i--;
		}
		
		return motif;
	}

	
	/**
	 * Cette fonction sert à dessiner le motif courant dans la fenêtre.
	 * 
	 * @param g Variable permettant de dessiner dans une fenêtre.
	 */
	public void dessiner(Graphics2D g)
	{
		if(this.motif != null)
			this.motif.dessiner(g);
	}
	
	
	/**
	 * Cette fonction sert à dessiner tous les motifs du dessin dans la fenêtre.
	 */
	public void toutDessiner(Graphics2D g) 
	{
		for(int i = 0; i < this.Lesmotifs.size(); i++)
		{
			this.Lesmotifs.get(i).dessiner(g);	
		}
	}

	
	
	/**
	 * Cette fonction sert à parcourir une ligne d'un fichier xml et de la convertir en différent <i>MonMotif</i>.
	 * 
	 * @param nodes Ligne d'un fichier xml.
	 */
	public void parcourir(NodeList nodes)
	{
		Node node;
		Boolean find;
		MonMotif motif = new MonRectangle();
		
		for(int i = 0; i < nodes.getLength(); i++)
		{
			node = nodes.item(i);
			find = false;
			
			if(node.getChildNodes().getLength() < 2)
			{	
				if(node.getNodeName() == "rect")
				{		
					motif = new MonRectangle();
					find = true;
				}
				else if(node.getNodeName() == "ellipse")
				{
					motif = new Ellipse();
					find = true;
				}
				else if(node.getNodeName() == "line")
				{
					motif = new Ligne();
					find = true;
				}
				else if(node.getNodeName() == "text")
				{
					motif = new Texte();
					find = true;
				}
				
				if(find)
				{
							
					this.ajouteMotif(motif);
				}
			}
			else
				this.parcourir(node.getChildNodes());
		}
	}
	
		
	
	@Override
	public String toString()
	{
		String Letxt = "";
		
		for (int i = 0; i < this.Lesmotifs.size(); i++)
		{
			Letxt += this.Lesmotifs.get(i).toString();
		}
	
		return Letxt;
	}
	
	
	/**
	 * Cette fonction nous permet d'ajouter un <i>MonMotif</i> dans le dessin. 
	 */
	public void ajouteMotif(MonMotif motifSelectionne)
	{
		this.motif = motifSelectionne;
		this.Lesmotifs.add(motifSelectionne);
	}
	

	/**
	 * Cette fonction sert à supprimer un <i>MonMotif</i> du dessin grâce à <i>indice</i>. 
	 */
	public void supprimerMonMotif(int indice)
	{
		this.Lesmotifs.remove(indice);
	}
	
		/**
	 * Cette fonction sert à supprimer un <i>MonMotif</i> du dessin grâce à <i>motif</i>. 
	 */
	public void supprimerMonMotif(MonMotif motif)
	{
		this.Lesmotifs.remove(motif);
	}
	

	/**
	 * Cette permet de savoir si il y a aucun <i>MonMotif</i> dans le dessin.
	 */
	public boolean motifsEstVide()
	{		
		return this.Lesmotifs.isEmpty();
	}
	
	
	/**
	 * Cette fonction nous permet d'ajouter un <i>MonMotif</i> dans le dessin. 
	 */
	public void ajouteMotif()
	{
		this.Lesmotifs.add(this.motif);
	}
	
	
	/**
	 * Cette fonction sert à remplacer <i>motifAModifier</i> du dessin par <i>motifModifier</i>.
	 */
	public void remplaceMotif(MonMotif motifAModifier, MonMotif motifModifier)
	{
		int i = 0;
		int indice = -1;
		boolean find = false;
		
		while(i < this.Lesmotifs.size() && !find)
		{
			if(this.Lesmotifs.elementAt(i).equals(motifAModifier))
			{
				indice = i;
				find = true;
			}
			
			i++;
		}
		
		this.Lesmotifs.set(indice, motifModifier.Monclone());	
	}
	
	
	/**
	 * Cette fonction sert à find si <i>motif</i> est present dans dessin.
	 */
	public boolean trouverMotif(MonMotif motif)
	{
		int i = 0;
		boolean find = false;
		
		while(i < this.Lesmotifs.size() && !find)
		{
			if(this.Lesmotifs.elementAt(i).equals(motif))
			{
				find = true;
			}
			i++;
		}
		return find;
	}

 
//========================= Getters et Setters =========================//
 	

	/**
	 * Retourne le motif courant du <i>MonDessin</i>.
	 */
	public MonMotif getMonMotif()
	{
		return motif;
	}

	/**
	 * Cette fonction sert à modifier la valeur du motif courant.
	 */
	public void setMonMotif(MonMotif motif)
	{
		this.motif = motif;
	}


	/**
	 * Retourne la largeur du <i>MonDessin</i>.
	 */
	public int getLargeur()
	{
		return maLargeur;
	}


	/**
	 * Cette fonction sert à modifier la largeur du <i>MonDessin</i>.
	 */
	public void setLargeur(int maLargeur)
	{
		this.maLargeur = maLargeur;
	}


	/**
	 * Retourne la hauteur du <i>MonDessin</i>.
	 */
	public int getHauteur()
	{
		return Mahauteur;
	}


	/**
	 * Cette fonction sert à modifier la hauteur du <i>MonDessin</i>.
	 */
	public void setHauteur(int Mahauteur)
	{
		this.Mahauteur = Mahauteur;
	}


	/**
	 * Retourne tous les motifs du <i>MonDessin</i>.
	 */
	public Vector<MonMotif> getMotifs()
	{
		return Lesmotifs;
	}


	/**
	 * Cette fonction sert à modifier le vecteur de Lesmotifs du <i>MonDessin</i>.
	 */
	public void setMotifs(Vector<MonMotif> Lesmotifs)
	{
		this.Lesmotifs = Lesmotifs;
	}


	/**
	 * Retourne l'historique du <i>MonDessin</i>.
	 */
	public Historique getMonHistorique()
	{
		return historique;
	}


	/**
	 * Cette fonction sert à modifier l'historique du <i>MonDessin</i>.
	 */
	public void setMonHistorique(Historique historique)
	{
		this.historique = historique;
	}


	/**
	 * Retourne le monZoom du <i>MonDessin</i>.
	 */
	public float getMonZoom()
	{
		return monZoom;
	}


	/**
	 * Cette fonction sert à modifier le monZoom du <i>MonDessin</i>.
	 */
	public void setMonZoom(float monZoom)
	{
		this.monZoom = monZoom;
	}


	/**
	 * Retourne la couleur du <i>MonDessin</i>.
	 */
	public Color getCouleur()
	{
		return couleur;
	}


	/**
	 * Cette fonction sert à modifier la couleur du <i>MonDessin</i>.
	 */
	public void setCouleur(Color couleur)
	{
		this.couleur = couleur;
	}


	/**
	 * Retourne le nom du dessin
	 */
	public String getNom()
	{
		return nom;
	}


	/**
	 * Cette fonction sert à modifier le nom du dessin.
	 */
	public void setNom(String nom)
	{
		this.nom = nom;
	}

	/**
	 * Cette fonction sert à copy le motif courant.
	 *
	 */
	public void copy() 
	{
		pressePapier = motif.Monclone();
	} 
	
	/**
	 * Cette fonction sert à copier le motif courant.
	 */
	public void cut() 
	{
		pressePapier = motif.Monclone();
		Evenement e = new SuppressionMonEvenement(this, pressePapier);
		historique.ajouteMonEvenement(e);
		e.execution();
		
		historique.suppHistoriqueARetab();
	} 
	/**
	 * Cette fonction sert à coller le motif courant.
	 */
	public void past() 
	{
		if(pressePapier != null)
		{
			motif.setSelectionner(false);
			pressePapier.setSelectionner(true);
			Evenement e = new CreationMonEvenement(this, pressePapier);
			historique.ajouteMonEvenement(e);
			e.execution();

			historique.suppHistoriqueARetab();
		}

	} 
}