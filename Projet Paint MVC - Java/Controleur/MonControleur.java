package Controleur;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import Model.Evenement.*;
import Vue.*;
import Model.*;


/**
 * On fait la liaison vu - controleur
 */
public class MonControleur 
{
//============================ Variables ===============================//
	
	/* ici, on défini tout les différent modes */

	public final static int MODEDE_CREATION = 0;
	
	public final static int MODEDE_ZOOM = 3;
	
	public final static int MODEDE_GOMME = 2;
	
	public final static int MODEDE_SOURIS = 1;
	
		
	// element associé au controleur et dernier etat du monmotif
		
	private MonDessin mondessin; 
	
	private MonMotif monmotif; 
	
	private MaFenetre mafenetre; 
	
	private int monMode = MODEDE_SOURIS; 
  
	
//========================== Constructeurs =============================//


	
	public MonControleur()
	{
		this.mafenetre = new MaFenetre(this, "Titre", 800, 600);
		this.mondessin = new MonDessin();	
	}
	
	
 
//============================= Méthodes ===============================//
 

	/**
	 * La fonction sert à repeindre le <i>MonDessin</i>. 
	 */
	public void repaintMonDessin()
	{
		this.mafenetre.repaintMonDessin();
	}
	
	
	/**
	 * La fonction sert à créer un nouveau <i>MonDessin</i> du contrôleur.
	 */
	public void nouvDessin(String n, int l, int h)
	{
		this.mondessin = new MonDessin(n, l, h);
		this.mafenetre.dispose();
		this.mafenetre = new MaFenetre(this, n, l, h);
		this.mafenetre.repaintMonDessin();
	}
	
	
	/**
	 * La fonction permet de changer d'monoutils (ex: maLigne, Lerectangle, ...).
	 */
	public void nouvOutils(String monoutils)
	{
		if(monoutils == "maSouris")
		{
			if(this.mondessin.getMonMotif() != null)
				this.mondessin.getMonMotif().setSelectionner(false);
			
			this.mondessin.setMonMotif(null);
			this.monMode = MODEDE_SOURIS;
		}
		else if(monoutils == "Ligne")
		{
			this.mondessin.creationObjet(monoutils);
			this.monMode = MODEDE_CREATION;
		}
		else if(monoutils == "MonRectangle")
		{
			if(this.mondessin.creationObjet(monoutils))
			this.monMode = MODEDE_CREATION;
		}
		else if(monoutils == "Ellipse")
		{
			if(this.mondessin.creationObjet(monoutils))
			this.monMode = MODEDE_CREATION;
		}
		
		else if(monoutils == "Lagomme")
		{
			this.mondessin.setMonMotif(null);
			this.monMode = MODEDE_GOMME;
		}
		else if(monoutils == "Texte")
		{
			if(this.mondessin.creationObjet(monoutils))
			this.monMode = MODEDE_CREATION;
		}
		

		this.miseAJourDuMenu(true);
	}
	
	
	/**
	 * La fonction sert à dessiner le mondessin dans la fenêtre.
	 */
	public void dessiner(Graphics g, boolean dessinerTout)
	{		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(dessinerTout)
		{
			float monZoom = this.mondessin.getMonZoom();
			g2d.scale(monZoom, monZoom);
			
			this.mondessin.toutDessiner(g2d);
		}
		else
		{
			if(this.mondessin.getMonMotif() != null && !this.mondessin.getMonMotif().getSelectionner())
				this.mondessin.dessiner(g2d);
		}
	}
	
	
	/**
	 * Retourne le monmotif courant du mondessin.
	 */
	public MonMotif motifCourant()
	{
		return this.mondessin.getMonMotif();
	}
	
	
	/**
	 * Retourne le monZoom courant du mondessin.
	 */
	public float zoomEnCourt()
	{
		return this.mondessin.getMonZoom();
	}
	
	
	/**
	 * Retourne la macouleur courante du mondessin.
	 */
	public Color couleurEnCourt()
	{
		return this.mondessin.getCouleur();
	}
	
	
	/**
	 * La fonction sert à faire correspondre les valeurs des Lesmotifs dessinés par l'utilisateur lors de la création de ce dernier au valeur de ce monmotif. 
	 */
	public void CorrespondreMotif(Point point1, Point point2)
	{
		this.mondessin.getMonMotif().aFaireCorrespondre(point1.x, point1.y, point2.x, point2.y);
	}
	
	
	/**
	 * La fonction sert à déplacer un monmotif.
	 */
	public void motifDeplacer(int x, int y)
	{
		this.mondessin.getMonMotif().deplacer(x, y);
		this.mondessin.getMonHistorique().suppHistoriqueARetab();
	}
	
	
	/**
     * La fonction permet d'appliquer les modifications nécessaires lors du redim d'un monmotif par l'utilisateur.
     */
	public void motifRedimentionner(Point point1, Point point2)
	{
		this.mondessin.getMonMotif().redim(point1.x, point2.x, point1.y, point2.y);
		this.mondessin.getMonHistorique().suppHistoriqueARetab();
	}
	
	
	/**
	 * La fonction permet d'afficher les coordonnées de la maSouris, si cette dernière se trouve dans la zone de mondessin.
	 */
	public void affPositionSouris(int x, int y)
	{
		this.mafenetre.affPositionSouris(x, y);
	}
	
	
	/**
	 * La fonction sert à retourner la poignée qui est sélectionnée. 
	 */
	public int getSelHandles()
	{
		return this.mondessin.getMonMotif().getSelHandle();
	}
	
	
	/**
	 * La fonction permet d'ajouter un monmotif au mondessin.
	 */
	public void ajouteMotif()
	{
		this.mondessin.getMonMotif().setSelectionner(false);

		Evenement e = new CreationMonEvenement(this.mondessin, this.mondessin.getMonMotif());
		this.mondessin.getMonHistorique().ajouteMonEvenement(e);
		e.execution();
		
		this.mondessin.getMonHistorique().suppHistoriqueARetab();
		this.monmotif = this.mondessin.getMonMotif().Monclone();
	}
	
	
	/**
	 * La fonction sert à modifier un monmotif se trouvant dans le mondessin.
	 */
	public void modifieMotif(boolean estDifferent)
	{
		if(this.mondessin.getMonMotif() != null && this.monmotif != null && (!this.mondessin.getMonMotif().equals(this.monmotif) || !estDifferent))
		{
			Evenement e;
			
			if(estDifferent)
				e = new ModificationMonEvenement(this.mondessin, this.monmotif, this.mondessin.getMonMotif());
			else
				e = new ModificationMonEvenement(this.mondessin, this.mondessin.getMonMotif(), this.mondessin.getMonMotif());
				
			this.mondessin.getMonHistorique().ajouteMonEvenement(e);
			e.execution();
		}
				
		if(this.mondessin.getMonMotif() != null)
			this.monmotif = this.mondessin.getMonMotif().Monclone();
	}
	
	
	/**
	 * La fonction permet d'supprimer un monmotif se trouvant dans le mondessin.
	 */
	public boolean effacerMonMotif(Point positionMaSouris)
	{
		MonMotif m;
		
		if((m = this.mondessin.effacerMonMotif(positionMaSouris)) != null)
		{
			Evenement e = new SuppressionMonEvenement(this.mondessin, m);
			this.mondessin.getMonHistorique().ajouteMonEvenement(e);
			e.execution();
			
			
		
			this.mondessin.getMonHistorique().suppHistoriqueARetab();

			return true;
		}
		else
			return false;
	}
	
	
	/**
	 * La fonction sert à sélectionner un monmotif se trouvant dans le mondessin.
	 */
	public void selectionneMonMotif(Point positionMaSouris, Graphics g)
	{
		MonMotif motifSelectionne = this.mondessin.getMonMotif();
		
		if(motifSelectionne != null)
		{
			motifSelectionne.setSelectionner(false);
		}
		
		
		if(mondessin.find(positionMaSouris))
			motifSelectionne = mondessin.getMonMotif();
		
		if(motifSelectionne != null && motifSelectionne.getSelectionner())
		{
			this.monmotif = motifSelectionne.Monclone();
			motifSelectionne.dessPoign((Graphics2D) g);
			motifSelectionne.touverHandles(positionMaSouris);
		}
		this.miseAJourDuMenu(false);
	}
	
	
	/**
	 * La fonction sert à mettre à jour le monMenu en fonction de l'monoutils (ex: maLigne, Lerectangle, ...) choisi.
	 */
	public void miseAJourDuMenu(boolean supprimer)
	{
		if(this.mondessin.getMonMotif() != null && !supprimer)
		{
			String typeDeMonMotif = this.mondessin.getMonMotif().getClass().getCanonicalName();

			if(typeDeMonMotif =="Model.Texte")
			{
				this.mafenetre.affMenuText(true);
				this.mafenetre.affMenuOpa(true);

				Texte Letxt = (Texte) this.mondessin.getMonMotif();
				this.mafenetre.modValMenuTaillePolice(Letxt.getTailleDePolice());
				this.mafenetre.modValMenuPolice(Letxt.getPolice());
				this.mafenetre.modValMenuOpa((int) (Letxt.getOpaCouleur() * 100f));
			}
			else if(typeDeMonMotif =="Model.Ellipse" || typeDeMonMotif =="Model.Ligne" || typeDeMonMotif =="Model.MonRectangle")
			{
				this.mafenetre.affMenuText(false);
				this.mafenetre.affMenuOpa(true);

				this.mafenetre.modValMenuOpa((int) (this.mondessin.getMonMotif().getOpaCouleur() * 100f));
			}
			
			else
			{
				this.mafenetre.affMenuText(false);
				this.mafenetre.affMenuOpa(false);	
			}
			
			if(this.mondessin.getMonMotif().getSelectionner())
			{
				this.mafenetre.modValMenuCouleurRempli(this.mondessin.getMonMotif().getCouleur());
			}
			else
			{
				this.mafenetre.modValMenuCouleurRempli(this.mondessin.getCouleur());
			}
		}
		else
		{
			this.mafenetre.affMenuText(false);
			this.mafenetre.affMenuOpa(false);	
		}
	}
	
