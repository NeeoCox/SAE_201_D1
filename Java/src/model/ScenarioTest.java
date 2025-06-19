package model;
import model.persistence.*;

import java.util.ArrayList;
import java.util.List;
/*
 * Scenario d'affectation des secouriste avec l'algo glouton
 */
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
        secouriste1.setNom("Dupond");
        secouriste1.setPrenom("Alice");

        Secouriste secouriste2 = new Secouriste();
        secouriste2.setId(2);
        secouriste2.setNom("Martin");
        secouriste2.setPrenom("Bob");

        Secouriste secouriste3 = new Secouriste();
        secouriste3.setId(3);
        secouriste3.setNom("Durand");
        secouriste3.setPrenom("Charlie");

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

        // Création des sites
        Site site1 = new Site("SITE1", "Stade Municipal", 48.8566f, 2.3522f);
        Site site2 = new Site("SITE2", "Parc des Expositions", 48.8584f, 2.2945f);

        // Création des DPS avec sites
        DPS dps1 = new DPS(1, 8, 12, journee, site1, sport1);
        DPS dps2 = new DPS(2, 14, 18, journee, site2, sport2);

        // Affectation gloutonne
        System.out.println("Affectation gloutonne :");

        for (DPS dps : List.of(dps1, dps2)) {
            System.out.println("Traitement du DPS : " + dps.getId() + ", site : " + dps.getALieuDans().getNom());

            for (Secouriste secouriste : List.of(secouriste1, secouriste2, secouriste3)) {
                System.out.println("  Vérification du secouriste : " + secouriste.getNom());

                boolean disponible = false;
                for (EstDisponible dispo : secouriste.getDisponibilites()) {
                    System.out.println("    Vérifie disponibilité pour la journée : " + dispo.getLaJournee().toString());
                    if (dispo.getLaJournee().estEgale(journee)) {
                        System.out.println("    --> Disponible ce jour-là !");
                        disponible = true;
                        break;
                    }
                }

                boolean possedeCompetence = false;
                for (Possede possession : secouriste.getPossessions()) {
                    System.out.println("    Vérifie compétence : " + possession.getLaCompetence().getIntitule());
                    if (possession.getLaCompetence().equalsIntitule(dps.getConcerne().getNom())) {
                        System.out.println("    --> Possède la compétence : " + dps.getConcerne().getNom());
                        possedeCompetence = true;
                        break;
                    }
                }

                if (disponible && possedeCompetence) {
                    System.out.println("  >>> Secouriste " + secouriste.getNom() + " affecté à DPS " + dps.getId() + " au site " + dps.getALieuDans().getNom());
                    break; // Glouton : on prend le premier secouriste valide
                } else {
                    System.out.println("  --> Ce secouriste ne peut pas être affecté (disponibilité ou compétence manquante).");
                }
            }
        }
    }
}