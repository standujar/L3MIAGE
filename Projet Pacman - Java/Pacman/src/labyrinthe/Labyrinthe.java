package labyrinthe;

import constantes.Constantes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Labyrinthe 
{
    
    private int[][] itabLabyrintheRender;
    private int[][] itabLabyrintheCollision;
    private SpriteSheet ssTexture;
    
    /**
     * 
     * @param sParCheminFichierLab Chemin d'accès au fichier représentant le labyrinthe
     * @param sParCheminTextureLab Chemin d'accès aux images utilisé par le labyrinthe
     * @throws FileNotFoundException
     * @throws SlickException 
     */
    public Labyrinthe(String sParCheminFichierLab, String sParCheminTextureLab) throws FileNotFoundException, SlickException
    {
        this.initTabLabyrinthe(sParCheminFichierLab);
        ssTexture = new SpriteSheet(sParCheminTextureLab, Constantes.iTAILLE_TILE_WIDTH, Constantes.iTAILLE_TILE_HEIGHT);
    }
    
    /**
     * initialisation du tableau 2D représentant le labyrinthe (chaque numéro correspond à une image (mur, vide, pac-gomme, ...)
     * 
     * @param sParCheminFichierLab Chemin d'accès au fichier servant à initialiser le labyrinthe
     * @throws FileNotFoundException 
     */
    private void initTabLabyrinthe(String sParCheminFichierLab) throws FileNotFoundException 
    {
        itabLabyrintheRender = new int[Constantes.iTAILLE_LABYRINTHE_LIGNE][Constantes.iTAILLE_LABYRINTHE_COLONNE];
        itabLabyrintheCollision = new int[Constantes.iTAILLE_LABYRINTHE_LIGNE][Constantes.iTAILLE_LABYRINTHE_COLONNE];
        
        // on parcour les lignes du fichier et on stocke chaque numéro dans le tableau.
        Scanner scanner = new Scanner(new File(sParCheminFichierLab));
        
            int iLigne, iColonne;
            // On boucle sur chaque champ detecté
            iLigne = 0;
            while (scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
                    for(iColonne = 0; iColonne < Constantes.iTAILLE_LABYRINTHE_COLONNE; iColonne++)
                    {
                        char car = line.charAt(iColonne);
                        if(String.valueOf(car) != null)
                        {
                            switch (String.valueOf(car)) 
                            {
                                case "P":
                                    itabLabyrintheRender[iLigne][iColonne] = Constantes.iINTERSECTION;
                                    break;
                                case "B":
                                    itabLabyrintheRender[iLigne][iColonne] = Constantes.iB;
                                    break;
                                case "C":
                                    itabLabyrintheRender[iLigne][iColonne] = Constantes.iC;
                                    break;
                                case "D":
                                    itabLabyrintheRender[iLigne][iColonne] = Constantes.iD;
                                    break;
                                case "E":
                                    itabLabyrintheRender[iLigne][iColonne] = Constantes.iE;
                                    break;
                                case "T":
                                    itabLabyrintheRender[iLigne][iColonne] = Constantes.iTUNNEL;
                                    break;
                                case "S":
                                    itabLabyrintheRender[iLigne][iColonne] = Constantes.iPACGOMME_SPECIAL;
                                    break;
                                default:
                                    itabLabyrintheRender[iLigne][iColonne] = Character.getNumericValue(car);
                                    break;
                            }
                        }
                        itabLabyrintheCollision[iLigne][iColonne] = itabLabyrintheRender[iLigne][iColonne];
                    }
                
                iLigne++;
            }

        scanner.close();
    }
    
    /**
     * 
     * @param iParLigne
     * @param iParColonne
     * @return la case du tableau représentant le labyrinthe aux coordonnée voulues, 
     * ce qui peut permettre de savoir si la case représente un mur / le vide ou une intersection
     */
    public int getTypeCaseLabyrintheCollision(int iParLigne, int iParColonne)
    {
        return this.itabLabyrintheCollision[iParLigne][iParColonne];
    }
    
    public void setTypeCaseLabyrintheRender(int iParLigne, int iParColonne, int iParValue)
    {
        this.itabLabyrintheRender[iParLigne][iParColonne] = iParValue;
    }
    
    public int getTypeCaseLabyrintheRender(int iParLigne, int iParColonne)
    {
        return this.itabLabyrintheRender[iParLigne][iParColonne];
    }
    
    public void render(Graphics grphcsPar)
    {
        int ligne, colonne;
        for(ligne = 0; ligne < Constantes.iTAILLE_LABYRINTHE_LIGNE; ligne++)
        {
            for(colonne = 0; colonne < Constantes.iTAILLE_LABYRINTHE_COLONNE; colonne++)
            {
                if(this.itabLabyrintheRender[ligne][colonne] == Constantes.iTUNNEL)
                {
                    grphcsPar.drawImage(this.ssTexture.getSubImage(Constantes.iINTERSECTION, 0), colonne*Constantes.iTAILLE_TILE_WIDTH, ligne*Constantes.iTAILLE_TILE_HEIGHT);
                }
                else
                    grphcsPar.drawImage(this.ssTexture.getSubImage(this.itabLabyrintheRender[ligne][colonne], 0), colonne*Constantes.iTAILLE_TILE_WIDTH, ligne*Constantes.iTAILLE_TILE_HEIGHT);
            }
        }
    }
    
}
