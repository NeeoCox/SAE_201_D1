import model.persistence.*;

public class Scenario {
    public static void main(String[] args) {
        //On va tester un scénario d'affectation de secouriste a un DPS
        //On va d'abbord créer les Secouristes

        Secouriste secouriste1 = new Secouriste(1, "Coignard", "Maël", "16-10-2005", "maelcoignard16@gmail.com", "0650166242", "1 rue de la paix, 75000 Paris");
        Secouriste secouriste2 = new Secouriste(2, "Vimart", "Léa", "22-05-2005", "leavimart@gmail.com", "07502698", "2 rue de la paix, 75000 Paris");
        Secouriste secouriste3 = new Secouriste(3, "Coudière", "Adrien", "06-06-2005", "maelcoignard16@gmail.com", "12582259", "6 rue de la paix, 75000 Paris");
        Secouriste secouriste4 = new Secouriste(4, "Coignard", "Maël", "1999-01-01", "maelcoignard16@gmail.com", "27168411", "7 rue de la paix, 75000 Paris");

        

        
    }
}
