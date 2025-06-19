package model.graph.algorithm;

import java.util.Objects;
import model.persistence.Competence;
import model.persistence.DPS;

public class BesoinUnitaire {
    private final DPS dps;
    private final Competence competence;

    public BesoinUnitaire(DPS dps, Competence competence) {
        this.dps = dps;
        this.competence = competence;
    }

    public DPS getDps() { return dps; }
    public Competence getCompetence() { return competence; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BesoinUnitaire that = (BesoinUnitaire) o;
        return Objects.equals(dps, that.dps) && Objects.equals(competence, that.competence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dps, competence);
    }
}