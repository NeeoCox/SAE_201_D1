package model.dao;
import java.sql.Connection;

import java.sql.Connection;

public class TestConnexion {
    public static void main(String[] args) {
        try {
            Connection conn = DAO.createConnection();
            System.out.println("Connexion réussie !");
            conn.close();
        } catch (Exception e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
            e.printStackTrace();
        }
    }
}