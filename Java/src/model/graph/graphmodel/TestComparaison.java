package model.graph.graphmodel;

import java.util.Random;

public class TestComparaison {

    /**
     * Génère une matrice binaire aléatoire de taille n
     */
    public static int[][] genererMatrice(int n, double probaUn) {
        int[][] M = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                M[i][j] = rand.nextDouble() < probaUn ? 1 : 0;
        return M;
    }

    public static void main(String[] args) {
        int[][][] casTests = {
            // Cas normal (main de Glouton)
            {
                {0, 1, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 0, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 1, 1}
            },
            // Cas dense (n=11, proba=0.85)
            genererMatrice(11, 0.85),
            // Cas sparse (n=11, proba=0.2)
            genererMatrice(11, 0.2),
            // Cas "piège" (n=6)
            {
                {1, 1, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 0, 1}
            }
        };

        for (int i = 0; i < casTests.length; i++) {
            System.out.println("\n=== Cas de test #" + (i+1) + " ===");
            int[][] M = casTests[i];
            System.out.println("Matrice :");
            for (int[] ligne : M) System.out.println(java.util.Arrays.toString(ligne));
            System.out.println("\n--- Glouton ---");
            Glouton.test(M, false);
            System.out.println("\n--- Exhaustif ---");
            Exhaustive.test(M, false);
        }
    }
}