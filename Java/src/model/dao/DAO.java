package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO<T> {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://172.20.10.11:3306/Secouristes";
    private static String USERNAME = "client";
    private static String PASSWORD = "password";

    // Méthode pour établir une connexion
    public static Connection createConnection() throws SQLException {
        try {
            Class.forName(DRIVER); // Charge le driver
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL non trouvé", e);
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Setters pour configurer dynamiquement (optionnel)
    public static void setCredentials(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Username ou mot de passe invalide");
        }
        USERNAME = username;
        PASSWORD = password;
    }
}