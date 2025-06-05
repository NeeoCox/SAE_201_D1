package model.persistence;

/**
 * Représente un sport associé à un DPS.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Sport {
    /**
     * Le code unique du sport.
     */
    private String code;

    /**
     * Le nom du sport.
     */
    private String nom;


    /**
     * Constructeur de la classe Sport.
     * @param unCode Le code unique du sport.
     * @param unNom Le nom du sport.
     */
    public Sport(String unCode, String unNom) {
        this.code = unCode;
        this.nom = unNom;
    }


    /**
     * Accesseur de l'attribut code.
     * @return Le code unique du sport.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Mutateur de l'attribut code.
     * @param unCode Le nouveau code unique du sport.
     */
    public void setCode(String unCode) {
        this.code = unCode;
    }


    /**
     * Accesseur de l'attribut nom.
     * @return Le nom du sport.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Mutateur de l'attribut nom.
     * @param unNom Le nouveau nom du sport.
     */
    public void setNom(String unNom) {
        this.nom = unNom;
    }
}