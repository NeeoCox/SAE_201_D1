package model.graph.algorithm;

import java.util.*;
import model.data.Competence;
import model.data.Necessite;
import model.graph.algorithm.dag.VerificationDAG;

/**
 * Gère le graphe des compétences et ses dépendances.
 */
public class GrapheCompetences {
    private final List<Competence> competences;
    private final List<Necessite> necessites;

    public GrapheCompetences() {
        this.competences = new ArrayList<>();
        this.necessites = new ArrayList<>();
    }

    public GrapheCompetences(List<Competence> competences, List<Necessite> necessites) {
        this.competences = new ArrayList<>(competences);
        this.necessites = new ArrayList<>(necessites);
    }

    public List<Competence> getCompetences() { return competences; }
    public List<Necessite> getNecessites() { return necessites; }

    public boolean contientCompetence(Competence c) {
        return competences.contains(c);
    }
    public boolean contientNecessite(Necessite n) {
        return necessites.contains(n);
    }

    public void ajouterCompetence(Competence c) {
        competences.add(c);
    }

    public void supprimerCompetence(Competence c) {
        competences.remove(c);
        // Supprime aussi les liens associés
        necessites.removeIf(n -> n.getLaCompetence().equals(c) || n.getCompetenceNecessaire().equals(c));
    }

    public void ajouterNecessite(Necessite n) {
        necessites.add(n);
    }

    public void supprimerNecessite(Necessite n) {
        necessites.remove(n);
    }

    /**
     * Vérifie si le graphe courant est un DAG.
     * @return true si le graphe est un DAG, false sinon.
     */
    public boolean estDAG() {
        VerificationDAG verif = new VerificationDAG();
        return verif.isDag(competences, necessites);
    }
}