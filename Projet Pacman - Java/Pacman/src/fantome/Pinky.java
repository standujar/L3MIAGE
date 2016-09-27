package fantome;

import constantes.Constantes;
import labyrinthe.Labyrinthe;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import pacman.Pacman;

public class Pinky extends Fantom
{
    public Pinky(Labyrinthe labPar, Pacman pacParCible)
    {
        super(labPar, pacParCible);
        
        try {
            this.imgNotFrightened = new Image(Constantes.sCHEMIN_TEXTURE_PINKY);
            this.imgRender = this.imgNotFrightened;
        } catch (SlickException ex) {}   
        
        posColonne  = 16*Constantes.iTAILLE_TILE_WIDTH;
        posLigne    = 11*Constantes.iTAILLE_TILE_HEIGHT-8;
        
        
    }
    
    public void initialisation()
    {
        posColonne  = 16*Constantes.iTAILLE_TILE_WIDTH;
        posLigne    = 11*Constantes.iTAILLE_TILE_HEIGHT-8;
        
        if(Math.random() > 0.5f)        
            this.iDirection = Constantes.iDROITE;
        else
            this.iDirection = Constantes.iGAUCHE;
        
        this.bFlagDirectionChoisi   = false;
        this.bVitRalenti            = false;
        
        this.setBehaviorChase();
    }
    
