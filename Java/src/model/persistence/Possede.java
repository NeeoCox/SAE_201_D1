package model.persistence;

/**
 * Représente la possession d'une compétence par un secouriste.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Possede {
    /**
     * Le secouriste possédant la compétence.
     */
    private Secouriste leSecouriste;
    /**
     * L'identifiant du secouriste possédant la compétence.
     */
    private long idSecouriste;
    /**
     * La compétence possédée par le secouriste.
     */
    private Competence laCompetence;
    /**
     * L'intitulé de la compétence possédée par le secouriste.
     */
    private String intituleCompetence;


    /**
     * Accesseur de l'attribut leSecouriste.
     * @return Le secouriste possédant la compétence.
     */
    public Secouriste getSecouriste() {
        return this.leSecouriste;
    }

    /**
     * Mutateur de l'attribut leSecouriste.
     * @param unSecouriste Le nouveau secouriste possédant la compétence.
     */
    public void setSecouriste(Secouriste unSecouriste) {
        this.leSecouriste = unSecouriste;
    }


    /**
     * Accesseur de l'attribut idSecouriste.
     * @return L'identifiant du secouriste possédant la compétence.
     */
    public long getIdSecouriste() {
        return this.idSecouriste;
    }

    /**
     * Mutateur de l'attribut idSecouriste.
     * @param unIdSecouriste Le nouvel identifiant du secouriste possédant la compétence.
     */
    public void setIdSecouriste(int unIdSecouriste) {
        this.idSecouriste = unIdSecouriste;
    }


    /**
     * Accesseur de l'attribut laCompetence.
     * @return La compétence possédée par le secouriste.
     */
    public Competence getLaCompetence() {
        return this.laCompetence;
    }

    /**
     * Mutateur de l'attribut laCompetence.
     * @param uneCompetence La nouvelle compétence possédée par le secouriste.
     */
    public void setLaCompetence(Competence uneCompetence) {
        this.laCompetence = uneCompetence;
    }

    
    /**
     * Accesseur de l'attribut intituleCompetence.
     * @return L'intitulé de la compétence possédée par le secouriste.
     */
    public String getIntituleCompetence() {
        return this.intituleCompetence;
    }

    /**
     * Mutateur de l'attribut intituleCompetence.
     * @param unIntituleCompetence Le nouvel intitulé de la compétence possédée par le secouriste.
     */
    public void setIntituleCompetence(String unIntituleCompetence) {
        this.intituleCompetence = unIntituleCompetence;
    }
}