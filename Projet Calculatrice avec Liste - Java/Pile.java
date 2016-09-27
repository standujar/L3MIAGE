package calculatrice;

public class Pile { // Classe et primite sur la Pile (vu en cours)
    
    private static ListedeListe L;
    
    public Pile()
    {
        L=null;
    }
    
    public static Liste Sommet(Pile P)
    {
        return P.L.tete();
    }
    public static void Depiler(Pile P)
    {
        P.L=P.L.queue();
    }
    
    public static void Empiler(Liste L1, Pile P)
    {
        P.L= new ListedeListe(L1, P.L);
    }
}
