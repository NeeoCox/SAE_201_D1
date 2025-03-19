package metier.persistence;

/**
 * Représente une journée avec une date précise.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Journee {
    /**
     * Le jour de la date.
     */
    private int jour;

    /**
     * Le mois de la date.
     */
    private int mois;

    /**
     * L'année de la date.
     */
    private int annee;


    /**
     * Accesseur de l'attribut jour.
     * @return Le jour de la date.
     */
    public int getJour() {
        return this.jour;
    }

    /**
     * Mutateur de l'attribut jour.
     * @param unJour Le nouveau jour de la date.
     */
    public void setJour(int unJour) {
        this.jour = unJour;
    }


    /**
     * Accesseur de l'attribut mois.
     * @return Le mois de la date.
     */
    public int getMois() {
        return this.mois;
    }

    /**
     * Mutateur de l'attribut mois.
     * @param unMois Le nouveau mois de la date.
     */
    public void setMois(int unMois) {
        this.mois = unMois;
    }


    /**
     * Accesseur de l'attribut annee.
     * @return L'année de la date.
     */
    public int getAnnee() {
        return this.annee;
    }

    /**
     * Mutateur de l'attribut annee.
     * @param uneAnnee La nouvelle année de la date.
     */
    public void setAnnee(int uneAnnee) {
        this.annee = uneAnnee;
    }
}