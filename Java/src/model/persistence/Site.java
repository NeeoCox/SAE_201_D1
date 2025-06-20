package model.persistence;

/**
 * Représente un site où un DPS peut avoir lieu.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Site {
    /**
     * Le code unique du site.
     */
    private String code;
    /**
     * Le nom du site.
     */
    private String nom;
    /**
     * La longitude géographique du site.
     */
    private float longitude;
    /**
     * La latitude géographique du site.
     */
    private float latitude;

    /**
     * Constructeur de la classe Site.
     * @param unCode Le code unique du site.
     * @param unNom Le nom du site.
     * @param uneLongitude La longitude géographique du site.
     * @param uneLatitude La latitude géographique du site.
     */
    public Site(String unCode, String unNom, float uneLongitude, float uneLatitude) {
        this.code = unCode;
        this.nom = unNom;
        this.longitude = uneLongitude;
        this.latitude = uneLatitude;
    }
    

    /**
     * Accesseur de l'attribut code.
     * @return Le code unique du site.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Mutateur de l'attribut code.
     * @param unCode Le nouveau code unique du site.
     */
    public void setCode(String unCode) {
        this.code = unCode;
    }


    /**
     * Accesseur de l'attribut nom.
     * @return Le nom du site.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Mutateur de l'attribut nom.
     * @param unNom Le nouveau nom du site.
     */
    public void setNom(String unNom) {
        this.nom = unNom;
    }


    /**
     * Accesseur de l'attribut longitude.
     * @return La longitude géographique du site.
     */
    public float getLongitude() {
        return this.longitude;
    }

    /**
     * Mutateur de l'attribut longitude.
     * @param uneLongitude La nouvelle longitude géographique du site.
     */
    public void setLongitude(float uneLongitude) {
        this.longitude = uneLongitude;
    }

    
    /**
     * Accesseur de l'attribut latitude.
     * @return La latitude géographique du site.
     */
    public float getLatitude() {
        return this.latitude;
    }

    /**
     * Mutateur de l'attribut latitude.
     * @param uneLatitude La nouvelle latitude géographique du site.
     */
    public void setLatitude(float uneLatitude) {
        this.latitude = uneLatitude;
    }


    @Override
    public String toString() {
        return "Site{" +
                "code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }


    /**
     * Vérifie si le site est à une coordonnée donnée.
     * @param longitude La longitude à tester.
     * @param latitude La latitude à tester.
     * @return true si les coordonnées correspondent, false sinon.
     */
    public boolean estALaCoordonnee(float longitude, float latitude) {
        return this.longitude == longitude && this.latitude == latitude;
    }
}