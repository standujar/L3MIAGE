package Vue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Controleur.MonControleur;

/**
 * Cette fonction sert à définir la barre d'options pour l'application.
 */
public class MaBarreOption extends JToolBar implements ActionListener
{
 
//============================ Variables ===============================//
 
	
	private MonControleur controleur;
	
	private JButton nouveau = new JButton(new ImageIcon("icon/filenew.png"));
	private JButton copier = new JButton(new ImageIcon("icon/copier.png"));
	private JButton couper = new JButton(new ImageIcon("icon/couper.png"));
	private JButton coller = new JButton(new ImageIcon("icon/coller.png"));
	

 
//========================== Constructeurs =============================//
 
		
	/**
	 * Crée une nouvelle instance de <i>MaBarreOption</i>.
	 *
	 * @param controleur
	 */
    public MaBarreOption(MonControleur controleur)
    {
    	this.controleur = controleur;
		this.setFloatable(false);
    	
    	JPanel panneau1 = new JPanel();
    	panneau1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    	
    	this.nouveau.setName("nouveau");
    	this.nouveau.addActionListener(this);
    	panneau1.add(nouveau);
    	    	
    	panneau1.setLayout(new BoxLayout(panneau1, BoxLayout.X_AXIS));
    	this.add(panneau1);

    	
    	JPanel panneau2 = new JPanel();
    	panneau2.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

    	this.add(panneau2);
    	panneau2.setLayout(new BoxLayout(panneau2, BoxLayout.X_AXIS));

    	JPanel panneau3 = new JPanel();
    	panneau3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    	panneau3.add(copier);
    	panneau3.add(couper);
    	panneau3.add(coller);
    	this.add(panneau3);
    	panneau3.setLayout(new BoxLayout(panneau3, BoxLayout.X_AXIS));
    	
    	JPanel panneau4 = new JPanel();
    	panneau4.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    	
    	this.copier.setName("copier");
    	this.copier.addActionListener(this);
    	
    	this.couper.setName("couper");
    	this.couper.addActionListener(this);
    	
    	this.coller.setName("coller");
    	this.coller.addActionListener(this);
    	this.coller.setEnabled(false);

    	this.add(panneau4);
    	panneau4.setLayout(new BoxLayout(panneau4, BoxLayout.X_AXIS));
    }
    

 
//====================== Méthodes Implémentées= ========================//
 
    
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton bouton = (JButton) e.getSource();
		
		if(bouton.getName() == "nouveau")
		{
			final JFrame fenetre = new JFrame("Nouneau");			
			GridLayout layout = new GridLayout(4, 3, 5, 5);
			JPanel panneau = new JPanel(layout);
			JLabel labelNom = new JLabel("Nom :");
			final JTextField fieldNom = new JTextField("Sans titre");
			JLabel labelLargeur = new JLabel("Largeur :");
			final JTextField fieldLargeur = new JTextField("800");
			JLabel labelHauteur = new JLabel("Hauteur :");
			final JTextField fieldHauteur = new JTextField("600");
			JButton boutonOk = new JButton("Ok");
			JButton boutonAnnuler = new JButton("Annuler");
			JLabel labelPixels1 = new JLabel("pixels");
			JLabel labelPixels2 = new JLabel("pixels");
			JLabel label1 = new JLabel("");
			JLabel label2 = new JLabel("");
			
			panneau.add(labelNom);
			panneau.add(fieldNom);
			panneau.add(label1);
			panneau.add(labelLargeur);
			panneau.add(fieldLargeur);
			panneau.add(labelPixels1);
			panneau.add(labelHauteur);
			panneau.add(fieldHauteur);
			panneau.add(labelPixels2);
			panneau.add(label2);
			panneau.add(boutonAnnuler);
			panneau.add(boutonOk);
	    	fenetre.add(panneau);
	    	
	    	panneau.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));  
			
			fenetre.setVisible(true);
			fenetre.pack();
		
			boutonAnnuler.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					fenetre.dispose();
				}
			});
			
			boutonOk.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					controleur.nouvDessin(fieldNom.getText(), Integer.valueOf(fieldLargeur.getText()), Integer.valueOf(fieldHauteur.getText()));
					fenetre.dispose();
				}
			});
		}
		else if(bouton.getName() == "copier")
		{
			this.controleur.copy();
			coller.setEnabled(true);
		}
		else if(bouton.getName() == "couper")
		{
			this.controleur.cut();
			coller.setEnabled(true);
		}
		else if(bouton.getName() == "coller")
		{
			this.controleur.past();				
		}
		else
		{
			System.out.println("Action non implémentée.");
		}
	}
	
	public void setEnableColler(boolean bool)
	{
		coller.setEnabled(bool);
	}
}