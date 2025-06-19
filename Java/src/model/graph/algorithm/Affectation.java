package model.graph.algorithm;

import java.util.List;

import model.persistence.Besoin;
import model.persistence.DPS;
import model.persistence.Secouriste;
/**
 * Interface représentant une stratégie d'affectation de secouristes à des dossiers de prise en charge (DPS).
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public interface Affectation {
    /**
     * Méthode pour affecter des secouristes à des DPS en fonction des besoins.
     * @param secouristes Liste des secouristes disponibles pour l'affectation.
     * @param dps Liste des dossiers de prise en charge (DPS) à affecter.
     * @param besoins Liste des besoins associés aux DPS, indiquant les compétences requises pour chaque DPS.
     * @return ResultatAffectation contenant les affectations des secouristes aux DPS en fonction des besoins.
     */
    ResultatAffectation affecter(List<Secouriste> secouristes, List<DPS> dps, List<Besoin> besoins);
}