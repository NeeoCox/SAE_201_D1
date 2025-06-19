package model.graph.graphmodel;

import java.util.Arrays;

/**
 * La classe Exhaustive implémente un algorithme de recherche exhaustive pour trouver la meilleure affectation
 * de secouristes aux compétences, en maximisant la couverture des compétences.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Exhaustive {
    /**
     * Matrice d'adjacence où M[i][j] = 1 si le secouriste i possède la compétence j, sinon 0.
     */
    private final int[][] M;
    /**
     * Nombre de secouristes et de compétences (la matrice est carrée).
     */
    private final int n;
    /**
     * Meilleure affectation trouvée (index de la compétence -> index du secouriste).
     */
    private int[] meilleureAffectation;
    /**
     * Nombre maximum de compétences couvertes par la meilleure affectation trouvée.
     */
    private int meilleureCouverture;
    /**
     * Nombre de solutions testées durant la recherche exhaustive.
     */
    private int nbSolutionsTestees;

    /**
     * Constructeur de la classe Exhaustive.
     * @param M Matrice d'adjacence représentant les compétences des secouristes.
     */
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
     * Échange deux éléments dans un tableau d'entiers.
     * @param tab Le tableau d'entiers dans lequel on souhaite échanger les éléments.
     * @param i L'index du premier élément à échanger.
     * @param j L'index du second élément à échanger.
     */
    private void echanger(int[] tab, int i, int j) {
        int tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }

    /**
     * Affiche le résultat de la recherche exhaustive, y compris la meilleure affectation,
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
}