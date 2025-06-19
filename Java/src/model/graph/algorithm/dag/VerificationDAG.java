package model.graph.algorithm.dag;

import java.util.*;

import model.persistence.Competence;
import model.persistence.Necessite;

/**
 * Permet de vérifier que le graphe des compétences est un DAG (pas de cycle).
 * Utilise un parcours en profondeur (DFS) pour la détection de cycle.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class VerificationDAG {

    /**
     * Vérifie si le graphe des compétences (défini par les relations de nécessité) est un DAG.
     * @param competences Liste des compétences existantes.
     * @param necessites Liste des relations de nécessité (arêtes orientées).
     * @return true si le graphe est un DAG, false si un cycle est détecté.
     */
    public boolean isDag(List<Competence> competences, List<Necessite> necessites) {
        // Construction du graphe : chaque compétence pointe vers ses "successeurs"
        Map<Competence, List<Competence>> graphe = new HashMap<>();
        for (Competence c : competences) {
            graphe.put(c, new ArrayList<>());
        }
        for (Necessite n : necessites) {
            Competence prerequis = n.getCompetenceNecessaire();
            Competence cible = n.getLaCompetence();
            if (graphe.containsKey(prerequis)) {
                graphe.get(prerequis).add(cible);
            }
        }

        // États pour le DFS
        Map<Competence, Etat> etats = new HashMap<>();
        for (Competence c : competences) {
            etats.put(c, Etat.BLANC);
        }

        // DFS pour chaque compétence non encore visitée
        for (Competence c : competences) {
            if (etats.get(c) == Etat.BLANC) {
                if (aCycle(c, graphe, etats)) {
                    return false; // Cycle détecté
                }
            }
        }
        return true; // Aucun cycle détecté
    }

    /**
     * États possibles pour le parcours DFS.
     */
    private enum Etat {
        BLANC,  // Non visité
        GRIS,   // En cours d'exploration
        NOIR    // Complètement exploré
    }

    /**
     * Parcours en profondeur pour détecter un cycle à partir d'une compétence.
     * @param c Compétence de départ.
     * @param graphe Graphe des dépendances.
     * @param etats États de visite des compétences.
     * @return true si un cycle est détecté, false sinon.
     */
    private boolean aCycle(Competence c, Map<Competence, List<Competence>> graphe, Map<Competence, Etat> etats) {
        etats.put(c, Etat.GRIS);
        for (Competence voisin : graphe.get(c)) {
            if (etats.get(voisin) == Etat.GRIS) {
                return true; // Cycle trouvé
            }
            if (etats.get(voisin) == Etat.BLANC && aCycle(voisin, graphe, etats)) {
                return true;
            }
        }
        etats.put(c, Etat.NOIR);
        return false;
    }
}