    public int quelleDirectionPrendreChaseMode()
    {
        int iDirectionPac = this.pacCible.getDirection();
        float fPosCibleColonne  = this.pacCible.getPosColonne();
        float fPosCibleLigne    = this.pacCible.getPosLigne();
        
        if(iDirectionPac == Constantes.iDROITE)
	{
		fPosCibleColonne=fPosCibleColonne+4*Constantes.iTAILLE_TILE_WIDTH;
		
		while((int)(fPosCibleColonne/Constantes.iTAILLE_TILE_WIDTH) > 27)
		{
			fPosCibleColonne-=Constantes.iTAILLE_TILE_WIDTH;
		}
	}
	else if(iDirectionPac == Constantes.iGAUCHE)
	{
		fPosCibleColonne=fPosCibleColonne-4*Constantes.iTAILLE_TILE_WIDTH;
		
		while((int)(fPosCibleColonne/Constantes.iTAILLE_TILE_WIDTH) < 0)
		{
			fPosCibleColonne+=Constantes.iTAILLE_TILE_WIDTH;
		}
	}
	else if(iDirectionPac == Constantes.iHAUT)
	{
		fPosCibleLigne=fPosCibleLigne-4*Constantes.iTAILLE_TILE_HEIGHT;
		
		while((int)(fPosCibleLigne/Constantes.iTAILLE_TILE_HEIGHT) < 0)
		{
			fPosCibleLigne+=Constantes.iTAILLE_TILE_HEIGHT;
		}
	}
	else if(iDirectionPac == Constantes.iBAS)
	{
		fPosCibleLigne=fPosCibleLigne+4*Constantes.iTAILLE_TILE_WIDTH;
		
		while((int)(fPosCibleLigne/Constantes.iTAILLE_TILE_HEIGHT) > 30)
		{
			fPosCibleLigne-=Constantes.iTAILLE_TILE_HEIGHT;
		}
	}

        float fDistanceFantomePacmanDeplacementDroit     = 0;
        float fDistanceFantomePacmanDeplacementGauche    = 0;
        float fDistanceFantomePacmanDeplacementHaut      = 0;
        float fDistanceFantomePacmanDeplacementBas       = 0;


        float fDistanceChoisi   = 0;
        int iDirectionChoisi    = -1;

        if(!this.testCollision(Constantes.iDROITE) && this.iDirection != Constantes.iGAUCHE)
        {
            int posColonneDroit                         = (int)(posColonne + 3*Constantes.iTAILLE_TILE_WIDTH);
            fDistanceFantomePacmanDeplacementDroit      = ((fPosCibleColonne - posColonneDroit)*(fPosCibleColonne - posColonneDroit) + (fPosCibleLigne - posLigne)*(fPosCibleLigne - posLigne));
            fDistanceChoisi                             = fDistanceFantomePacmanDeplacementDroit;
            iDirectionChoisi                            = Constantes.iDROITE;
        }
        if(!this.testCollision(Constantes.iGAUCHE) && this.iDirection != Constantes.iDROITE)
        {
            int posColonneGauche                        = (int)(posColonne - 3*Constantes.iTAILLE_TILE_WIDTH);
            fDistanceFantomePacmanDeplacementGauche     = ((fPosCibleColonne - posColonneGauche)*(fPosCibleColonne - posColonneGauche) + (fPosCibleLigne - posLigne)*(fPosCibleLigne - posLigne));

            if(fDistanceChoisi > fDistanceFantomePacmanDeplacementGauche)
            {
                fDistanceChoisi     = fDistanceFantomePacmanDeplacementGauche;
                iDirectionChoisi    = Constantes.iGAUCHE;
            }
            else if (fDistanceChoisi == 0)
            {
                fDistanceChoisi     = fDistanceFantomePacmanDeplacementGauche;
                iDirectionChoisi    = Constantes.iGAUCHE;
            }
        }
        if(!this.testCollision(Constantes.iHAUT)  && this.iDirection != Constantes.iBAS)
        {
            int posLigneHaut                        = (int)(posLigne - 3*Constantes.iTAILLE_TILE_HEIGHT);
            fDistanceFantomePacmanDeplacementHaut   = ((fPosCibleColonne - posColonne)*(fPosCibleColonne - posColonne) + (fPosCibleLigne - posLigneHaut)*(fPosCibleLigne - posLigneHaut));

            if(fDistanceChoisi > fDistanceFantomePacmanDeplacementHaut)
            {
                fDistanceChoisi     = fDistanceFantomePacmanDeplacementHaut;
                iDirectionChoisi    = Constantes.iHAUT;
            }
            else if (fDistanceChoisi == 0)
            {
                fDistanceChoisi     = fDistanceFantomePacmanDeplacementHaut;
                iDirectionChoisi    = Constantes.iHAUT;
            }
        }
        if(!this.testCollision(Constantes.iBAS)  && this.iDirection != Constantes.iHAUT)
        {
            int posLigneBas                         = (int)(posLigne + 3*Constantes.iTAILLE_TILE_HEIGHT);
            fDistanceFantomePacmanDeplacementBas    = ((fPosCibleColonne - posColonne)*(fPosCibleColonne - posColonne) + (fPosCibleLigne - posLigneBas)*(fPosCibleLigne - posLigneBas));

            if(fDistanceChoisi > fDistanceFantomePacmanDeplacementBas)
            {
                fDistanceChoisi     = fDistanceFantomePacmanDeplacementBas;
                iDirectionChoisi    = Constantes.iBAS;
            }
            else if (fDistanceChoisi == 0)
            {
                fDistanceChoisi     = fDistanceFantomePacmanDeplacementBas;
                iDirectionChoisi    = Constantes.iBAS;
            }
        }  

        return iDirectionChoisi;
    }
    
    
    public int quelleDirectionPrendreScatterMode()
    {
        float fPosCibleColonne  = Constantes.iCOLONNE_COIN_HAUT_GAUCHE * Constantes.iTAILLE_TILE_WIDTH;
        float fPosCibleLigne    = Constantes.iLIGNE_COIN_HAUT_GAUCHE * Constantes.iTAILLE_TILE_HEIGHT;

        float fDistanceFantomePacmanDeplacementDroit     = 0;
        float fDistanceFantomePacmanDeplacementGauche    = 0;
        float fDistanceFantomePacmanDeplacementHaut      = 0;
        float fDistanceFantomePacmanDeplacementBas       = 0;


        float fDistanceChoisi   = 0;
        int iDirectionChoisi    = -1;

        if(!this.testCollision(Constantes.iDROITE) && this.iDirection != Constantes.iGAUCHE)
        {
            int posColonneDroit                         = (int)(posColonne + 3*Constantes.iTAILLE_TILE_WIDTH);
            fDistanceFantomePacmanDeplacementDroit      = ((fPosCibleColonne - posColonneDroit)*(fPosCibleColonne - posColonneDroit) + (fPosCibleLigne - posLigne)*(fPosCibleLigne - posLigne));
            fDistanceChoisi                             = fDistanceFantomePacmanDeplacementDroit;
            iDirectionChoisi                            = Constantes.iDROITE;
        }
        if(!this.testCollision(Constantes.iGAUCHE) && this.iDirection != Constantes.iDROITE)
        {
            int posColonneGauche                        = (int)(posColonne - 3*Constantes.iTAILLE_TILE_WIDTH);
            fDistanceFantomePacmanDeplacementGauche     = ((fPosCibleColonne - posColonneGauche)*(fPosCibleColonne - posColonneGauche) + (fPosCibleLigne - posLigne)*(fPosCibleLigne - posLigne));

            if(fDistanceChoisi > fDistanceFantomePacmanDeplacementGauche)
            {
                fDistanceChoisi     = fDistanceFantomePacmanDeplacementGauche;
                iDirectionChoisi    = Constantes.iGAUCHE;
            }
            else if (fDistanceChoisi == 0)
            {
                fDistanceChoisi     = fDistanceFantomePacmanDeplacementGauche;
                iDirectionChoisi    = Constantes.iGAUCHE;
            }
        }
        if(!this.testCollision(Constantes.iHAUT)  && this.iDirection != Constantes.iBAS)
        {
            int posLigneHaut                        = (int)(posLigne - 3*Constantes.iTAILLE_TILE_HEIGHT);
            fDistanceFantomePacmanDeplacementHaut   = ((fPosCibleColonne - posColonne)*(fPosCibleColonne - posColonne) + (fPosCibleLigne - posLigneHaut)*(fPosCibleLigne - posLigneHaut));

            if(fDistanceChoisi > fDistanceFantomePacmanDeplacementHaut)
            {
                fDistanceChoisi     = fDistanceFantomePacmanDeplacementHaut;
                iDirectionChoisi    = Constantes.iHAUT;
            }
            else if (fDistanceChoisi == 0)
            {
                fDistanceChoisi     = fDistanceFantomePacmanDeplacementHaut;
                iDirectionChoisi    = Constantes.iHAUT;
            }
        }
        if(!this.testCollision(Constantes.iBAS)  && this.iDirection != Constantes.iHAUT)
        {
            int posLigneBas                         = (int)(posLigne + 3*Constantes.iTAILLE_TILE_HEIGHT);
            fDistanceFantomePacmanDeplacementBas    = ((fPosCibleColonne - posColonne)*(fPosCibleColonne - posColonne) + (fPosCibleLigne - posLigneBas)*(fPosCibleLigne - posLigneBas));

            if(fDistanceChoisi > fDistanceFantomePacmanDeplacementBas)
            {
                fDistanceChoisi     = fDistanceFantomePacmanDeplacementBas;
                iDirectionChoisi    = Constantes.iBAS;
            }
            else if (fDistanceChoisi == 0)
            {
                fDistanceChoisi     = fDistanceFantomePacmanDeplacementBas;
                iDirectionChoisi    = Constantes.iBAS;
            }
        }  

        return iDirectionChoisi;
    }
    
    
    public void update(GameContainer gcPar, int iParDeltaTimer)
    {
        this.updateTimerEtMode(iParDeltaTimer);
        
        if(this.testCollisionWithPacman())
        {
            if(this.iBehavior == Constantes.iFRIGHTENED)
            {
                this.pacCible.mangeFantome();
                this.initialisation();
            }
            else
            {
                this.pacCible.fantomTouchedMe();
            }
        }
        
        
        if( this.iBehavior == Constantes.iCHASE )
        {
            if(this.AmIOnIntersection())
            {
                // On place blinky correctement sur l'intersection afin de simplifier au maximum les collisions
                if(this.iDirection == Constantes.iGAUCHE)
                {
                    int fPosBlinkyColonne   = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
                    float fPosIntersectionColonne = (fPosBlinkyColonne * Constantes.iTAILLE_TILE_WIDTH) + (Constantes.iTAILLE_TILE_WIDTH/2);
                    float fPosCentreBlinkyColonne = posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2);

                    if(fPosCentreBlinkyColonne > fPosIntersectionColonne)
                    {
                        this.posColonne -= this.fVitesse*iParDeltaTimer;
                    }
                    else if(bFlagDirectionChoisi == true)
                    {
                        this.posColonne = fPosIntersectionColonne - (Constantes.iTAILLE_PACMAN_WIDTH/2);
                        this.iDirection = this.quelleDirectionPrendreChaseMode();
                        this.bFlagDirectionChoisi = false;
                    }
                }
                else if(this.iDirection == Constantes.iDROITE)
                {
                    int fPosBlinkyColonne   = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
                    float fPosIntersectionColonne = (fPosBlinkyColonne * Constantes.iTAILLE_TILE_WIDTH) + (Constantes.iTAILLE_TILE_WIDTH/2);
                    float fPosCentreBlinkyColonne = posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2);

                    if(fPosCentreBlinkyColonne < fPosIntersectionColonne)
                    {
                        this.posColonne += this.fVitesse*iParDeltaTimer;
                    }
                    else if(bFlagDirectionChoisi == true)
                    {
                        this.posColonne = fPosIntersectionColonne - (Constantes.iTAILLE_PACMAN_WIDTH/2);
                        this.iDirection = this.quelleDirectionPrendreChaseMode();
                        this.bFlagDirectionChoisi = false;
                    }
                }
                else if(this.iDirection == Constantes.iHAUT)
                {
                    int fPosBlinkyLigne     = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
                    float fPosIntersectionLigne = (fPosBlinkyLigne * Constantes.iTAILLE_TILE_HEIGHT) + (Constantes.iTAILLE_TILE_HEIGHT/2);
                    float fPosCentreBlinkyLigne = posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2);

                    if(fPosCentreBlinkyLigne > fPosIntersectionLigne)
                    {
                        this.posLigne -= this.fVitesse*iParDeltaTimer;
                    }
                    else if(bFlagDirectionChoisi == true)
                    {
                        this.posLigne   = fPosIntersectionLigne - (Constantes.iTAILLE_PACMAN_HEIGHT/2);
                        this.iDirection = this.quelleDirectionPrendreChaseMode();
                        this.bFlagDirectionChoisi = false;
                    }
                }
                else if(this.iDirection == Constantes.iBAS)
                {
                    int fPosBlinkyLigne     = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
                    float fPosIntersectionLigne = (fPosBlinkyLigne * Constantes.iTAILLE_TILE_HEIGHT) + (Constantes.iTAILLE_TILE_HEIGHT/2);
                    float fPosCentreBlinkyLigne = posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2);

