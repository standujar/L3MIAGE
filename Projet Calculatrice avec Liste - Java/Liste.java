package calculatrice;

public class Liste {    // Fonction primitive des Liste et classe Liste vu en cours
    
	public int valeur;
	public Liste suivant;
	
	public Liste (int entier, Liste reste)
	{
		this.valeur=entier;
		this.suivant=reste;
	}
	public int tete()
	{
		return (this.valeur);
	}
	public Liste queue()
	{
		return (this.suivant);
	}
        
        static void Afficher(Liste L)   // Fonction permettant d'afficher le résultat sous la forme d'un nombre (alors que c'est une Liste d'entier)
        {
	Liste Lcopy=L;
            while(Lcopy!=null)
            {
		System.out.print(Lcopy.tete());
		//if(Lcopy.suivant!=null) System.out.print("->");
		Lcopy=Lcopy.queue();
            }
            System.out.println();
        }
        
        static Liste InverserListe(Liste L) // Fonction permettant d'inverser la Liste
        {
            Liste Inv=null,Lcopy=L;
            while(Lcopy!=null)
                {
                    Inv = new Liste(Lcopy.tete(),Inv);
                    Lcopy=Lcopy.queue();
                }
            return Inv;
        }
        
        static int Taille(Liste L)  // Fonction permettant de connaitre la taille d'une Liste
        {
            int i=0;
            Liste Lcop=L;
            while(Lcop!=null)
            {
                Lcop=Lcop.queue();
                i=i+1;
            }
            return i;
        }
        
        static int Comparateur(Liste L1, Liste L2)  // Fonction permettant de connaître le plus petit "nombre" entre 2 Liste d'entier
        {
            Liste L1cop=L1, L2cop=L2;
            if(Taille(L1cop)>Taille(L2cop)) return 1;
            if(Taille(L1cop)<Taille(L2cop)) return 3;
            
            L1cop=InverserListe(L1cop);
            L2cop=InverserListe(L2cop);

            if (Taille(L1cop)==Taille(L2cop))
            {
                while(L1cop!=null && L2cop!=null)
                {
                    if(L1cop.tete()>L2cop.tete()) return 1;
                    if(L1cop.tete()<L2cop.tete()) return 3;
 
                    if(L1.tete()==L2cop.tete())
                    {
                        L1cop=L1cop.queue();
                        L2cop=L2cop.queue();
                    }
                    else
                    {
                    L1cop=L1cop.queue();
                    L2cop=L2cop.queue();
                    }
                }
            }
            return 2;
        }
        
        static Liste Acceleration(Liste L)  // Fonction non utilisé
        {
            Liste Resultat=null, Lcopy=L;
            int i=0;
            
            if(L==null) return Resultat;
            
            while(Lcopy.suivant!=null)
            {
                Lcopy=Lcopy.suivant;
                i=i+1;
            }
            return Resultat;
        }
}
