public class DAG {
    // Structure du graphe : compétence -> liste des compétences préalables
    private Map<String, List<String>> graph = new HashMap<>();

    public void addDependency(String from, String to) {
        graph.putIfAbsent(from, new ArrayList<>());
        graph.putIfAbsent(to, new ArrayList<>());
        graph.get(from).add(to); // de 'from' vers 'to' (from est requis pour to)
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
        CompetenceGraph g = new CompetenceGraph();

        // Ajout des dépendances du graphe (d'après l'image)
        g.addDependency("PSE1", "PSE2");
        g.addDependency("SSA", "PSE2");
        g.addDependency("VPSP", "PSE2");
        g.addDependency("PSE2", "CE");
        g.addDependency("CE", "CP");
        g.addDependency("CP", "CO");
        g.addDependency("PBF", "PBC");

        if (g.hasCycle()) {
            System.out.println("Erreur : le graphe a une boucle (ce n’est pas un DAG)");
        } else {
            System.out.println("Ordre possible pour acquérir les compétences :");
            System.out.println(g.topologicalSort());
        }
    }
}

