package calculatrice;

public class Soustraction {
    
    static Liste Del(Liste L1, Liste L2)    // Fonction permettant d'effectué une soustraction de 2 nombre (Liste)
    {
	Liste Resultat=null; 
        Liste L1cop=L1, L2cop=L2, Lshort=null, Lbig=null;
	int x,i=0,j=0; 
        boolean Retenue=false;
        
        if(Liste.Comparateur(L1cop, L2cop)==1)  // On attribu un pointeur sur le plus grand et le plus petit nombre (Liste)
        {
            Lbig=L1cop;
            Lshort=L2cop;
        }
        if(Liste.Comparateur(L1cop, L2cop)==3 || Liste.Comparateur(L1cop, L2cop)==2)
        {
            Lbig=L2cop;
            Lshort=L1cop;
        }
        
	while(Lbig!=null && Lshort!=null)   // Tant que les 2 Liste ne sont pas null
	{
            if(Retenue) 
            {
                Lshort.valeur=Lshort.tete()+1;  // S'il y a une retenue on addition 1 à la valeur de la tête de la plus petit Liste
            }
            
            if(Lbig.tete()>=Lshort.tete())
            {
                x=Lbig.tete()-Lshort.tete();
                Resultat= new Liste(x,Resultat);
                Retenue=false;
            }
            
            else 
            {    
                
                x=(Lbig.tete()+10)-Lshort.tete();
                Resultat= new Liste(x,Resultat);
                Retenue=true;
            }
  
            Lbig=Lbig.queue();
            Lshort=Lshort.queue();		
	}
	
        if(Lshort==null && Lbig!=null)  // Tant que la plus petit Liste et null et que la Plus grande n'est pas null
	{
		while(Lbig!=null)
                {           
                    if(Retenue) 
                    {
                        if(Lbig.tete()==0) 
                        {
                            Lbig.valeur=10;
                            x=Lbig.tete()-1;
                            Resultat= new Liste(x,Resultat);
                            Retenue=true;
                        }
                        else
                        {
                            x=Lbig.tete()-1;
                            Resultat= new Liste(x,Resultat);
                            Retenue=false; 
                        }
                    }
                    else Resultat= new Liste(Lbig.tete(),Resultat);
                    
                    Lbig=Lbig.queue();		
                }
	}
        
        while(Resultat.queue()!=null && Resultat.tete()==0)
        {
           if(Resultat.tete()==0) Resultat=Resultat.queue();
            
        }
	return Resultat;
}
}
