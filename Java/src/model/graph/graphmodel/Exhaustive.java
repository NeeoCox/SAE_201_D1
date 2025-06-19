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
    /**
     * Matrice des coûts où couts[i][j] représente le coût d'affecter le secouriste i à la compétence j.
     */
    private final int[][] couts;

    /**
     * Matrice de compatibilité où M[i][j] = 1 si le secouriste i possède la compétence j, sinon 0.
     */
    private final int[][] M;
    /**
     * Nombre de secouristes (ou compétences) dans la matrice.
     */
    private final int n;
    /**
     * Meilleure affectation trouvée, où meilleureAffectation[j] = i signifie que le secouriste i est affecté à la compétence j.
     */
    private int[] meilleureAffectation;
    /**
     * Meilleure couverture trouvée, c'est-à-dire le nombre de compétences couvertes par l'affectation.
     */
    private int meilleureCouverture;
    /**
     * Nombre de solutions testées durant la recherche exhaustive.
     */
    private int nbSolutionsTestees;

    /**
     * Constructeur de la classe Exhaustive.
     * @param M Matrice de compatibilité où M[i][j] = 1 si le secouriste i possède la compétence j, sinon 0.
     */
    public Exhaustive(int[][] M) {
        this.M = M;
        this.couts = M;
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
        for (int i = 0; i < n; i++) {
            permutation[i] = i;
        }

        permuter(permutation, 0);
    }

    /**
     * Génère toutes les permutations possibles de secouristes pour les compétences
     */
    private void permuter(int[] perm, int index, boolean verbose) {
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