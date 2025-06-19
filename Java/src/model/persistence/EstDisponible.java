package model.persistence;

/**
 * Représente la disponibilité d'un secouriste pour une journée donnée.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class EstDisponible {
    /**
     * Le secouriste disponible.
     */
    private Secouriste leSecouriste;

    /**
     * L'identifiant du secouriste disponible.
     */
    private long idSecouriste;

    /**
     * La journée pour laquelle le secouriste est disponible.
     */
    private Journee laJournee;

    /**
     * Le jour de la date de disponibilité.
     */
    private int jourJournee;

    /**
     * Le mois de la date de disponibilité.
     */
    private int moisJournee;

    /**
     * L'année de la date de disponibilité.
     */
    private int anneeJournee;


    /**
     * Constructeur par défaut de la classe EstDisponible.
     * Initialise les attributs à leurs valeurs par défaut.
     */
    public EstDisponible() {
        this.leSecouriste = null;
        this.idSecouriste = 0;
        this.laJournee = new Journee();
        this.jourJournee = 1;
        this.moisJournee = 1;
        this.anneeJournee = 1970;
    }
    /**
     * Constructeur de la classe EstDisponible.
     * @param unSecouriste Le secouriste disponible.
     * @param uneJournee La journée pour laquelle le secouriste est disponible.
     * @param unJour Le jour de la date de disponibilité.
     * @param unMois Le mois de la date de disponibilité.
     * @param uneAnnee L'année de la date de disponibilité.
     */
    public EstDisponible(Secouriste unSecouriste, Journee uneJournee, int unJour, int unMois, int uneAnnee) {
        this.leSecouriste = unSecouriste;
        this.idSecouriste = unSecouriste.getId();
        this.laJournee = uneJournee;
        this.jourJournee = unJour;
        this.moisJournee = unMois;
        this.anneeJournee = uneAnnee;
    }


    /**
     * Accesseur de l'attribut leSecouriste.
     * @return Le secouriste disponible.
     */
    public Secouriste getSecouriste() {
        return this.leSecouriste;
    }

    /**
     * Mutateur de l'attribut leSecouriste.
     * @param unSecouriste Le nouveau secouriste disponible.
     */
    public void setSecouriste(Secouriste unSecouriste) {
        this.leSecouriste = unSecouriste;
    }


    /**
     * Accesseur de l'attribut idSecouriste.
     * @return L'identifiant du secouriste disponible.
     */
    public long getIdSecouriste() {
        return this.idSecouriste;
    }

    /**
     * Mutateur de l'attribut idSecouriste.
     * @param unIdSecouriste Le nouvel identifiant du secouriste disponible.
     */
    public void setIdSecouriste(long unIdSecouriste) {
        this.idSecouriste = unIdSecouriste;
    }


    /**
     * Accesseur de l'attribut laJournee.
     * @return La journée pour laquelle le secouriste est disponible.
     */
    public Journee getLaJournee() {
        return this.laJournee;
    }

    /**
     * Mutateur de l'attribut laJournee.
     * @param uneJournee La nouvelle journée pour laquelle le secouriste est disponible.
     */
    public void setLaJournee(Journee uneJournee) {
        this.laJournee = uneJournee;
    }


    /**
     * Accesseur de l'attribut jourJournee.
     * @return Le jour de la date de disponibilité.
     */
    public int getJourJournee() {
        return this.jourJournee;
    }

    /**
     * Mutateur de l'attribut jourJournee.
     * @param unJourJournee Le nouveau jour de la date de disponibilité.
     */
    public void setJourJournee(int unJourJournee) {
        this.jourJournee = unJourJournee;
    }


    /**
     * Accesseur de l'attribut moisJournee.
     * @return Le mois de la date de disponibilité.
     */
    public int getMoisJournee() {
        return this.moisJournee;
    }

    /**
     * Mutateur de l'attribut moisJournee.
     * @param unMoisJournee Le nouveau mois de la date de disponibilité.
     */
    public void setMoisJournee(int unMoisJournee) {
        this.moisJournee = unMoisJournee;
    }

    
    /**
     * Accesseur de l'attribut anneeJournee.
     * @return L'année de la date de disponibilité.
     */
    public int getAnneeJournee() {
        return this.anneeJournee;
    }

    /**
     * Mutateur de l'attribut anneeJournee.
     * @param uneAnneeJournee La nouvelle année de la date de disponibilité.
     */
    public void setAnneeJournee(int uneAnneeJournee) {
        this.anneeJournee = uneAnneeJournee;
    }


    /**
     * Méthode toString pour afficher les informations de la disponibilité.
     */
    @Override
    public String toString() {
        return "EstDisponible{" +
                "leSecouriste=" + leSecouriste +
                ", idSecouriste=" + idSecouriste +
                ", laJournee=" + laJournee +
                ", jourJournee=" + jourJournee +
                ", moisJournee=" + moisJournee +
                ", anneeJournee=" + anneeJournee +
                '}';
    }


    /**
     * Vérifie si la disponibilité correspond à une date donnée.
     * @param jour Le jour.
     * @param mois Le mois.
     * @param annee L'année.
     * @return true si la date correspond, false sinon.
     */
    public boolean estDisponibleLe(int jour, int mois, int annee) {
        return this.jourJournee == jour && this.moisJournee == mois && this.anneeJournee == annee;
    }
}