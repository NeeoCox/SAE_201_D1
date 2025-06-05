package model.graph;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
public class DAG {
    // Structure du graphe : compétence -> liste des compétences préalables
    private Map<String, List<String>> competencesDep = new HashMap<>();

    private static final String[] COMPETENCES = {"PSE1", "PSE2", "SSA", "VPSP", "CE", "CP", "CO", "PBF", "PBC"};

    public DAG(){

    }

    /**
     * Ajoute une dépendance entre deux compétences 
     * @param requis la compétence requise pour acquérir une autre compétence
     * @param pourAvoir
     */
    public void addDependence(String requis, String pourAvoir){
        if((requis == null || pourAvoir == null) || (requis.isEmpty() || pourAvoir.isEmpty())) {
            throw new IllegalArgumentException("Les compétences ne peuvent pas être nulles");
        }
        else{
            List<String> listeDep = competencesDep.get(requis);
            if(listeDep != null) {    
                competencesDep.put(requis, listeDep);
            }
            else{
                listeDep = new ArrayList<>();
                listeDep.add(pourAvoir);
                competencesDep.put(requis, new ArrayList<>());
            } 
        }
    }

    public void initDep(){
        addDependence("CO", "CP");
        addDependence("CP", "CE");
        addDependence("CE", "PSE2");
        addDependence("PSE2", "PSE1");
        addDependence("SSA", "PSE1");
        addDependence("VPSP", "PSE2");
        addDependence("PBF", "PBC");
    }

    /**
     * renvoie vrai si la compétences est possédée
     * @param competences la compétences à vérifier
     * @return true si la compétence est possédée, false sinon
     */
    public boolean estPossedee(String competences){
        boolean possede = false;
        if(competences == null || competences.isEmpty()) {
            throw new IllegalArgumentException("La compétence ne peut pas être nulle ou vide");
        }
        for(String comp : COMPETENCES){
            if(comp.equals(competences)){
                possede = true;
                break;
            }
        }
        return possede;
    }

    /**
     * Renvoi la liste des compétences requisee pour aquérir une compétence donnée
     * @param competences la compétences pour laquelle on veut connaitre les compétences requises
     * @return la liste des compétences requises pour aquérir la compétence
     */
    public List<String> getCompetencesRequise(String competences){
        List<String> listeRequise;
        if(competences == null || competences.isEmpty()) {
            throw new IllegalArgumentException("La compétence ne peut pas être nulle ou vide");
        }
        listeRequise = competencesDep.get(competences);
        return listeRequise;
    }

    

    public boolean respectDepCompetences(String competences){
        boolean respect = false;
        if(competences == null || competences.isEmpty()) {
            throw new IllegalArgumentException("La compétence ne peut pas être nulle ou vide");
        }
        

        return respect;
    }


    // Vérifie s'il y a un cycle (pas un vrai DAG sinon)
    public boolean hasCycle() {
        Set<String> visited = new HashSet<>();
        Set<String> recStack = new HashSet<>();

        for (String node : graph.keySet()) {
            if (hasCycleUtil(node, visited, recStack)) return true;
        }
        return false;
    }

    private boolean hasCycleUtil(String node, Set<String> visited, Set<String> recStack) {
        if (recStack.contains(node)) return true;
        if (visited.contains(node)) return false;

        visited.add(node);
        recStack.add(node);

        for (String neighbor : graph.get(node)) {
            if (hasCycleUtil(neighbor, visited, recStack)) return true;
        }

        recStack.remove(node);
        return false;
    }

    // Trie topologique : donne l'ordre valide des compétences
    public List<String> topologicalSort() {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                topologicalSortUtil(node, visited, stack);
            }
        }

        List<String> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private void topologicalSortUtil(String node, Set<String> visited, Stack<String> stack) {
        visited.add(node);
        for (String neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }
        stack.push(node);
    }

    public static void main(String[] args) {
        DAG g = new DAG();

        // Ajout des dépendances du graphe (d'après l'image)
        g.addDependency("PSE1", "PSE2");
        g.addDependency("SSA", "PSE2");
        g.addDependency("VPSP", "PSE2");
        g.addDependency("PSE2", "CE");
        g.addDependency("CE", "CP");
        g.addDependency("CP", "CO");
        g.addDependency("PBF", "PBC");

        if (g.hasCycle()) {
            System.out.println("Erreur : le graphe a une boucle (ce n'est pas un DAG)");
        } else {
            System.out.println("Ordre possible pour acquérir les compétences :");
            System.out.println(g.topologicalSort());
        }
    }
}

