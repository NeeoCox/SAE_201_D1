package model.graph.algorithm.greedy;

import java.util.*;
import model.graph.algorithm.*;
import model.persistence.*;

/**
 * Implémente l'affectation gloutonne des secouristes aux besoins des DPS.
 * Affecte progressivement chaque besoin au secouriste disponible et compétent le plus contraint.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class AffectationGloutonne implements Affectation {

    Map<BesoinUnitaire, Secouriste> affectationUnitaires = new HashMap<>();

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

        // Pour chaque besoin unitaire, on garde la liste des secouristes qui peuvent le satisfaire
        Map<BesoinUnitaire, List<Secouriste>> mapBesoinSecouristes = new HashMap<>();
        for (BesoinUnitaire b : besoinsUnitaires) {
            List<Secouriste> possibles = new ArrayList<>();
            for (Secouriste s : secouristes) {
                if (estAffectable(s, b)) {
                    possibles.add(s);
                }
            }
            mapBesoinSecouristes.put(b, possibles);
        }

        // On trie les besoins unitaires par nombre croissant de secouristes pouvant les satisfaire
        besoinsUnitaires.sort(Comparator.comparingInt(b -> mapBesoinSecouristes.get(b).size()));

        // Pour chaque secouriste, on garde la liste des besoins unitaires qu'il peut satisfaire (et qui restent à pourvoir)
        Map<Secouriste, Set<BesoinUnitaire>> mapSecouristeBesoins = new HashMap<>();
        for (Secouriste s : secouristes) {
            Set<BesoinUnitaire> possibles = new HashSet<>();
            for (BesoinUnitaire b : besoinsUnitaires) {
                if (estAffectable(s, b)) {
                    possibles.add(b);
                }
            }
            mapSecouristeBesoins.put(s, possibles);
        }

        // Pour chaque DPS, la liste des secouristes affectés
        Map<DPS, List<Secouriste>> affectation = new HashMap<>();
        Set<Secouriste> secouristesAffectes = new HashSet<>();

        for (BesoinUnitaire besoin : besoinsUnitaires) {
            // On ne considère que les secouristes non affectés qui peuvent satisfaire ce besoin
            List<Secouriste> candidats = new ArrayList<>();
            for (Secouriste s : mapBesoinSecouristes.get(besoin)) {
                if (!secouristesAffectes.contains(s)) {
                    candidats.add(s);
                }
            }
            if (candidats.isEmpty()) {
                // Aucun secouriste disponible pour ce besoin, on continue
                continue;
            }
            // On choisit le secouriste qui a le moins de possibilités restantes (le plus contraint)
            Secouriste choisi = candidats.get(0);
            int minPossibilites = mapSecouristeBesoins.get(choisi).size();
            for (Secouriste s : candidats) {
                int nbPoss = mapSecouristeBesoins.get(s).size();
                if (nbPoss < minPossibilites) {
                    choisi = s;
                    minPossibilites = nbPoss;
                }
            }
            // On affecte le secouriste choisi à ce besoin
            affectation.computeIfAbsent(besoin.getDps(), k -> new ArrayList<>()).add(choisi);
            affectationUnitaires.put(besoin, choisi);
            secouristesAffectes.add(choisi);

            // On met à jour les possibilités des autres secouristes (on retire ce besoin de leurs listes)
            for (Secouriste s : secouristes) {
                mapSecouristeBesoins.get(s).remove(besoin);
            }
        }

        return new ResultatAffectation(affectation, affectationUnitaires);
    }

    /**
     * Initialise un tableau d'entiers représentant l'ordre des lignes ou des colonnes.
     * @param n Nombre de lignes ou de colonnes.
     * @return Un tableau d'entiers de taille n, initialisé avec les indices de 0 à n-1.
     */
    private static int[] initialiserOrdre(int n) {
        int[] ordre = new int[n];
        for (int i = 0; i < n; i++) {
            ordre[i] = i;
        }
        return ordre;
    }

    /**
     * Trie les lignes de la matrice par degré (somme des éléments de chaque ligne).
     * Met à jour l'ordre des lignes dans le tableau ordreLignes.
     * @param M La matrice à trier.
     * @param ordreLignes Tableau qui stocke l'ordre des lignes après tri.
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
     * Trie les colonnes de la matrice en fonction des valeurs des lignes.
     * @param M La matrice à trier.
     * @param ordreColonnes Tableau qui stocke l'ordre des colonnes après tri.
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