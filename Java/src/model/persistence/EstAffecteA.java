package model.persistence;

/**
 * Représente l'affectation d'un secouriste à une compétence pour un DPS.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class EstAffecteA {
    /**
     * Le secouriste affecté.
     */
    private Secouriste leSecouriste;
    /**
     * L'identifiant du secouriste affecté.
     */
    private long idSecouriste;
    /**
     * La compétence à laquelle le secouriste est affecté.
     */
    private Competence laCompetence;
    /**
     * L'intitulé de la compétence à laquelle le secouriste est affecté.
     */
    private String intituleCompetence;
    /**
     * Le DPS auquel le secouriste est affecté.
     */
    private DPS leDPS;
    /**
     * L'identifiant du DPS auquel le secouriste est affecté.
     */
    private long idDPS;


    /**
     * Accesseur de l'attribut leSecouriste.
     * @return Le secouriste affecté.
     */
    public Secouriste getSecouriste() {
        return this.leSecouriste;
    }

    /**
     * Mutateur de l'attribut leSecouriste.
     * @param unSecouriste Le nouveau secouriste affecté.
     */
    public void setSecouriste(Secouriste unSecouriste) {
        this.leSecouriste = unSecouriste;
    }


    /**
     * Accesseur de l'attribut idSecouriste.
     * @return L'identifiant du secouriste affecté.
     */
    public long getIdSecouriste() {
        return this.idSecouriste;
    }

    /**
     * Mutateur de l'attribut idSecouriste.
     * @param unIdSecouriste Le nouvel identifiant du secouriste affecté.
     */
    public void setIdSecouriste(long unIdSecouriste) {
        this.idSecouriste = unIdSecouriste;
    }


    /**
     * Accesseur de l'attribut laCompetence.
     * @return La compétence à laquelle le secouriste est affecté.
     */
    public Competence getLaCompetence() {
        return this.laCompetence;
    }

    /**
     * Mutateur de l'attribut laCompetence.
     * @param uneCompetence La nouvelle compétence à laquelle le secouriste est affecté.
     */
    public void setLaCompetence(Competence uneCompetence) {
        this.laCompetence = uneCompetence;
    }


    /**
     * Accesseur de l'attribut intituleCompetence.
     * @return L'intitulé de la compétence à laquelle le secouriste est affecté.
     */
    public String getIntituleCompetence() {
        return this.intituleCompetence;
    }

    /**
     * Mutateur de l'attribut intituleCompetence.
     * @param unIntituleCompetence Le nouvel intitulé de la compétence à laquelle le secouriste est affecté.
     */
    public void setIntituleCompetence(String unIntituleCompetence) {
        this.intituleCompetence = unIntituleCompetence;
    }


    /**
     * Accesseur de l'attribut leDPS.
     * @return Le DPS auquel le secouriste est affecté.
     */
    public DPS getLeDPS() {
        return this.leDPS;
    }

    /**
     * Mutateur de l'attribut leDPS.
     * @param unDPS Le nouveau DPS auquel le secouriste est affecté.
     */
    public void setLeDPS(DPS unDPS) {
        this.leDPS = unDPS;
    }

    
    /**
     * Accesseur de l'attribut idDPS.
     * @return L'identifiant du DPS auquel le secouriste est affecté.
     */
    public long getIdDPS() {
        return this.idDPS;
    }

    /**
     * Mutateur de l'attribut idDPS.
     * @param unIdDPS Le nouvel identifiant du DPS auquel le secouriste est affecté.
     */
    public void setIdDPS(long unIdDPS) {
        this.idDPS = unIdDPS;
    }


    @Override
    public String toString() {
        return "EstAffecteA{" +
                "leSecouriste=" + leSecouriste +
                ", idSecouriste=" + idSecouriste +
                ", laCompetence=" + laCompetence +
                ", intituleCompetence='" + intituleCompetence + '\'' +
                ", leDPS=" + leDPS +
                ", idDPS=" + idDPS +
                '}';
    }


    /**
     * Vérifie si l'affectation concerne un secouriste donné.
     * @param idSecouriste L'identifiant du secouriste.
     * @return true si l'affectation concerne ce secouriste, false sinon.
     */
    public boolean concerneSecouriste(long idSecouriste) {
        return this.idSecouriste == idSecouriste
            || (leSecouriste != null && leSecouriste.getId() == idSecouriste);
    }
}