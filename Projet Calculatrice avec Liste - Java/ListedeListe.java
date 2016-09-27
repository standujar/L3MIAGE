package calculatrice;

public class ListedeListe { //  Fonction primitive Liste de Liste et Classe Liste de Liste
    
        public Liste Liste;
	public ListedeListe suivant;
	
	public ListedeListe (Liste L, ListedeListe reste)
	{
		this.Liste=L;
		this.suivant=reste;
	}
	public Liste tete()
	{
		return (this.Liste);
	}
	public ListedeListe queue()
	{
		return (this.suivant);
	}
    
}
