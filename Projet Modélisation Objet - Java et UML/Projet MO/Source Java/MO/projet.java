package MO;

import java.util.*;

public class projet {

    protected int id_projet;
    private String nomProjet;
    public int nombreTache;
    private double coutFinancier;
    private Date DateDebut;
    private Date DateFin;
    private int TempsTotal;
    private List<tache> taches;

    public projet(int idp, String nomp, int nmtache, double cf, Date dd, Date df, int tempstot) {
    }

    private boolean VerifPrecedence() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void MaJ() {
    }

    public boolean GenererPlanning() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
