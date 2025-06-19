package model.persistence;

/**
 * Représente un utilisateur du système.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class User {
    /**
     * L'identifiant unique de l'utilisateur.
     */
    private long id;
    /**
     * Le nom d'utilisateur.
     */
    private String username;
    /**
     * Le mot de passe haché de l'utilisateur.
     */
    private String passwordHash;
    /**
     * Le rôle de l'utilisateur (par exemple, "admin", "user").
     */
    private String role;
    /**
     * La date de création du compte utilisateur.
     */
    private java.sql.Timestamp createdAt;

    /**
     * Constructeur par défaut de la classe User.
     */
    public User() {}

    /**
     * Constructeur de la classe User avec tous les attributs.
     * @param id L'identifiant unique de l'utilisateur.
     * @param username Le nom d'utilisateur.
     * @param passwordHash Le mot de passe haché de l'utilisateur.
     * @param role Le rôle de l'utilisateur.
     * @param createdAt La date de création du compte utilisateur.
     */
    public User(long id, String username, String passwordHash, String role, java.sql.Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.createdAt = createdAt;
    }

    /**
     * Accesseur de l'attribut id.
     * @return L'identifiant unique de l'utilisateur.
     */
    public long getId() {
        return id;
    }

    /**
     * Mutateur de l'attribut id.
     * @param id Le nouvel identifiant unique de l'utilisateur.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Accesseur de l'attribut username.
     * @return Le nom d'utilisateur.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Mutateur de l'attribut username.
     * @param username Le nouveau nom d'utilisateur.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Accesseur de l'attribut passwordHash.
     * @return Le mot de passe haché de l'utilisateur.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Mutateur de l'attribut passwordHash.
     * @param passwordHash Le nouveau mot de passe haché de l'utilisateur.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Accesseur de l'attribut role.
     * @return Le rôle de l'utilisateur.
     */
    public String getRole() {
        return role;
    }

    /**
     * Mutateur de l'attribut role.
     * @param role Le nouveau rôle de l'utilisateur.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Accesseur de l'attribut createdAt.
     * @return La date de création du compte utilisateur.
     */
    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Mutateur de l'attribut createdAt.
     * @param createdAt La nouvelle date de création du compte utilisateur.
     */
    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet User.
     */
    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", role='" + role + '\'' +
               ", createdAt=" + createdAt +
               '}';
    }
}

