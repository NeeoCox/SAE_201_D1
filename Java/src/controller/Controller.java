package controller;
// Import des librairies Java
import java.io.IOException;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.net.URL;

//Import des librairies JavaFX
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

//Import de nos classes
import model.dao.DAO;
import model.dao.DAOBesoin;
import model.dao.DAOCompetence;
import model.dao.DAODPS;
import model.dao.DAOJournee;
import model.dao.DAONecessite;
import model.dao.DAOPossede;
import model.dao.DAOSecouriste;
import model.dao.DAOSite;
import model.dao.DAOSport;
import model.persistence.*;
import model.graph.algorithm.*;
import model.service.MngtEstAffecteA;
import model.dao.DAOEstAffecteA;
import model.dao.DAOEstDisponible;




/**
 * La classe Controller de l'application
 * @author M.COIGNARD, L.VIMART, A.COUDIERE
 */
public class Controller  {

	/**
	 ***********************************
	 * Connection base de donnée
	 ***********************************
	 */
	
	// Mettre les DAOSecouriste et autre la
	DAOSecouriste daoSecouriste = new DAOSecouriste();
	DAOBesoin daoBesoin = new DAOBesoin();
	DAOSite daoSite = new DAOSite();
	DAODPS daoDPS = new DAODPS();
	DAOSport daoSport = new DAOSport();
	DAOCompetence daoCompetence = new DAOCompetence();
	DAONecessite daoNecessite = new DAONecessite();
	DAOPossede daoPossede = new DAOPossede();
	DAOJournee daoJournee = new DAOJournee();
	DAOEstDisponible daoEstDisponible = new DAOEstDisponible();


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
	 * Bouton et TextField pour la page de connection Admin
	 ***********************************
	 */
	@FXML
	private Button buttonConncetionAdmin;
	@FXML 
	private Button buttonRetourConnectAdmin;
	@FXML 
	private TextField usernameFieldAmin;
	@FXML 
	private TextField passwordFieldAdmin;


	/**
	 ***********************************
	 * Bouton pour la page connection Secouriste
	 ***********************************
	 */
	@FXML 
	private Button buttonRetourAcceuilSec;
	@FXML 
	private Button buttonConnectSec;
	@FXML 
	private TextField usernameFieldSec;
	@FXML 
	private TextField passwordFieldSec;


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

	/**
	 ***********************************
	 * Bouton pour la page gestion des secouriste
	 ***********************************
	 */
	@FXML
	private Button buttonRetGestSec;
	@FXML
	private Button buttonCreategestSec;
	@FXML
	private Button buttonUpdategestSec;
	@FXML
	private Button buttonDeletegestSec;

	/**
	 ***********************************
	 * Bouton pour la page gestion des compétences
	 ***********************************
	 */
	@FXML
	private Button buttonRetourAcGestComp;
	@FXML
	private Button buttonCreateComp;
	@FXML
	private Button buttonUpdateComp;
	@FXML
	private Button buttonDeleteComp;

	/**
	 ***********************************
	 * Bouton pour la page DPS
	 ***********************************
	 */
	@FXML
	private Button buttonRetourAcDPS;
	@FXML
	private Button buttonCreationDPS;
	@FXML
	private Button buttonModifierDPS;
	@FXML
	private Button buttonSupprimerDPS;

	/**
	 ***********************************
	 * Bouton pour la page Affectation des secouriste
	 ***********************************
	 */
	@FXML
	private Button buttonRetAcAffectSec;
	



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
	//Pour la creation d'un secouriste
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
	private Button buttonRetAcceuilSec;

	//Pour la modification d'un secouriste
	@FXML
	private TextField nomSecModif;
	@FXML
	private TextField prenomSecModif;
	@FXML
	private TextField dateNaissSecModif;
	@FXML
	private TextField mailSecModif;
	@FXML
	private TextField adressSecModif;
	@FXML
	private TextField idSecModif;
	@FXML
	private TextField passWordSecModif;
	@FXML 
	private Button ModifButtonSec;

	//Pour la suppression d'un secouriste
	@FXML
	private TextField idSecDelete;
	@FXML 
	private Button deleteButtonSec;

	/**
	 ***********************************
	 * Texte field et Button pour DPS
	 ***********************************
	 */
	//Pour la création d'un DPS
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
	private TextField CompReqDPSCreate;
	@FXML 
	private TextField nbSecDPSCreate;
	@FXML
	private Button createButtonDPS;

	//Pour la modification d'un DPS
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
	private TextField CompReqDPSModif;
	@FXML 
	private TextField nbSecDPSModif;
	@FXML
	private Button ModifButtonDPS;

	//Pour la suppression d'un DPS
	@FXML
	private TextField idDPSDelete;
	@FXML
	private Button deleteButtonDPS;

