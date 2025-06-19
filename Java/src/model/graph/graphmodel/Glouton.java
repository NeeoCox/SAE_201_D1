package model.graph.graphmodel;

import java.util.Arrays;

/**
 * La classe Glouton implémente un algorithme glouton pour résoudre le problème d'affectation
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Glouton {

    /**
     * Algorithme glouton pour l'affectation de secouristes à des compétences.
     * @param M Matrice d'adjacence où M[i][j] = 1 si le secouriste i possède la compétence j, sinon 0.
     * @param verbose Si true, affiche les détails de l'algorithme (ordre des lignes et colonnes, affectations).
     * @return Un tableau d'entiers où l'index représente la compétence et la valeur à cet index représente le secouriste affecté à cette compétence.
     */
    public static int[] glouton(int[][] M, boolean verbose) {
        int n = M.length;
        int[][] matrice = new int[n][n];
        for (int i = 0; i < n; i++) matrice[i] = Arrays.copyOf(M[i], n);

        int[] ordreLignes = new int[n];
        int[] ordreColonnes = new int[n];
        for (int i = 0; i < n; i++) {
            ordreLignes[i] = i;
            ordreColonnes[i] = i;
        }

        // Calcul du degré de chaque ligne
        int[] L = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                L[i] += matrice[i][j];

        // Tri des lignes par degré croissant
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (L[i] > L[j]) {
                    int tempL = L[i]; L[i] = L[j]; L[j] = tempL;
                    int[] tempM = matrice[i]; matrice[i] = matrice[j]; matrice[j] = tempM;
                    int tempOrdre = ordreLignes[i]; ordreLignes[i] = ordreLignes[j]; ordreLignes[j] = tempOrdre;
                }
            }
        }
        if (verbose) {
            System.out.print("Ordre des lignes : ");
            for (int i = 0; i < n; i++) System.out.print(ordreLignes[i] + " ");
            System.out.println();
        }

        // Tri des colonnes
        for (int j = 0; j < n - 1; j++) {
            int minCol = j;
            for (int k = j + 1; k < n; k++) {
                int i = 0;
                while (i < n && matrice[i][k] == matrice[i][minCol]) i++;
                if (i < n && matrice[i][k] < matrice[i][minCol]) minCol = k;
            }
            if (minCol != j) {
                for (int i = 0; i < n; i++) {
                    int temp = matrice[i][j];
                    matrice[i][j] = matrice[i][minCol];
                    matrice[i][minCol] = temp;
                }
                int tempOrdre = ordreColonnes[j];
                ordreColonnes[j] = ordreColonnes[minCol];
                ordreColonnes[minCol] = tempOrdre;
            }
        }
        if (verbose) {
            System.out.print("Ordre des colonnes : ");
            for (int i = 0; i < n; i++) System.out.print(ordreColonnes[i] + " ");
            System.out.println();
            System.out.println("Matrice d'adjacence M avec colonnes triées dans l'ordre croissant :");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) System.out.print(matrice[i][j] + " ");
                System.out.println();
            }
        }

        int[] S = new int[n];
        Arrays.fill(S, -1);

        for (int i = 0; i < n; i++) {
            int j = 0;
            boolean posValide = false;
            while (j < n && !posValide) {
                if (matrice[i][j] == 1) {
                    boolean estDansS = false;
                    int k = 0;
                    while (k < n && !estDansS) {
                        if (S[k] == j) estDansS = true;
                        k++;
                    }
                    if (!estDansS) {
                        S[i] = j;
                        posValide = true;
                        if (verbose) System.out.println("Ligne " + i + " (secouriste " + ordreLignes[i] + ") affectée à colonne " + j + " (compétence " + ordreColonnes[j] + ")");
                    }
                }
                j++;
            }
            if (!posValide && verbose) {
                System.out.println("Ligne " + i + " (secouriste " + ordreLignes[i] + ") n'a pas pu être affectée.");
            }
        }

        // Remettre l'affectation dans l'ordre des compétences d'origine
        int[] affectation = new int[n];
        Arrays.fill(affectation, -1);
        for (int i = 0; i < n; i++) {
            if (S[i] != -1) {
                affectation[ordreColonnes[S[i]]] = ordreLignes[i];
            }
        }
        return affectation;
    }

    /**
     * Calculer la couverture des compétences à partir de l'affectation.
     * @param affectation Tableau d'entiers où l'index représente la compétence et la valeur à cet index représente le secouriste affecté à cette compétence.
     * @return Le nombre de compétences couvertes par l'affectation.
     */
    public static int couverture(int[] affectation) {
        int c = 0;
        for (int a : affectation) if (a != -1) c++;
        return c;
    }

    /**
     * Méthode de test pour l'algorithme glouton.
     * @param M Matrice d'adjacence où M[i][j] = 1 si le secouriste i possède la compétence j, sinon 0.
     * @param verbose Si true, affiche les détails de l'algorithme.
     */
    public static void test(int[][] M, boolean verbose) {
        long t0 = System.currentTimeMillis();
        int[] affectation = glouton(M, verbose);
        long t1 = System.currentTimeMillis();
        int couverture = couverture(affectation);
        System.out.println("[Glouton] Temps : " + (t1 - t0) + " ms | Compétences couvertes : " + couverture + "/" + M.length);
        System.out.println("[Glouton] Affectation (compétence -> secouriste) : " + Arrays.toString(affectation));
    }

    /**
     * Point d'entrée de l'application pour tester l'algorithme glouton.
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        int[][] M = {
            {0, 1, 1, 0, 0},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 0, 1},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 1, 1}
        };
        System.out.println("Test Glouton sur matrice M :");
        for (int[] ligne : M) {
            System.out.println(Arrays.toString(ligne));
        }
        test(M, true);
    }
}