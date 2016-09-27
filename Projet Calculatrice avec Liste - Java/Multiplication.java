package calculatrice;

public class Multiplication {
    
    static Liste Mult(Liste L1, Liste L2) // La multiplication fonctionne de la sorte : On fait l'addition du nombre 2 entre lui même
                                         // jusqu'a ce que le nombre 1 soit égal à 0 (car on le décrémente de 1 a chaque passage de boucle)
{
    Liste L1cop=L1,L2cop=L2, Resultat=L2cop, Temoin=null;
    Temoin= new Liste(1,Temoin);
        while(L1cop.tete()!=1 || Liste.Taille(L1cop)>1)
        {
            L1cop=Soustraction.Del(L1cop,Temoin);
            L1cop=Liste.InverserListe(L1cop);
            Resultat=Addition.Add(Resultat, L2cop);
            Resultat=Liste.InverserListe(Resultat);   
        }
        Resultat=Liste.InverserListe(Resultat);
    return Resultat;   
}  
}
