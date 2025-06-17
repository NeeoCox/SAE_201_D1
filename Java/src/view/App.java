package view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        try {
            System.out.println("lancement de l'application");

            URL fxmlLocation = new URL("file:../fxml/pageFxml/PageConnection/PageConnection.fxml");
            Parent root = FXMLLoader.load(fxmlLocation);

            stage.setTitle("Application gestion planning");
            stage.setScene(new Scene(root));
            
            stage.getIcons().add(new Image("file:../fxml/img/logo.png"));
            
		    stage.centerOnScreen();
            
            stage.show();
            
            
        } catch (IOException e) {
            System.out.println("Erreur");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
