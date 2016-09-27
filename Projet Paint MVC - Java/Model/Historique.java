package Model;

import java.util.Iterator;
import java.util.Stack;

import Model.Evenement.Evenement;

/**
 * Cette fonction sert à gérer l'historique d'un dessin.
 */
public class Historique
{
 
//============================ Variables ===============================//
 
	
	private Stack<Evenement> historiqueAAnnuler = new Stack(); // historique d'évènement annuler
	private Stack<Evenement> historiqueARetablir = new Stack(); // historique d'évènement rétablir
	
	
 
//============================= Méthodes ===============================//
 
	
	/**
	 * Cette fonction nous permet d'ajouter un <i>Evenement</i> dans l'historique d'annulation.
	 */
	public void ajouteMonEvenement(Evenement evenement)
	{
		this.historiqueAAnnuler.add(evenement);
	}
	

	/**
	 * Cette fonction sert à retirer un <i>Evenement</i> dans l'historique d'annulation et de l'ajouter dans l'historique de rétablissement.
	 */
	public Evenement getEvenementPrec()
	{
		Evenement e = this.historiqueAAnnuler.pop();
		this.historiqueARetablir.add(e);
		
		return e;
	}
	
	
	/**
	 * Cette fonction sert à retirer un <i>Evenement</i> dans l'historique de rétablissement et de l'ajouter dans l'historique d'annulation.
	 */
	public Evenement getEvenementSuiv()
	{
		Evenement e = this.historiqueARetablir.pop();
		this.historiqueAAnnuler.add(e);
		
		return e;
	}
	
	
	/**
	 * Cette fonction sert à retirer le dernier <i>Evenement</i> de l'historique d'annulation et de le retourner.
	 */
	public Evenement suppEvenementHistorique()
	{
		return this.historiqueAAnnuler.pop();
	}
	
	
	/**
	 * Cette fonction sert à savoir si l'historique d'annulation est vide.
	 */
	public boolean isHistoriqueEmpty()
	{
		return this.historiqueAAnnuler.isEmpty();
	}
	
	
	/**
	 *  Cette fonction sert à savoir si l'historique de rétablissement est vide.
	 */
	public boolean isHistoriqueRetablirEmpty()
	{
		return this.historiqueARetablir.isEmpty();
	}
	
	
	/**
	 * Cette fonction sert à supprimer l'historique de rétablissement.
	 */
	public void suppHistoriqueARetab()
	{
		this.historiqueARetablir.clear();
	}
	
	
	/**
	 * Cette fonction sert à vérifier si le motif après l'évènement du dernier évènement est égale au motif initial du dernier évènement.
	 */
	public boolean motifsLastEventEgaux()
	{
		return this.historiqueAAnnuler.lastElement().motifsEgaux();
	}
	
	
	@Override
	public String toString()
	{
		Iterator<Evenement> it = this.historiqueAAnnuler.iterator();
		String Letxt = "";
		
		while(it.hasNext())
			Letxt += it.next()+"\n";
		
		return Letxt;
	}
}