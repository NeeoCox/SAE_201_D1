package model.graph.algorithm;

import java.util.List;
import model.data.Besoin;
import model.data.DPS;
import model.data.Secouriste;

public interface Affectation {
    ResultatAffectation affecter(List<Secouriste> secouristes, List<DPS> dps, List<Besoin> besoins);
}