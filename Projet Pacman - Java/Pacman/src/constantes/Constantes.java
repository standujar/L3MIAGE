package constantes;

public class Constantes 
{
    // ID des états du jeu 
        public static final int iID_ETAT_MENU_PRINCIPAL = 0;
        public static final int iID_ETAT_JEU            = 1;
        public static final int iID_ETAT_MENU_PAUSE     = 2;
        public static final int iID_ETAT_FIN_PARTIE     = 3;
        public static final int iID_ETAT_AJOUT_PSEUDO   = 4;
        public static final int iID_ETAT_TABLEAU_SCORE  = 5;
    
    
    // ID des item du menu principal
        public static final int iID_NOUVELLE_PARTIE         = 0;
        public static final String sLABEL_NOUVELLE_PARTIE   = "Nouvelle Partie";

        public static final int iID_MEILLEURS_SCORES        = 1;
        public static final String sLABEL_MEILLEURS_SCORE   = "Meilleurs Scores";
        
        public static final int iID_DIFFICULTE      = 2;
        public static final String sLABEL_LENT      = "Lent";
        public static final int iID_LABEL_LENT      = 0;
        public static final String sLABEL_NORMAL    = "Normal";
        public static final int iID_LABEL_NORMAL    = 1;
        public static final String sLABEL_RAPIDE    = "Rapide";
        public static final int iID_LABEL_RAPIDE    = 2;
        
        public static final int iID_QUITTER         = 3;
        public static final String sLABEL_QUTTER    = "Quitter";
        
        public static final String sLABEL_MENU_PRINCIPAL   = "Menu Principal";
    
    
    // Chemin des images (et fichier des labirynthes)
        // chemin des images pour les items des menus 
        public static final String sCHEMIN_FLECHE_DROITE_ITEM_CHOIX_MULTIPLE    = "Images\\fleche-droite.png";       
        public static final String sCHEMIN_FLECHE_GAUCHE_ITEM_CHOIX_MULTIPLE    = "Images\\fleche-gauche.png";
        public static final String sCHEMIN_SON_ON                               = "Images\\son-on.png"; 
        public static final String sCHEMIN_SON_OFF                              = "Images\\son-off.png"; 
        public static final String sCHEMIN_GAME_OVER                            = "Images\\game-over.jpg"; 
    
        // labyrinthe
        public static final String sCHEMIN_TEXTURE_LABYRINTHE_1 = "Images\\texture-labyrinthe1.png";
        public static final String sCHEMIN_LABYRINTHE_1         = "Labyrinthe\\Labyrinthe1.txt"; 
        
    // ID des images du labyrinthe
        public static final int iINTERSECTION       = 10;        
        public static final int iB                  = 11;
        public static final int iC                  = 12;
        public static final int iD                  = 13;
        public static final int iE                  = 14;
        public static final int iTUNNEL             = 20;
        public static final int iVIDE               = 15;
        public static final int iPACGOMME           = 0;
        public static final int iPACGOMME_SPECIAL   = 16;
        
         
        
    // Caractéristique du labyrinthe
        public static final int iTAILLE_LABYRINTHE_LIGNE    = 31;
        public static final int iTAILLE_LABYRINTHE_COLONNE  = 28;
        public static final int iTAILLE_TILE_WIDTH          = 27;
        public static final int iTAILLE_TILE_HEIGHT         = 25;
        public static final int iCOORDONNE_LIMITE_LABYRINTHE_GAUCHE = -10;
        public static final int iCOORDONNE_LIMITE_LABYRINTHE_DROITE = iTAILLE_TILE_WIDTH * (iTAILLE_LABYRINTHE_COLONNE-1);
        
    // Image Pac-man
        public static final String sCHEMIN_TEXTURE_PACMAN_DROITE    = "Images\\mspacman.jpg";
        public static final String sCHEMIN_TEXTURE_PACMAN_GAUCHE    = "Images\\mspacman_gauche.jpg";
        public static final String sCHEMIN_TEXTURE_PACMAN_HAUT      = "Images\\mspacman_haut.jpg";
        public static final String sCHEMIN_TEXTURE_PACMAN_BAS       = "Images\\mspacman_bas.jpg";
        public static final int iTAILLE_PACMAN_WIDTH                = 47;
        public static final int iTAILLE_PACMAN_HEIGHT               = 44;
        
        
    // Image Fantomes
        public static final String sCHEMIN_TEXTURE_BLINKY       = "Images\\blinky.bmp";
        public static final String sCHEMIN_TEXTURE_CLYDE        = "Images\\clyde.bmp";
        public static final String sCHEMIN_TEXTURE_INKY         = "Images\\inky.bmp";
        public static final String sCHEMIN_TEXTURE_PINKY        = "Images\\pinky.bmp";
        public static final String sCHEMIN_TEXTURE_FRIGHTENED   = "Images\\ghost_frightened.png";
        
    // Behavior fantôme
        public static final int iSCATTER    = 0;
        public static final int iCHASE      = 1;
        public static final int iFRIGHTENED = 2;
        
    // Direction
        public static final int iGAUCHE = 0;
        public static final int iDROITE = 1;
        public static final int iHAUT   = 2;
        public static final int iBAS    = 3;
        
    // Vitesse du jeu 
        public static final float fVITESSE_LENTE    = 0.16f;
        public static final float fVITESSE_NORMALE  = 0.19f;
        public static final float fVITESSE_RAPIDE   = 0.23f;
        
    
    // Coordonnées des coins du labyrinthe
        public static final int iLIGNE_COIN_HAUT_DROIT      = 0;
        public static final int iLIGNE_COIN_HAUT_GAUCHE     = 0;
        public static final int iLIGNE_COIN_BAS_DROIT       = 30;
        public static final int iLIGNE_COIN_BAS_GAUCHE      = 30;
        public static final int iCOLONNE_COIN_HAUT_DROIT    = 27;
        public static final int iCOLONNE_COIN_HAUT_GAUCHE   = 0;
        public static final int iCOLONNE_COIN_BAS_DROIT     = 27;
        public static final int iCOLONNE_COIN_BAS_GAUCHE    = 0;
        
        
    // Timers
        public static int iDureeChase       = 20000;
        public static int iDureeScatter     = 7000;
        public static int iDureeFrightened  = 7000;
        
        
    // Current score  
        public static int iScore  = 0;
        public static int iMAX_PACGOMME_PAR_LEVEL = 230;
        
     // Information tableau de score
        public static String[] iTabNomScore = new String[5];
        public static int[] iTabScore = new int[5];
        
     // Variable pour differencie l'etat
        public static int iPause = 0;
}