	/**
	 * La fonction sert à créer un nouvelle instance du type du monmotif courant du mondessin.
	 */
	public void newInstanceMotif()
	{
		this.mondessin.setMonMotif(this.mondessin.getMonMotif().newInstance());
	}
	
	
	/**
	 * La fonction permet d'afficher une zone de Letxt pour pouvoir éditer du Letxt.
	 */
	public void maZoneDeTexte(final Graphics g)
	{
		JTextArea maJtf;
		final MonMotif motifSelectionne = this.mondessin.getMonMotif();
		final Texte monTxt;
		
		if(motifSelectionne != null)
		{
			if(motifSelectionne.getClass().getCanonicalName() == "Model.Texte")
			{
				monTxt = (Texte) motifSelectionne;
				maJtf = monTxt.getMaZoneTxt();
				maJtf.setText(monTxt.getTexte());
				final JFrame maFrame = new JFrame();
				maFrame.setLayout(new BorderLayout());
				maFrame.setLocation(monTxt.getX()+monTxt.getLargeur(), monTxt.getY());
				maFrame.setMinimumSize(new Dimension(300,100));
				maFrame.setVisible(true);
		        maFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				maFrame.addFocusListener(new FocusListener()
				{
					public void focusLost(FocusEvent arg0) 
					{
						maFrame.dispose();
					}
					public void focusGained(FocusEvent arg0){}
				});
				
				maFrame.add(maJtf,BorderLayout.CENTER);
				
				JPanel p = new JPanel(new BorderLayout());
				JButton boutonOk = new JButton("Ok");
				JButton boutonAnnuler = new JButton("Annuler");
				p.add(boutonOk,BorderLayout.EAST);
				p.add(boutonAnnuler, BorderLayout.WEST);
				
				boutonOk.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						if(monTxt!=null)
						{
							monTxt.miseAJourText((Graphics2D) g);
							if (!mondessin.trouverMotif(motifSelectionne))
								ajouteMotif();
							
							mafenetre.repaintMonDessin();
							mondessin.toutDessiner((Graphics2D) g);
							maFrame.dispose();
						}
					}
				});
				
