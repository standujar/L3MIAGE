package Model.Evenement;

import Model.MonDessin;
import Model.MonMotif;


/**
 * Cette fonction sert à définir un évènement de type création, c'est à dire l'action de création d'un motif par l'utilisateur.
 */
public class CreationMonEvenement extends Evenement
{
 
//========================== Constructeurs =============================//
 
	
	/**
	 * une nouvelle instance de <i>CreationMonEvenement</i> qui sera un clone de <i>evenement</i>. 
	 */
	public CreationMonEvenement(Evenement evenement)
	{
		super(evenement);
	}
	
	
	/**
	 * Crée une nouvelle instance de <i>CreationMonEvenement</i> qui sera associée au <i>dessin</i> et qui aura comme motif après l'execution de l'évènement <i>motifApresEvnement</i>.
	 */
	public CreationMonEvenement(MonDessin dessin, MonMotif motifApresMonEvenement)
	{
		super(dessin);
		this.motifApresMonEvenement = motifApresMonEvenement.Monclone();
		
		if(this.motifApresMonEvenement != null)
			this.motifApresMonEvenement.setSelectionner(false);
	}
	
	
 
//======================== Méthodes abstraites =========================//
 	
	
	@Override
	public void annuler()
	{		
		this.dessin.supprimerMonMotif(this.motifApresMonEvenement.Monclone());
	}

	
	@Override
	public void retablir()
	{
		this.dessin.ajouteMotif(this.motifApresMonEvenement.Monclone());
	}


	@Override
	public Evenement Monclone()
	{
		return new CreationMonEvenement(this);
	}	
}
