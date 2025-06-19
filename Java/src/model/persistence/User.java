package model.persistence;

public class User {
    private long id;
    private String username;
    private String passwordHash;
    private String role;
    private java.sql.Timestamp createdAt;

    // Constructeur vide
    public User() {}

    // Constructeur complet (optionnel)
    public User(long id, String username, String passwordHash, String role, java.sql.Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.createdAt = createdAt;
    }

    // Getters et setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

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

