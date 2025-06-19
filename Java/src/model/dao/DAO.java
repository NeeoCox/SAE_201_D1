package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Classe abstraite DAO qui fournit des méthodes de base pour la gestion des connexions à la base de données.
 * Cette classe est générique et peut être étendue pour des types spécifiques.
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public abstract class DAO<T> {
    /**
     * Nom du driver JDBC pour MySQL.
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * URL de connexion à la base de données MySQL.
     */
    private static String URL = "jdbc:mysql://localhost:3306/bd_sae";
    /**
     * Nom d'utilisateur pour la connexion à la base de données.
     */
    private static String USERNAME = "root";

    /**
     * Mot de passe pour la connexion à la base de données.
     */
    private static String PASSWORD = "password";

    /**
     * Crée une connexion à la base de données MySQL.
     * @return Connection objet représentant la connexion à la base de données.
     * @throws SQLException si une erreur se produit lors de la connexion à la base de données.
     */
    public static Connection createConnection() throws SQLException {
        try {
            Class.forName(DRIVER); // Charge le driver
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL non trouvé", e);
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /**
     * Méthode pour définir les informations d'identification de la base de données.
     * @param username nom d'utilisateur pour la connexion à la base de données
     * @param password mot de passe pour la connexion à la base de données
     */
    public static void setCredentials(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username ou mot de passe invalide");
        }
        USERNAME = username;
        PASSWORD = password;
    }
}