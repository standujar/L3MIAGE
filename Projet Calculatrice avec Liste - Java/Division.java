package calculatrice;

public class Division {
    
        static Liste Div(Liste L1, Liste L2)
    {
        Liste L1cop=L1,L2cop=L2, Lshort=null, Lbig=null;
        Liste Resultat= new Liste(0,null);
        Liste Temoin=new Liste(1,null);
        Liste Final=L2cop;
        
        if(Liste.Comparateur(L1cop, Final)==1)
        {
            Lbig=L1cop;
            Lshort=Final;
        }
        if(Liste.Comparateur(L1cop, Final)==3 || Liste.Comparateur(L1cop, Final)==2)
        {
            Lbig=Final;
            Lshort=L1cop;
        }
        
        Liste Ajouter=Lshort;
               
        if(Lshort.tete()==0 && Liste.Taille(Lshort)<2)
        {
            System.err.println("Division par 0 impossible");
            return Resultat;
        }
        
        while(Liste.Comparateur(Lbig, Lshort)==1)
            {
                Lshort=Addition.Add(Lshort,Ajouter);
                Lshort=Liste.InverserListe(Lshort);
                Resultat=Addition.Add(Resultat,Temoin);
                Resultat=Liste.InverserListe(Resultat);
            }
        
        if(Liste.Comparateur(Lbig, Lshort)==3)
        {
            Resultat=Liste.InverserListe(Resultat);
        }
        if(Liste.Comparateur(Lbig, Lshort)==2)
        {
            Resultat=Addition.Add(Resultat,Temoin);
        }
        return Resultat; 
    }  
}
