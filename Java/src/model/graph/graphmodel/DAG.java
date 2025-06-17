package model.graph.graphmodel;
import java.util.*;

public class DAG {

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

    // Constructeur : initialise le graphe avec les relations hiérarchiques
    public DAG() {
        initCompDep();
    }

    // Ajoute les arêtes orientées comme dans la figure 1
    private void initCompDep() {
        ajouterArete("CO", "CP");
        ajouterArete("CP", "CE");
        ajouterArete("CE", "PSE2");
        ajouterArete("PSE2", "PSE1");
        ajouterArete("SSA", "PSE1");
        ajouterArete("VPSP", "PSE2");
        ajouterArete("PBF", "PBC");
    }

    // Vérifie si une compétence est présente dans le graphe
    public boolean contientCompetence(String competence) {
        return graphe.containsKey(competence);
    }


    // Ajoute une arête dirigée dans le graphe
    public void ajouterArete(String depuis, String vers) {
        graphe.putIfAbsent(depuis, new ArrayList<>());
        graphe.get(depuis).add(vers);
        graphe.putIfAbsent(vers, new ArrayList<>());
    }

    // Fonction principale : vérifie si le graphe est un DAG
    public boolean verifierDAG() {
        // Initialisation : tous les sommets sont blancs
        for (String noeud : graphe.keySet()) {
            etats.put(noeud, Etat.BLANC);
        }

        // On lance une DFS pour chaque sommet non encore visité
        for (String noeud : graphe.keySet()) {
            if (etats.get(noeud) == Etat.BLANC) {
                if (aCycle(noeud)) {
                    return false; // Cycle détecté
                }
            }
        }

        return true; // Aucun cycle détecté
    }

    /**
     * Réalise un parcour en profondeur pour détécter s'il y a un cycles
     * @param noeud 
     * @return
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
