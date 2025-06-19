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
     * Constructeur de la classe ResultatAffectation.
     * @param affectations la map associant chaque DPS à une liste de Secouristes qui lui sont affectés
     */
    public ResultatAffectation(Map<DPS, List<Secouriste>> affectations) {
        this.affectations = affectations;
    }

    /**
     * Retourne la map des affectations.
     * @return la map associant chaque DPS à une liste de Secouristes
     */
    public Map<DPS, List<Secouriste>> getAffectations() {
        return affectations;
    }
}