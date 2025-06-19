package model.graph.algorithm;

import java.util.List;

import model.persistence.Besoin;
import model.persistence.DPS;
import model.persistence.Secouriste;

public interface Affectation {
    ResultatAffectation affecter(List<Secouriste> secouristes, List<DPS> dps, List<Besoin> besoins);
}