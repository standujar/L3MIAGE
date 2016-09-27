package Vue;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;

import Controleur.MonControleur;

/**
 * Cette fonction sert à créer et gérer la barre état du bas de la fenêtre principal de l'application.
 */
public class MaBarreEtatBas extends JPanel implements MouseListener
{
//============================ Les Variables ===============================//

	private MonControleur controleur;
	
	private JLabel libCoorX = new JLabel("X: ");
    private JLabel libCoorY = new JLabel("Y: ");
    private JLabel labCoorX = new JLabel("0");
    private JLabel labCoorY = new JLabel("0");
    
    private JLabel libCouleurRemp = new JLabel("Couleur: ");
    private JLabel labCouleurRemp = new JLabel("       ");
    
//========================== Le Constructeurs =============================//
    

    public MaBarreEtatBas(MonControleur controleur)
    {    	
    	this.controleur = controleur;
    	this.setLayout(new BorderLayout());
    	
    	JPanel panCoor = new JPanel(new BorderLayout());
    	JPanel panCoorX = new JPanel(new BorderLayout());
    	JPanel panCoorY = new JPanel(new BorderLayout()); 
    	
    	JPanel panColor = new JPanel(new BorderLayout());
    	JPanel panColorRemp = new JPanel(new BorderLayout());

    	panCoorX.add(this.libCoorX, BorderLayout.WEST);
    	panCoorX.add(this.labCoorX, BorderLayout.EAST);
    	panCoorX.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 30));  
    	panCoor.add(panCoorX, BorderLayout.NORTH);
    	
    	panCoorY.add(this.libCoorY, BorderLayout.WEST);
    	panCoorY.add(this.labCoorY, BorderLayout.EAST);
    	panCoorY.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 30));  
    	panCoor.add(panCoorY, BorderLayout.SOUTH);    
    	
    	this.labCouleurRemp.setName("couleurDeRemplissage");
    	this.labCouleurRemp.setOpaque(true);
    	this.labCouleurRemp.setBackground(Color.blue);
    	panColorRemp.add(this.libCouleurRemp, BorderLayout.WEST);
    	panColorRemp.add(this.labCouleurRemp, BorderLayout.EAST);
    	panColorRemp.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 2));
    	panColor.add(panColorRemp, BorderLayout.NORTH);
    	
    	this.add(panCoor, BorderLayout.EAST);
    	this.add(panColor, BorderLayout.WEST);
    	
    	this.labCouleurRemp.addMouseListener(this);

    }

    

//============================= Les Méthodes ===============================//

    
    /**
     * Cette fonction nous permet d'afficher la position de la maSouris dans la barre état du bas de la fenêtre.
     */
	public void affPositionSouris(int x, int y) 
	{
		this.labCoorX.setText(Integer.toString(x));
		this.labCoorY.setText(Integer.toString(y));
	}
	
	
	/**
	 * Cette fonction sert à modifier la couleur de remplissage d'un motif qui sera affiché dans la fenêtre.
	 */
	public void modValMenuCouleurRempli(Color couleur)
	{
		this.labCouleurRemp.setBackground(couleur);
	}

	
	
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		JLabel label = (JLabel) e.getSource();

		JFrame f = new JFrame();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f.setTitle("Couleur remplissage");
		f.setVisible(true);

		JColorChooser boiteDeCouleur = new JColorChooser();

		if(label.getName() == "couleurRemplissage")
		{
			boiteDeCouleur.getSelectionModel().addChangeListener(new ChangeListener()
			{
				public void stateChanged(ChangeEvent e) 
				{
					Color couleur = ((DefaultColorSelectionModel)e.getSource()).getSelectedColor();

					controleur.modifierCouleurMonMotif(couleur);
				}
			});
		}


		f.add(boiteDeCouleur);
		f.pack();
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}
}