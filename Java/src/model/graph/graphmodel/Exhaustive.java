package model.graph.graphmodel;

/**
 * La classe Exhaustive résout un problème d'affectation de type "assignment problem"
 * en utilisant une recherche exhaustive de toutes les permutations possibles.
 * 
 * Chaque secouriste (ligne) doit être affecté à une compétence (colonne) de sorte à maximiser
 * le score total, où le score est défini par la matrice de coûts fournie.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Exhaustive {
    /**
     * Matrice des coûts où couts[i][j] représente le coût d'affecter le secouriste i à la compétence j.
     */
    private final int[][] couts;
    /**
     * Nombre de secouristes (et compétences, supposées égales).
     */
    private final int n;

    /**
     * Meilleure permutation trouvée pour maximiser le score.
     */
    private int[] meilleurePermutation;
    /**
     * Score total de la meilleure permutation trouvée.
     */
    private int meilleurScore = Integer.MIN_VALUE;

    /**
     * Constructeur de la classe Exhaustive.
     * @param couts Matrice des coûts pour l'affectation des secouristes aux compétences.
     */
    public Exhaustive(int[][] couts) {
        this.couts = couts;
        this.n = couts.length;
        this.meilleurePermutation = new int[n];
    }

    /**
     * Exécute la recherche exhaustive pour trouver la meilleure affectation
     */
    public void executer() {
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = i;
        }

        permuter(permutation, 0);
    }

    /**
     * Génère toutes les permutations possibles de la liste des secouristes
     * et évalue chacune d'elles pour trouver la meilleure affectation.
     * @param permutation Tableau représentant la permutation actuelle des secouristes.
     * @param index Index actuel dans la permutation.
     */
    private void permuter(int[] permutation, int index) {
        if (index == n) {
            int score = evaluer(permutation);
            if (score > meilleurScore) {
                meilleurScore = score;
                meilleurePermutation = permutation.clone();
            }
            return;
        }

        for (int i = index; i < n; i++) {
            echanger(permutation, i, index);
            permuter(permutation, index + 1);
            echanger(permutation, i, index); // backtrack
        }
    }

    /**
     * Échange les éléments à deux indices donnés dans le tableau.
     * @param tab Tableau dans lequel les éléments seront échangés.
     * @param i Indice du premier élément à échanger.
     * @param j Indice du second élément à échanger.
     */
    private void echanger(int[] tab, int i, int j) {
        int tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }

    /**
     * Évalue le score total d'une permutation donnée en fonction de la matrice des coûts.
     * @param permutation Tableau représentant une permutation des secouristes.
     * @return Le score total de la permutation.
     */
    private int evaluer(int[] permutation) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += couts[i][permutation[i]];
        }
        return total;
    }

    /**
     * Affiche le résultat de la meilleure affectation trouvée.
     */
    public void afficherResultat() {
        System.out.println("Meilleure affectation (Secouriste -> Competence) :");
        for (int i = 0; i < n; i++) {
            System.out.println("Secouriste " + i + " -> Competence " + meilleurePermutation[i]);
        }
        System.out.println("Score total : " + meilleurScore);
    }

    /**
     * Point d'entrée principal pour tester la classe Exhaustive.
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        int[][] couts = {
            {5, 2, 1, 3, 4},
            {1, 3, 2, 2, 1},
            {3, 1, 4, 1, 3},
            {2, 3, 1, 2, 3},
            {4, 1, 2, 3, 1}
        };

        Exhaustive ex = new Exhaustive(couts);
        ex.executer();
        ex.afficherResultat();
    }
}