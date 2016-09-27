package Vue;
import java.awt.event.*;

import javax.swing.*;

import Controleur.MonControleur;

/**
 * Cette fonction sert à définir et gérer la barre d'outils de l'application.
 */
public class MaBarreOutils extends JToolBar implements ActionListener
{

//============================ Variables ===============================//

	private MonControleur controleur;
	
    private JButton maSouris = new JButton(new ImageIcon("icon/select.png"));
    private JButton maLigne = new JButton(new ImageIcon("icon/maLigne.png"));
    private JButton Lerectangle = new JButton(new ImageIcon("icon/draw-Lerectangle.png"));
    private JButton Uncercle = new JButton(new ImageIcon("icon/draw-ellipse.png"));
    private JButton Letxt = new JButton(new ImageIcon("icon/draw-text.png"));
    private JButton Lagomme = new JButton(new ImageIcon("icon/draw-eraser.png"));
	

//========================== Constructeurs =============================//

    /**
     * Crée une nouvelle instance de <i>MaBarreOutils</i>.
     */
    public MaBarreOutils(MonControleur controleur)
    {
    	this.controleur = controleur;    	
    	this.setOrientation(JToolBar.VERTICAL);
		this.setFloatable(false);
    	
    	this.maSouris.setName("maSouris");
    	this.maSouris.addActionListener(this);
    	this.add(this.maSouris);	
    	
    	this.maLigne.setName("Ligne");
    	this.maLigne.addActionListener(this);
    	this.add(this.maLigne);
    	
    	this.Lerectangle.setName("MonRectangle");
    	this.Lerectangle.addActionListener(this);
    	this.add(this.Lerectangle);
    	
    	this.Uncercle.setName("Ellipse");
    	this.Uncercle.addActionListener(this);
    	this.add(this.Uncercle);
    	
    	this.Lagomme.setName("Lagomme");
    	this.Lagomme.addActionListener(this);
    	this.add(this.Lagomme);
    	
    	this.Letxt.setName("Texte");
    	this.Letxt.addActionListener(this);
    	this.add(this.Letxt);
	
    }

    
 
//====================== Méthodes Implémentées= ========================//
 
    
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton bouton = (JButton) e.getSource();
		
		this.controleur.nouvOutils(bouton.getName());
	}
}