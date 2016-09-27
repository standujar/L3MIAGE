package Vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import Controleur.MonControleur;

/**
 * Cette fonction sert à créer et gérer la barre état du haut de la fenêtre principal de l'application.
 */
public class MaBarreEtatHaut extends JToolBar implements ChangeListener, ActionListener, FocusListener
{   
//============================ Variables ===============================//
	
	private MonControleur controleur;
	

    private JLabel labelDOpacite = new JLabel("Opacite: ");
    private SpinnerModel pourcentageDOpacite = new SpinnerNumberModel(100, 0, 100, 1);
    private JSpinner lOpaciter = new JSpinner(pourcentageDOpacite);
    
	private JButton boutonGras = new JButton(new ImageIcon("icon/gras.png"));
	private JButton boutonItalique = new JButton(new ImageIcon("icon/italique.png"));    
	private JComboBox listDesPolice;
	private SpinnerModel valTaillePolice = new SpinnerNumberModel(100, 0, 100, 1);
	private JSpinner tailleDePolice = new JSpinner(valTaillePolice);

//========================== Constructeurs =============================//

	/**
	 * Crée une nouvelle instance de <i>MaBarreEtatHaut</i>.
	 */
    public MaBarreEtatHaut(MonControleur controleur)
    {
    	this.controleur = controleur;
		this.setFloatable(false);
    	
    	String[] nomPolice = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    	this.listDesPolice = new JComboBox(nomPolice);
    	
    	this.lOpaciter.setName("lOpaciter");
    	this.listDesPolice.setName("listDesPolice");
    	this.boutonGras.setName("gras");
    	this.boutonItalique.setName("italique");  
    	this.tailleDePolice.setName("tailleDePolice");
    	
    	this.setPreferredSize(new Dimension(0,35));
    	this.tailleDePolice.addChangeListener(this);
    	this.lOpaciter.addChangeListener(this);
    	this.listDesPolice.addActionListener(this);
    	this.boutonGras.addActionListener(this);
    	this.boutonItalique.addActionListener(this); 
    	
    	((JSpinner.DefaultEditor)this.lOpaciter.getEditor()).getTextField().addFocusListener(this);
    	((JSpinner.DefaultEditor)this.tailleDePolice.getEditor()).getTextField().addFocusListener(this);
    }
    
 
//============================= Méthodes ===============================//
    
    /**
     * Cette fonction nous permet d'afficher ou enlever le monMenu permettant de modifier les caractéristiques d'un Letxt.
     */
	public void affMenuText(boolean affiche) 
	{
		if(affiche)
		{	
			this.add(listDesPolice);
			this.add(tailleDePolice);
			this.add(boutonGras);
			this.add(boutonItalique);
		}
		else
		{
			this.remove(listDesPolice);
			this.remove(tailleDePolice);
			this.remove(boutonGras);
			this.remove(boutonItalique);
			
			repaint();
		}
	}
	
	
	/**
     * Cette fonction nous permet d'afficher ou enlever le monMenu permettant de modifier l'opacité d'un motif.
     */
	public void affMenuOpa(boolean affiche)
	{
		if(affiche)
		{	
			this.add(this.labelDOpacite);
			this.add(this.lOpaciter);
		}
		else
		{
			this.remove(this.labelDOpacite);
			this.remove(this.lOpaciter);
			
			repaint();
		}
	}
	

	/**
	 * Cette fonction sert à modifier la valeur de l'opacité du motif qui sera affiché par le monMenu.
	 */
	public void modValMenuOpa(int lOpaciter)
	{
		this.lOpaciter.setValue(lOpaciter);
	}
	

	/**
	 * Cette fonction sert à modifier la valeur de la maPolice d'un Letxt qui sera affiché par le monMenu.
	 */
	public void modValMenuPolice(String maPolice)
	{
		this.listDesPolice.setSelectedItem(maPolice);
	}
	
	/**
	 * Cette fonction sert à modifier la valeur de la taille de la maPolice d'un Letxt qui sera affiché par le monMenu.
	 *  
	 * @param tailleDePolice Nouvelle valeur de la taille de la maPolice d'un Letxt.
	 */
	public void modValMenuTaillePolice(int tailleDePolice) 
	{
		this.tailleDePolice.setValue(tailleDePolice);
	}

//====================== Méthodes Implémentées= ========================//
	
	@Override
	public void stateChanged(ChangeEvent e)
	{
		JSpinner spinner = (JSpinner) e.getSource();

		if(((JSpinner.DefaultEditor)spinner.getEditor()).getTextField().hasFocus())
		{
			if(spinner.getName() == "lOpaciter")
			{
				this.controleur.changerOpacite(Float.valueOf(spinner.getValue().toString()));
			}
			else if(spinner.getName() == "tailleDePolice")
			{
				this.controleur.changerTaillePolice(Integer.valueOf(spinner.getValue().toString()));
			}
			
			this.controleur.modifierDernierMonEvent();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.controleur.modifierCaracMonTxt(e.getSource());
	}
	
	@Override
	public void focusLost(FocusEvent e)
	{
		if(this.controleur.motifsLastEventEgaux())
			this.controleur.suppDernierMonEvent();
	}
	
	@Override
	public void focusGained(FocusEvent e)
	{
		this.controleur.modifieMotif(false);
	}
}