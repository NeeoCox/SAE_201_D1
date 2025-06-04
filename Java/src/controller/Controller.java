package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;

/**
 * La classe Controller de l'application
 * @author M.COIGNARD, L.VIMART, A.COUDIERE
 */
public class Controller {

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


	public Controller(){
		System.out.println("controller");
	}

	/**
	 ***********************************
	 * TOUTES LES METHODE DES BOUTON DES PAGES DE CONNEXION 
	 ***********************************
	 */

	 /**
	  * Methode pour aller a la page de connexion
	  * @param event l'evenement de l'action
	  */
	public void goToPageConnection(ActionEvent event){
		System.out.println("goToPageConnection");
		goTo("/pageFxml/PageConnexion/PageConnection.fxml", event);
	}

	/**
	 * Methode pour aller a la page de connection Admin
	 * @param event l'evenement de l'action
	 */
	public void goToConnectionAdmin(ActionEvent event){
		System.out.println("goToConnectionAdmin");
		goTo("/pageFxml/PageConnexion/PageConnectionAdmin.fxml", event);
	}

	/**
	 * Methode pour aller a la page de connection Secouriste
	 * @param event l'evenement de l'action
	 */
	public void goToConnectionSecouriste(ActionEvent event){
		System.out.println("goToConnectionSecouriste");
		goTo("/pageFxml/PageConnexion/PageConnectionSecouriste.fxml", event);
	}

	/**
	 * Methode pour aller a la page ResetPassWord
	 * @param event l'evenement de l'action
	 */
	public void goToResetPassWord(ActionEvent event){
		System.out.println("goToResetPassWord");
		goTo("/pageFxml/PageConnexion/ResetPassWord.fxml", event);
	}

	/**
	 * Methode pour aller a la page ChangePassWord
	 * @param event l'evenement de l'action
	 */
	public void goToChangePassWord(ActionEvent event){
		System.out.println("goToChangePassWord");
		goTo("/pageFxml/PageConnexion/ChangePassWord.fxml", event);
	}

    /**
	 * Methode pour aller a la page acceuil admin
	 * @param event l'evenement de l'action
	 */
	public void goToPageAdminAcceuil(ActionEvent event) {
		System.out.println("goToPageAdminAcceuil");
		goTo("/pageFxml/Administrateur/PageAdminAcceuil.fxml", event);
	}

	/**
	 * Methode pour aller a la page acceuil secouriste 
	 * @param event l'evenement de l'action
	 */
	public void goToPageSecouristeAcceuil(ActionEvent event){
		System.out.println("goToPageSecouristeAcceuil");
		goTo("/pageFxml/Secouriste/PageSecouristeAcceuil.fxml", event);
	}

	/**
	 ***********************************
	 * METHODE POUR LES BOUTON DE LA PAGE ADMIN
	 ***********************************
	 */

	/**
	 * 
	 * @param event
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