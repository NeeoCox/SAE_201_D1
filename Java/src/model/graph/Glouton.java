package model.graph;
public class Glouton {
    public static void main(String[] args) {
        int n = 5;
        int[][] M = {
            {0, 1, 1, 0, 0},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 0, 1},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 1, 1}
        };

        int[] ordreLignes = new int[n];
        int[] ordreColonnes = new int[n];
        for (int i = 0; i < n; i++) {
            ordreLignes[i] = i;
            ordreColonnes[i] = i;
        }

        // Tri des lignes (par degré croissant)
        int[] L = new int[n];
        for (int i = 0; i < n; i++) {
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

                    // Mettre à jour l'ordre des lignes
                    int tempOrdre = ordreLignes[i];
                    ordreLignes[i] = ordreLignes[j];
                    ordreLignes[j] = tempOrdre;
                }
            }
        }

        // Tri des colonnes
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
                // Mettre à jour l'ordre des colonnes
                int tempOrdre = ordreColonnes[j];
                ordreColonnes[j] = ordreColonnes[minCol];
                ordreColonnes[minCol] = tempOrdre;
            }
        }

        
        System.out.print("Ordre des lignes : ");
        for (int i = 0; i < n; i++) System.out.print(ordreLignes[i] + " ");
        System.out.println();
        System.out.print("Ordre des colonnes : ");
        for (int i = 0; i < n; i++) System.out.print(ordreColonnes[i] + " ");
        System.out.println();


        System.out.println("Matrice d'adjacence M avec colonnes triées dans l'ordre croissant :");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }

        int[] S = new int[n];
        for (int i = 0; i <= n - 1; i++) {
            S[i] = -1;
        }

        for (int i = 0; i <= n - 1; i++) {
            int j = 0;
            boolean posValide = false;
            while (j <= n - 1 && !posValide) {
                if (M[i][j] == 1) {
                    boolean estDansS = false;
                    int k = 0;
                    while (k <= n - 1 && !estDansS) {
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

        System.out.println("Sommets S sélectionnés : " + toString(S, n));
    }



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