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
        List<BesoinUnitaire> besoinsUnitaires = genererBesoinsUnitaires(besoins);
        Map<DPS, List<Secouriste>> affectation = new HashMap<>();
        Set<Secouriste> dejaAffectes = new HashSet<>();

        for (BesoinUnitaire besoin : besoinsUnitaires) {
            Secouriste choisi = trouverPremierSecouristeDisponible(secouristes, dejaAffectes, besoin);
            if (choisi != null) {
                affectation.computeIfAbsent(besoin.getDps(), k -> new ArrayList<>()).add(choisi);
                dejaAffectes.add(choisi);
            }
            // Si aucun secouriste n'est disponible, le besoin reste non couvert
        }
        return new ResultatAffectation(affectation);
    }

    /**
     * Cherche le premier secouriste disponible et compétent pour un besoin.
     * @param secouristes Liste des secouristes.
     * @param dejaAffectes Secouristes déjà affectés.
     * @param besoin Le besoin à couvrir.
     * @return Le secouriste trouvé ou null si aucun.
     */
    private Secouriste trouverPremierSecouristeDisponible(List<Secouriste> secouristes, Set<Secouriste> dejaAffectes, BesoinUnitaire besoin) {
        for (Secouriste s : secouristes) {
            if (!dejaAffectes.contains(s) && estAffectable(s, besoin)) {
                return s;
            }
        }
        return null;
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