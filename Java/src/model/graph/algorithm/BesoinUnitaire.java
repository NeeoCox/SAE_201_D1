package model.graph.algorithm;

import model.data.Competence;
import model.data.DPS;

public class BesoinUnitaire {
    private final DPS dps;
    private final Competence competence;

    public BesoinUnitaire(DPS dps, Competence competence) {
        this.dps = dps;
        this.competence = competence;
    }

    public DPS getDps() { return dps; }
    public Competence getCompetence() { return competence; }
}