package calculatrice;

import java.util.Scanner;

public class Choix {
    
    static Liste Stocker(Liste L)   // Fonction pour Placer un String saisi en paramètre dans une Liste d'entier unique (exemple : 521 donnera 1,2,5)
{
	String Nombre = new Scanner(System.in).next();
	char[] c;
	c=Nombre.toCharArray();

	for (int i=0; i<Nombre.length(); i++)
	{
		int x=Character.getNumericValue(c[i]) ; 
		L= new Liste(x, L);
	}
	return L;
}
    
    static Liste Operation()    // Fonction Permetant d'effectuer une opération Addition, Soustration, Multiplication ou Division selon un opérateur tapé (+,-,* ou /)
    {
        Liste Resultat=null;
        Liste Nombre1=null, Nombre2=null;

        Nombre1=Stocker(Nombre1);
        String Operateur = new Scanner(System.in).next();
        Nombre2=Stocker(Nombre2);
        Trait(Nombre1,Nombre2);
        
        if(Operateur.charAt(0)=='+') return Resultat=Addition.Add(Nombre1, Nombre2);

        if(Operateur.charAt(0)=='-') return Resultat=Soustraction.Del(Nombre1, Nombre2);

        if(Operateur.charAt(0)=='*') return Resultat=Multiplication.Mult(Nombre1, Nombre2);
        
        if(Operateur.charAt(0)=='/') return Resultat=Division.Div(Nombre2,Nombre1);
        
        else 
        {
            System.err.println("Parametre: '"+Operateur+"' inconnu");
            System.err.println("Recommencez");
            return Operation();
        }
    }
    
    static void Trait(Liste L1, Liste L2)   // Fonction permettant d'agir sur le visuel affichant une barre sous l'opération et de modifier sa taille
    {                                       // selon la taille du nombre le plus grand
        Liste L1cop=L1, L2cop=L2;
        int L1taille,L2taille, i;
        L1taille=Liste.Taille(L1cop);
        L2taille=Liste.Taille(L2cop);
        if(L1taille>=L2taille)
        {
            while(L1taille>0)
            {
                System.out.print("_");
                L1taille=L1taille-1;
            }
        }
        else 
        {
            while(L2taille>0) 
            {
                System.out.print("_");
                L2taille=L2taille-1;
            }
        }
        System.out.println();
    }
}
