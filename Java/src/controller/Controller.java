package controller;
// Import des librairies Java
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//Import des librairies JavaFX
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;

//Import de nos class
import model.persistence.Secouriste;
import model.persistence.Site;
import model.persistence.Sport;
import model.service.MngtDPS;
import model.service.MngtSecouriste;
import model.service.MngtSite;
import model.service.MngtSport;
import model.dao.DAODPS;
import model.dao.DAOSecouriste;
import model.dao.DAOSite;
import model.dao.DAOSport;
import model.persistence.Journee;

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

	/**
	 ***********************************
	 * Texte field et Button pour secouriste
	 ***********************************
	 */
	@FXML
	private TextField nomSec;
	@FXML
	private TextField prenomSec;
	@FXML
	private TextField idSec;
	@FXML
	private TextField passWordSec;
	@FXML
	private TextField dateNaissSec;
	@FXML
	private TextField mailSec;
	@FXML
	private TextField adressSec;
	@FXML 
	private Button createButtonSec;
	@FXML
	private TextField idSecDelete;
	@FXML
	private Button deleteButtonSec;

	private MngtSecouriste mngtSecouriste;

	/**
	 ***********************************
	 * Texte field et Button pour DPS
	 ***********************************
	 */
	@FXML
	private TextField idDPSCreate;
	@FXML
	private TextField heureDebutDPSCreate;
	@FXML
	private TextField heureFinDPSCreate;
	@FXML
	private DatePicker dateCreateDPS;
	@FXML
	private TextField lieuRencDPSCreate;
	@FXML
	private TextField sportDPSCreate;
	@FXML
	private Button createButtonDPS;

	@FXML
	private TextField idDPSModif;
	@FXML
	private TextField heureDebutDPSModif;
	@FXML
	private TextField heureFinDPSModif;
	@FXML
	private DatePicker dateModifDPS;
	@FXML
	private TextField lieuRencDPSModif;
	@FXML
	private TextField sportDPSModif;
	@FXML
	private Button ModifButtonDPS;
	@FXML
	private TextField CompReqDPSCreate;
	@FXML
	private TextField CompReqDPSModif;

	private MngtDPS mngtDPS;

	private MngtSite mngtSite;

	private MngtSport mngtSport;

	
	public Controller(){
		System.out.println("controller");

	}

	public void initializer(){
		System.out.println("Initializer");
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

	/**
	 ***********************************
	 * GESTION DES SECOURISTES
	 ***********************************
	 */

	 /**
	  * Permet de créer un secouriste
	  */
	public void createSecouriste(){
		System.out.println("createSecouriste");
		// Recup des valeur dans les textField
		if (nomSec.getText().isEmpty() || prenomSec.getText().isEmpty() || dateNaissSec.getText().isEmpty() || 
			mailSec.getText().isEmpty() || adressSec.getText().isEmpty() || idSec.getText().isEmpty() || passWordSec.getText().isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
		}
		else{
			String nom = nomSec.getText();
			String prenom = prenomSec.getText();
			String dateNaissance = dateNaissSec.getText();
			String email = mailSec.getText();
			String adresse = adressSec.getText();
			String id = idSec.getText();
			long idLong = Long.parseLong(id); 
			String passWord = passWordSec.getText();

			DAOSecouriste daoSecouriste = new DAOSecouriste(null);// La connexion a la base de donné 
			mngtSecouriste = new MngtSecouriste(daoSecouriste);

			try{
				mngtSecouriste.creerSecouriste(idLong, nom, prenom, dateNaissance, email, passWord, adresse);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la création du secouriste : " + e.getMessage());
			}
		}
		 
	}

	public void updateSecouriste(){
		System.out.println("updateSecouriste");
		if (nomSec.getText().isEmpty() || prenomSec.getText().isEmpty() || dateNaissSec.getText().isEmpty() || 
			mailSec.getText().isEmpty() || adressSec.getText().isEmpty() || idSec.getText().isEmpty() || passWordSec.getText().isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
		}
		else{
			String nom = nomSec.getText();
			String prenom = prenomSec.getText();
			String dateNaissance = dateNaissSec.getText();
			String email = mailSec.getText();
			String adresse = adressSec.getText();
			String id = idSec.getText();
			long idLong = Long.parseLong(id); 
			String passWord = passWordSec.getText();

			DAOSecouriste daoSecouriste = new DAOSecouriste(null);// La connexion a la base de donné 
			mngtSecouriste = new MngtSecouriste(daoSecouriste);

			try{
				mngtSecouriste.modifierSecouriste(idLong, nom, prenom, dateNaissance, email, passWord, adresse);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la création du secouriste : " + e.getMessage());
			}
		}
	}

	public void deleteSecouriste(){
		System.out.println("deleteSecouriste");
		if (idSec.getText().isEmpty()) {
			System.out.println("Veuillez remplir le champ id.");
		}
		else{
			String id = idSecDelete.getText();
			long idLong = Long.parseLong(id); 

			DAOSecouriste daoSecouriste = new DAOSecouriste(null);// La connexion a la base de donné 
			mngtSecouriste = new MngtSecouriste(daoSecouriste);

			try{
				mngtSecouriste.supprimerSecouriste(idLong);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la création du secouriste : " + e.getMessage());
			}
		}
	}

	/**
	 ***********************************
	 * GESTION DISPOSITIF DE SECOURS
	 ***********************************
	 */

	public void createDispositifDeSecours(){
		System.out.println("crateDispositifDeSecours");
		if (idDPSCreate.getText().isEmpty() || heureDebutDPSCreate.getText().isEmpty() || 
			heureFinDPSCreate.getText().isEmpty() 
			|| lieuRencDPSCreate.getText().isEmpty() || sportDPSCreate.getText().isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
		}
		else{
			String idDPS = idDPSCreate.getText();
			long idDPSLong = Long.parseLong(idDPS);

			String heureDebutStr = heureDebutDPSCreate.getText();
			int heureDebut = Integer.parseInt(heureDebutStr);

			String heureFinStr = heureFinDPSCreate.getText();
			int heureFin = Integer.parseInt(heureFinStr);

			//DATE
			LocalDate selectedDate = dateCreateDPS.getValue();
    
			int jour = selectedDate.getDayOfMonth(); 
			int mois = selectedDate.getMonthValue();   
			int annee = selectedDate.getYear();        
			
			Journee journee = new Journee(jour, mois, annee);

			String[] compReqStr = CompReqDPSCreate.getText().split(";");
			int nbCompReq = compReqStr.length;

			try{
				DAOSite daoSite = new DAOSite(null);// La connexion a la base de donné 
				mngtSite = new MngtSite(daoSite);
				
				String lieuRenc = lieuRencDPSCreate.getText();
				Site site = mngtSite.lireSite(lieuRenc);

				// Sport
				DAOSport daoSport = new DAOSport(null); // La connexion a la base de donné 
				mngtSport = new MngtSport(daoSport);

				String sport = sportDPSCreate.getText();
				Sport sportObj = mngtSport.lireSport(sport);

				DAODPS daoDPS = new DAODPS(null);// La connexion a la base de donné 
				mngtDPS = new MngtDPS(daoDPS);
				mngtDPS.creerDPS(idDPSLong, heureDebut,heureFin, journee, site, sportObj);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la création du DPS : " + e.getMessage());
			}
		}
	}	

	public void updateDispositifDeSecours(){
		System.out.println("crateDispositifDeSecours");
		if (idDPSModif.getText().isEmpty() || heureDebutDPSModif.getText().isEmpty() || 
			heureFinDPSModif.getText().isEmpty() 
			|| lieuRencDPSModif.getText().isEmpty() || sportDPSModif.getText().isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
		}
		else{
			String idDPS = idDPSModif.getText();
			long idDPSLong = Long.parseLong(idDPS);

			String heureDebutStr = heureDebutDPSModif.getText();
			int heureDebut = Integer.parseInt(heureDebutStr);

			String heureFinStr = heureFinDPSModif.getText();
			int heureFin = Integer.parseInt(heureFinStr);

			//DATE
			LocalDate selectedDate = dateModifDPS.getValue();
    
			int jour = selectedDate.getDayOfMonth(); 
			int mois = selectedDate.getMonthValue();   
			int annee = selectedDate.getYear();        
			
			Journee journee = new Journee(jour, mois, annee);

			try{
				DAOSite daoSite = new DAOSite(null);// La connexion a la base de donné 
				mngtSite = new MngtSite(daoSite);
				
				String lieuRenc = lieuRencDPSModif.getText();
				Site site = mngtSite.lireSite(lieuRenc);

				// Sport
				DAOSport daoSport = new DAOSport(null); // La connexion a la base de donné 
				mngtSport = new MngtSport(daoSport);

				String sport = sportDPSModif.getText();
				Sport sportObj = mngtSport.lireSport(sport);

				DAODPS daoDPS = new DAODPS(null);// La connexion a la base de donné 
				mngtDPS = new MngtDPS(daoDPS);
				mngtDPS.modifierDPS(idDPSLong, heureDebut,heureFin, journee, site, sportObj);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la création du DPS : " + e.getMessage());
			}
		}
	}
	
	public void deleteDispositifDeSecours(){
		System.out.println("crateDispositifDeSecours");
		if (idDPSModif.getText().isEmpty() || heureDebutDPSModif.getText().isEmpty() || 
			heureFinDPSModif.getText().isEmpty() 
			|| lieuRencDPSModif.getText().isEmpty() || sportDPSModif.getText().isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
		}
		else{
			String idDPS = idDPSModif.getText();
			long idDPSLong = Long.parseLong(idDPS);

			try{
				DAODPS daoDPS = new DAODPS(null);// La connexion a la base de donné 
				mngtDPS = new MngtDPS(daoDPS);
				mngtDPS.supprimerDPS(idDPSLong);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la création du DPS : " + e.getMessage());
			}
		}
	}

	/**
	 ***********************************
	 * GESTION DES COMPETENCES
	 ***********************************
	 */

	
}