	/**
	 ***********************************
	 * Texte field et Button pour Compétences
	 ***********************************
	 */
	
	//Pour créer une compétence
	@FXML
	private TextField intituleCreateComp;
	@FXML
	private TextField necessiteCreateComp;
	@FXML
	private Button createButtonComp;

	//Pour modifier une compétence
	@FXML
	private TextField intituleUpdateComp;
	@FXML
	private TextField necessiteUpdateComp;
	@FXML
	private Button updateButtonComp;

	//Pour supprimer une compétence

	/**
	 ***********************************
	 * Variable pour affichage des secouristes
	 ***********************************
	 */

	@FXML
    private TableView<Secouriste> tableSecouristes;
    @FXML
    private TableColumn<Secouriste, Long> idSecTable;
    @FXML
    private TableColumn<Secouriste, String> nomSecTable;
    @FXML
    private TableColumn<Secouriste, String> prenomSecTable;
	@FXML
    private TableColumn<Secouriste, String> dateNaisSecTable; 
	@FXML
    private TableColumn<Secouriste, String> emailSecTable; 
	@FXML
    private TableColumn<Secouriste, String> telSecTable; 
	@FXML
    private TableColumn<Secouriste, String> adresseSecTable; 
	private ObservableList<Secouriste> dataSec = FXCollections.observableArrayList();

	/**
	 ***********************************
	 * Variable pour affichage des DPS
	 ***********************************
	 */

	@FXML
    private TableView<DPS> tableDPS;
    @FXML
    private TableColumn<DPS, Long> idTableDPS;
    @FXML
    private TableColumn<DPS, String> heureDebTableDPS;
    @FXML
    private TableColumn<DPS, String> heureFinTableDPS;
	@FXML
    private TableColumn<DPS, String> dateTableDPS; 
	@FXML
    private TableColumn<DPS, String> lieuTableDPS; 
	@FXML
    private TableColumn<DPS, String> sportTableDPS;
	private ObservableList<DPS> dataDPS = FXCollections.observableArrayList();


	@FXML
    private FlowPane calendar;

    @FXML
    private Text year;

    @FXML
    private Text month;

	@FXML 
	private TextField compSecCreate;
	/**
	 ***********************************
	 * Variable pour le PLANNING
	 ***********************************
	 */
	@FXML private Label lblWeek;
    @FXML private Label lblMon, lblTue, lblWed, lblThu, lblFri, lblSat, lblSun;
    @FXML private Button btnPrevWeek, btnNextWeek;

    // VBox qui contiennent les labels des jours + les tâches
    @FXML
    private VBox vboxMon;
    @FXML
    private VBox vboxTue;
    @FXML
    private VBox vboxWed;
    @FXML
    private VBox vboxThu;
    @FXML
    private VBox vboxFri;
    @FXML
    private VBox vboxSat;
    @FXML
    private VBox vboxSun;

    private LocalDate currentMonday;

    private Map<LocalDate, List<String>> tasksByDate = new HashMap<>();

    @FXML private GridPane gridWeek;

    private VBox[][] taskBoxes = new VBox[7][24]; // 7 jours * 24h


	GrapheCompetences grapheCompetences;
	private ResultatAffectation resultatAffectation;

	public Controller(){
		System.out.println("controller");

	}
	/**
	 ***********************************
	 * Connection BDD
	 ***********************************
	 */
	public void connectAdmin(ActionEvent event){
		String username = usernameFieldAmin.getText();
		String password = passwordFieldAdmin.getText();

		DAO.setCredentials(username, password);	
		
		goToPageAdminAcceuil(event);
	}

	private void setPerm(String username, String password){
		
	}


	/**
	 ***********************************
	 * Les methode pour changer de page 
	 ***********************************
	 */

	/**
	 ***********************************
	 * Les methode PageConnection 
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
	 * Les methode PageAdmin Acceuil
	 ***********************************
	 */

	
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
	 * Les methode PageAdmin Gestion des secouristes
	 ***********************************
	 */

	public void goToCreationDeSouriste(ActionEvent event){
		System.out.println("goToCreationDeSouriste");
		goTo("/pageFxml/Administrateur/CreationDeSecouriste.fxml", event);
	}

	public void goToModifSecouriste(ActionEvent event){
		System.out.println("goToModifSecouriste");
		goTo("/pageFxml/Administrateur/ModifSecouriste.fxml", event);
	}

	public void goToDeleteSec(ActionEvent event){
		System.out.println("goToDeleteSec");
		goTo("/pageFxml/Administrateur/DeleteSec.fxml", event);
	}

	/**
	 ***********************************
	 * Les methode PageAdmin Gestion des DPS
	 ***********************************
	 */

	public void goToCreationDPS(ActionEvent event){
		System.out.println("goToCreationDPS");
		goTo("/pageFxml/Administrateur/CreationDPS.fxml", event);
	}