                    if(fPosCentreBlinkyLigne < fPosIntersectionLigne)
                    {
                        this.posLigne += this.fVitesse*iParDeltaTimer;
                    }
                    else if(bFlagDirectionChoisi == true)
                    {
                        this.posLigne   = fPosIntersectionLigne - (Constantes.iTAILLE_PACMAN_HEIGHT/2);
                        this.iDirection = this.quelleDirectionPrendreChaseMode();
                        this.bFlagDirectionChoisi = false;
                    }
                }
            }
            else 
            {
                bFlagDirectionChoisi = true;
            }
                    
            if(this.onWhichTypeOfCaseAmI() == Constantes.iTUNNEL)
            {
                if(this.bVitRalenti == false)
                {
                    this.fVitesse *= 0.5f;
                    this.bVitRalenti = true;
                }

                if(this.bTunnelPassed == false)
                {
                     if((int)this.posColonne < Constantes.iCOORDONNE_LIMITE_LABYRINTHE_GAUCHE)
                    {
                        int iColonneMax = Constantes.iTAILLE_LABYRINTHE_COLONNE - 1;
                        this.posColonne =  iColonneMax * Constantes.iTAILLE_TILE_WIDTH;
                        this.bTunnelPassed = true;
                    }
                    else if((int)this.posColonne > Constantes.iCOORDONNE_LIMITE_LABYRINTHE_DROITE)
                    {
                        this.posColonne = 1;
                        this.bTunnelPassed = true;
                    }
                }  
            }
            else
            {
                if(this.bVitRalenti == true)
                {
                    this.fVitesse *= 2;
                    this.bVitRalenti = false;
                }
                this.bTunnelPassed = false;
            }
            
            // Blinky avance selon la direction choisi à la dernière intersection
            if(this.iDirection == Constantes.iGAUCHE)
            {
                this.posColonne -= this.fVitesse*iParDeltaTimer;
            }
            else if(this.iDirection == Constantes.iDROITE)
            {
                this.posColonne += this.fVitesse*iParDeltaTimer;
            }
            else if(this.iDirection == Constantes.iHAUT)
            {
                this.posLigne   -= this.fVitesse*iParDeltaTimer;
            }
            else if(this.iDirection == Constantes.iBAS)
            {
                this.posLigne   += this.fVitesse*iParDeltaTimer;
            }
        }    
        
        
        else if(this.iBehavior == Constantes.iSCATTER)
        {
            if(this.AmIOnIntersection())
            {
                // On place blinky correctement sur l'intersection afin de simplifier au maximum les collisions
                if(this.iDirection == Constantes.iGAUCHE)
                {
                    int fPosBlinkyColonne   = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
                    float fPosIntersectionColonne = (fPosBlinkyColonne * Constantes.iTAILLE_TILE_WIDTH) + (Constantes.iTAILLE_TILE_WIDTH/2);
                    float fPosCentreBlinkyColonne = posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2);

                    if(fPosCentreBlinkyColonne > fPosIntersectionColonne)
                    {
                        this.posColonne -= this.fVitesse*iParDeltaTimer;
                    }
                    else if(bFlagDirectionChoisi == true)
                    {
                        this.posColonne = fPosIntersectionColonne - (Constantes.iTAILLE_PACMAN_WIDTH/2);
                        this.iDirection = this.quelleDirectionPrendreScatterMode();
                        this.bFlagDirectionChoisi = false;
                    }
                }
                else if(this.iDirection == Constantes.iDROITE)
                {
                    int fPosBlinkyColonne   = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
                    float fPosIntersectionColonne = (fPosBlinkyColonne * Constantes.iTAILLE_TILE_WIDTH) + (Constantes.iTAILLE_TILE_WIDTH/2);
                    float fPosCentreBlinkyColonne = posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2);

                    if(fPosCentreBlinkyColonne < fPosIntersectionColonne)
                    {
                        this.posColonne += this.fVitesse*iParDeltaTimer;
                    }
                    else if(bFlagDirectionChoisi == true)
                    {
                        this.posColonne = fPosIntersectionColonne - (Constantes.iTAILLE_PACMAN_WIDTH/2);
                        this.iDirection = this.quelleDirectionPrendreScatterMode();
                        this.bFlagDirectionChoisi = false;
                    }
                }
                else if(this.iDirection == Constantes.iHAUT)
                {
                    int fPosBlinkyLigne     = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
                    float fPosIntersectionLigne = (fPosBlinkyLigne * Constantes.iTAILLE_TILE_HEIGHT) + (Constantes.iTAILLE_TILE_HEIGHT/2);
                    float fPosCentreBlinkyLigne = posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2);

                    if(fPosCentreBlinkyLigne > fPosIntersectionLigne)
                    {
                        this.posLigne -= this.fVitesse*iParDeltaTimer;
                    }
                    else if(bFlagDirectionChoisi == true)
                    {
                        this.posLigne   = fPosIntersectionLigne - (Constantes.iTAILLE_PACMAN_HEIGHT/2);
                        this.iDirection = this.quelleDirectionPrendreScatterMode();
                        this.bFlagDirectionChoisi = false;
                    }
                }
                else if(this.iDirection == Constantes.iBAS)
                {
                    int fPosBlinkyLigne     = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
                    float fPosIntersectionLigne = (fPosBlinkyLigne * Constantes.iTAILLE_TILE_HEIGHT) + (Constantes.iTAILLE_TILE_HEIGHT/2);
                    float fPosCentreBlinkyLigne = posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2);

                    if(fPosCentreBlinkyLigne < fPosIntersectionLigne)
                    {
                        this.posLigne += this.fVitesse*iParDeltaTimer;
                    }
                    else if(bFlagDirectionChoisi == true)
                    {
                        this.posLigne   = fPosIntersectionLigne - (Constantes.iTAILLE_PACMAN_HEIGHT/2);
                        this.iDirection = this.quelleDirectionPrendreScatterMode();
                        this.bFlagDirectionChoisi = false;
                    }
                }
            }
            else 
            {
                bFlagDirectionChoisi = true;
            }
            
            
            if(this.onWhichTypeOfCaseAmI() == Constantes.iTUNNEL)
            {
                if(this.bVitRalenti == false)
                {
                    this.fVitesse *= 0.5f;
                    this.bVitRalenti = true;
                }

                if(this.bTunnelPassed == false)
                {
                    if((int)this.posColonne < Constantes.iCOORDONNE_LIMITE_LABYRINTHE_GAUCHE)
                    {
                        int iColonneMax = Constantes.iTAILLE_LABYRINTHE_COLONNE - 1;
                        this.posColonne =  iColonneMax * Constantes.iTAILLE_TILE_WIDTH;
                        this.bTunnelPassed = true;
                    }
                    else if((int)this.posColonne > Constantes.iCOORDONNE_LIMITE_LABYRINTHE_DROITE)
                    {
                        this.posColonne = 1;
                        this.bTunnelPassed = true;
                    }
                }  
            }
            else
            {
                if(this.bVitRalenti == true)
                {
                    this.fVitesse *= 2;
                    this.bVitRalenti = false;
                }
                this.bTunnelPassed = false;
            }
            
            
            // Blinky avance selon la direction choisi à la dernière intersection
            if(this.iDirection == Constantes.iGAUCHE)
            {
                this.posColonne -= this.fVitesse*iParDeltaTimer;
            }
            else if(this.iDirection == Constantes.iDROITE)
            {
                this.posColonne += this.fVitesse*iParDeltaTimer;
            }
            else if(this.iDirection == Constantes.iHAUT)
            {
                this.posLigne   -= this.fVitesse*iParDeltaTimer;
            }
            else if(this.iDirection == Constantes.iBAS)
            {
                this.posLigne   += this.fVitesse*iParDeltaTimer;
            } 
        }
        
        else if(this.iBehavior == Constantes.iFRIGHTENED)
        {
            if(this.AmIOnIntersection())
            {
                if(this.iDirection == Constantes.iGAUCHE)
                {
                    int fPosBlinkyColonne   = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
                    float fPosIntersectionColonne = (fPosBlinkyColonne * Constantes.iTAILLE_TILE_WIDTH) + (Constantes.iTAILLE_TILE_WIDTH/2);
                    float fPosCentreBlinkyColonne = posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2);

                    if(fPosCentreBlinkyColonne > fPosIntersectionColonne)
                    {
                        this.posColonne -= this.fVitesse*iParDeltaTimer;
                    }
                    else if(bFlagDirectionChoisi == true)
                    {
                        this.posColonne = fPosIntersectionColonne - (Constantes.iTAILLE_PACMAN_WIDTH/2);
                        this.iDirection = this.quelleDirectionPrendreFrightenedMode();
                        this.bFlagDirectionChoisi = false;
                    }
                }
                else if(this.iDirection == Constantes.iDROITE)
                {
                    int fPosBlinkyColonne   = (int)((posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2))/Constantes.iTAILLE_TILE_WIDTH);
                    float fPosIntersectionColonne = (fPosBlinkyColonne * Constantes.iTAILLE_TILE_WIDTH) + (Constantes.iTAILLE_TILE_WIDTH/2);
                    float fPosCentreBlinkyColonne = posColonne+(Constantes.iTAILLE_PACMAN_WIDTH/2);

                    if(fPosCentreBlinkyColonne < fPosIntersectionColonne)
                    {
                        this.posColonne += this.fVitesse*iParDeltaTimer;
                    }
                    else if(bFlagDirectionChoisi == true)
                    {
                        this.posColonne = fPosIntersectionColonne - (Constantes.iTAILLE_PACMAN_WIDTH/2);
                        this.iDirection = this.quelleDirectionPrendreFrightenedMode();
                        this.bFlagDirectionChoisi = false;
                    }
                }
                else if(this.iDirection == Constantes.iHAUT)
                {
                    int fPosBlinkyLigne     = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
                    float fPosIntersectionLigne = (fPosBlinkyLigne * Constantes.iTAILLE_TILE_HEIGHT) + (Constantes.iTAILLE_TILE_HEIGHT/2);
                    float fPosCentreBlinkyLigne = posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2);

                    if(fPosCentreBlinkyLigne > fPosIntersectionLigne)
                    {
                        this.posLigne -= this.fVitesse*iParDeltaTimer;
                    }
                    else if(bFlagDirectionChoisi == true)
                    {
                        this.posLigne   = fPosIntersectionLigne - (Constantes.iTAILLE_PACMAN_HEIGHT/2);
                        this.iDirection = this.quelleDirectionPrendreFrightenedMode();
                        this.bFlagDirectionChoisi = false;
                    }
                }
                else if(this.iDirection == Constantes.iBAS)
                {
                    int fPosBlinkyLigne     = (int)((posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2))/Constantes.iTAILLE_TILE_HEIGHT);
                    float fPosIntersectionLigne = (fPosBlinkyLigne * Constantes.iTAILLE_TILE_HEIGHT) + (Constantes.iTAILLE_TILE_HEIGHT/2);
                    float fPosCentreBlinkyLigne = posLigne+(Constantes.iTAILLE_PACMAN_HEIGHT/2);

                    if(fPosCentreBlinkyLigne < fPosIntersectionLigne)
                    {
                        this.posLigne += this.fVitesse*iParDeltaTimer;
                    }
                    else if(bFlagDirectionChoisi == true)
                    {
                        this.posLigne   = fPosIntersectionLigne - (Constantes.iTAILLE_PACMAN_HEIGHT/2);
                        this.iDirection = this.quelleDirectionPrendreFrightenedMode();
                        this.bFlagDirectionChoisi = false;
                    }
                }
            }
            else 
            {
                bFlagDirectionChoisi = true;
            }
             
            if(this.onWhichTypeOfCaseAmI() == Constantes.iTUNNEL)
            {
                if(this.bVitRalenti == false)
                {
                    this.fVitesse *= 0.5f;
                    this.bVitRalenti = true;
                }

                if(this.bTunnelPassed == false)
                {
                    if((int)this.posColonne < Constantes.iCOORDONNE_LIMITE_LABYRINTHE_GAUCHE)
                    {
                        int iColonneMax = Constantes.iTAILLE_LABYRINTHE_COLONNE - 1;
                        this.posColonne =  iColonneMax * Constantes.iTAILLE_TILE_WIDTH;
                        this.bTunnelPassed = true;
                    }
                    else if((int)this.posColonne > Constantes.iCOORDONNE_LIMITE_LABYRINTHE_DROITE)
                    {
                        this.posColonne = 1;
                        this.bTunnelPassed = true;
                    }
                }  
            }
            else
            {
                if(this.bVitRalenti == true)
                {
                    this.fVitesse *= 2;
                    this.bVitRalenti = false;
                }
                this.bTunnelPassed = false;
            }
            
            
            // Blinky avance selon la direction choisi à la dernière intersection
            if(this.iDirection == Constantes.iGAUCHE)
            {
                this.posColonne -= this.fVitesse*iParDeltaTimer;
            }
            else if(this.iDirection == Constantes.iDROITE)
            {
                this.posColonne += this.fVitesse*iParDeltaTimer;
            }
            else if(this.iDirection == Constantes.iHAUT)
            {
                this.posLigne   -= this.fVitesse*iParDeltaTimer;
            }
            else if(this.iDirection == Constantes.iBAS)
            {
                this.posLigne   += this.fVitesse*iParDeltaTimer;
            }
    
        }
    }
    
    public void render(Graphics grphcsPar)
    {
        grphcsPar.setAntiAlias(true);
        grphcsPar.drawImage(this.imgRender, this.posColonne, this.posLigne);
    }
    
    
}
