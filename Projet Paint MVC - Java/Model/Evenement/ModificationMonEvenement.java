package Model.Evenement;

import Model.MonDessin;
import Model.MonMotif;

/**
 * Cette fonction sert à définir un évènement de type modification, c'est à dire l'action de modification d'un motif par l'utilisateur (ex: déplacer, redim un motif).
 */
public class ModificationMonEvenement extends Evenement
{
 
//========================== Constructeurs =============================//
 
	
	/**
	 * Crée une nouvelle instance de <i>ModificationMonEvenement</i> qui sera un Monclone de <i>evenement</i>. 
	 */
	public ModificationMonEvenement(Evenement evenement)
	{
		super(evenement);
	}
	
	
	/**
	 * Crée une nouvelle instance de <i>ModificationMonEvenement</i> qui sera associée au <i>dessin</i>, qui aura comme motif avant l'execution <i>motiInitial</i> et comme motif après l'execution de l'évènement <i>motifApresEvnement</i>.
	 */
	public ModificationMonEvenement(MonDessin dessin, MonMotif monMotifInitial, MonMotif motifApresMonEvenement)
	{
		super(dessin);
		this.monMotifInitial = monMotifInitial.Monclone();
		this.motifApresMonEvenement = motifApresMonEvenement.Monclone();

		if(this.monMotifInitial != null)
			this.monMotifInitial.setSelectionner(false);
		
		if(this.motifApresMonEvenement != null)
			this.motifApresMonEvenement.setSelectionner(false);
	}
	

 
//======================== Méthodes abstraites =========================//
 	
	
	@Override
	public void annuler()
	{		
		dessin.remplaceMotif(this.motifApresMonEvenement.Monclone(), this.monMotifInitial.Monclone());
	}

	
	@Override
	public void retablir()
	{
		dessin.remplaceMotif(this.monMotifInitial.Monclone(), this.motifApresMonEvenement.Monclone());
	}

	
	@Override
	public Evenement Monclone()
	{
		return new ModificationMonEvenement(this);
	}
	
	
	@Override
	public void execution()
	{
	}
}
