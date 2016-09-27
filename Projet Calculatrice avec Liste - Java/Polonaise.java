package calculatrice;

import java.util.Scanner;

public class Polonaise 
{
    
    static Liste InserPile(int Ope) 
    {
        
        Liste Nombre1, Nombre2, Resultat=null;
        Pile P=null;
                
        Nombre1=Pile.Sommet(P);
        Pile.Depiler(P);
        Nombre2=Pile.Sommet(P);
        Pile.Depiler(P);
        Nombre1=Liste.InverserListe(Nombre1);
        Nombre2=Liste.InverserListe(Nombre2);
        if(Ope==1) Resultat=Addition.Add(Nombre1, Nombre2);
        if(Ope==2) Resultat=Soustraction.Del(Nombre2, Nombre1);
        if(Ope==3) Resultat=Multiplication.Mult(Nombre1, Nombre2);
        if(Ope==4) Resultat=Division.Div(Nombre1, Nombre2);
        Pile.Empiler(Resultat, P);
        
        return Resultat;
    }
   
    static Liste Saisir()   // Fonction permettant de saisir un expression 
    {
        Liste Resultat=null;
        Liste temp=null;
        Pile P=null;
        
        
        String Expression = new Scanner(System.in).nextLine();
        String[] ExpressionSep = Expression.split("\\s+");
        
        for(int i=0; i<ExpressionSep.length; i++) // Boucle permettant de traiter chaque element (Nombre et Opérateur) de l'expression
        {
            String NB=ExpressionSep[i];
            
            if(NB.charAt(0)=='+')
            {
                Resultat=InserPile(1);
            }
            
            if(NB.charAt(0)=='-')
            {
                Resultat=InserPile(2);
            }
            
            if(NB.charAt(0)=='*')
            {
                Resultat=InserPile(3);
            }
            
            if(NB.charAt(0)=='/')
            {
                Resultat=InserPile(4);
            }
            
            if(NB.charAt(0)!='+' && NB.charAt(0)!='-' && NB.charAt(0)!='/' && NB.charAt(0)!='*') // Si l'expression n'est pas un opérateur on Empile le Nombre (Liste) dans la Pile
            {
                for(int j=0; j<NB.length(); j++)
                {
                    char[] c;
                    c=NB.toCharArray();
                    int x=Character.getNumericValue(c[j]);
                    temp= new Liste(x, temp);
                }
                temp=Liste.InverserListe(temp);
                Pile.Empiler(temp, P);
                temp=null;
            }
        }
        
        System.out.println();
        System.out.print("= ");
        return Resultat;
    }
}
