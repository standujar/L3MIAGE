package calculatrice;

public class Addition {
    
    static Liste Add(Liste L1, Liste L2)
{
	Liste Resultat=null,L1cop=L1,L2cop=L2;   // Variable qui permet de garder une copie des Liste placé en paramètre
	String Nombre;                      
	int x;
        boolean Retenue=false;     // Variable qui permet de savoir au chaque passage si il y a une retenu
                
	while(L1cop!=null && L2cop!=null)  // On repète la boucle jusqu'à ce que le Nombre 1 et 2 soit null
	{
            if(Retenue)x=L1cop.tete()+L2cop.tete()+1; // Si il y a une retenue on Additionne simplement mais en ajoutant 1
		
            else x=L1cop.tete()+L2cop.tete(); // Sinon pas d'ajout de 1
		
            Nombre=Integer.toString(x); // On place le résultat de l'opération x (en int) en String dans nombre
            if(Nombre.length()>1) // Si la taille de ce nombre (toujours en String) est supérieur à 1 (donc qu'il y a 2 chiffre)
            {
		Resultat= new Liste(Character.getNumericValue(Nombre.charAt(1)),Resultat); Retenue=true; // On prend le second chiffre et on le place dans la Liste résultat
            }
            else
            {
        	Resultat= new Liste(Character.getNumericValue(Nombre.charAt(0)),Resultat); Retenue=false; // Sinon on prend le premier chiffre (vu qu'il n'y en a qu'un dans ce cas)
            }
            L1cop=L1cop.queue();    // On passe au suivant
            L2cop=L2cop.queue();    // On passe au suivant
	}
	
        if(L1cop==null && L2cop!=null)  // On repète la boucle jusqu'a ce que le Nombre 2 soit null si et seulement si ce nombre est différent de null et que le nombre 1 est null
	{
		while(L2cop!=null)
		{
                    if(Retenue)x=L2cop.tete()+1;
                    
                    else x=L2cop.tete();
                
                    Nombre=Integer.toString(x);
                    if(Nombre.length()>1)
                    {
                        Resultat= new Liste(Character.getNumericValue(Nombre.charAt(1)),Resultat); //
			Retenue=true;
                    }
                    else
                    {
                        Resultat= new Liste(Character.getNumericValue(Nombre.charAt(0)),Resultat);
                        Retenue=false;
                    }
    		L2cop=L2cop.queue();
		}
                
                if(Retenue)Resultat= new Liste(1,Resultat);
	}
	if(L2cop==null && L1cop!=null) // Même chose que le précèdent mais pour le Nombre 1
	{
		while(L1cop!=null)
		{
                    if(Retenue)x=L1cop.tete()+1;

                    else x=L1cop.tete();
			
                    Nombre=Integer.toString(x);
                    if(Nombre.length()>1)
                    {
                        Resultat= new Liste(Character.getNumericValue(Nombre.charAt(1)),Resultat);
                        Retenue=true;
                    }
			else
			{
                            Resultat= new Liste(Character.getNumericValue(Nombre.charAt(0)),Resultat);
                            Retenue=false;
			}
			L1cop=L1cop.queue();		
		}
		if(Retenue)Resultat= new Liste(1,Resultat); // Si il y avait une retenue ajoute le chiffre 1
	}
        return Resultat;
}   
}
