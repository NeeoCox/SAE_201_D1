package model.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un secouriste avec ses informations personnelles.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Secouriste {
    /**
     * L'identifiant unique du secouriste.
     */
    private long id;
    /**
     * Le nom du secouriste.
     */
    private String nom;
    /**
     * Le prénom du secouriste.
     */
    private String prenom;
    /**
     * La date de naissance du secouriste.
     */
    private String dateNaissance;
    /**
     * L'adresse email du secouriste.
     */
    private String email;
    /**
     * Le numéro de téléphone du secouriste.
     */
    private String tel;
    /**
     * L'adresse postale du secouriste.
     */
    private String adresse;
    /**
     * Liste des compétences possédées par le secouriste.
     */
    private List<Possede> possessions = new ArrayList<>();

    
    public Secouriste() {

    }

    public Secouriste(long id, String nom, String prenom, String dateNaissance, String email, String tel, String adresse, List<Possede> possessions) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.tel = tel;
        this.adresse = adresse;
        this.possessions = possessions != null ? possessions : new ArrayList<>();
    }


    /**
     * Accesseur de l'attribut id.
     * @return L'identifiant unique du secouriste.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Mutateur de l'attribut id.
     * @param unId Le nouvel identifiant unique du secouriste.
     */
    public void setId(long unId) {
        this.id = unId;
    }


    /**
     * Accesseur de l'attribut nom.
     * @return Le nom du secouriste.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Mutateur de l'attribut nom.
     * @param unNom Le nouveau nom du secouriste.
     */
    public void setNom(String unNom) {
        this.nom = unNom;
    }


    /**
     * Accesseur de l'attribut prenom.
     * @return Le prénom du secouriste.
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Mutateur de l'attribut prenom.
     * @param unPrenom Le nouveau prénom du secouriste.
     */
    public void setPrenom(String unPrenom) {
        this.prenom = unPrenom;
    }


    /**
     * Accesseur de l'attribut dateNaissance.
     * @return La date de naissance du secouriste.
     */
    public String getDateNaissance() {
        return this.dateNaissance;
    }

    /**
     * Mutateur de l'attribut dateNaissance.
     * @param uneDateNaissance La nouvelle date de naissance du secouriste.
     */
    public void setDateNaissance(String uneDateNaissance) {
        this.dateNaissance = uneDateNaissance;
    }


    /**
     * Accesseur de l'attribut email.
     * @return L'adresse email du secouriste.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Mutateur de l'attribut email.
     * @param unEmail La nouvelle adresse email du secouriste.
     */
    public void setEmail(String unEmail) {
        this.email = unEmail;
    }


    /**
     * Accesseur de l'attribut tel.
     * @return Le numéro de téléphone du secouriste.
     */
    public String getTel() {
        return this.tel;
    }

    /**
     * Mutateur de l'attribut tel.
     * @param unTel Le nouveau numéro de téléphone du secouriste.
     */
    public void setTel(String unTel) {
        this.tel = unTel;
    }

    
    /**
     * Accesseur de l'attribut adresse.
     * @return L'adresse postale du secouriste.
     */
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * Mutateur de l'attribut adresse.
     * @param uneAdresse La nouvelle adresse postale du secouriste.
     */
    public void setAdresse(String uneAdresse) {
        this.adresse = uneAdresse;
    }


    /**
     * Accesseur de la liste des possessions.
     * @return La liste des compétences possédées.
     */
    public List<Possede> getPossessions() {
        return possessions;
    }

    /**
     * Mutateur de la liste des possessions.
     * @param possessions La nouvelle liste de compétences possédées.
     */
    public void setPossessions(List<Possede> possessions) {
        this.possessions = possessions;
    }


    @Override
    public String toString() {
        return "Secouriste{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}