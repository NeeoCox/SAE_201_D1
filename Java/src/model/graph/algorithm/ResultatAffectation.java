package model.graph.algorithm;

import java.util.List;
import java.util.Map;
import model.persistence.DPS;
import model.persistence.Secouriste;
/**
 * Cette classe représente le résultat d'une affectation de secouristes à des dossiers de prise en charge (DPS).
 * Elle contient une map qui associe chaque DPS à une liste de secouristes qui lui sont affectés.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class ResultatAffectation {
    
    /**
     * Map associant chaque DPS à une liste de Secouristes qui lui sont affectés.
     */
    private final Map<DPS, List<Secouriste>> affectations;
    /**
     * Map associant chaque BesoinUnitaire à un Secouriste qui lui est affecté.
     */
    private final Map<BesoinUnitaire, Secouriste> affectationsUnitaires;

    /**
     * Constructeur de la classe ResultatAffectation.
     * @param affectations Map associant chaque DPS à une liste de Secouristes
     * @param affectationsUnitaires Map associant chaque BesoinUnitaire à un Secouriste
     */
    public ResultatAffectation(Map<DPS, List<Secouriste>> affectations, Map<BesoinUnitaire, Secouriste> affectationsUnitaires) {
        this.affectations = affectations;
        this.affectationsUnitaires = affectationsUnitaires;
    }

    /**
     * Retourne la map des affectations.
     * @return la map associant chaque DPS à une liste de Secouristes
     */
    public Map<DPS, List<Secouriste>> getAffectations() {
        return affectations;
    }

    /**
     * Retourne la map des affectations unitaires.
     * @return la map associant chaque BesoinUnitaire à un Secouriste
     */
    public Map<BesoinUnitaire, Secouriste> getAffectationsUnitaires() {
        return affectationsUnitaires;
    }
}