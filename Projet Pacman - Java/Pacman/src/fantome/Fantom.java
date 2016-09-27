
package fantome;

import constantes.Constantes;
import labyrinthe.Labyrinthe;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import pacman.Pacman;


public class Fantom 
{
    protected Image imgRender;
    protected Image imgFrightened;
    protected Image imgNotFrightened;
      
    protected float posColonne;
    protected float posLigne;
    
    protected int iDirection;
    protected boolean bFlagDirectionChoisi;
    
    protected float fVitesse;
    
    protected int iBehavior;
    
    protected Labyrinthe labyrinthe;
    protected Pacman pacCible;
    
    protected int iTimer;
    private int iCompteurTransitionMode;
    
    protected boolean bTunnelPassed;
    protected boolean bVitRalenti;
    

    
    public Fantom(Labyrinthe labPar, Pacman pacParCible)
    {   
        this.labyrinthe = labPar;
        this.pacCible   = pacParCible;
        this.iTimer     = 0;
        this.iBehavior  = Constantes.iCHASE;
        
        this.fVitesse   = this.pacCible.getVitesse() * 0.90f;
        
        if(Math.random() > 0.5f)        
            this.iDirection = Constantes.iDROITE;
        else
            this.iDirection = Constantes.iGAUCHE;
        
        this.bFlagDirectionChoisi   = false;
        this.bTunnelPassed          = false;
        this.bVitRalenti            = false;
        
        try {
            this.imgFrightened = new Image(Constantes.sCHEMIN_TEXTURE_FRIGHTENED);
        } catch (SlickException ex) {}  
    }
    
    /**
     * Test la collision entre le pacman et le fantome
     * @return true s'il y a collision, false sinon
     */
    public boolean testCollisionWithPacman()
    {
        int iPosColonneFantom = (int)(posColonne/Constantes.iTAILLE_TILE_WIDTH);
        int iPosLigneFantom = (int)(posLigne/Constantes.iTAILLE_TILE_HEIGHT);
        
        int iColonnePacman = (int)(this.pacCible.getPosColonne()/Constantes.iTAILLE_TILE_WIDTH);
        int iLignePacman = (int)(this.pacCible.getPosLigne()/Constantes.iTAILLE_TILE_HEIGHT);
        
        if( (iPosColonneFantom == iColonnePacman ) && (iPosLigneFantom == iLignePacman ) )
        {
            return true;
        }
        else
            return false;
    }
    
