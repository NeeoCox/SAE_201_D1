package model.graph.algorithm;

import java.util.List;
import java.util.Map;

import model.persistence.DPS;
import model.persistence.Secouriste;

public class ResultatAffectation {
    private final Map<DPS, List<Secouriste>> affectations;

    public ResultatAffectation(Map<DPS, List<Secouriste>> affectations) {
        this.affectations = affectations;
    }

    public Map<DPS, List<Secouriste>> getAffectations() {
        return affectations;
    }
}