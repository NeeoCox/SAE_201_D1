package model.dao;
import java.sql.Connection;

public class TestConnexion {
    public static void main(String[] args) {
        try {
            Connection conn = DAO.createConnection();
            System.out.println("Connexion réussie !");
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
