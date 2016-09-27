package launch;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import states.*;

public class PacmanLauncher extends StateBasedGame{

    // liste des états du jeu 
    private MenuPrincipal   stateMenuPrincipal  = new MenuPrincipal();
    private MenuPause       stateMenuPause      = new MenuPause();
    private TableauScore    stateTableauScore   = new TableauScore();
    private Jeu             stateJeu            = new Jeu();
    private FinPartie       stateFinPartie      = new FinPartie();
    private AjoutPseudo     stateAjoutPseudo    = new AjoutPseudo();

    public static void main(String[] args) throws SlickException 
    {
        
        // Mise en place de la fenêtre
        AppGameContainer agc = new AppGameContainer(new PacmanLauncher("Pacman v1.0"));
        
        agc.setDisplayMode(880,880,false);
        agc.setShowFPS(true);
        agc.start();
    }

    public PacmanLauncher(String sParName) 
    {
        super(sParName);
    }

    @Override
    public void initStatesList(GameContainer gcPar) throws SlickException 
    {
        
        // Ajout des états du jeu 
        addState(stateMenuPrincipal);
        addState(stateMenuPause);
        addState(stateTableauScore);
        addState(stateJeu);
        addState(stateFinPartie);
        addState(stateAjoutPseudo);
        
        // Lancement du 1er états (Le menu principal)
        enterState(stateMenuPrincipal.getID());
        
    }
    
}
