package model.graph.graphmodel;

public class Exhaustive {
    private final int[][] couts;
    private final int n;

    private int[] meilleurePermutation;
    private int meilleurScore = Integer.MIN_VALUE;

    public Exhaustive(int[][] couts) {
        this.couts = couts;
        this.n = couts.length;
        this.meilleurePermutation = new int[n];
    }

    public void executer() {
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = i;
        }

        permuter(permutation, 0);
    }

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

    private void echanger(int[] tab, int i, int j) {
        int tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }

    private int evaluer(int[] permutation) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += couts[i][permutation[i]];
        }
        return total;
    }

    public void afficherResultat() {
        System.out.println("Meilleure affectation (Secouriste -> Competence) :");
        for (int i = 0; i < n; i++) {
            System.out.println("Secouriste " + i + " -> Competence " + meilleurePermutation[i]);
        }
        System.out.println("Score total : " + meilleurScore);
    }

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