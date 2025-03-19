package metier.persistence;

/**
 * Représente une relation de nécessité entre deux compétences.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Necessite {
    /**
     * La compétence principale.
     */
    private Competence laCompetence;

    /**
     * L'intitulé de la compétence principale.
     */
    private String intituleCompetence;

    /**
     * La compétence nécessaire pour exercer la compétence principale.
     */
    private Competence competenceNecessaire;

    /**
     * L'intitulé de la compétence nécessaire.
     */
    private String intituleCompetenceNecessaire;


    /**
     * Accesseur de l'attribut laCompetence.
     * @return La compétence principale.
     */
    public Competence getLaCompetence() {
        return this.laCompetence;
    }

    /**
     * Mutateur de l'attribut laCompetence.
     * @param uneCompetence La nouvelle compétence principale.
     */
    public void setLaCompetence(Competence uneCompetence) {
        this.laCompetence = uneCompetence;
    }


    /**
     * Accesseur de l'attribut intituleCompetence.
     * @return L'intitulé de la compétence principale.
     */
    public String getIntituleCompetence() {
        return this.intituleCompetence;
    }

    /**
     * Mutateur de l'attribut intituleCompetence.
     * @param unIntituleCompetence Le nouvel intitulé de la compétence principale.
     */
    public void setIntituleCompetence(String unIntituleCompetence) {
        this.intituleCompetence = unIntituleCompetence;
    }


    /**
     * Accesseur de l'attribut competenceNecessaire.
     * @return La compétence nécessaire pour exercer la compétence principale.
     */
    public Competence getCompetenceNecessaire() {
        return this.competenceNecessaire;
    }

    /**
     * Mutateur de l'attribut competenceNecessaire.
     * @param uneCompetence La nouvelle compétence nécessaire.
     */
    public void setCompetenceNecessaire(Competence uneCompetence) {
        this.competenceNecessaire = uneCompetence;
    }


    /**
     * Accesseur de l'attribut intituleCompetenceNecessaire.
     * @return L'intitulé de la compétence nécessaire.
     */
    public String getIntituleCompetenceNecessaire() {
        return this.intituleCompetenceNecessaire;
    }

    /**
     * Mutateur de l'attribut intituleCompetenceNecessaire.
     * @param unIntituleCompetence Le nouvel intitulé de la compétence nécessaire.
     */
    public void setIntituleCompetenceNecessaire(String unIntituleCompetence) {
        this.intituleCompetenceNecessaire = unIntituleCompetence;
    }
}