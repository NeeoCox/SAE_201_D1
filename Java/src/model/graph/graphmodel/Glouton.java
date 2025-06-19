package model.graph.graphmodel;
/**
 * La classe Glouton implémente un algorithme glouton pour sélectionner des sommets
 * dans un graphe représenté par une matrice d'adjacence.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Glouton {

    /**
     * Point d'entrée de l'application.
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        int n = 5;
        int[][] M = {
            {0, 1, 1, 0, 0},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 0, 1},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 1, 1}
        };

        int[] ordreLignes = initialiserOrdre(n);
        int[] ordreColonnes = initialiserOrdre(n);

        trierLignesParDegre(M, ordreLignes);
        trierColonnes(M, ordreColonnes);

        afficherOrdre("lignes", ordreLignes);
        afficherOrdre("colonnes", ordreColonnes);
        afficherMatrice(M);

        int[] S = selectionnerSommets(M);

        System.out.println("Sommets S sélectionnés : " + toString(S, n));
    }

    /**
     * Initialise un tableau d'entiers de taille n avec les indices de 0 à n-1.
     * @param n La taille du tableau.
     * @return Un tableau d'entiers initialisé.
     */
    private static int[] initialiserOrdre(int n) {
        int[] ordre = new int[n];
        for (int i = 0; i < n; i++) {
            ordre[i] = i;
        }
        return ordre;
    }

    /**
     * Trie les lignes de la matrice M par degré croissant et met à jour l'ordre des lignes.
     * @param M La matrice d'adjacence.
     * @param ordreLignes Le tableau qui stocke l'ordre des lignes après le tri.
     */
    private static void trierLignesParDegre(int[][] M, int[] ordreLignes) {
        int n = M.length;
        int[] L = new int[n];
        for (int i = 0; i < n; i++) {
            L[i] = 0;
            for (int j = 0; j < n; j++) {
                L[i] += M[i][j];
            }
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (L[i] > L[j]) {
                    int tempL = L[i];
                    L[i] = L[j];
                    L[j] = tempL;

                    int[] tempM = M[i];
                    M[i] = M[j];
                    M[j] = tempM;

                    int tempOrdre = ordreLignes[i];
                    ordreLignes[i] = ordreLignes[j];
                    ordreLignes[j] = tempOrdre;
                }
            }
        }
    }

    /**
     * Trie les colonnes de la matrice M par ordre croissant en utilisant le premier élément non nul de chaque colonne.
     * @param M La matrice d'adjacence.
     * @param ordreColonnes Le tableau qui stocke l'ordre des colonnes après le tri.
     */
    private static void trierColonnes(int[][] M, int[] ordreColonnes) {
        int n = M.length;
        for (int j = 0; j < n - 1; j++) {
            int minCol = j;
            for (int k = j + 1; k < n; k++) {
                int i = 0;
                while (i < n && M[i][k] == M[i][minCol]) {
                    i++;
                }
                if (i < n && M[i][k] < M[i][minCol]) {
                    minCol = k;
                }
            }
            if (minCol != j) {
                for (int i = 0; i < n; i++) {
                    int temp = M[i][j];
                    M[i][j] = M[i][minCol];
                    M[i][minCol] = temp;
                }
                int tempOrdre = ordreColonnes[j];
                ordreColonnes[j] = ordreColonnes[minCol];
                ordreColonnes[minCol] = tempOrdre;
            }
        }
    }

    /**
     * Affiche l'ordre des lignes ou des colonnes.
     * @param nom Le nom de l'ordre (lignes ou colonnes).
     * @param ordre Le tableau contenant l'ordre des indices.
     */
    private static void afficherOrdre(String nom, int[] ordre) {
        System.out.print("Ordre des " + nom + " : ");
        for (int i = 0; i < ordre.length; i++) {
            System.out.print(ordre[i] + " ");
        }
        System.out.println();
    }

    /**
     * Affiche la matrice d'adjacence M avec les colonnes triées dans l'ordre croissant.
     * @param M La matrice d'adjacence.
     */
    private static void afficherMatrice(int[][] M) {
        System.out.println("Matrice d'adjacence M avec colonnes triées dans l'ordre croissant :");
        for (int[] row : M) {
            for (int j = 0; j < row.length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Sélectionne les sommets S dans la matrice d'adjacence M en suivant l'algorithme glouton.
     * @param M La matrice d'adjacence.
     * @return Un tableau contenant les indices des sommets sélectionnés.
     */
    private static int[] selectionnerSommets(int[][] M) {
        int n = M.length;
        int[] S = new int[n];
        for (int i = 0; i < n; i++) {
            S[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            int j = 0;
            boolean posValide = false;
            while (j < n && !posValide) {
                if (M[i][j] == 1) {
                    boolean estDansS = false;
                    int k = 0;
                    while (k < n && !estDansS) {
                        if (S[k] == j) {
                            estDansS = true;
                        }
                        k++;
                    }
                    if (!estDansS) {
                        S[i] = j;
                        posValide = true;
                    }
                }
                j++;
            }
        }
        return S;
    }

    /**
     * Convertit un tableau d'entiers en une chaîne de caractères.
     * @param tab Le tableau d'entiers à convertir.
     * @param length La longueur du tableau à considérer.
     * @return Une chaîne de caractères représentant le tableau.
     */
    public static String toString(int[] tab, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(tab[i]);
            if (i < length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}