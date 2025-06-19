package model.graph.algorithm;

import model.persistence.Competence;
import model.persistence.DPS;

/**
 * Représente un besoin unitaire dans le système, associant un DPS (Dossier de Prise en Charge) à une compétence spécifique.
 * Cette classe est utilisée pour gérer les besoins individuels de compétences pour chaque DPS.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class BesoinUnitaire {
    /**
     * Dossier de Prise en Charge (DPS) associé à ce besoin unitaire.
     */
    private final DPS dps;
    /**
     * Compétence requise pour ce besoin unitaire.
     */
    private final Competence competence;

    /**
     * Constructeur de la classe BesoinUnitaire.
     * @param dps Dossier de Prise en Charge (DPS) associé à ce besoin unitaire.
     * @param competence Compétence requise pour ce besoin unitaire.
     */
    public BesoinUnitaire(DPS dps, Competence competence) {
        this.dps = dps;
        this.competence = competence;
    }

    /**
     * Retourne le Dossier de Prise en Charge (DPS) associé à ce besoin unitaire.
     * @return Dossier de Prise en Charge (DPS) associé à ce besoin unitaire.
     */
    public DPS getDps() { 
        return dps; 
    }
    /**
     * Retourne la compétence requise pour ce besoin unitaire.
     * @return  Compétence requise pour ce besoin unitaire.
     */
    public Competence getCompetence() { 
        return competence; 
    }
}