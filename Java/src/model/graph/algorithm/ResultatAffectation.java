package model.graph.algorithm;

import java.util.List;
import java.util.Map;
import model.persistence.DPS;
import model.persistence.Secouriste;

public class ResultatAffectation {
    private final Map<DPS, List<Secouriste>> affectations;
    private final Map<BesoinUnitaire, Secouriste> affectationsUnitaires;

    public ResultatAffectation(Map<DPS, List<Secouriste>> affectations, Map<BesoinUnitaire, Secouriste> affectationsUnitaires) {
        this.affectations = affectations;
        this.affectationsUnitaires = affectationsUnitaires;
    }

    public Map<DPS, List<Secouriste>> getAffectations() {
        return affectations;
    }

    public Map<BesoinUnitaire, Secouriste> getAffectationsUnitaires() {
        return affectationsUnitaires;
    }
}