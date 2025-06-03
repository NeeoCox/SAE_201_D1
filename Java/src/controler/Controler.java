package controler;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
/**
 * La classe Controler de l'application
 * @author M.COIGNARD, L.VIMART, A.COUDIERE
 */
public class Controler {

	private Stage stage;

	/**
	 ***********************************
	 * Bouton pour la page de connexion
	 ***********************************
	 */
	@FXML
	private Button buttonAdmin;
	@FXML
	private Button buttonScouriste;

	/**
	 ***********************************
	 * Bouton pour la page acceuil Admin
	 ***********************************
	 */
	@FXML
	private Button buttonAffectSec;
	@FXML
	private Button buttonGestSec;
	@FXML
	private Button buttonDPS;
	@FXML 
	private Button buttonGestComp;
	@FXML 
	private Button buttonCreationSec;


	public Controler(){
		System.out.println("controler");
	}

	/**
	 ***********************************
	 * Methode pour changer de page de la page connexion
	 ***********************************
	 */

    /**
	 * Methode pour aller a la page acceuil admin
	 * @param event
	 */
	public void goToPageAdminAcceuil(ActionEvent event) {
		System.out.println("goToPageAdminAcceuil");
		goTo("/pageFxml/Administrateur/PageAdminAcceuil.fxml", event);
	}

	/**
	 * Methode pour aller a la page acceuil secouriste 
	 * @param event
	 */
	public void goToPageSecouristeAcceuil(ActionEvent event){
		System.out.println("goToPageSecouristeAcceuil");
		goTo("/pageFxml/Secouriste/PageSecouristeAcceuil.fxml", event);
	}

	/**
	 ***********************************
	 * Methode pour changer de page de la page acceuil admin
	 ***********************************
	 */

	public void goToCreationDeSouriste(ActionEvent event){
		System.out.println("goToCreationDeSouriste");
		goTo("/pageFxml/Administrateur/CreationSecouriste.fxml", event);
	}

	public void goToGestionDesSecouristes(ActionEvent event){
		System.out.println("goToGestionDesSecouristes");
		goTo("/pageFxml/Administrateur/GestionDesSecouristes.fxml", event);
	}

	public void goToGestionDesCompetences(ActionEvent event){
		System.out.println("goToGestionDesCompetences");
		goTo("/pageFxml/Administrateur/GestionDesCompetences.fxml", event);
	}
	
	public void goToPageAffectationSecouristes(ActionEvent event){
		System.out.println("goToPageAffectationSecouristes");
		goTo("/pageFxml/Administrateur/AffectationSecouristes.fxml", event);
	}

	public void goToDispositifsDeSecours(ActionEvent event){
		System.out.println("goToDispositifsDeSecours");
		goTo("/pageFxml/Administrateur/DispositifsDeSecours.fxml", event);
	}
	

	/**
	 * Methode pour charger la page a afficher 
	 * @param fichier acces au fichier fxml
	 * @param event event de l'action
	 */
	private void goTo(String fichier, ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(fichier));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.err.println("Resource not found: " + fichier);
		}
	}

	public void initializer(){
		System.out.println("Initializer");

	}
	
}