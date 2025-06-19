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