	public void goToModifDPS(ActionEvent event){
		System.out.println("goToModifDPS");
		goTo("/pageFxml/Administrateur/ModifDPS.fxml", event);
	}

	public void goToDeleteDPS(ActionEvent event){
		System.out.println("goToDeleteDPS");
		goTo("/pageFxml/Administrateur/DeleteDPS.fxml", event);
	}

	/**
	 ***********************************
	 * Les methode PageAdmin Gestion des Compétences
	 ***********************************
	 */

	public void goToCreateComp(ActionEvent event){
		System.out.println("goToCreateComp");
		goTo("/pageFxml/Administrateur/CreateComp.fxml", event);
	}

	public void goToModifComp(ActionEvent event){
		System.out.println("goToModifComp");
		goTo("/pageFxml/Administrateur/ModifComp.fxml", event);
	}

	public void goToDeleteComp(ActionEvent event){
		System.out.println("goToDeleteComp");
		goTo("/pageFxml/Administrateur/DeleteComp.fxml", event);
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
			String[] compPossede = compSecCreate.getText().split(";");
			List<Possede> listCompPos = new ArrayList<Possede>();

			for(int i = 0; i<compPossede.length; i++){
				Possede compPos = new Possede();
				compPos.setIdSecouriste(idLong);
				compPos.setIntituleCompetence(compPossede[i]);
			}

			Secouriste secouriste = new Secouriste(idLong, nom, prenom, dateNaissance, email, passWord, adresse, listCompPos);
						
			try{
				daoSecouriste.create(secouriste);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la création du secouriste : " + e.getMessage());
			}
		}
		 
	}

	public void updateSecouriste(){
		System.out.println("updateSecouriste");
		if (nomSecModif.getText().isEmpty() || prenomSecModif.getText().isEmpty() ||
			dateNaissSecModif.getText().isEmpty() || 
			mailSecModif.getText().isEmpty() || adressSecModif.getText().isEmpty() || 
			idSecModif.getText().isEmpty() || passWordSecModif.getText().isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
		}
		else{
			String nom = nomSecModif.getText();
			String prenom = prenomSecModif.getText();
			String dateNaissance = dateNaissSecModif.getText();
			String email = mailSecModif.getText();
			String adresse = adressSecModif.getText();
			String id = idSecModif.getText();
			long idLong = Long.parseLong(id); 
			String passWord = passWordSecModif.getText();
			String[] compPossede = compSecCreate.getText().split(";");
			List<Possede> listCompPos = new ArrayList<Possede>();

			for(int i = 0; i<compPossede.length; i++){
				Possede compPos = new Possede();
				compPos.setIdSecouriste(idLong);
				compPos.setIntituleCompetence(compPossede[i]);
			}

			Secouriste secouriste = new Secouriste(idLong, nom, prenom, dateNaissance, email, passWord, adresse, listCompPos);
						
			try{
				daoSecouriste.update(secouriste);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la modification du secouriste : " + e.getMessage());
			}
		}
	}

	public void deleteSecouriste(){
		System.out.println("deleteSecouriste");
		if (idSecDelete.getText().isEmpty()) {
			System.out.println("Veuillez remplir le champ id.");
		}
		else{
			String id = idSecDelete.getText();
			long idLong = Long.parseLong(id); 

			try{
				daoSecouriste.delete(idLong);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la suppression du secouriste : " + e.getMessage());
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
			|| lieuRencDPSCreate.getText().isEmpty() || sportDPSCreate.getText().isEmpty()
			|| nbSecDPSCreate.getText().isEmpty()) {
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

			String nombreStr = nbSecDPSCreate.getText();
			int nombre = Integer.parseInt(nombreStr);
			try{
				
				Besoin besoin;
				for(int i = 0; i<nbCompReq; i++){
					besoin = new Besoin(nombre, compReqStr[i], idDPSLong);
					daoBesoin.create(besoin);
				}
				 
				
				String lieuRenc = lieuRencDPSCreate.getText();
				Site site = daoSite.read(lieuRenc);

				// Sport
				
				String sport = sportDPSCreate.getText();
				Sport sportObj = daoSport.read(sport);

				
				DPS dps = new DPS(idDPSLong, heureDebut, heureFin, journee, site, sportObj);
				daoDPS.create(dps);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la création du DPS : " + e.getMessage());
			}
		}
	}	

	public void updateDispositifDeSecours(){
		System.out.println("updateDispositifDeSecours");
		if (idDPSModif.getText().isEmpty() || heureDebutDPSModif.getText().isEmpty() || 
			heureFinDPSModif.getText().isEmpty() 
			|| lieuRencDPSModif.getText().isEmpty() || sportDPSModif.getText().isEmpty()
			|| nbSecDPSModif.getText().isEmpty()) {
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

			String[] compReqStr = CompReqDPSCreate.getText().split(";");
			int nbCompReq = compReqStr.length;

			String nombreStr = nbSecDPSModif.getText();
			int nombre = Integer.parseInt(nombreStr);
			try{
				Besoin besoin;
				for(int i = 0; i<nbCompReq; i++){
					besoin = new Besoin(nombre, compReqStr[i], idDPSLong);
					daoBesoin.update(besoin);
				}

				String lieuRenc = lieuRencDPSModif.getText();
				Site site = daoSite.read(lieuRenc);

				// Sport

				String sport = sportDPSModif.getText();
				Sport sportObj = daoSport.read(sport);

				DPS dps = new DPS(idDPSLong, heureDebut, heureFin, journee, site, sportObj);
				daoDPS.update(dps);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la modification du DPS : " + e.getMessage());
			}
		}
	}
	
	public void deleteDispositifDeSecours(){
		System.out.println("deleteDispositifDeSecours");
		if (idDPSDelete.getText().isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
		}
		else{		
			String idDPS = idDPSDelete.getText();
			long idDPSLong = Long.parseLong(idDPS);

			try{
				List<Besoin> besoin = daoBesoin.readAll();

				for (Besoin b : besoin) {
					Long idDPSFor = b.getIdDPS();
					if(idDPSFor == idDPSLong){
						daoBesoin.delete(idDPSLong, idDPS);
					}
				}
				daoDPS.delete(idDPSLong);
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la suppression du DPS : " + e.getMessage());
			}
		}
	}

	/**
	 ***********************************
	 * GESTION DES COMPETENCES
	 ***********************************
	 */
	public void initGrapheCompetences() {
		try {

			List<Competence> competences = daoCompetence.readAll();
			List<Necessite> necessites = daoNecessite.readAll();

			this.grapheCompetences = new GrapheCompetences(competences, necessites);

			if (!grapheCompetences.estDAG()) {
				System.out.println("Attention : le graphe des compétences contient un cycle !");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de l'initialisation du graphe des compétences : " + e.getMessage());
		}
	}

	public void createCompetences() {
		if (intituleCreateComp.getText().isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
			return;
		}

		String intituleStr = intituleCreateComp.getText();
		String[] compNecStr = null;
		boolean necessiteVide = necessiteCreateComp.getText().isEmpty();
		if (!necessiteVide) {
			compNecStr = necessiteCreateComp.getText().split(";");
		}

		Competence comp = new Competence();
		comp.setIntitule(intituleStr);

		try {
			

			// Vérifier existence des compétences nécessaires
			List<Competence> dependances = new ArrayList<>();
			if (!necessiteVide) {
				for (String nec : compNecStr) {
					Competence compNec = trouverCompetenceParIntitule(nec, grapheCompetences.getCompetences());
					if (compNec == null) {
						System.out.println("Compétence requise inexistante dans le graphe : " + nec);
						return;
					}
					dependances.add(compNec);
				}
			}

			// Créer une copie temporaire du graphe et tester DAG
			List<Competence> tempCompetences = new ArrayList<>(grapheCompetences.getCompetences());
			tempCompetences.add(comp);
			List<Necessite> tempNecessites = new ArrayList<>(grapheCompetences.getNecessites());
			for (Competence nec : dependances) {
				Necessite n = new Necessite();
				n.setLaCompetence(comp);
				n.setCompetenceNecessaire(nec);
				n.setIntituleCompetence(intituleStr);
				n.setIntituleCompetenceNecessaire(nec.getIntitule());
				tempNecessites.add(n);
			}
			GrapheCompetences grapheTemp = new GrapheCompetences(tempCompetences, tempNecessites);

			if (!grapheTemp.estDAG()) {
				System.out.println("Impossible d'ajouter la compétence : cela créerait un cycle dans le graphe !");
				return;
			}

			// Étape 3 : insérer la compétence
			daoCompetence.create(comp);
			grapheCompetences.ajouterCompetence(comp);

			// Étape 4 : insérer les dépendances dans la base + graphe actuel
			for (Competence nec : dependances) {
				Necessite besoin = new Necessite();
				besoin.setLaCompetence(comp);
				besoin.setCompetenceNecessaire(nec);
				besoin.setIntituleCompetence(intituleStr);
				besoin.setIntituleCompetenceNecessaire(nec.getIntitule());
				daoNecessite.create(besoin);
				grapheCompetences.ajouterNecessite(besoin);
			}

			System.out.println("Compétence ajoutée avec succès.");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la création de la Compétence : " + e.getMessage());
		}
	}

	/**
	 * Recherche une compétence par son intitulé dans une liste.
	 * @param intitule L'intitulé recherché.
	 * @param competences La liste des compétences.
	 * @return La compétence trouvée, ou null si absente.
	 */
	private Competence trouverCompetenceParIntitule(String intitule, List<Competence> competences) {
		for (Competence c : competences) {
			if (c.getIntitule().equals(intitule)) {
				return c;
			}
		}
		return null;
	}

	public void updateCompetence(String ancienIntitule, String nouvelIntitule) {
		if (ancienIntitule == null || nouvelIntitule == null || ancienIntitule.isEmpty() || nouvelIntitule.isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
			return;
		}

		try {
			DAOCompetence daoCompetence = new DAOCompetence();

			Competence comp = trouverCompetenceParIntitule(ancienIntitule, grapheCompetences.getCompetences());
			if (comp == null) {
				System.out.println("Compétence à modifier inexistante.");
				return;
			}

			// Mise à jour en base
			Competence nouvelleComp = new Competence();
			nouvelleComp.setIntitule(nouvelIntitule);
			daoCompetence.update(nouvelleComp, ancienIntitule);

			// Mise à jour dans le graphe
			comp.setIntitule(nouvelIntitule);

			// Mettre à jour les intitulés dans les dépendances (Necessite)
			for (Necessite n : grapheCompetences.getNecessites()) {
				if (n.getIntituleCompetence().equals(ancienIntitule)) {
					n.setIntituleCompetence(nouvelIntitule);
				}
				if (n.getIntituleCompetenceNecessaire().equals(ancienIntitule)) {
					n.setIntituleCompetenceNecessaire(nouvelIntitule);
				}
			}

			System.out.println("Compétence modifiée avec succès.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la modification de la compétence : " + e.getMessage());
		}
	}

	public void deleteCompetence(String intitule) {
		if (intitule == null || intitule.isEmpty()) {
			System.out.println("Veuillez indiquer l'intitulé de la compétence à supprimer.");
			return;
		}

		try {
			DAOCompetence daoCompetence = new DAOCompetence();
			DAONecessite daoNecessite = new DAONecessite();

			// Suppression en base
			daoCompetence.delete(intitule);

			// Suppression des dépendances associées en base et dans le graphe
			List<Necessite> necessites = new ArrayList<>(grapheCompetences.getNecessites());
			for (Necessite n : necessites) {
				if (n.getIntituleCompetence().equals(intitule) || n.getIntituleCompetenceNecessaire().equals(intitule)) {
					daoNecessite.delete(n.getIntituleCompetence(), n.getIntituleCompetenceNecessaire());
					grapheCompetences.supprimerNecessite(n);
				}
			}

			// Suppression dans le graphe
			Competence comp = trouverCompetenceParIntitule(intitule, grapheCompetences.getCompetences());
			if (comp != null) {
				grapheCompetences.supprimerCompetence(comp);
			}

			System.out.println("Compétence supprimée avec succès.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la suppression de la compétence : " + e.getMessage());
		}
	}


	/*
	 * ***********************************
	 * GESTION DES DÉPENDANCES DES COMPÉTENCES
	 ***********************************
	 */

	public void createNecessite() {
		if (intituleCreateComp.getText().isEmpty() || necessiteCreateComp.getText().isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
			return;
		}

		String intituleComp = intituleCreateComp.getText();
		String intituleNec = necessiteCreateComp.getText();

		Competence comp = trouverCompetenceParIntitule(intituleComp, grapheCompetences.getCompetences());
		Competence nec = trouverCompetenceParIntitule(intituleNec, grapheCompetences.getCompetences());

		if (comp == null || nec == null) {
			System.out.println("Compétence ou prérequis inexistant.");
			return;
		}

		try {
			// Créer une copie temporaire pour vérifier le DAG
			List<Necessite> tempNecessites = new ArrayList<>(grapheCompetences.getNecessites());
			Necessite n = new Necessite();
			n.setLaCompetence(comp);
			n.setCompetenceNecessaire(nec);
			n.setIntituleCompetence(intituleComp);
			n.setIntituleCompetenceNecessaire(intituleNec);
			tempNecessites.add(n);

			GrapheCompetences grapheTemp = new GrapheCompetences(grapheCompetences.getCompetences(), tempNecessites);

			if (!grapheTemp.estDAG()) {
				System.out.println("Impossible d'ajouter la dépendance : cela créerait un cycle !");
				return;
			}

			daoNecessite.create(n);
			grapheCompetences.ajouterNecessite(n);

			System.out.println("Dépendance ajoutée avec succès.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la création de la dépendance : " + e.getMessage());
		}
	}

	public void updateNecessite(String intituleComp, String ancienNec, String nouveauNec) {
		// Vérification des champs
		if (intituleComp == null || ancienNec == null || nouveauNec == null ||
			intituleComp.isEmpty() || ancienNec.isEmpty() || nouveauNec.isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
			return;
		}

		Competence comp = trouverCompetenceParIntitule(intituleComp, grapheCompetences.getCompetences());
		Competence ancienneCompetenceNecessaire = trouverCompetenceParIntitule(ancienNec, grapheCompetences.getCompetences());
		Competence nouvelleCompetenceNecessaire = trouverCompetenceParIntitule(nouveauNec, grapheCompetences.getCompetences());

		if (comp == null || ancienneCompetenceNecessaire == null || nouvelleCompetenceNecessaire == null) {
			System.out.println("Compétence ou dépendance inexistante.");
			return;
		}

		try {
			// Créer une copie temporaire du graphe pour tester le DAG
			List<Necessite> tempNecessites = new ArrayList<>(grapheCompetences.getNecessites());
			// Supprimer l'ancienne dépendance
			tempNecessites.removeIf(n -> n.getIntituleCompetence().equals(intituleComp)
									&& n.getIntituleCompetenceNecessaire().equals(ancienNec));
			// Ajouter la nouvelle dépendance
			Necessite nouvelleNecessite = new Necessite();
			nouvelleNecessite.setLaCompetence(comp);
			nouvelleNecessite.setCompetenceNecessaire(nouvelleCompetenceNecessaire);
			nouvelleNecessite.setIntituleCompetence(intituleComp);
			nouvelleNecessite.setIntituleCompetenceNecessaire(nouveauNec);
			tempNecessites.add(nouvelleNecessite);

			GrapheCompetences grapheTemp = new GrapheCompetences(grapheCompetences.getCompetences(), tempNecessites);

			if (!grapheTemp.estDAG()) {
				System.out.println("Impossible de modifier la dépendance : cela créerait un cycle !");
				return;
			}

			// Mise à jour en base
			daoNecessite.delete(intituleComp, ancienNec);
			daoNecessite.create(nouvelleNecessite);

			// Mise à jour dans le graphe interne
			Necessite aSupprimer = null;
			for (Necessite n : grapheCompetences.getNecessites()) {
				if (n.getIntituleCompetence().equals(intituleComp)
					&& n.getIntituleCompetenceNecessaire().equals(ancienNec)) {
					aSupprimer = n;
					break;
				}
			}
			if (aSupprimer != null) {
				grapheCompetences.supprimerNecessite(aSupprimer);
			}
			grapheCompetences.ajouterNecessite(nouvelleNecessite);

			System.out.println("Dépendance modifiée avec succès.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la modification de la dépendance : " + e.getMessage());
		}
	}

	public void deleteNecessite() {
		if (intituleCreateComp.getText().isEmpty() || necessiteCreateComp.getText().isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
			return;
		}

		String intituleComp = intituleCreateComp.getText();
		String intituleNec = necessiteCreateComp.getText();

		try {
			daoNecessite.delete(intituleComp, intituleNec);

			// Suppression dans le graphe
			Necessite aSupprimer = null;
			for (Necessite n : grapheCompetences.getNecessites()) {
				if (n.getIntituleCompetence().equals(intituleComp) &&
					n.getIntituleCompetenceNecessaire().equals(intituleNec)) {
					aSupprimer = n;
					break;
				}
			}
			if (aSupprimer != null) {
				grapheCompetences.supprimerNecessite(aSupprimer);
			}

			System.out.println("Dépendance supprimée avec succès.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la suppression de la dépendance : " + e.getMessage());
		}
	}


	/*
	 ***************************************
	 * AFFECTATION DES SECOURISTES AUX DPS *
	 ***************************************
	 */

	/**
	 * Affecte les secouristes disponibles à la journée donnée, selon l'algo choisi.
	 * @param jour Jour à traiter (objet Journee)
	 * @param algo "glouton" ou "exhaustif"
	 */
	public void affecterSecouristesPourJournee(Journee jour, String algo) {
		try {
			// Récupérer les secouristes disponibles ce jour-là
			List<Secouriste> tousSecouristes = daoSecouriste.readAll();
			List<EstDisponible> dispos = daoEstDisponible.readAll();
			List<Secouriste> secouristesDispos = new ArrayList<>();
			for (EstDisponible dispo : dispos) {
				if (dispo.getJourJournee() == jour.getJour() &&
					dispo.getMoisJournee() == jour.getMois() &&
					dispo.getAnneeJournee() == jour.getAnnee()) {
					Secouriste s = dispo.getSecouriste();
					if (s == null) {
						s = trouverSecouristeParId(dispo.getIdSecouriste(), tousSecouristes);
					}
					if (s != null && !secouristesDispos.contains(s)) {
						secouristesDispos.add(s);
					}
				}
			}

			// Récupérer les DPS de la journée
			List<DPS> tousDPS = daoDPS.readAll();
			List<DPS> dpsJour = new ArrayList<>();
			for (DPS dps : tousDPS) {
				Journee j = dps.getEstProgramme();
				if (j.getJour() == jour.getJour() &&
					j.getMois() == jour.getMois() &&
					j.getAnnee() == jour.getAnnee()) {
					dpsJour.add(dps);
				}
			}

			// Récupérer les besoins pour ces DPS
			List<Besoin> besoins = daoBesoin.readAll();
			List<Besoin> besoinsJour = new ArrayList<>();
			for (Besoin b : besoins) {
				if (contientDPS(b.getLeDPS(), dpsJour)) {
					besoinsJour.add(b);
				}
			}

			// Choix de l'algo
			Affectation algoAffect;
			if ("exhaustif".equalsIgnoreCase(algo)) {
				algoAffect = new model.graph.algorithm.exhaustive.AffectationExhaustive();
			} else {
				algoAffect = new model.graph.algorithm.greedy.AffectationGloutonne();
			}

			// Exécution de l'affectation
			resultatAffectation = algoAffect.affecter(secouristesDispos, dpsJour, besoinsJour);

			// Stockage en base via le service
			DAOEstAffecteA daoEstAffecteA = new DAOEstAffecteA();
			MngtEstAffecteA mngtAffect = new MngtEstAffecteA(daoEstAffecteA);

			for (DPS dps : dpsJour) {
				for (Secouriste s : tousSecouristes) {
					try { mngtAffect.supprimerAffectation(s.getId(), dps.getId()); } catch (Exception ignore) {}
				}
			}

			// On insère les nouvelles affectations
			for (Map.Entry<DPS, List<Secouriste>> entry : resultatAffectation.getAffectations().entrySet()) {
				DPS dps = entry.getKey();
				for (Secouriste s : entry.getValue()) {
					for (Besoin b : besoinsJour) {
						if (b.getLeDPS().equals(dps)) {
							mngtAffect.creerAffectation(s.getId(), b.getLaCompetence().getIntitule(), dps.getId());
						}
					}
				}
			}

			System.out.println("Affectation réalisée pour le " + jour + " avec l'algo " + algo);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de l'affectation : " + e.getMessage());
		}
	}

	/** Recherche un secouriste par son id dans une liste. */
	private Secouriste trouverSecouristeParId(long id, List<Secouriste> secouristes) {
		for (Secouriste s : secouristes) {
			if (s.getId() == id) {
				return s;
			}
		}
		return null;
	}

	/** Vérifie si un DPS est contenu dans une liste (par equals). */
	private boolean contientDPS(DPS dps, List<DPS> liste) {
		for (DPS d : liste) {
			if (d.equals(dps)) {
				return true;
			}
		}
		return false;
	}






	/**
	 ***********************************
	 * Integration des valeurs dans les tables view 
	 ***********************************
	 */

	public void viewAllSecouristes() {
		System.out.println("viewAllSecouristes");

		try {
			// Toujours vider avant d’ajouter
			dataSec.clear();

			// Récupération des secouristes depuis la base
			List<Secouriste> listSec = daoSecouriste.readAll();

			// Ajout dans l’observable list
			dataSec.addAll(listSec);

			// Lier la table à la liste
			tableSecouristes.setItems(dataSec);

			// Définir les mappings colonnes -> attributs de l'objet
			idSecTable.setCellValueFactory(new PropertyValueFactory<>("id"));
			nomSecTable.setCellValueFactory(new PropertyValueFactory<>("nom"));
			prenomSecTable.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			dateNaisSecTable.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
			emailSecTable.setCellValueFactory(new PropertyValueFactory<>("email"));
			telSecTable.setCellValueFactory(new PropertyValueFactory<>("tel"));
			adresseSecTable.setCellValueFactory(new PropertyValueFactory<>("adresse"));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de l'affichage des secouristes : " + e.getMessage());
		}
	}


	public void viewAllDPS(){
		try{
			List<DPS> listDPS = daoDPS.readAll();

			for(DPS dps : listDPS){
				dataDPS.add(dps);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Erreur lors de l'affichage des DPS : " + e.getMessage());
		}
	}

	

	/**
	 ***********************************
	 * GESTION DU PLANNING
	 ***********************************
	 */

	@FXML
	public void initialize() {
		System.out.println("hola");
		// Si on est sur la page du planning
		if (gridWeek != null) {
			LocalDate today = LocalDate.now();
			currentMonday = today.with(DayOfWeek.MONDAY);
			generateHourLabelsAndTaskBoxes();
			afficherSemaine(currentMonday);
			btnPrevWeek.setOnAction(e -> afficherSemaine(currentMonday.minusWeeks(1)));
			btnNextWeek.setOnAction(e -> afficherSemaine(currentMonday.plusWeeks(1)));

			vboxMon.setFillWidth(true);
			vboxTue.setFillWidth(true);
			vboxWed.setFillWidth(true);
			vboxThu.setFillWidth(true);
			vboxFri.setFillWidth(true);
			vboxSat.setFillWidth(true);
			vboxSun.setFillWidth(true);
		}

		// Si on est sur la page secouristes
		if (tableSecouristes != null) {
			System.out.println("hola deux");
			viewAllSecouristes();
		}
	}

    public void addTask(LocalDate date, String taskDescription) {
        tasksByDate.computeIfAbsent(date, k -> new ArrayList<>()).add(taskDescription);
        if (isDateInCurrentWeek(date)) {
            afficherTachesPourDate(date);
        }
    }

    private boolean isDateInCurrentWeek(LocalDate date) {
        return !date.isBefore(currentMonday) && !date.isAfter(currentMonday.plusDays(6));
    }

    public void afficherSemaine(LocalDate monday) {
        currentMonday = monday;

        // Met à jour les labels des jours avec les dates
        updateDayLabels();

        // Vide les VBoxes des jours (sauf le label titre)
        clearAllDayBoxes();

        // Ajoute les tâches existantes dans la semaine
        for (int i = 0; i < 7; i++) {
            LocalDate date = currentMonday.plusDays(i);
            afficherTachesPourDate(date);
        }
    }

    private void afficherTachesPourDate(LocalDate date) {
        int dayIndex = (int) ChronoUnit.DAYS.between(currentMonday, date);
        if (dayIndex < 0 || dayIndex > 6) return;

        VBox dayBox = getDayVBox(dayIndex);
        if (dayBox == null) return;

        // Le premier enfant est le label du jour, on vide le reste
        // Donc on garde uniquement le label du jour (enfant 0)
        while (dayBox.getChildren().size() > 1) {
            dayBox.getChildren().remove(1);
        }

        List<String> tasks = tasksByDate.getOrDefault(date, Collections.emptyList());
        for (String task : tasks) {
            Label taskLabel = new Label(task);
            taskLabel.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 3; -fx-border-radius: 3; -fx-background-radius: 3;");
            dayBox.getChildren().add(taskLabel);
        }
    }

    private void clearAllDayBoxes() {
        vboxMon.getChildren().retainAll(lblMon);
        vboxTue.getChildren().retainAll(lblTue);
        vboxWed.getChildren().retainAll(lblWed);
        vboxThu.getChildren().retainAll(lblThu);
        vboxFri.getChildren().retainAll(lblFri);
        vboxSat.getChildren().retainAll(lblSat);
        vboxSun.getChildren().retainAll(lblSun);
    }

    private void updateDayLabels() {
        lblMon.setText("Lundi\n" + currentMonday);
        lblTue.setText("Mardi\n" + currentMonday.plusDays(1));
        lblWed.setText("Mercredi\n" + currentMonday.plusDays(2));
        lblThu.setText("Jeudi\n" + currentMonday.plusDays(3));
        lblFri.setText("Vendredi\n" + currentMonday.plusDays(4));
        lblSat.setText("Samedi\n" + currentMonday.plusDays(5));
        lblSun.setText("Dimanche\n" + currentMonday.plusDays(6));
    }



    private VBox getDayVBox(int dayIndex) {
        switch (dayIndex) {
            case 0: return vboxMon;
            case 1: return vboxTue;
            case 2: return vboxWed;
            case 3: return vboxThu;
            case 4: return vboxFri;
            case 5: return vboxSat;
            case 6: return vboxSun;
            default: return null;
        }
    }

    /**
     * 
     * @param event
     */
    @FXML
    public void onAddTaskClicked(ActionEvent event) {
        // Exemple : on ajoute une tâche fixe au mercredi de la semaine affichée
        LocalDate taskDate = currentMonday.plusDays(2); // mercredi
        String taskDesc = "Tâche prédéfinie";

        addTask(taskDate, taskDesc);
    }

    private void generateHourLabelsAndTaskBoxes() {
		if (gridWeek == null) {
			System.err.println("gridWeek n'est pas encore initialisé");
			return;
		}

		for (int hour = 0; hour < 24; hour++) {
			// Colonne 0 : heure
			Label hourLabel = new Label(String.format("%02dh", hour));
			hourLabel.setStyle("-fx-font-size: 10px;");
			GridPane.setRowIndex(hourLabel, hour + 1); // +1 car ligne 0 = titres
			GridPane.setColumnIndex(hourLabel, 0);
			gridWeek.getChildren().add(hourLabel);

			// Colonnes 1 à 7 : VBoxes pour chaque jour
			for (int day = 0; day < 7; day++) {
				VBox box = new VBox();
				box.setSpacing(2);
				box.setPadding(new Insets(2));
				box.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #cccccc;");
				GridPane.setRowIndex(box, hour + 1);
				GridPane.setColumnIndex(box, day + 1);
				gridWeek.getChildren().add(box);
				taskBoxes[day][hour] = box;
			}
		}
	}
}