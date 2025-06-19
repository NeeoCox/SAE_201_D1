package model.graph.graphmodel;

import java.util.Arrays;

/**
 * La classe Exhaustive résout un problème d'affectation de type "assignment problem"
 * en utilisant une recherche exhaustive de toutes les permutations possibles.
 * 
 * Chaque secouriste (ligne) doit être affecté à une compétence (colonne) de sorte à maximiser
 * le score total, où le score est défini par la matrice de coûts fournie.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Exhaustive {

    private final int[][] M;
    private final int n;
    private int[] meilleureAffectation;
    private int meilleureCouverture;
    private int nbSolutionsTestees;

    public Exhaustive(int[][] M) {
        this.M = M;
        this.n = M.length;
        this.meilleureAffectation = new int[n];
        this.meilleureCouverture = -1;
        this.nbSolutionsTestees = 0;
    }

    /**
     * Lance la recherche exhaustive de la meilleure affectation
     */
    public void executer(boolean verbose) {
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) permutation[i] = i;
        meilleureCouverture = -1;
        permuter(permutation, 0, verbose);
    }

    /**
     * Génère toutes les permutations possibles de secouristes pour les compétences
     */
    private void permuter(int[] perm, int index, boolean verbose) {
        if (index == n) {
            nbSolutionsTestees++;
            int[] affectation = new int[n];
            Arrays.fill(affectation, -1);
            boolean[] secouristePris = new boolean[n];
            int couverture = 0;
            for (int j = 0; j < n; j++) {
                int i = perm[j];
                if (M[i][j] == 1 && !secouristePris[i]) {
                    affectation[j] = i;
                    secouristePris[i] = true;
                    couverture++;
                }
            }
            if (verbose) {
                System.out.println("Solution testée : " + Arrays.toString(affectation) + " | Couverture : " + couverture);
            }
            if (couverture > meilleureCouverture) {
                meilleureCouverture = couverture;
                meilleureAffectation = affectation.clone();
                if (verbose) {
                    System.out.println("  -> Nouvelle meilleure solution !");
                }
            }
            return;
        }
        for (int i = index; i < n; i++) {
            echanger(perm, i, index);
            permuter(perm, index + 1, verbose);
            echanger(perm, i, index);
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
        System.out.println("[Exhaustif] Meilleure affectation (compétence -> secouriste) : " + Arrays.toString(meilleureAffectation));
        System.out.println("[Exhaustif] Compétences couvertes : " + meilleureCouverture + "/" + n);
        System.out.println("[Exhaustif] Nombre de solutions testées : " + nbSolutionsTestees);
    }

    /**
     * Lance un test exhaustif sur une matrice, affiche le temps et la couverture
     */
    public static void test(int[][] M, boolean verbose) {
        long t0 = System.currentTimeMillis();
        Exhaustive ex = new Exhaustive(M);
        ex.executer(verbose);
        long t1 = System.currentTimeMillis();
        System.out.println("[Exhaustif] Temps : " + (t1 - t0) + " ms");
        ex.afficherResultat();
    }

    /**
     * Exemple d'utilisation
     */
    public static void main(String[] args) {
        int[][] M = {
            {0, 1, 1, 0, 0},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 0, 1},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 1, 1}
        };
        System.out.println("\n\n\nTest Exhaustif sur matrice M :");
        for (int[] ligne : M) {
            System.out.println(Arrays.toString(ligne));
        }
        test(M, true);
    }
}