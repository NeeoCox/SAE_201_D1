package controller;
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
 * La classe Controller de l'application
 * @author M.COIGNARD, L.VIMART, A.COUDIERE
 */
public class Controller {

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
	 * Bouton pour la page de connection Admin
	 ***********************************
	 */
	@FXML
	private Button buttonConncetionAdmin;
	@FXML 
	private Button buttonRetourConnectAdmin;

	/**
	 ***********************************
	 * Bouton pour la page acceuil Admin
	 ***********************************
	 */
	@FXML
	private Button buttonRetourAcceuilAdmin;
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

	/**
	 ***********************************
	 * Bouton pour la page connction Secouriste
	 ***********************************
	 */
	@FXML 
	private Button buttonRetourAcceuilSec;
	@FXML 
	private Button buttonConnectSec;

	/**
	 ***********************************
	 * Bouton pour la page acceuil Secouriste
	 ***********************************
	 */

	 @FXML
	 private Button buttonMesComp;
	 @FXML
	 private Button buttonMonPlanning;
	 @FXML
	 private Button buttonMesDisp;


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
		goTo("/pageFxml/PageConnection/PageConnection.fxml", event);
	}

	/**
	 * Methode pour aller a la page de connection Admin
	 * @param event l'evenement de l'action
	 */
	public void goToConnectionAdmin(ActionEvent event){
		System.out.println("goToConnectionAdmin");
		goTo("/pageFxml/PageConnection/ConnectionAdmin.fxml", event);
	}

	/**
	 * Methode pour aller a la page de connection Secouriste
	 * @param event l'evenement de l'action
	 */
	public void goToConnectionSecouriste(ActionEvent event){
		System.out.println("goToConnectionSecouriste");
		goTo("/pageFxml/PageConnection/ConnectionSecouriste.fxml", event);
	}

	/**
	 * Methode pour aller a la page ResetPassWord
	 * @param event l'evenement de l'action
	 */
	public void goToResetPassWord(ActionEvent event){
		System.out.println("goToResetPassWord");
		goTo("/pageFxml/PageConnection/ResetPassWord.fxml", event);
	}

	/**
	 * Methode pour aller a la page ChangePassWord
	 * @param event l'evenement de l'action
	 */
	public void goToChangePassWord(ActionEvent event){
		System.out.println("goToChangePassWord");
		goTo("/pageFxml/PageConnection/ChangePassWord.fxml", event);
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
		goTo("/pageFxml/Administrateur/CreationDeSecouriste.fxml", event);
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
		goTo("/pageFxml/Administrateur/PageAffectationSecouristes.fxml", event);
	}

	public void goToDispositifsDeSecours(ActionEvent event){
		System.out.println("goToDispositifsDeSecours");
		goTo("/pageFxml/Administrateur/DispositifsDeSecours.fxml", event);
	}

	/**
	 ***********************************
	 * METHODE POUR LES BOUTON DE LA PAGE SECOURISTE
	 ***********************************
	 */

	public void goToMesCompetences(ActionEvent event){
		System.out.println("goToMesCompetences");
		goTo("/pageFxml/Secouriste/MesCompetences.fxml", event);
	}

	public void goToMesDisponibilite(ActionEvent event){
		System.out.println("goToMesDisponibilite");
		goTo("/pageFxml/Secouriste/MesDisponibilite.fxml", event);
	}

	public void goToMonPlanningEtAffectations(ActionEvent event){
		System.out.println("goToMonPlanningEtAffectations");
		goTo("/pageFxml/Secouriste/MonPlanningEtAffectations.fxml", event);
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