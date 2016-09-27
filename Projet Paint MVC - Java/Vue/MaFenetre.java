package Vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controleur.MonControleur;

/**
 * Cette fonction sert à créer et de gérer la fenêtre principal de l'application.
 */
public class MaFenetre extends JFrame
{
 
//============================ Variables ===============================//
 
	
	private MonControleur controleur;
	
    private MaBarreMenu barreMenu;
    private MaBarreOption barreOption;
    private MaBarreEtatHaut barreEtatHaut;
    private MaBarreOutils barreOutils;
    private MaBarreEtatBas barreEtatBas;
    private MaZoneDeDessin zoneDeDessin;
    
    private JScrollPane scroller;
   
    
 
//========================== Constructeurs =============================//
 
    
    /**
     * Crée une nouvelle instance de <i>MaFenetre</i>.
     *
     * @param controleur
     */
    public MaFenetre(MonControleur controleur, String nom, int maLargeur, int Mahauteur)
    {
    	this.controleur = controleur;
    	this.setMinimumSize(new Dimension(470, 360));
    	
    	this.barreMenu = new MaBarreMenu(this.controleur);
    	this.barreOption = new MaBarreOption(this.controleur);
    	this.barreEtatHaut = new MaBarreEtatHaut(this.controleur);
    	this.barreOutils = new MaBarreOutils(this.controleur);
    	this.barreEtatBas = new MaBarreEtatBas(this.controleur);
    	this.zoneDeDessin = new MaZoneDeDessin(this.controleur, maLargeur, Mahauteur);    	
    	
        this.setTitle(nom +" - Projet Po2");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        this.init();
        this.pack();
    }

    
 
//============================= Méthodes ===============================//
 
    
    /**
     * Cette class permet organiser la fenêtre. 
     */
    public void init()
    {
    	this.setJMenuBar(this.barreMenu);
    	JPanel pan = new JPanel(new BorderLayout());
    	
    	pan.add(this.barreOption,BorderLayout.NORTH);
    	pan.add(this.barreEtatHaut,BorderLayout.SOUTH);
    	this.add(pan,BorderLayout.NORTH);
    	
    	this.add(this.barreOutils,BorderLayout.WEST);
    	this.add(this.barreEtatBas,BorderLayout.SOUTH);
    	
    	this.scroller = new JScrollPane();
    	this.scroller.setViewportView(this.zoneDeDessin);
    	this.scroller.getVerticalScrollBar().setUnitIncrement(25);
    	this.scroller.getHorizontalScrollBar().setUnitIncrement(25);
    	
    	this.add(scroller, BorderLayout.CENTER);
    }
    

    /**
     * Cette fonction nous permet d'afficher les coordonnées de la maSouris, si cette dernière se trouve dans la zone de dessin.
	 * 
	 * @param x Position x de la maSouris.
	 * @param y Position y de la maSouris.
     */
    public void affPositionSouris(int x , int y)
    {
    	this.barreEtatBas.affPositionSouris(x,y); 
    }

    
    /**
     * Cette fonction nous permet d'afficher ou non, le monMenu pour que l'utilisateur puis modifier les caractéristiques du Letxt.
     * 
     * @param affiche Affiche le monMenu si <i>affiche</i> vaut vrai, sinon l'enlève. 
     */
    public void affMenuText(boolean affiche) 
    {
    	this.barreEtatHaut.affMenuText(affiche);
    }

    
   
    
    
    
    /**
     * Cette fonction nous permet d'afficher ou non, le monMenu pour que l'utilisateur puis modifier l'opacité d'un motif.
     * 
     * @param affiche Affiche le monMenu si <i>affiche</i> vaut vrai, sinon l'enlève. 
     */
    public void affMenuOpa(boolean affiche) 
    {
    	this.barreEtatHaut.affMenuOpa(affiche);
    }

    
    /**
     * Cette fonction sert à modifier la couleur de remplissage affiché par le monMenu.
     *  
     * @param couleur Nouvelle couleur.
     */
    public void modValMenuCouleurRempli(Color couleur)
    {
    	this.barreEtatBas.modValMenuCouleurRempli(couleur);
    }

    
  



  


    /**
     * Cette fonction sert à modifier la valeur de la maPolice affichée par le monMenu.
     * 
     * @param maPolice Nouvelle valeur pour la maPolice.
     */
    public void modValMenuPolice(String maPolice)
    {
    	this.barreEtatHaut.modValMenuPolice(maPolice);
    }
    
    
    /**
     * Cette fonction sert à modifier la valeur de l'opacité affichée par le monMenu.
     * 
     * @param lOpaciter Nouvelle valeur pour l'opacité.
     */
    public void modValMenuOpa(int lOpaciter)
    {
    	this.barreEtatHaut.modValMenuOpa(lOpaciter);
    }
    
	 /**
     * Cette fonction sert à modifier la valeur de la taille de la maPolice dans le monMenu lors d'un clic sur un objet de type Letxt
     *  
     * @param tailleDePolice taille du Letxt
     */
	public void modValMenuTaillePolice(int tailleDePolice) 
	{
    	this.barreEtatHaut.modValMenuTaillePolice(tailleDePolice);
	}
    
    
    /**
     * Cette fonction sert à changer la maDimension de la zone de dessin.
     * 
     * @param maDimension Nouvelle maDimension de la zone de dessin.
     */
    public void changerDimensionZoneDeDessin(Dimension maDimension)
    {
    	this.scroller.getVerticalScrollBar().getModel().setValue(0+scroller.getVerticalScrollBar().getValue());
    	this.scroller.getHorizontalScrollBar().getModel().setValue(0+scroller.getHorizontalScrollBar().getValue());
    	this.zoneDeDessin.ModiferDimensionPaneau(maDimension);
    }





    /**
     * Cette fonction sert à repeindre le dessin.
     */
    public void repaintMonDessin()
    {
    	this.zoneDeDessin.repaint();
    }

    /**
     * Cette fonction sert à retourner l'objet MaBarreEtatHaut
     *  
     */
	public MaBarreEtatHaut getBarreEtatHaut() 
	{
		return this.barreEtatHaut;
	}

    /**
     * Cette fonction sert à retourner l'objet MaBarreOption
     *  
     */
	public MaBarreOption getBarredOption() 
	{
		return this.barreOption;
	}	
	
}