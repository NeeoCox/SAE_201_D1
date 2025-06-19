package controller;
// Import des librairies Java
import java.io.IOException;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Import des librairies JavaFX
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

//Import nos class
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
import model.graph.algorithm.Affectation;
import model.graph.algorithm.exhaustive.AffectationExhaustive;
import model.graph.algorithm.greedy.AffectationGloutonne;
import model.persistence.Besoin;
import model.persistence.Competence;
import model.persistence.DPS;
import model.persistence.Journee;
import model.persistence.Necessite;
import model.persistence.Possede;
import model.persistence.Secouriste;
import model.persistence.Site;
import model.persistence.Sport;
import model.graph.algorithm.ResultatAffectation;
import model.graph.algorithm.dag.VerificationDAG;
import model.graph.algorithm.BesoinUnitaire;
import model.graph.algorithm.GrapheCompetences;
import model.persistence.EstDisponible;
import model.dao.DAOEstDisponible;
import model.persistence.EstAffecteA;
import model.service.MngtEstAffecteA;
import model.dao.DAOEstAffecteA;
import model.service.MngtSession;
import model.persistence.User;
import model.dao.DAOUser;




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
	private TextField usernameField;
	@FXML 
	private TextField passwordField;


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
	private TextField telSec;
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
	private TextField telSecModif;
	@FXML
	private TextField compSecModif;
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
	private Button deleteButtonDPS;

	/**
	 ***********************************
	 * Texte field et Button pour Compétences
	 ***********************************
	 */
	
	//Pour créer une compétence
	@FXML
	private TextField intitulerCreateComp;
	@FXML
	private TextField necessiteCreateComp;
	@FXML
	private Button createButtonComp;

	//Pour modifier une compétence
	@FXML
	private TextField intitulerUpdateComp;
	@FXML
	private TextField necessiteUpdateComp;
	@FXML
	private TextField newIntitulerUpdateComp;
	@FXML
	private Button updateCompetence;

	//Pour supprimer une compétence

	@FXML
	private TextField intitulerDeleteComp;
	@FXML
	private Button deleteButtonComp;


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
    private TableColumn<DPS, Integer> heureDebTableDPS;
    @FXML
    private TableColumn<DPS, Integer> heureFinTableDPS;
	@FXML
    private TableColumn<DPS, String> dateTableDPS; 
	@FXML
    private TableColumn<DPS, String> lieuTableDPS; 
	@FXML
    private TableColumn<DPS, String> sportTableDPS;
	private ObservableList<DPS> dataDPS = FXCollections.observableArrayList();

	/**
	 ***********************************
	 * Variable pour affichage des Compétences
	 ***********************************
	 */
	@FXML
    private TableView<Possede> tableCompetencesSec;
    @FXML
    private TableColumn<Possede, Long> colSecouriste;
    @FXML
    private TableColumn<Possede, String> colIntituleCompSec;

	private ObservableList<Possede> dataCompSec = FXCollections.observableArrayList();


	@FXML
    private TableView<Competence> tableCompetences;
    @FXML
    private TableColumn<Competence, String> colIntituleComp;

	private ObservableList<Competence> dataComp = FXCollections.observableArrayList();


	@FXML
    private TableView<Necessite> tableCompetencesNec;
    @FXML
    private TableColumn<Necessite, String> colIntitule;
	@FXML
    private TableColumn<Necessite, String> colIntituleNec;
	
	private ObservableList<Necessite> dataCompNec = FXCollections.observableArrayList();

	/**
	 ***********************************
	 * Variable pour affichage des Compétences secouriste
	 ***********************************
	 */
	@FXML
    private TableView<Possede> tableMesCompetencesSec;
    @FXML
    private TableColumn<Possede, String> colIntituleMesCompSec;
	private ObservableList<Possede> dataMesComp = FXCollections.observableArrayList();


	
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
    @FXML private Button btnSemainePrecedente, btnSemaineSuivante;
	@FXML private Button btnAffectationAdmin;
	@FXML private Button btnRetourPlanningSecouriste;
	@FXML private RadioButton radioGlouton;
	@FXML private RadioButton radioExhaustif;
	@FXML private DatePicker datePickerAffectation;
	@FXML private Button btnAffecterSecouristes;
	@FXML private ToggleGroup algoGroup;
	@FXML private DatePicker datePickerSuppression;

    private LocalDate currentMonday;

    private Map<LocalDate, List<String>> tasksByDate = new HashMap<>();

    @FXML
	private GridPane gridWeek;

    private VBox[][] taskBoxes = new VBox[7][24]; // 7 jours * 24h


	GrapheCompetences grapheCompetences;

	
	private ResultatAffectation resultatAffectation;

	/**
	 ***********************************
	 * Gestion connection d'un secouriste
	 ***********************************
	 */
	private Secouriste utilisateurConnecte;

    public void setUtilisateurConnecte(Secouriste secouriste) {
        this.utilisateurConnecte = secouriste;
    }

    public long getIdUtilisateurConnecte() {
        return utilisateurConnecte != null ? utilisateurConnecte.getId() : -1;
    }

	public Controller(){
		System.out.println("controller");

	}

	@FXML
	public void connectUser(ActionEvent event) {
		System.out.println("connectUser");
		String username = usernameField.getText();
		String password = passwordField.getText();

		try {
			DAOUser daoUser = new DAOUser();
			User user = daoUser.readByUsername(username);

			if (user != null) {
				if (verifyPassword(password, user.getPasswordHash())) {
					if ("secouriste".equals(user.getRole())) {
						DAOSecouriste daoSec = new DAOSecouriste();
						Secouriste secouriste = daoSec.readByNom(username); // Recherche par nom (username = nom)
						if (secouriste != null) {
							System.out.println("Secouriste trouvé : " + secouriste);
							MngtSession.setUtilisateurConnecte(secouriste);
							goToPageSecouristeAcceuil(event);
						} else {
							System.out.println("Aucun secouriste trouvé avec ce nom.");
						}
					} else if ("admin".equals(user.getRole())) {
						System.out.println("Connexion admin réussie");
						MngtSession.setUtilisateurConnecte(user);  // Ici on stocke l'objet User
						goToPageAdminAcceuil(event);
					}
				} else {
					System.out.println("Mot de passe incorrect");
				}
			} else {
				System.out.println("Nom d'utilisateur non trouvé");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la connexion");
		}
	}




	/**
	 * Méthode utilitaire locale pour vérifier le mot de passe.
	 * À remplacer par une vraie vérification de hash si besoin.
	 */
	private boolean verifyPassword(String password, String passwordHash) {
		
		return password.equals(passwordHash);
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

	@FXML
	public void goToModifSecouriste(ActionEvent event) {
		System.out.println("goToModifSecouriste");
		Secouriste sec = tableSecouristes.getSelectionModel().getSelectedItem();
		if (sec == null) {
			// Affiche un message d'erreur ou une alerte
			System.out.println("Veuillez sélectionner un secouriste à modifier.");
			return;
		}
		MngtSession.setIdSecouristeAModifier(sec.getId());
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

	public void goToModifDPS(ActionEvent event) {
		System.out.println("goToModifDPS");
		DPS dps = tableDPS.getSelectionModel().getSelectedItem();
		if (dps == null) {
			System.out.println("Veuillez sélectionner un DPS à modifier.");
			return;
		}
		MngtSession.setIdDPSAModifier(dps.getId());
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
		Competence comp = tableCompetences.getSelectionModel().getSelectedItem();
		if (comp == null) {
			System.out.println("Veuillez sélectionner une compétence à modifier.");
			return;
		}
		MngtSession.setIntituleCompetenceAModifier(comp.getIntitule());
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
	@FXML
	public void createSecouriste() {
		System.out.println("createSecouriste");

		// Vérification des champs requis
		if (nomSec.getText().isEmpty() || prenomSec.getText().isEmpty() || dateNaissSec.getText().isEmpty() ||
			mailSec.getText().isEmpty() || adressSec.getText().isEmpty() || telSec.getText().isEmpty() || idSec.getText().isEmpty()) {
			
			System.out.println("Veuillez remplir tous les champs.");
			return;
		}

		try {
			String nom = nomSec.getText();
			String prenom = prenomSec.getText();
			String dateNaissance = dateNaissSec.getText();
			String email = mailSec.getText();
			String adresse = adressSec.getText();
			String telephone = telSec.getText();
			long idLong = Long.parseLong(idSec.getText());

			// Liste des compétences possédées (si champ présent)
			String[] compPossede = compSecCreate.getText().split(";");
			List<Possede> listCompPos = new ArrayList<>();

			// Création du secouriste
			
			for (String comp : compPossede) {
				Possede compPos = new Possede();
				compPos.setIdSecouriste(idLong);
				compPos.setIntituleCompetence(comp.trim());
				listCompPos.add(compPos);
			}
			
			Secouriste secouriste = new Secouriste(idLong, nom, prenom, dateNaissance, email, telephone, adresse, listCompPos);

			daoSecouriste.create(secouriste);
			
			for (String comp : compPossede) {
				Possede compPos = new Possede();
				compPos.setIdSecouriste(idLong);
				compPos.setSecouriste(secouriste);
				compPos.setIntituleCompetence(comp.trim());
				Competence competence = new Competence();
				competence.setIntitule(comp.trim());

				compPos.setLaCompetence(competence);
				daoPossede.create(compPos);
			}

			
			

			System.out.println("Secouriste créé avec succès.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la création du secouriste : " + e.getMessage());
		}
	}


	public void updateSecouriste() {
		System.out.println("updateSecouriste");

		if (nomSecModif.getText().isEmpty() || prenomSecModif.getText().isEmpty() ||
			dateNaissSecModif.getText().isEmpty() || 
			mailSecModif.getText().isEmpty() || adressSecModif.getText().isEmpty() || 
			idSecModif.getText().isEmpty() || telSecModif.getText().isEmpty()) {

			System.out.println("Veuillez remplir tous les champs.");
			return;
		}

		try {
			String nom = nomSecModif.getText().trim();
			String prenom = prenomSecModif.getText().trim();
			String dateNaissance = dateNaissSecModif.getText().trim();
			String email = mailSecModif.getText().trim();
			String adresse = adressSecModif.getText().trim();
			String telephone = telSecModif.getText().trim();
			long idLong = Long.parseLong(idSecModif.getText().trim());

			String[] compPossede = compSecModif.getText().split(";");
			List<Possede> listCompPos = new ArrayList<>();

			DAOCompetence daoCompetence = new DAOCompetence();
			DAOPossede daoPossede = new DAOPossede();
			DAOSecouriste daoSecouriste = new DAOSecouriste();

			for (String comp : compPossede) {
				String intitule = comp.trim();
				
				// Vérifie si la compétence existe
				if (!daoCompetence.exists(intitule)) {
					Competence newComp = new Competence();
					newComp.setIntitule(intitule);
					daoCompetence.create(newComp);
				}

				Possede compPos = new Possede();
				compPos.setIdSecouriste(idLong);
				compPos.setIntituleCompetence(intitule);

				Competence competence = new Competence();
				competence.setIntitule(intitule);
				compPos.setLaCompetence(competence);

				listCompPos.add(compPos);
			}

			// Mise à jour du secouriste
			Secouriste secouriste = new Secouriste(idLong, nom, prenom, dateNaissance, email, telephone, adresse, listCompPos);
			daoSecouriste.update(secouriste);

			// Suppression des anciennes compétences et réinsertion des nouvelles
			daoPossede.deleteBySecouristeId(idLong);
			for (Possede compPos : listCompPos) {
				daoPossede.create(compPos);
			}

			System.out.println("Secouriste mis à jour avec succès.");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la modification du secouriste : " + e.getMessage());
		}
	}



	@FXML
	public void deleteSecouriste(ActionEvent event) {
		System.out.println("deleteSecouriste");

		// Récupère le secouriste sélectionné dans la TableView
		Secouriste sec = tableSecouristes.getSelectionModel().getSelectedItem();
		if (sec == null) {
			System.out.println("Veuillez sélectionner un secouriste à supprimer.");
			return;
		}

		try {
			// Supprimer les compétences associées d'abord
			DAOPossede daoPossede = new DAOPossede();
			daoPossede.deleteBySecouristeId(sec.getId());

			// Puis supprimer le secouriste
			daoSecouriste.delete(sec.getId());

			// Rafraîchir la table
			viewAllSecouristes();

			System.out.println("Secouriste supprimé avec succès.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la suppression du secouriste : " + e.getMessage());
		}
	}


	/**
	 ***********************************
	 * GESTION DISPOSITIF DE SECOURS
	 ***********************************
	 */

	public void createDispositifDeSecours() {
		System.out.println("createDispositifDeSecours");

		if (idDPSCreate.getText().isEmpty() || heureDebutDPSCreate.getText().isEmpty() ||
			heureFinDPSCreate.getText().isEmpty() || lieuRencDPSCreate.getText().isEmpty() ||
			sportDPSCreate.getText().isEmpty() || nbSecDPSCreate.getText().isEmpty() ||
			CompReqDPSCreate.getText().isEmpty() || dateCreateDPS.getValue() == null) {
			
			System.out.println("Veuillez remplir tous les champs.");
			return;
		}

		try {
			// Lecture et parsing des champs
			int idDPSLong = Integer.parseInt(idDPSCreate.getText().trim());
			int heureDebut = Integer.parseInt(heureDebutDPSCreate.getText().trim());
			int heureFin = Integer.parseInt(heureFinDPSCreate.getText().trim());

			if (heureDebut >= heureFin) {
				System.out.println("L'heure de début doit être inférieure à l'heure de fin.");
				return;
			}

			LocalDate selectedDate = dateCreateDPS.getValue();
			Journee journee = new Journee(
				selectedDate.getDayOfMonth(),
				selectedDate.getMonthValue(),
				selectedDate.getYear()
			);

			// Parsing des compétences et nombres de secouristes
			String[] compReqStr = CompReqDPSCreate.getText().split(";");
			String[] nbSecStr = nbSecDPSCreate.getText().split(";");

			if (compReqStr.length != nbSecStr.length) {
				System.out.println("Le nombre de compétences ne correspond pas au nombre de secouristes.");
				return;
			}

			// Lecture du site
			String lieuRenc = lieuRencDPSCreate.getText().trim();
			Site site = daoSite.read(lieuRenc);
			if (site == null) {
				System.out.println("Le site '" + lieuRenc + "' n'existe pas.");
				return;
			}

			// Lecture du sport
			String sport = sportDPSCreate.getText().trim();
			Sport sportObj = daoSport.read(sport);
			if (sportObj == null) {
				System.out.println("Le sport '" + sport + "' n'existe pas.");
				return;
			}

			// Création du DPS
			DPS dps = new DPS(idDPSLong, heureDebut, heureFin, journee, site, sportObj);
			
			// Vérifie si la journée existe déjà
			DAOJournee daoJournee = new DAOJournee();
			if (daoJournee.read(journee.getJour(), journee.getMois(), journee.getAnnee()) == null) {
				daoJournee.create(journee);
			}
			daoDPS.create(dps);

			// Création des Besoins
			for (int i = 0; i < compReqStr.length; i++) {
				String comp = compReqStr[i].trim();
				int nb = Integer.parseInt(nbSecStr[i].trim());
				Besoin besoin = new Besoin(nb, comp, idDPSLong);
				daoBesoin.create(besoin);
			}
			System.out.println("DPS créé avec succès.");

		} catch (NumberFormatException e) {
			System.out.println("Erreur de format numérique : " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la création du DPS : " + e.getMessage());
		}
	}

	public void updateDispositifDeSecours() {
		System.out.println("updateDispositifDeSecours");

		if (idDPSModif.getText().isEmpty() || heureDebutDPSModif.getText().isEmpty() ||
			heureFinDPSModif.getText().isEmpty() || lieuRencDPSModif.getText().isEmpty() ||
			sportDPSModif.getText().isEmpty() || nbSecDPSModif.getText().isEmpty() ||
			CompReqDPSModif.getText().isEmpty() || dateModifDPS.getValue() == null) {

			System.out.println("Veuillez remplir tous les champs.");
			return;
		}

		try {
			int idDPSLong = Integer.parseInt(idDPSModif.getText().trim());
			int heureDebut = Integer.parseInt(heureDebutDPSModif.getText().trim());
			int heureFin = Integer.parseInt(heureFinDPSModif.getText().trim());

			if (heureDebut >= heureFin) {
				System.out.println("L'heure de début doit être inférieure à l'heure de fin.");
				return;
			}

			LocalDate selectedDate = dateModifDPS.getValue();
			Journee journee = new Journee(
				selectedDate.getDayOfMonth(),
				selectedDate.getMonthValue(),
				selectedDate.getYear()
			);

			String[] compReqStr = CompReqDPSModif.getText().split(";");
			String[] nbSecStr = nbSecDPSModif.getText().split(";");

			if (compReqStr.length != nbSecStr.length) {
				System.out.println("Le nombre de compétences ne correspond pas au nombre de secouristes.");
				return;
			}

			String lieuRenc = lieuRencDPSModif.getText().trim();
			Site site = daoSite.read(lieuRenc);
			if (site == null) {
				System.out.println("Le site '" + lieuRenc + "' n'existe pas.");
				return;
			}

			String sport = sportDPSModif.getText().trim();
			Sport sportObj = daoSport.read(sport);
			if (sportObj == null) {
				System.out.println("Le sport '" + sport + "' n'existe pas.");
				return;
			}

			// Mise à jour du DPS
			DPS dps = new DPS(idDPSLong, heureDebut, heureFin, journee, site, sportObj);
			daoDPS.update(dps);

			// Supprimer les anciens besoins liés à ce DPS
			daoBesoin.deleteByDpsId(idDPSLong);

			// Création et insertion des nouveaux besoins
			for (int i = 0; i < compReqStr.length; i++) {
				String comp = compReqStr[i].trim();
				int nb = Integer.parseInt(nbSecStr[i].trim());
				Besoin besoin = new Besoin(nb, comp, idDPSLong);
				daoBesoin.create(besoin);
			}

			System.out.println("DPS modifié avec succès.");

		} catch (NumberFormatException e) {
			System.out.println("Erreur de format numérique : " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la modification du DPS : " + e.getMessage());
		}
	}



	
	@FXML
	public void deleteDispositifDeSecours(ActionEvent event) {
		System.out.println("deleteDispositifDeSecours");

		// On récupère le DPS sélectionné dans la TableView
		DPS dps = tableDPS.getSelectionModel().getSelectedItem();
		if (dps == null) {
			System.out.println("Veuillez sélectionner un DPS à supprimer.");
			return;
		}

		try {
			// Supprimer tous les besoins associés
			daoBesoin.deleteByDpsId(dps.getId());

			// Supprimer le DPS
			daoDPS.delete(dps.getId());

			// Rafraîchir la table
			viewAllDPS();

			System.out.println("DPS et ses besoins supprimés avec succès.");

		} catch (NumberFormatException e) {
			System.out.println("Format d'identifiant invalide : " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la suppression du DPS : " + e.getMessage());
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
		if (intitulerCreateComp == null || intitulerCreateComp.getText().isEmpty()) {
			System.out.println("Champ 'Intitulé' vide ou non initialisé.");
			return;
		}

		String intituleStr = intitulerCreateComp.getText();
		String[] compNecStr = null;

		// Vérifie que le champ existe avant d'appeler une méthode dessus
		boolean necessiteVide = (necessiteCreateComp == null || 
								necessiteCreateComp.getText() == null || 
								necessiteCreateComp.getText().isEmpty());

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

			// insérer la compétence
			daoCompetence.create(comp);
			grapheCompetences.ajouterCompetence(comp);

			// insérer les dépendances dans la base + graphe actuel
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

	public void updateCompetence() {
		// Vérification des champs requis
		if (intitulerUpdateComp == null || newIntitulerUpdateComp == null || intitulerUpdateComp.getText().isEmpty() || newIntitulerUpdateComp.getText().isEmpty()) {
			System.out.println("Veuillez remplir l'ancien et le nouvel intitulé.");
			return;
		}

		String ancienIntitule = intitulerUpdateComp.getText().trim();
		String nouvelIntitule = newIntitulerUpdateComp.getText().trim();
		String[] compNecStr = null;

		boolean necessiteVide = (necessiteUpdateComp == null || necessiteUpdateComp.getText() == null || necessiteUpdateComp.getText().isEmpty());

		if (!necessiteVide) {
			compNecStr = necessiteUpdateComp.getText().split(";");
		}

		try {
			// Récupérer la compétence existante
			Competence ancienneComp = trouverCompetenceParIntitule(ancienIntitule, grapheCompetences.getCompetences());
			if (ancienneComp == null) {
				System.out.println("Compétence à modifier inexistante.");
				return;
			}

			// Préparer la nouvelle instance
			Competence nouvelleComp = new Competence();
			nouvelleComp.setIntitule(nouvelIntitule);

			// Dépendances
			List<Competence> dependances = new ArrayList<>();
			if (!necessiteVide) {
				for (String nec : compNecStr) {
					String trimmedNec = nec.trim();
					if (!trimmedNec.isEmpty()) {
						Competence compNec = trouverCompetenceParIntitule(trimmedNec, grapheCompetences.getCompetences());
						if (compNec == null) {
							System.out.println("Compétence requise inexistante dans le graphe : " + trimmedNec);
							return;
						}
						dependances.add(compNec);
					}
				}
			}

			// Vérification de cycle (graphe temporaire)
			List<Competence> tempCompetences = new ArrayList<>(grapheCompetences.getCompetences());
			tempCompetences.remove(ancienneComp);
			tempCompetences.add(nouvelleComp);

			List<Necessite> tempNecessites = new ArrayList<>();
			for (Necessite n : grapheCompetences.getNecessites()) {
				if (!n.getIntituleCompetence().equals(ancienIntitule) &&
					!n.getIntituleCompetenceNecessaire().equals(ancienIntitule)) {
					tempNecessites.add(n);
				}
			}

			for (Competence nec : dependances) {
				Necessite n = new Necessite();
				n.setLaCompetence(nouvelleComp);
				n.setCompetenceNecessaire(nec);
				n.setIntituleCompetence(nouvelIntitule);
				n.setIntituleCompetenceNecessaire(nec.getIntitule());
				tempNecessites.add(n);
			}

			GrapheCompetences grapheTemp = new GrapheCompetences(tempCompetences, tempNecessites);
			if (!grapheTemp.estDAG()) {
				System.out.println("Modification impossible : cela créerait un cycle dans le graphe.");
				return;
			}

			// Mise à jour base
			daoCompetence.update(nouvelleComp, ancienIntitule);
			daoNecessite.deleteByCompetence(ancienIntitule);

			for (Competence nec : dependances) {
				Necessite besoin = new Necessite();
				besoin.setLaCompetence(nouvelleComp);
				besoin.setCompetenceNecessaire(nec);
				besoin.setIntituleCompetence(nouvelIntitule);
				besoin.setIntituleCompetenceNecessaire(nec.getIntitule());
				daoNecessite.create(besoin);
			}

			// Mise à jour graphe
			ancienneComp.setIntitule(nouvelIntitule);
			grapheCompetences.getNecessites().removeIf(n ->
				n.getIntituleCompetence().equals(ancienIntitule) ||
				n.getIntituleCompetenceNecessaire().equals(ancienIntitule)
			);

			for (Competence nec : dependances) {
				Necessite besoin = new Necessite();
				besoin.setLaCompetence(ancienneComp); // même objet, nouvel intitulé
				besoin.setCompetenceNecessaire(nec);
				besoin.setIntituleCompetence(nouvelIntitule);
				besoin.setIntituleCompetenceNecessaire(nec.getIntitule());
				grapheCompetences.ajouterNecessite(besoin);
			}

			System.out.println("Compétence modifiée avec succès.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la modification de la compétence : " + e.getMessage());
		}
	}
	
	public void deleteCompetence() {
		// Vérification du champ de compétence à supprimer
		if (intitulerDeleteComp == null || intitulerDeleteComp.getText().isEmpty()) {
			System.out.println("Veuillez remplir l'intitulé de la compétence à supprimer.");
			return;
		}

		String intituleASupprimer = intitulerDeleteComp.getText().trim();

		try {
			// Récupérer la compétence existante dans le graphe
			Competence compASupprimer = trouverCompetenceParIntitule(intituleASupprimer, grapheCompetences.getCompetences());
			if (compASupprimer == null) {
				System.out.println("Compétence à supprimer inexistante.");
				return;
			}

			// Étape 1 : Supprimer toutes les nécessités liées à cette compétence en base
			daoNecessite.deleteByCompetence(intituleASupprimer);

			// Étape 2 : Supprimer la compétence en base
			daoCompetence.delete(compASupprimer.getIntitule());


			// Étape 3 : Mettre à jour le graphe en mémoire
			// Supprimer la compétence
			grapheCompetences.getCompetences().remove(compASupprimer);

			// Supprimer toutes les nécessités liées à cette compétence (en tant que compétence ou compétence nécessaire)
			grapheCompetences.getNecessites().removeIf(n ->
				(n.getIntituleCompetence() != null && n.getIntituleCompetence().equals(intituleASupprimer)) ||
				(n.getIntituleCompetenceNecessaire() != null && n.getIntituleCompetenceNecessaire().equals(intituleASupprimer))
			);

			System.out.println("Compétence supprimée avec succès.");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la suppression de la compétence : " + e.getMessage());
		}
	}

	@FXML
	public void onDeleteSelectedCompetence(ActionEvent event) {
		Competence selected = tableCompetences.getSelectionModel().getSelectedItem();
		if (selected == null) {
			System.out.println("Veuillez sélectionner une compétence à supprimer.");
			return;
		}
		String intituleASupprimer = selected.getIntitule();
		try {
			// Supprimer toutes les nécessités liées à cette compétence en base
			daoNecessite.deleteByCompetence(intituleASupprimer);

			// Supprimer la compétence en base
			daoCompetence.delete(intituleASupprimer);

			// Supprimer la compétence dans le graphe en mémoire si besoin
			if (grapheCompetences != null) {
				Competence compASupprimer = trouverCompetenceParIntitule(intituleASupprimer, grapheCompetences.getCompetences());
				if (compASupprimer != null) {
					grapheCompetences.getCompetences().remove(compASupprimer);
					grapheCompetences.getNecessites().removeIf(n ->
						(n.getIntituleCompetence() != null && n.getIntituleCompetence().equals(intituleASupprimer)) ||
						(n.getIntituleCompetenceNecessaire() != null && n.getIntituleCompetenceNecessaire().equals(intituleASupprimer))
					);
				}
			}

			// Rafraîchir la table
			viewAllComp();

			System.out.println("Compétence supprimée avec succès.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la suppression de la compétence : " + e.getMessage());
		}
	}


	/*******************************************
	 * GESTION DES DÉPENDANCES DES COMPÉTENCES *
	 *******************************************/

	public void createNecessite() {
		if (intitulerCreateComp.getText().isEmpty() || necessiteCreateComp.getText().isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
			return;
		}

		String intituleComp = intitulerCreateComp.getText();
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
		if (intitulerCreateComp.getText().isEmpty() || necessiteCreateComp.getText().isEmpty()) {
			System.out.println("Veuillez remplir tous les champs.");
			return;
		}

		String intituleComp = intitulerCreateComp.getText();
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
				for (DPS dps : dpsJour) {
					if (b.getIdDPS() == dps.getId()) {
						b.setLeDPS(dps);
						besoinsJour.add(b);
						break;
					}
				}
			}
			DAOCompetence daoCompetence = new DAOCompetence();
			for (Besoin b : besoinsJour) {
				b.setLaCompetence(daoCompetence.findByIntitule(b.getIntituleCompetence()));
			}

			System.out.println("Nb besoins pour le jour " + jour + " : " + besoinsJour.size());
			System.out.println("Besoins : " + besoinsJour);

			// Choix de l'algo
			Affectation algoAffect;
			if ("exhaustif".equalsIgnoreCase(algo)) {
				algoAffect = new model.graph.algorithm.exhaustive.AffectationExhaustive();
			} else {
				algoAffect = new model.graph.algorithm.greedy.AffectationGloutonne();
			}
 			
			
			resultatAffectation = algoAffect.affecter(secouristesDispos, dpsJour, besoinsJour);
			
			// Exécution de l'affectation
			System.out.println("Secouristes dispos : " + secouristesDispos.size());
			System.out.println("DPS ce jour : " + dpsJour.size());
			System.out.println("Besoins ce jour : " + besoinsJour.size());
			for (Secouriste s : secouristesDispos) {
				System.out.println("Secouriste " + s.getId() + " possède :");
				for (Possede p : s.getPossessions()) {
					System.out.println("  - " + p.getIntituleCompetence());
				}
			}

			for (Besoin b : besoinsJour) {
				System.out.println("Besoin : " + b.getIntituleCompetence());
			}

			resultatAffectation = algoAffect.affecter(secouristesDispos, dpsJour, besoinsJour);
			
			System.out.println("Nb affectations trouvées : " + resultatAffectation.getAffectations().size());

			// Stockage en base via le service
			DAOEstAffecteA daoEstAffecteA = new DAOEstAffecteA();
			MngtEstAffecteA mngtAffect = new MngtEstAffecteA(daoEstAffecteA);
			for (DPS dps : dpsJour) {
				for (Secouriste s : tousSecouristes) {
					try { mngtAffect.supprimerAffectation(s.getId(), dps.getId()); } catch (Exception ignore) {}
				}
			}

			// *** NOUVELLE BOUCLE D'INSERTION ***
			Map<BesoinUnitaire, Secouriste> affectationsUnitaires = resultatAffectation.getAffectationsUnitaires();
			for (Map.Entry<BesoinUnitaire, Secouriste> entry : affectationsUnitaires.entrySet()) {
				BesoinUnitaire besoin = entry.getKey();
				Secouriste secouriste = entry.getValue();
				DPS dps = besoin.getDps();
				String competence = besoin.getCompetence().getIntitule();

				try {
					mngtAffect.creerAffectation(secouriste.getId(), competence, dps.getId());
					System.out.println("Insertion : secouriste=" + secouriste.getId() + ", comp=" + competence + ", dps=" + dps.getId());
				} catch (Exception e) {
					e.printStackTrace();
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

	public List<EstAffecteA> getAffectationsPourSecouriste(long idSecouriste) {
		DAOEstAffecteA daoEstAffecteA = new DAOEstAffecteA();
		try {
			return daoEstAffecteA.readBySecouristeId(idSecouriste);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public void afficherAffectationsSecouriste() {
		// Récupérer le secouriste connecté
		Secouriste secouriste = (Secouriste) MngtSession.getUtilisateurConnecte();
		if (secouriste == null) {
			System.out.println("Aucun secouriste connecté !");
			return;
		}

		// Récupérer ses affectations
		List<EstAffecteA> affectations = getAffectationsPourSecouriste(secouriste.getId());

		// Pour chaque affectation, ajouter une tâche dans le planning
		for (EstAffecteA aff : affectations) {
			DPS dps = aff.getLeDPS(); // ou récupère via DAO si besoin
			Journee journee = dps.getEstProgramme();
			LocalDate date = LocalDate.of(journee.getAnnee(), journee.getMois(), journee.getJour());

			// Crée une description à afficher
			String desc = "DPS " + dps.getId() + " (" + dps.getConcerneSport() + ") "
						+ aff.getLaCompetence().getIntitule();

			// Ajoute la tâche au planning
			addTask(date, desc);
		}
	}

	public void afficherAffectationsSecouristeDansGridPane() {
		clearAllTaskBoxes();
		// Récupérer le secouriste connecté
		Secouriste secouriste = (Secouriste) MngtSession.getUtilisateurConnecte();
		if (secouriste == null) {
			System.out.println("Aucun secouriste connecté !");
			return;
		}

		// Récupérer ses affectations
		List<EstAffecteA> affectations = getAffectationsPourSecouriste(secouriste.getId());

		// Pour chaque affectation, placer un label dans le GridPane
		for (EstAffecteA aff : affectations) {
			DPS dps = aff.getLeDPS();
			Journee journee = dps.getEstProgramme();
			LocalDate date = LocalDate.of(journee.getAnnee(), journee.getMois(), journee.getJour());

			// Calcul du jour de la semaine (0 = lundi, 6 = dimanche)
			int dayIndex = (int) ChronoUnit.DAYS.between(currentMonday, date);
			if (dayIndex < 0 || dayIndex > 6) continue; // hors de la semaine affichée

			// Heures de début et de fin
			int heureDebut = dps.getHoraireDepart();
			int heureFin = dps.getHoraireFin();

			// Description à afficher
			String desc = "DPS " + dps.getId() + " (" + dps.getConcerneSport() + ") " + aff.getLaCompetence().getIntitule();

			// Pour chaque heure de la plage, ajoute un label dans la bonne VBox
			for (int h = heureDebut; h < heureFin; h++) {
				if (h >= 0 && h < 24) {
					VBox box = taskBoxes[dayIndex][h];
					Label label = new Label(desc);
					label.setStyle("-fx-background-color: #D3EAFD; -fx-padding: 2 5 2 5; -fx-border-radius: 3; -fx-background-radius: 3;");
					box.getChildren().add(label);
				}
			}
		}
	}

	@FXML
	public void onAffecterSecouristes(ActionEvent event) {
		System.out.println("onAffecterSecouristes");
		String algo = radioGlouton.isSelected() ? "glouton" : "exhaustif";
		LocalDate date = datePickerAffectation.getValue();
		if (date == null) {
			System.out.println("Veuillez choisir une date.");
			return;
		}
		Journee journee = new Journee(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
		affecterSecouristesPourJournee(journee, algo);
		afficherAffectationsTousSecouristesDansGridPane();
	}

	public void afficherAffectationsTousSecouristesDansGridPane() {
		clearAllTaskBoxes();
		DAOEstAffecteA daoEstAffecteA = new DAOEstAffecteA();
		try {
			List<EstAffecteA> affectations = daoEstAffecteA.readAll();
			for (EstAffecteA aff : affectations) {
				DPS dps = aff.getLeDPS();
				Journee journee = dps.getEstProgramme();
				LocalDate date = LocalDate.of(journee.getAnnee(), journee.getMois(), journee.getJour());
				int dayIndex = (int) ChronoUnit.DAYS.between(currentMonday, date);
				if (dayIndex < 0 || dayIndex > 6) continue;
				int heureDebut = dps.getHoraireDepart();
				int heureFin = dps.getHoraireFin();
				String desc = "S" + aff.getIdSecouriste() + " : DPS " + dps.getId() + " (" + dps.getConcerneSport() + ") " + aff.getLaCompetence().getIntitule();
				for (int h = heureDebut; h < heureFin; h++) {
					if (h >= 0 && h < 24) {
						VBox box = taskBoxes[dayIndex][h];
						Label label = new Label(desc);
						label.setStyle("-fx-background-color: #FFD580; -fx-padding: 2 5 2 5; -fx-border-radius: 3; -fx-background-radius: 3;");
						box.getChildren().add(label);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de l'affichage des affectations : " + e.getMessage());
		}
	}


	@FXML
	public void onSupprimerAffectationsJournee(ActionEvent event) {
		// Utilise le DatePicker dédié à la suppression
		LocalDate date = datePickerSuppression.getValue();
		if (date == null) {
			System.out.println("Veuillez choisir une date.");
			return;
		}
		Journee journee = new Journee(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
		try {
			DAOEstAffecteA daoEstAffecteA = new DAOEstAffecteA();
			int nbSuppr = daoEstAffecteA.deleteByJournee(journee);
			System.out.println(nbSuppr + " affectations supprimées pour la journée " + date);
			afficherAffectationsTousSecouristesDansGridPane(); // Rafraîchir l'affichage
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la suppression des affectations : " + e.getMessage());
		}
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

	public void viewAllComp(){
		System.out.println("viewAllComp");

		try {
			dataCompSec.clear();

			List<Possede> listPos = daoPossede.readAll();

			dataCompSec.addAll(listPos);

			tableCompetencesSec.setItems(dataCompSec);

			colSecouriste.setCellValueFactory(new PropertyValueFactory<>("idSecouriste"));
			colIntituleCompSec.setCellValueFactory(new PropertyValueFactory<>("intituleCompetence"));


			dataComp.clear();

			List<Competence> listComp = daoCompetence.readAll();

			dataComp.addAll(listComp);

			tableCompetences.setItems(dataComp);

			colIntituleComp.setCellValueFactory(new PropertyValueFactory<>("intitule"));


			dataCompNec.clear();

			List<Necessite> listNec = daoNecessite.readAll();

			dataCompNec.addAll(listNec);

			tableCompetencesNec.setItems(dataCompNec);

			colIntitule.setCellValueFactory(new PropertyValueFactory<>("intituleCompetence"));
			colIntituleNec.setCellValueFactory(new PropertyValueFactory<>("intituleCompetenceNecessaire"));


		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de l'affichage des secouristes : " + e.getMessage());
		}
	}

	public void viewAllDPS(){
		try{

			dataDPS.clear();

			List<DPS> listDPS = daoDPS.readAll();

			dataDPS.addAll(listDPS);

			tableDPS.setItems(dataDPS);

			idTableDPS.setCellValueFactory(new PropertyValueFactory<>("id"));
			heureDebTableDPS.setCellValueFactory(new PropertyValueFactory<>("horaireDepart"));
			heureFinTableDPS.setCellValueFactory(new PropertyValueFactory<>("horaireFin"));

			dateTableDPS.setCellValueFactory(new PropertyValueFactory<>("estProgrammeJournee"));
			lieuTableDPS.setCellValueFactory(new PropertyValueFactory<>("ALieuDansSite"));
			sportTableDPS.setCellValueFactory(new PropertyValueFactory<>("concerneSport"));

		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Erreur lors de l'affichage des DPS : " + e.getMessage());
		}
	}

	public void viewCompForSecouriste(long idSecouriste) {
		System.out.println("viewCompForSecouriste - ID: " + idSecouriste);

		try {
			dataMesComp.clear();

			// Récupère uniquement les compétences du secouriste
			List<Possede> listPos = daoPossede.read(idSecouriste);

			dataMesComp.addAll(listPos);

			tableMesCompetencesSec.setItems(dataMesComp);

			colIntituleMesCompSec.setCellValueFactory(new PropertyValueFactory<>("intituleCompetence"));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erreur lors de l'affichage des compétences du secouriste : " + e.getMessage());
		}
	}


	

	/**
	 ***********************************
	 * GESTION DU PLANNING
	 ***********************************
	 */

	@FXML
	public void initialize() {
		System.out.println("initialize");
		// Si on est sur la page du planning
		initGrapheCompetences();
		if (gridWeek != null) {
			LocalDate today = LocalDate.now();
			currentMonday = today.with(DayOfWeek.MONDAY);
			generateHourLabelsAndTaskBoxes();
			afficherSemaine(currentMonday);

			if (isPageAffectationAdmin()) {
				afficherAffectationsTousSecouristesDansGridPane();
			} else {
				afficherAffectationsSecouristeDansGridPane();
			}
		}

		// Si on est sur la page secouristes
		if (tableSecouristes != null) {
			
			viewAllSecouristes();
		}

		if(tableCompetencesSec != null){
			viewAllComp();
		}

		if(tableDPS != null){
			viewAllDPS();
		}

		if (nomSecModif != null) {
			initModifSecouriste();
		}

		if (idDPSModif != null) {
			initModifDPS();
		}

		if (intitulerUpdateComp != null) {
			initModifComp();
		}

		if (tableMesCompetencesSec != null) {
			// Vérifie le type d'utilisateur connecté
			Object user = MngtSession.getUtilisateurConnecte();

			if (user instanceof Secouriste) {
				Secouriste sec = (Secouriste) user;
				long idSecouriste = sec.getId();

				viewCompForSecouriste(idSecouriste);
			} else {
				System.out.println("Aucun secouriste connecté !");
			}
		}
	}

	// Méthode pour détecter la page d'affectation admin
	private boolean isPageAffectationAdmin() {
		// Par exemple, un bouton spécifique à la page admin
		return btnAffectationAdmin != null;
	}

	// Méthode pour détecter la page planning secouriste
	private boolean isPagePlanningSecouriste() {
		// Par exemple, un bouton ou label spécifique à la page secouriste
		return btnRetourPlanningSecouriste != null;
	}

	private void initModifSecouriste() {
		Long id = MngtSession.getIdSecouristeAModifier();
		if (id != null) {
			DAOSecouriste daoSecouriste = new DAOSecouriste();
			try {
				Secouriste sec = daoSecouriste.read(id);
				if (sec != null) {
					nomSecModif.setText(sec.getNom());
					prenomSecModif.setText(sec.getPrenom());
					dateNaissSecModif.setText(sec.getDateNaissance());
					mailSecModif.setText(sec.getEmail());
					adressSecModif.setText(sec.getAdresse());
					idSecModif.setText(String.valueOf(sec.getId()));
					telSecModif.setText(sec.getTel());
					// Pour les compétences, concatène les intitulés séparés par ;
					StringBuilder sb = new StringBuilder();
					if (sec.getPossessions() != null) {
						for (Possede p : sec.getPossessions()) {
							if (sb.length() > 0) sb.append(";");
							sb.append(p.getIntituleCompetence());
						}
					}
					compSecModif.setText(sb.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la récupération du secouriste : " + e.getMessage());
			}
		}
	}

	public void initModifDPS() {
		Long id = MngtSession.getIdDPSAModifier();
		if (id != null) {
			DAODPS daoDPS = new DAODPS();
			DAOBesoin daoBesoin = new DAOBesoin();
			try {
				DPS dps = daoDPS.read(id);
				if (dps != null) {
					idDPSModif.setText(String.valueOf(dps.getId()));
					heureDebutDPSModif.setText(String.valueOf(dps.getHoraireDepart()));
					heureFinDPSModif.setText(String.valueOf(dps.getHoraireFin()));
					dateModifDPS.setValue(LocalDate.of(
						dps.getEstProgramme().getAnnee(),
						dps.getEstProgramme().getMois(),
						dps.getEstProgramme().getJour()
					));
					
					if (dps.getALieuDans() != null)
						lieuRencDPSModif.setText(dps.getALieuDans().getCode());
					else
						lieuRencDPSModif.setText("");
					if (dps.getConcerne() != null)
						sportDPSModif.setText(dps.getConcerne().getCode());
					else
						sportDPSModif.setText("");

					// Préremplissage des compétences requises et nombre de secouristes
					List<Besoin> besoins = daoBesoin.readByDpsId(dps.getId());
					StringBuilder compReq = new StringBuilder();
					StringBuilder nbSec = new StringBuilder();
					for (Besoin b : besoins) {
						if (compReq.length() > 0) compReq.append(";");
						if (nbSec.length() > 0) nbSec.append(";");
						compReq.append(b.getIntituleCompetence());
						nbSec.append(b.getNombre());
					}
					CompReqDPSModif.setText(compReq.toString());
					nbSecDPSModif.setText(nbSec.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la récupération du DPS : " + e.getMessage());
			}
		}
	}

	public void initModifComp() {
		String intitule = MngtSession.getIntituleCompetenceAModifier();
		if (intitule != null) {
			DAOCompetence daoCompetence = new DAOCompetence();
			try {
				Competence comp = daoCompetence.read(intitule);
				if (comp != null) {
					intitulerUpdateComp.setText(comp.getIntitule());
					// Préremplis les autres champs si besoin
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

    public void addTask(LocalDate date, String taskDescription) {
        tasksByDate.computeIfAbsent(date, k -> new ArrayList<>()).add(taskDescription);
    }

	private void clearAllTaskBoxes() {
		for (int day = 0; day < 7; day++) {
			for (int hour = 0; hour < 24; hour++) {
				if (taskBoxes[day][hour] != null) {
					taskBoxes[day][hour].getChildren().clear();
				}
			}
		}
	}

    private boolean isDateInCurrentWeek(LocalDate date) {
        return !date.isBefore(currentMonday) && !date.isAfter(currentMonday.plusDays(6));
    }

    public void afficherSemaine(LocalDate monday) {
		currentMonday = monday;
		updateDayLabels();

		if (isPageAffectationAdmin()) {
			afficherAffectationsTousSecouristesDansGridPane();
		} else {
			afficherAffectationsSecouristeDansGridPane();
		}
	}


    private void updateDayLabels() {
        lblMon.setText(" Lundi\n " + currentMonday);
        lblTue.setText(" Mardi\n " + currentMonday.plusDays(1));
        lblWed.setText(" Mercredi\n " + currentMonday.plusDays(2));
        lblThu.setText(" Jeudi\n " + currentMonday.plusDays(3));
        lblFri.setText(" Vendredi\n " + currentMonday.plusDays(4));
        lblSat.setText(" Samedi\n " + currentMonday.plusDays(5));
        lblSun.setText(" Dimanche\n " + currentMonday.plusDays(6));
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