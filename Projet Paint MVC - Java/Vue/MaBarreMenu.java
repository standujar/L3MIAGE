package Vue;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;

import javax.print.attribute.*;
import javax.swing.*;

import Controleur.MonControleur;
import Model.MonDessin;

/**
 * Cette fonction sert à définir et gérer le monMenu de la fenêtre principal du programme. 
 */
public class MaBarreMenu extends JMenuBar implements ActionListener
{
//============================ Variables ===============================//
	
	private MonControleur controleur;
	
	private JMenuBar barMenu = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	private JMenu edition = new JMenu("Edition");

	private JMenuItem nouveau = new JMenuItem("Nouveau");

	private JMenuItem copy = new JMenuItem("Copier");
	private JMenuItem cut = new JMenuItem("Couper");
	private JMenuItem past = new JMenuItem("Coller");

//========================== Constructeurs =============================//

	public MaBarreMenu(MonControleur controleur)
	{
		this.controleur = controleur;
		
		this.nouveau.setName("nouveau");
		this.nouveau.addActionListener(this);
		this.fichier.add(nouveau);
		
		this.edition.add(copy);
		copy.setName("copy");
		copy.addActionListener(this);
		this.edition.add(cut);
		cut.setName("cut");
		cut.addActionListener(this);
		this.edition.add(past);
		past.setName("past");
		past.addActionListener(this);

		
		this.add(fichier);
		this.add(edition);
		
		this.nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		this.copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		this.cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
		this.past.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
	}
	
//====================== Méthodes Implémentées= ========================//
    
    @Override
	public void actionPerformed(ActionEvent e)
	{
    	JMenuItem monMenu = (JMenuItem) e.getSource();
		
		if(monMenu.getName() == "nouveau")
		{
				final JFrame Mafenetre = new JFrame("Nouneau");			
				GridLayout layout = new GridLayout(4, 3, 5, 5);
				JPanel panneauTout = new JPanel(layout);
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
				
				panneauTout.add(labelNom);
				panneauTout.add(fieldNom);
				panneauTout.add(label1);
				panneauTout.add(labelLargeur);
				panneauTout.add(fieldLargeur);
				panneauTout.add(labelPixels1);
				panneauTout.add(labelHauteur);
				panneauTout.add(fieldHauteur);
				panneauTout.add(labelPixels2);
				panneauTout.add(label2);
				panneauTout.add(boutonAnnuler);
				panneauTout.add(boutonOk);
		    	Mafenetre.add(panneauTout);
		    	
		    	panneauTout.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));  
				
				Mafenetre.setVisible(true);
				Mafenetre.pack();
			
				boutonAnnuler.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						Mafenetre.dispose();
					}
				});
				
				boutonOk.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						controleur.nouvDessin(fieldNom.getText(), Integer.valueOf(fieldLargeur.getText()), Integer.valueOf(fieldHauteur.getText()));
						Mafenetre.dispose();
					}
				});
			
		}
		
		else if(monMenu.getName() == "copy")
		{
			this.controleur.copy();
		}
		else if(monMenu.getName() == "cut")
		{
			this.controleur.cut();
		}
		else if(monMenu.getName() == "past")
		{
			this.controleur.past();
		}
		else
		{
			System.out.println("BarOutils: Action \""+ monMenu.getName() +"\" n'est pas encore implémentée.");
		}
	}
}