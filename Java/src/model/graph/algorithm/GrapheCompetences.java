package model.graph.algorithm;

import java.util.*;

import model.graph.algorithm.dag.VerificationDAG;
import model.persistence.Competence;
import model.persistence.Necessite;

/**
 * Gère le graphe des compétences et ses dépendances.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class GrapheCompetences {
    /**
     * Liste des compétences du graphe.
     */
    private final List<Competence> competences;
    /**
     * Liste des nécessités (dépendances) entre les compétences.
     */
    private final List<Necessite> necessites;

    /**
     * Constructeur par défaut qui initialise les listes de compétences et de nécessités.
     */
    public GrapheCompetences() {
        this.competences = new ArrayList<>();
        this.necessites = new ArrayList<>();
    }

    /**
     * Constructeur qui initialise le graphe avec des compétences et des nécessités spécifiques.
     * @param competences Liste de compétences à ajouter au graphe.
     * @param necessites Liste de nécessités à ajouter au graphe.
     */
    public GrapheCompetences(List<Competence> competences, List<Necessite> necessites) {
        this.competences = new ArrayList<>(competences);
        this.necessites = new ArrayList<>(necessites);
    }

    /**
     * Retourne la liste des compétences du graphe.
     * @return Liste des compétences.
     */
    public List<Competence> getCompetences() { 
        return competences; 
    }
    
    /**
     * Retourne la liste des nécessités du graphe.
     * @return Liste des nécessités.
     */
    public List<Necessite> getNecessites() { return necessites; }

    /**
     * Vérifie si le graphe contient une compétence spécifique.
     * @param c La compétence à vérifier.
     * @return true si la compétence est présente dans le graphe, false sinon.
     */
    public boolean contientCompetence(Competence c) {
        return competences.contains(c);
    }

    /**
     * Vérifie si le graphe contient une nécessité spécifique.
     * @param n La nécessité à vérifier.
     * @return true si la nécessité est présente dans le graphe, false sinon.
     */
    public boolean contientNecessite(Necessite n) {
        return necessites.contains(n);
    }

    /**
     * Ajoute une compétence au graphe.
     * @param c La compétence à ajouter.
     */
    public void ajouterCompetence(Competence c) {
        competences.add(c);
    }

    /**
     * Supprime une compétence du graphe.
     * @param c La compétence à supprimer.
     */
    public void supprimerCompetence(Competence c) {
        competences.remove(c);
        // Supprime aussi les liens associés
        necessites.removeIf(n -> n.getLaCompetence().equals(c) || n.getCompetenceNecessaire().equals(c));
    }

    /**
     * Ajoute une nécessité (dépendance) entre deux compétences.
     * @param n La nécessité à ajouter, qui lie une compétence à une autre.
     */
    public void ajouterNecessite(Necessite n) {
        necessites.add(n);
    }

    /**
     * Supprime une nécessité (dépendance) entre deux compétences.
     * @param n La nécessité à supprimer.
     */
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