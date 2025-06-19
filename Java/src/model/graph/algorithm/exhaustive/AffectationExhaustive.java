package model.graph.algorithm.exhaustive;

import java.util.*;
import model.graph.algorithm.*;
import model.persistence.*;

/**
 * Implémente l'affectation exhaustive des secouristes aux besoins des DPS.
 * Explore toutes les permutations possibles pour maximiser la couverture des besoins.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class AffectationExhaustive implements Affectation {

    /**
     * Réalise l'affectation exhaustive des secouristes aux besoins.
     * @param secouristes Liste des secouristes disponibles.
     * @param dps Liste des dispositifs de secours.
     * @param besoins Liste des besoins à couvrir.
     * @return Le résultat de l'affectation optimale.
     */
    @Override
    public ResultatAffectation affecter(List<Secouriste> secouristes, List<DPS> dps, List<Besoin> besoins) {
        List<BesoinUnitaire> besoinsUnitaires = genererBesoinsUnitaires(besoins);

        int n = Math.max(secouristes.size(), besoinsUnitaires.size());
        List<Secouriste> secouristesCompletes = new ArrayList<>(secouristes);
        while (secouristesCompletes.size() < n) secouristesCompletes.add(null);
        while (besoinsUnitaires.size() < n) besoinsUnitaires.add(null);

        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) permutation[i] = i;

        int[] meilleurePermutation = Arrays.copyOf(permutation, n);
        int[] meilleurScore = {Integer.MIN_VALUE};

        recherche(permutation, 0, secouristesCompletes, besoinsUnitaires, n, meilleurePermutation, meilleurScore);

        Map<DPS, List<Secouriste>> affectation = new HashMap<>();
        Map<BesoinUnitaire, Secouriste> affectationUnitaires = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Secouriste s = secouristesCompletes.get(i);
            BesoinUnitaire b = besoinsUnitaires.get(meilleurePermutation[i]);
            if (s != null && b != null && estAffectable(s, b)) {
                affectation.computeIfAbsent(b.getDps(), k -> new ArrayList<>()).add(s);
                affectationUnitaires.put(b, s); // Ajout de l'affectation unitaire
            }
        }

        return new ResultatAffectation(affectation, affectationUnitaires);
    }

    /**
     * Génère la liste des besoins unitaires à partir des besoins globaux.
     * @param besoins Liste des besoins globaux.
     * @return Liste des besoins unitaires (un besoin par secouriste à affecter).
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
     * Vérifie si un secouriste peut être affecté à un besoin unitaire.
     * @param s Le secouriste à tester.
     * @param b Le besoin unitaire à couvrir.
     * @return true si le secouriste possède la compétence requise, false sinon.
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

    /**
     * Explore récursivement toutes les permutations possibles d'affectation.
     * Met à jour la meilleure permutation trouvée.
     * @param perm Tableau représentant la permutation courante.
     * @param index Indice courant dans la permutation.
     * @param secouristes Liste des secouristes (complétée avec des fictifs si besoin).
     * @param besoins Liste des besoins unitaires (complétée avec des fictifs si besoin).
     * @param n Taille de la permutation.
     * @param meilleurePerm Tableau de la meilleure permutation trouvée.
     * @param meilleurScore Score de la meilleure permutation trouvée.
     */
    private void recherche(int[] perm, int index, List<Secouriste> secouristes, List<BesoinUnitaire> besoins, int n, int[] meilleurePerm, int[] meilleurScore) {
        if (index == n) {
            int score = 0;
            for (int i = 0; i < n; i++) {
                Secouriste s = secouristes.get(i);
                BesoinUnitaire b = besoins.get(perm[i]);
                if (s != null && b != null && estAffectable(s, b)) {
                    score += 1;
                }
            }
            if (score > meilleurScore[0]) {
                meilleurScore[0] = score;
                System.arraycopy(perm, 0, meilleurePerm, 0, n);
            }
            return;
        }
        for (int i = index; i < n; i++) {
            echanger(perm, i, index);
            recherche(perm, index + 1, secouristes, besoins, n, meilleurePerm, meilleurScore);
            echanger(perm, i, index);
        }
    }

    /**
     * Échange deux éléments dans un tableau d'entiers.
     * @param tab Le tableau à modifier.
     * @param i Premier indice.
     * @param j Second indice.
     */
    private void echanger(int[] tab, int i, int j) {
        int tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }
}