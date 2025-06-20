package model.persistence;

/**
 * Représente un Dispositif Prévisionnel de Secours (DPS).
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class DPS {
    /**
     * L'identifiant unique du DPS.
     */
    private long id;
    /**
     * L'horaire de départ du DPS.
     */
    private int horaireDepart;
    /**
     * L'horaire de fin du DPS.
     */
    private int horaireFin;
    /**
     * La journée pendant laquelle le DPS est programmé.
     */
    private Journee estProgramme;
    /**
     * Le site où le DPS a lieu.
     */
    private Site aLieuDans;
    /**
     * Le sport concerné par le DPS.
     */
    private Sport concerne;

    
    /**
     * Constructeur de la classe DPS.
     * @param unId L'identifiant unique du DPS.
     * @param unHoraireDepart L'horaire de départ du DPS.
     * @param unHoraireFin L'horaire de fin du DPS.
     * @param uneJournee La journée pendant laquelle le DPS est programmé.
     * @param unSite Le site où le DPS a lieu.
     * @param unSport Le sport concerné par le DPS.
     */
    public DPS(long unId, int unHoraireDepart, int unHoraireFin, Journee uneJournee, Site unSite, Sport unSport) {
        this.id = unId;
        this.horaireDepart = unHoraireDepart;
        this.horaireFin = unHoraireFin;
        this.estProgramme = uneJournee;
        this.aLieuDans = unSite;
        this.concerne = unSport;
    }


    /**
     * Accesseur de l'attribut id.
     * @return L'identifiant unique du DPS.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Mutateur de l'attribut id.
     * @param unId Le nouvel identifiant unique du DPS.
     */
    public void setId(long unId) {
        this.id = unId;
    }

    
    /**
     * Accesseur de l'attribut horaireDepart.
     * @return L'horaire de départ du DPS.
     */
    public int getHoraireDepart() {
        return this.horaireDepart;
    }

    /**
     * Mutateur de l'attribut horaireDepart.
     * @param unHoraireDepart Le nouvel horaire de départ du DPS.
     */
    public void setHoraireDepart(int unHoraireDepart) {
        this.horaireDepart = unHoraireDepart;
    }


    /**
     * Accesseur de l'attribut horaireFin.
     * @return L'horaire de fin du DPS.
     */
    public int getHoraireFin() {
        return this.horaireFin;
    }

    /**
     * Mutateur de l'attribut horaireFin.
     * @param unHoraireFin Le nouvel horaire de fin du DPS.
     */
    public void setHoraireFin(int unHoraireFin) {
        this.horaireFin = unHoraireFin;
    }


    /**
     * Accesseur de l'attribut estProgramme.
     * @return La journée pendant laquelle le DPS est programmé.
     */
    public Journee getEstProgramme() {
        return this.estProgramme;
    }

    /**
     * Mutateur de l'attribut estProgramme.
     * @param uneJournee La nouvelle journée pendant laquelle le DPS est programmé.
     */
    public void setEstProgramme(Journee uneJournee) {
        this.estProgramme = uneJournee;
    }

    
    /**
     * Accesseur de l'attribut aLieuDans.
     * @return Le site où le DPS a lieu.
     */
    public Site getALieuDans() {
        return this.aLieuDans;
    }

    /**
     * Mutateur de l'attribut aLieuDans.
     * @param unSite Le nouveau site où le DPS a lieu.
     */
    public void setALieuDans(Site unSite) {
        this.aLieuDans = unSite;
    }


    /**
     * Accesseur de l'attribut concerne.
     * @return Le sport concerné par le DPS.
     */
    public Sport getConcerne() {
        return this.concerne;
    }

    /**
     * Mutateur de l'attribut concerne.
     * @param unSport Le nouveau sport concerné par le DPS.
     */
    public void setConcerne(Sport unSport) {
        this.concerne = unSport;
    }

    public String getEstProgrammeJournee() {
        if (estProgramme == null) return "";
        return String.format("%02d/%02d/%04d", estProgramme.getJour(), estProgramme.getMois(), estProgramme.getAnnee());
    }

    public String getALieuDansSite() {
        return aLieuDans != null ? aLieuDans.getNom() : "";
    }

    public String getConcerneSport() {
        return concerne != null ? concerne.getNom() : "";
    }


    @Override
    public String toString() {
        return "DPS{" +
                "id=" + id +
                ", horaireDepart=" + horaireDepart +
                ", horaireFin=" + horaireFin +
                ", estProgramme=" + estProgramme +
                ", aLieuDans=" + aLieuDans +
                ", concerne=" + concerne +
                '}';
    }


    /**
     * Vérifie si une heure donnée est comprise dans l'intervalle du DPS.
     * @param heure L'heure à tester.
     * @return true si l'heure est comprise dans l'intervalle, false sinon.
     */
    public boolean estDansIntervalleHoraire(int heure) {
        return heure >= horaireDepart && heure <= horaireFin;
    }
}