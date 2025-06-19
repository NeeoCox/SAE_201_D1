package model.graph.graphmodel;
import java.util.*;

/**
 * La classe VerifDAG implémente un algorithme pour vérifier si un graphe orienté est un DAG (Directed Acyclic Graph).
 * Elle utilise un parcours en profondeur pour détecter les cycles dans le graphe.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class VerifDAG {

    /**
     * Variable pour représenter les états des sommets lors du parcours 
     */
    private enum Etat {
        BLANC,  // Non visité
        GRIS,   // En cours d'exploration
        NOIR    // Complètement exploré
    }

    private final Map<String, List<String>> graphe = new HashMap<>();
    private final Map<String, Etat> etats = new HashMap<>();

    /**
     * Constructeur de la classe VerifDAG.
     * Initialise le graphe avec les arêtes orientées comme dans la figure 1.
     */
    public VerifDAG() {
        initCompDep();
    }

    /**
     * Point d'entrée de l'application.
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    private void initCompDep() {
        ajouterArete("CO", "CP");
        ajouterArete("CP", "CE");
        ajouterArete("CE", "PSE2");
        ajouterArete("PSE2", "PSE1");
        ajouterArete("SSA", "PSE1");
        ajouterArete("VPSP", "PSE2");
        ajouterArete("PBF", "PBC");
    }

    /**
     * Vérifie si le graphe contient une compétence donnée.
     * @param competence La compétence à vérifier.
     * @return true si la compétence est présente, false sinon.
     */
    public boolean contientCompetence(String competence) {
        return graphe.containsKey(competence);
    }


    /**
     * Ajoute une arête orientée entre deux sommets du graphe.
     * @param depuis Le sommet de départ de l'arête.
     * @param vers Le sommet d'arrivée de l'arête.
     */
    public void ajouterArete(String depuis, String vers) {
        graphe.putIfAbsent(depuis, new ArrayList<>());
        graphe.get(depuis).add(vers);
        graphe.putIfAbsent(vers, new ArrayList<>());
    }

    /**
     * Vérifie si le graphe est un DAG (Directed Acyclic Graph).
     * Utilise un parcours en profondeur pour détecter les cycles.
     * @return true si le graphe est un DAG, false sinon.
     */
    public boolean verifierDAG() {
        // Initialisation : tous les sommets sont blancs
        for (String noeud : graphe.keySet()) {
            etats.put(noeud, Etat.BLANC);
        }

        // On lance une DFS pour chaque sommet non encore visité
        for (String noeud : graphe.keySet()) {
            if (etats.get(noeud) == Etat.BLANC) {
                if (aCycle(noeud)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Réalise un parcours en profondeur pour détécter s'il y a un cycle
     * @param noeud Le sommet actuel à explorer.
     * @return true si un cycle est trouvé, false sinon.
     */
    private boolean aCycle(String noeud) {
        etats.put(noeud, Etat.GRIS);

        for (String voisin : graphe.get(noeud)) {
            if (etats.get(voisin) == Etat.GRIS) {
                return true; // Cycle trouvé
            }
            if (etats.get(voisin) == Etat.BLANC && aCycle(voisin)) {
                return true;
            }
        }

        etats.put(noeud, Etat.NOIR);
        return false;
    }
}