				boutonAnnuler.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						maFrame.dispose();
					}
				});
				
				maFrame.add(p,BorderLayout.SOUTH);
			}

		}
	}
	
	
	/**
	 * La fonction sert à modifier la macouleur du monmotif courant.
	 */
	public void modifierCouleurMonMotif(Color macouleur)
	{
		this.mondessin.setCouleur(macouleur);
		this.mafenetre.modValMenuCouleurRempli(macouleur);
		
		if(this.mondessin.getMonMotif() != null)
		{
			MonMotif motifSelectionne = this.mondessin.getMonMotif();
			motifSelectionne.setCouleur(macouleur);
			
			if(this.mondessin.getMonMotif().getX() != -1 && this.mondessin.getMonMotif().getY() != -1)
			{
				this.mondessin.getMonHistorique().suppHistoriqueARetab();
				this.modifieMotif(true);
				this.repaintMonDessin();
			}
		}
	}
	
	
	/**
	 * La fonction sert à modifier la valeur l'opacité du monmotif courant.
	 */
	public void changerOpacite(float monOpacite)
	{		
		MonMotif motifSelectionne = this.mondessin.getMonMotif();
		motifSelectionne.setOpaCouleur(Float.valueOf(monOpacite / 100.0f));
		this.mondessin.getMonHistorique().suppHistoriqueARetab();
		this.repaintMonDessin();
	}
	
	
	/**
	 * La fonction sert à changer la taille de la maPolice.
	 */
	public void changerTaillePolice(int tailleDeLaPolice) 
	{
		Texte monTxt = (Texte) this.mondessin.getMonMotif();
		monTxt.setTailleDeLaPolice(tailleDeLaPolice);
		this.mondessin.getMonHistorique().suppHistoriqueARetab();
		this.repaintMonDessin();
	}
	
	
	/**
	 * La fonction sert à modifier les caractéristiques d'un Letxt. 
	 */
	public void modifierCaracMonTxt(Object maSource)
	{
		Texte Letxt = (Texte) this.mondessin.getMonMotif();
		JComponent bouton = (JComponent) maSource;

		if(bouton.getName() == "listDesPolice")
		{
			JComboBox j = (JComboBox) maSource;
			Letxt.setPolice(j.getSelectedItem());
		}
		else if (bouton.getName() == "gras")
			Letxt.modEnGras();
		else if(bouton.getName() == "italique")
			Letxt.modEnItalique();

		
		this.mondessin.getMonHistorique().suppHistoriqueARetab();

		this.modifieMotif(true);
		this.repaintMonDessin();
	}
	
	
	/**
	 * La fonction sert à modifier le dernier <i>Evenement</i> de l'historique.
	 */
	public void modifierDernierMonEvent()
	{
		Evenement e = this.mondessin.getMonHistorique().suppEvenementHistorique();
		e.setMotifApresMonEvent(this.mondessin.getMonMotif().Monclone());
		this.mondessin.getMonHistorique().ajouteMonEvenement(e);
		e.execution();
		this.monmotif = this.mondessin.getMonMotif().Monclone();
	}
	
	
	/**
	 * La fonction sert à vérifier si le monmotif après l'évènement du dernier évènement est égale au monmotif initial du dernier évènement.
	 */
	public boolean motifsLastEventEgaux()
	{
		return this.mondessin.getMonHistorique().motifsLastEventEgaux();
	}
	
	
	/**
	 * La fonction sert à supprimer le dernier évènement de l'historique.
	 */
	public void suppDernierMonEvent()
	{
		this.mondessin.getMonHistorique().suppEvenementHistorique();
	}
	
	
	/**
	 * La fonction sert à retourner le vecteur de monmotif du mondessin.
	 */
	public Vector<MonMotif> getMotifs()
	{
		return this.mondessin.getMotifs();
	}


//========================= Getters et Setters =========================//
	
	/**
	 * Retourne le monmotif.
	 */
	public MonMotif getMonMotif()
	{
		return monmotif;
	}


	/**
	 * La fonction sert à modifier le monmotif.
	 */
	public void setMonMotif(MonMotif monmotif)
	{
		this.monmotif = monmotif;
	}
	
	
	/**
	 * Retourne le monMode que l'utilisateur a choisi.
	 */
	public int getMode()
	{
		return monMode;
	}


	/**
	 * La fonction sert à modifier le monMode.
	 */
	public void setMode(int monMode)
	{
		this.monMode = monMode;
	}

	/**
	 * La fonction sert à copy le monmotif courant.
	 */
	public void copy() 
	{
		mondessin.copy();
		mafenetre.getBarredOption().setEnableColler(true);
	}
	
	/**
	 * La fonction sert à cut le monmotif courant.
	 */
	public void cut() 
	{
		mondessin.cut();
		repaintMonDessin();
		mafenetre.getBarredOption().setEnableColler(true);
	}
	
	/**
	 * La fonction sert à copy le monmotif courant.
	 */
	public void past() 
	{
		mondessin.past();
		repaintMonDessin();
	}
}