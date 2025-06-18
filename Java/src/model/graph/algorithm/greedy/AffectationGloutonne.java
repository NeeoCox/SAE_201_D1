package model.graph.algorithm.greedy;

import java.util.*;
import model.graph.algorithm.*;
import model.persistence.*;

/**
 * Implémente l'affectation gloutonne des secouristes aux besoins des DPS.
 * Affecte progressivement chaque besoin au premier secouriste disponible et compétent.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class AffectationGloutonne implements Affectation {

    /**
     * Réalise l'affectation gloutonne des secouristes aux besoins.
     * @param secouristes Liste des secouristes disponibles.
     * @param dps Liste des dispositifs de secours.
     * @param besoins Liste des besoins à couvrir.
     * @return Le résultat de l'affectation.
     */
    @Override
    public ResultatAffectation affecter(List<Secouriste> secouristes, List<DPS> dps, List<Besoin> besoins) {
        // Générer la liste des besoins unitaires
        List<BesoinUnitaire> besoinsUnitaires = genererBesoinsUnitaires(besoins);

        int n = secouristes.size();
        int m = besoinsUnitaires.size();
        int[][] matrice = new int[n][m];

        // Construire la matrice binaire secouristes/besoinsUnitaires
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrice[i][j] = estAffectable(secouristes.get(i), besoinsUnitaires.get(j)) ? 1 : 0;
            }
        }

        // Appliquer le tri des lignes et colonnes comme dans Glouton
        int[] ordreSecouristes = initialiserOrdre(n);
        int[] ordreBesoins = initialiserOrdre(m);
        trierLignesParDegre(matrice, ordreSecouristes);
        trierColonnes(matrice, ordreBesoins);

        // Affecter les secouristes aux besoins dans l'ordre trié
        Map<DPS, List<Secouriste>> affectation = new HashMap<>();
        boolean[] secouristePris = new boolean[n];

        for (int j = 0; j < m; j++) {
            int besoinIdx = ordreBesoins[j];
            for (int i = 0; i < n; i++) {
                int secIdx = ordreSecouristes[i];
                if (!secouristePris[secIdx] && matrice[secIdx][besoinIdx] == 1) {
                    // Affecter secouriste à ce besoin
                    Secouriste s = secouristes.get(secIdx);
                    BesoinUnitaire b = besoinsUnitaires.get(besoinIdx);
                    affectation.computeIfAbsent(b.getDps(), k -> new ArrayList<>()).add(s);
                    secouristePris[secIdx] = true;
                    break;
                }
            }
        }

        return new ResultatAffectation(affectation);
    }

    private static int[] initialiserOrdre(int n) {
        int[] ordre = new int[n];
        for (int i = 0; i < n; i++) {
            ordre[i] = i;
        }
        return ordre;
    }

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
     * Génère la liste des besoins unitaires à partir des besoins globaux.
     * @param besoins Liste des besoins globaux.
     * @return Liste des besoins unitaires.
     */
    private List<BesoinUnitaire> genererBesoinsUnitaires(List<Besoin> besoins) {
        List<BesoinUnitaire> besoinsUnitaires = new ArrayList<>();
        for (Besoin besoin : besoins) {
            DPS dps = besoin.getLeDPS();
            Competence competence = besoin.getLaCompetence();
            int nombre = besoin.getNombre();
            for (int i = 0; i < nombre; i++) {
                besoinsUnitaires.add(new BesoinUnitaire(dps, competence));
            }
        }
        return besoinsUnitaires;
    }

    /**
     * Vérifie si un secouriste possède la compétence requise pour un besoin.
     * @param s Le secouriste à tester.
     * @param b Le besoin unitaire à couvrir.
     * @return true si le secouriste possède la compétence, false sinon.
     */
    private boolean estAffectable(Secouriste s, BesoinUnitaire b) {
        if (s == null || b == null) return false;
        for (Possede p : s.getPossessions()) {
            if (p.getLaCompetence().equals(b.getCompetence())) {
                return true;
            }
        }
        return false;
    }
}