package Model.Evenement;

import Model.MonDessin;
import Model.MonMotif;

/**
 * Cette fonction sert à définir un évènement en général.
 */
public abstract class Evenement
{
 
//============================ Variables ===============================//
 
	
	protected MonMotif monMotifInitial; // motif avant l'execution de l'évènement.
	protected MonMotif motifApresMonEvenement; // motif après l'execution de l'évènement.
	protected MonDessin dessin; // dessin auquel le motif est relié.
	
	
 
//========================== Constructeurs =============================//
 

	/**
	 * Crée une nouvelle instance de <i>Evenement</i> qui sera associé au <i>dessin</i>.
	 */
	public Evenement(MonDessin dessin)
	{
		this.dessin = dessin;
	}
	
	
	/**
	 * une nouvelle instance de <i>Evenement</i> qui sera un Monclone de <i>evenement</i>.
	 */
	public Evenement(Evenement evenement)
	{
		this.dessin = evenement.dessin;
		this.monMotifInitial = evenement.monMotifInitial.Monclone();
		this.motifApresMonEvenement = evenement.motifApresMonEvenement.Monclone();

		if(this.monMotifInitial != null)
			this.monMotifInitial.setSelectionner(false);
		
		if(this.motifApresMonEvenement != null)
			this.motifApresMonEvenement.setSelectionner(false);
	}
	
	
 
//======================== Méthodes abstraites =========================//
 	
	
	/**
	 * Cette fonction nous permet annulation l'état d'un évènement. Ce qui permet d'annuler une action faîte par un utilisateur.
	 */
	public abstract void annuler();
	
	
	/**
	 * Cette fonction nous permet rétablir l'état d'un évènement. Ce qui permet de rétablir une action précédemment annuler.
	 */
	public abstract void retablir();
	
	
	/**
	 * Cette fonction nous permet cloner l'évènement.
	 */
	public abstract Evenement Monclone();
	
	
 
//============================= Méthodes ===============================//
 
	
	/**
	 * Cette fonction nous permet d'execution un évènement.<br/>Ce qui permet de prendre en compte l'action réalisée par l'utilisateur.
	 */
	public void execution()
	{
		this.retablir();
	}
	
	
	/**
	 * Cette fonction sert à vérifier si le motif après l'évènement est égale au motif initial.
	 */
	public boolean motifsEgaux()
	{
		return this.monMotifInitial.equals(this.motifApresMonEvenement);
	}
	
	
	/** 
	 * Cette fonction sert à décrire un objet <i>Evenement</i>.
	 */
	public String toString()
	{
		return this.monMotifInitial+" ====> "+this.motifApresMonEvenement;
	}
	
	
 
//========================= Getters et Setters =========================//
 
	
	/**
	 * Retourne le motif avant l'execution de l'évènement.
	 */
	public MonMotif getMonMotifInitial()
	{
		return this.monMotifInitial;
	}
	

	/**
	 * Cette fonction sert à modifier le motif initial de l'évènement.
	 */
	public void setMonMotifInitial(MonMotif monMotifInitial)
	{
		this.monMotifInitial = monMotifInitial;
	}
	

	/**
	 * Retourne le motif après l'execution de l'évènement.
	 */
	public MonMotif getMotifApresEvenement()
	{
		return this.motifApresMonEvenement;
	}
	

	/**
	 * Cette fonction sert à modifier le motif après l'execution de l'évènement.
	 */
	public void setMotifApresMonEvent(MonMotif motifApresMonEvenement)
	{
		this.motifApresMonEvenement = motifApresMonEvenement;
	}
}