    /**
     * 
     * @param iDirection test la collision par rapport à la direction demandé
     * 
     * @return true si il y a collision, false s'il n'y a pas de colllision
     */
    public boolean testCollision(int iParDirection)
    {
        
        int iTypeCase = -1;
        int iColonne = 0;
        int iLigne = 0;
        

        if(iParDirection == Constantes.iGAUCHE)
        {
            iColonne    = (int)((posColonne-this.fVitesse)/Constantes.iTAILLE_TILE_WIDTH);
            iLigne      = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
        }
        else if(iParDirection == Constantes.iDROITE)
        {
            iColonne    = (int)((posColonne+this.fVitesse+Constantes.iTAILLE_PACMAN_WIDTH)/Constantes.iTAILLE_TILE_WIDTH);
            iLigne      = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
        }
        else if(iParDirection == Constantes.iHAUT)
        {
            iColonne    = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
            iLigne      = (int)((posLigne-this.fVitesse)/Constantes.iTAILLE_TILE_HEIGHT);
        }
        else if(iParDirection == Constantes.iBAS)
        {
            iColonne    = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
            iLigne      = (int)((posLigne+this.fVitesse+Constantes.iTAILLE_PACMAN_HEIGHT)/Constantes.iTAILLE_TILE_HEIGHT);
        }
        
         
        if( iColonne < 0 )
        {
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, 0);
        }
        else if( iColonne > 27 )
        {
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, 27);
        }
        else
        {
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, iColonne);
        }
         
        if( iTypeCase != Constantes.iPACGOMME && iTypeCase != Constantes.iINTERSECTION && iTypeCase != Constantes.iTUNNEL && iTypeCase != Constantes.iPACGOMME_SPECIAL)        
        {
            return true;
        }
        else
            return false;
    }
    
    /**
     * Permet de savoir sur quelle type de case se trouve le fantom (vide, mur, intersection ,... )
     * 
     * @return une entier représentant le type de la case sur laquelle se situe le fantome.
     */
    public int onWhichTypeOfCaseAmI()
    {
        int iTypeCase;
        int iColonne = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
        int iLigne = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);

        if ( iColonne < 0 )
        {
            iColonne = 0;
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, iColonne);
        }
        else if ( iColonne > 27 )
        {
            iColonne = 27;
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, iColonne);
        }
        else if( iLigne > 30 )
        {
            iLigne = 30;
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, iColonne);
        }
        else if( iLigne < 0 )
        {
            iLigne = 0;
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, iColonne);
        }
        else
        {
            iTypeCase = this.labyrinthe.getTypeCaseLabyrintheCollision(iLigne, iColonne);
        }
        
        return iTypeCase;
    }
    
    
    /**
     * permet de savoir si le fantom se trouve sur une intersection
     * 
     * @return true si le fantome est sur l'intersection, false sinon
     */
    public boolean AmIOnIntersection()
    {
        if(this.onWhichTypeOfCaseAmI() == Constantes.iINTERSECTION || this.onWhichTypeOfCaseAmI() == Constantes.iTUNNEL)
            return true;
        return false;
    }
    
    
    /**
     * Change la direction du fantome (il prend la direction opposé (ex : droite devient gauche, gauche devient droite,... )
     */
    public void reverseDirection()
    {
        if( this.iDirection == Constantes.iGAUCHE )
        {
            this.iDirection = Constantes.iDROITE;
        }
        else if( this.iDirection == Constantes.iDROITE )
        {
            this.iDirection = Constantes.iGAUCHE;
        }
        else if( this.iDirection == Constantes.iHAUT )
        {
            this.iDirection = Constantes.iBAS;
        } 
        else if( this.iDirection == Constantes.iBAS )
        {
            this.iDirection = Constantes.iHAUT;
        } 
    }
    
    public int quelleDirectionPrendreFrightenedMode()
    {
        int iDirectionOppose = -1;
        int iDirectionChoisi = -1;
        
        if( this.iDirection == Constantes.iGAUCHE )
        {
            iDirectionOppose = Constantes.iDROITE;
        }
        else if( this.iDirection == Constantes.iDROITE )
        {
            iDirectionOppose = Constantes.iGAUCHE;
        }
        else if( this.iDirection == Constantes.iHAUT )
        {
            iDirectionOppose = Constantes.iBAS;
        } 
        else if( this.iDirection == Constantes.iBAS )
        {
            iDirectionOppose = Constantes.iHAUT;
        }
        
     
        iDirectionChoisi = 4;
        if(iDirectionChoisi == iDirectionOppose)
            iDirectionChoisi--;

        while(testCollision(iDirectionChoisi) && iDirectionChoisi >= 0)
        {
            iDirectionChoisi--;
            if(iDirectionChoisi == iDirectionOppose)
                iDirectionChoisi--;    
        }

        return iDirectionChoisi;
    }
    
    
    /**
     * remet le timer interne du fantôme à 0
     * 
     */
    public void resetTimer()
    {
        this.iTimer = 0;
    }
    
    
    /**
     * Change le mode du fantôme en mode frightened
     */
    public void setBehaviorFrightened()
    {
        this.iBehavior  = Constantes.iFRIGHTENED;
        this.resetTimer();
        this.fVitesse   = this.pacCible.getVitesse() * 0.90f * 0.5f;
        this.reverseDirection();
        this.imgRender  = this.imgFrightened;
    }
    
    
    /**
     * Change le mode du fantôme en mode chase
     */
    public void setBehaviorChase() 
    {
        this.resetTimer();
        this.fVitesse   = this.pacCible.getVitesse() * 0.90f;
        this.iBehavior  = Constantes.iCHASE;
        this.imgRender  = this.imgNotFrightened;
    }
    
    
    /**
     * Cette Fonction gère la durée des modes de Blinky (chase et scatter)
     * en fonction du timer delta
     * 
     * @param iParDeltaTimer delta timer envoyé à la fonction update à chaque tour de boucle pour connaitre le temps 
     * écoulé depuis le dernier rafraichissement
     */
    public void updateTimerEtMode(int iParDeltaTimer)
    {
        if(this.iBehavior == Constantes.iCHASE)
        {
            if(this.iTimer > Constantes.iDureeChase)
            {
                this.iBehavior  = Constantes.iSCATTER;
                this.resetTimer();
                this.iCompteurTransitionMode++;
            }
            else
            {
                this.iTimer += iParDeltaTimer;
            }
        }
        else if(this.iBehavior == Constantes.iSCATTER)
        {
            if(this.iTimer > Constantes.iDureeScatter)
            {
                this.iBehavior  = Constantes.iCHASE;
                this.resetTimer();
            }
            else
                this.iTimer += iParDeltaTimer; 
            
            switch(this.iCompteurTransitionMode)
            {
                case 1 :
                {
                    Constantes.iDureeChase      = 20000;
                    Constantes.iDureeScatter    = 7000;
                    break;
                }
                case 2 : 
                {
                    Constantes.iDureeChase      = 20000;
                    Constantes.iDureeScatter    = 7000;
                    break;
                }
                case 3 : 
                {
                    Constantes.iDureeChase      = 20000;
                    Constantes.iDureeScatter    = 5000;
                    break;
                }
                case 4 :
                {
                    Constantes.iDureeChase      = 2100000000; // durée infinie
                    Constantes.iDureeScatter    = 5000;
                    break;
                }
            }           
        }
        else if(this.iBehavior == Constantes.iFRIGHTENED)
        {
            if(this.iTimer > Constantes.iDureeFrightened)
            {
                this.iBehavior  = Constantes.iCHASE;
                this.resetTimer();
                this.pacCible.setSuperModeOff();
                this.fVitesse   = this.pacCible.getVitesse() * 0.90f;
                this.imgRender  = this.imgNotFrightened;
                this.pacCible.resetNbFantomeEaterWhileSuperMode();
            }
            else
            {
                this.iTimer += iParDeltaTimer; 
            }
        }
    }
}
