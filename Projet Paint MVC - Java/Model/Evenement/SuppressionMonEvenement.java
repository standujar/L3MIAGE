package Model.Evenement;

import Model.MonDessin;
import Model.MonMotif;

/**
 * Cette fonction sert à définir un évènement de type suppression, c'est à dire l'action de suppression d'un motif par l'utilisateur.
 */
public class SuppressionMonEvenement extends Evenement
{
 
//========================== Constructeurs =============================//
 
	
	/**
	 * Crée une nouvelle instance de <i>SuppressionMonEvenement</i> qui sera un Monclone de <i>evenement</i>. 
	 * 
	 * @param evenement L'évènement
	 */
	public SuppressionMonEvenement(Evenement evenement)
	{
		super(evenement);
	}
	
	
	/**
	 * Crée une nouvelle instance de <i>SuppressionMonEvenement</i> qui sera associée au <i>dessin</i>, qui aura comme motif avant l'execution <i>motiInitial</i>.
	 * 
	 * @param dessin MonDessin auquel le motif est relié
	 * @param monMotifInitial MonMotif avant l'execution de l'évènement.
	 */
	public SuppressionMonEvenement(MonDessin dessin, MonMotif monMotifInitial)
	{
		super(dessin);
		this.monMotifInitial = monMotifInitial.Monclone();

		if(this.monMotifInitial != null)
			this.monMotifInitial.setSelectionner(false);
	}
	

 
//======================== Méthodes abstraites =========================//
 	
	
	@Override
	public void annuler()
	{		
		dessin.ajouteMotif(this.monMotifInitial.Monclone());
	}
	

	@Override
	public void retablir()
	{
		dessin.supprimerMonMotif(this.monMotifInitial.Monclone());
	}


	@Override
	public Evenement Monclone()
	{
		return new SuppressionMonEvenement(this);
	}
}
