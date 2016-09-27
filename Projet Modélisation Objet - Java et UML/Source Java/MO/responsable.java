package MO;

public class responsable extends acteur {

    public responsable(String n, String p, int id, double s) {
        super(n, p, id, s);
    }

    public intervenant AffecterInter(tache t, intervenant i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public intervenant ModifierInter(tache t, intervenant i, intervenant in) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public intervenant SupprimerInter(tache t, intervenant i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public tache ChangerEtatTache(tache t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public livrable ChangerEtatLivrable(livrable li) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
