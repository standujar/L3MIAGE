package calculatrice;

public class Calculatrice 
{
    public static void main(String[] args) {
        
        Liste Resultat;
        
        Resultat=Choix.Operation();
        //Resultat=Polonaise.Saisir();
        
        Liste.Afficher(Resultat);
        System.out.println();
        
        
    }
}

