package MO;

import java.util.*;

public class chefdeprojet extends acteur {

    public chefdeprojet(String n, String p, int id, double s) {
        super(n, p, id, s);
    }

    public int Ajout_precedence(int cible, int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public tache CreerUneTache() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double Set_CoutFinancier(double cout) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public controleur AffecterControleur(int id_projet, controleur c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public responsable AffecterResponsable(projet p, responsable r) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public responsable ChangerResponsable(projet p, responsable r, responsable nr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public controleur ChangerControleur(projet p, controleur c, controleur nc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public tache supprimerTache(tache t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public livrable ajout_livrable(livrable li) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int set_Etat(tache t, int cible) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Date set_DateProjet(Date debut, Date fin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
