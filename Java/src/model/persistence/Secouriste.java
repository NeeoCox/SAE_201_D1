package model.persistence;

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

    /**
     * Liste des disponibilités du secouriste
     */
    private List<EstDisponible> disponibilites = new ArrayList<>();

    /**
     * Constructeur par défaut de la classe Secouriste.
     */
    public Secouriste() {

    }

    /**
     * Constructeur de la classe Secouriste avec tous les attributs.
     * @param id L'identifiant unique du secouriste.
     * @param nom Le nom du secouriste.
     * @param prenom Le prénom du secouriste.
     * @param dateNaissance La date de naissance du secouriste.
     * @param email L'adresse email du secouriste.
     * @param tel Le numéro de téléphone du secouriste.
     * @param adresse L'adresse postale du secouriste.
     * @param possessions La liste des compétences possédées par le secouriste.
     */
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



    /**
     * Accesseur de la liste disponibilites
     * @return La liste des Disponibilité
     */
    public List<EstDisponible> getDisponibilites() {
        return disponibilites;
    }

    /**
     * Mutateur de la liste des disponibilité
     * @param disponibilites la liste des disponibilité
     */
    public void setDisponibilites(List<EstDisponible> disponibilites) {
        this.disponibilites = disponibilites;
    }


    /**
     * Méthode toString pour afficher les informations du secouriste.
     */
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


    /**
     * Vérifie si le secouriste possède une compétence donnée.
     * @param intitule L'intitulé de la compétence recherchée.
     * @return true si le secouriste possède cette compétence, false sinon.
     */
    public boolean aLaCompetence(String intitule) {
        for (Possede p : possessions) {
            if (p.possedeCompetence(intitule)) {
                return true;
            }
        }
        return false;
    }
}