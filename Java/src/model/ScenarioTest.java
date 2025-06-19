package model;
import model.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class ScenarioTest {
    public static void main(String[] args) {
        // Création des compétences
        Competence competence1 = new Competence();
        competence1.setIntitule("Premiers secours");

        Competence competence2 = new Competence();
        competence2.setIntitule("Secouriste avancé");

        // Création des sports associés aux compétences
        Sport sport1 = new Sport("S1", "Premiers secours");
        Sport sport2 = new Sport("S2", "Secouriste avancé");

        // Création des secouristes
        Secouriste secouriste1 = new Secouriste();
        secouriste1.setId(1);
        secouriste1.setNom("Alice");
        secouriste1.setPrenom("Dupont");

        Secouriste secouriste2 = new Secouriste();
        secouriste2.setId(2);
        secouriste2.setNom("Bob");
        secouriste2.setPrenom("Martin");

        Secouriste secouriste3 = new Secouriste();
        secouriste3.setId(3);
        secouriste3.setNom("Charlie");
        secouriste3.setPrenom("Durand");

        // Ajout des compétences aux secouristes
        Possede possession1 = new Possede();
        possession1.setSecouriste(secouriste1);
        possession1.setLaCompetence(competence1);

        Possede possession2 = new Possede();
        possession2.setSecouriste(secouriste2);
        possession2.setLaCompetence(competence1);

        Possede possession3 = new Possede();
        possession3.setSecouriste(secouriste2);
        possession3.setLaCompetence(competence2);

        Possede possession4 = new Possede();
        possession4.setSecouriste(secouriste3);
        possession4.setLaCompetence(competence2);

        secouriste1.getPossessions().add(possession1);
        secouriste2.getPossessions().add(possession2);
        secouriste2.getPossessions().add(possession3);
        secouriste3.getPossessions().add(possession4);

        // Définition des disponibilités des secouristes
        Journee journee = new Journee();
        journee.setJour(20);
        journee.setMois(6);
        journee.setAnnee(2025);

        EstDisponible dispo1 = new EstDisponible(secouriste1, journee, 20, 6, 2025);
        EstDisponible dispo2 = new EstDisponible(secouriste2, journee, 20, 6, 2025);
        EstDisponible dispo3 = new EstDisponible(secouriste3, journee, 20, 6, 2025);

        secouriste1.getDisponibilites().add(dispo1);
        secouriste2.getDisponibilites().add(dispo2);
        secouriste3.getDisponibilites().add(dispo3);

        // Création des DPS
        DPS dps1 = new DPS(1, 8, 12, journee, null, sport1);
        DPS dps2 = new DPS(2, 14, 18, journee, null, sport2);

        // Affectation gloutonne
        System.out.println("Affectation gloutonne :");
        for (DPS dps : List.of(dps1, dps2)) {
            for (Secouriste secouriste : List.of(secouriste1, secouriste2, secouriste3)) {
                boolean disponible = false;
                for (EstDisponible dispo : secouriste.getDisponibilites()) {
                    if (dispo.getLaJournee().estEgale(journee)) {
                        disponible = true;
                        break;
                    }
                }

                boolean possedeCompetence = false;
                for (Possede possession : secouriste.getPossessions()) {
                    if (possession.getLaCompetence().equalsIntitule(dps.getConcerne().getNom())) {
                        possedeCompetence = true;
                        break;
                    }
                }

                if (disponible && possedeCompetence) {
                    System.out.println("Secouriste " + secouriste.getNom() + " affecté à DPS " + dps.getId());
                    break; // Glouton : on prend le premier secouriste valide
                }
            }
        }

        // Affectation exhaustive
        System.out.println("\nAffectation exhaustive :");
        for (DPS dps : List.of(dps1, dps2)) {
            List<Secouriste> candidats = new ArrayList<>();
            for (Secouriste secouriste : List.of(secouriste1, secouriste2, secouriste3)) {
                boolean disponible = false;
                for (EstDisponible dispo : secouriste.getDisponibilites()) {
                    if (dispo.getLaJournee().estEgale(journee)) {
                        disponible = true;
                        break;
                    }
                }

                boolean possedeCompetence = false;
                for (Possede possession : secouriste.getPossessions()) {
                    if (possession.getLaCompetence().equalsIntitule(dps.getConcerne().getNom())) {
                        possedeCompetence = true;
                        break;
                    }
                }

                if (disponible && possedeCompetence) {
                    candidats.add(secouriste);
                }
            }

            if (!candidats.isEmpty()) {
                Secouriste meilleurCandidat = candidats.get(0);
                System.out.println("Secouriste " + meilleurCandidat.getNom() + " affecté à DPS " + dps.getId());
            }
        }
    }
}