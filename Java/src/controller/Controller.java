package controller;
// Import des librairies Java
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.List;


//Import des librairies JavaFX
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//Import de nos class
import model.persistence.Secouriste;
import model.persistence.Site;
import model.persistence.Besoin;
import model.persistence.Competence;
import model.persistence.Sport;
import model.persistence.DPS;
import model.dao.DAOBesoin;
import model.dao.DAOCompetence;
import model.dao.DAODPS;
import model.dao.DAONecessite;
import model.dao.DAOSecouriste;
import model.dao.DAOSite;
import model.dao.DAOSport;
import model.persistence.Journee;
import model.persistence.Necessite;

/**
 * La classe Controller de l'application
 * @author M.COIGNARD, L.VIMART, A.COUDIERE
 */
public class Controller {

	/**
	 ***********************************
	 * Connection base de donnée
	 ***********************************
	 */
	
	// Mettre les DAOSecouriste et autre la


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
	private Button updateButtonComp;

	//Pour supprimer une compétence

	/**
	 ***********************************
	 * Variable pour affichage des secouristes
	 ***********************************
	 */

	@FXML
    private TableView<Secouriste> tableViewSec;
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
	private ObservableList<Secouriste> data = FXCollections.observableArrayList();

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

			Secouriste secouriste = new Secouriste(idLong, nom, prenom, dateNaissance, email, passWord, adresse);
			DAOSecouriste daoSecouriste = new DAOSecouriste(null);// La connexion a la base de donné 
			
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

			Secouriste secouriste = new Secouriste(idLong, nom, prenom, dateNaissance, email, passWord, adresse);
			DAOSecouriste daoSecouriste = new DAOSecouriste(null);// La connexion a la base de donné 
			
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

			DAOSecouriste daoSecouriste = new DAOSecouriste(null);// La connexion a la base de donné

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
				DAOBesoin daoBesoin = new DAOBesoin(null);
				Besoin besoin;
				for(int i = 0; i<nbCompReq; i++){
					besoin = new Besoin(nombre, compReqStr[i], idDPSLong);
					daoBesoin.create(besoin);
				}
				DAOSite daoSite = new DAOSite(null);// La connexion a la base de donné 
				
				String lieuRenc = lieuRencDPSCreate.getText();
				Site site = daoSite.read(lieuRenc);

				// Sport
				DAOSport daoSport = new DAOSport(null); // La connexion a la base de donné

				String sport = sportDPSCreate.getText();
				Sport sportObj = daoSport.read(sport);

				DAODPS daoDPS = new DAODPS(null);// La connexion a la base de donné
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
				DAOBesoin daoBesoin = new DAOBesoin(null);
				Besoin besoin;
				for(int i = 0; i<nbCompReq; i++){
					besoin = new Besoin(nombre, compReqStr[i], idDPSLong);
					daoBesoin.update(besoin);
				}

				DAOSite daoSite = new DAOSite(null);// La connexion a la base de donné 
				
				String lieuRenc = lieuRencDPSModif.getText();
				Site site = daoSite.read(lieuRenc);

				// Sport
				DAOSport daoSport = new DAOSport(null); // La connexion a la base de donné

				String sport = sportDPSModif.getText();
				Sport sportObj = daoSport.read(sport);

				DAODPS daoDPS = new DAODPS(null);// La connexion a la base de donné
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
				DAOBesoin daoBesoin = new DAOBesoin(null);
				List<Besoin> besoin = daoBesoin.readAll();

				for (Besoin b : besoin) {
					Long idDPSFor = b.getIdDPS();
					if(idDPSFor == idDPSLong){
						daoBesoin.delete(idDPSLong, idDPS);
					}
				}
				DAODPS daoDPS = new DAODPS(null);// La connexion a la base de donné 
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

	public void createCompetences(){
		System.out.println();
		if(intitulerCreateComp.getText().isEmpty()){
			System.out.println("Veuillez remplir tous les champs.");
		}
		else{
			String intitulerStr = intitulerCreateComp.getText();
			String compNecStr = null;
			boolean necessiteVide = necessiteCreateComp.getText().isEmpty();
			if(!necessiteVide){
				compNecStr = necessiteCreateComp.getText();
			}
			Competence comp = new Competence();
			comp.setIntitule(intitulerStr);

			try{
				DAOCompetence daoCompetence = new DAOCompetence(null);
				daoCompetence.create(comp);

				if(!necessiteVide){
					DAONecessite daoNecessite = new DAONecessite(null);
					
					Competence compNec = new Competence();
					compNec.setIntitule(compNecStr);
					
					Necessite nec = new Necessite();
					nec.setLaCompetence(comp);
					nec.setCompetenceNecessaire(compNec);

					nec.setIntituleCompetence(intitulerStr);
					nec.setIntituleCompetenceNecessaire(compNecStr);
					daoNecessite.create(nec);
				}
				
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la création de la Compétence : " + e.getMessage());
			}


		}
	}

	//A REFAIRE QUAND MEIUX COMPRIS DAO COMP ET NEC
	/*public void updateCompetences(){
		System.out.println();
		if(intitulerCreateComp.getText().isEmpty()){
			System.out.println("Veuillez remplir tous les champs.");
		}
		else{
			String intitulerStr = intitulerCreateComp.getText();
			String compNecStr = null;
			boolean necessiteVide = necessiteCreateComp.getText().isEmpty();
			if(!necessiteVide){
				compNecStr = necessiteCreateComp.getText();
			}
			Competence comp = new Competence();
			comp.setIntitule(intitulerStr);

			try{
				DAOCompetence daoCompetence = new DAOCompetence(null);
				daoCompetence.update(comp, intitulerStr);

				if(!necessiteVide){
					DAONecessite daoNecessite = new DAONecessite(null);
					
					Competence compNec = new Competence();
					compNec.setIntitule(compNecStr);
					
					Necessite nec = new Necessite();
					nec.setLaCompetence(comp);
					nec.setCompetenceNecessaire(compNec);

					nec.setIntituleCompetence(intitulerStr);
					nec.setIntituleCompetenceNecessaire(compNecStr);
					daoNecessite.update(nec);
				}
				
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur lors de la modification de la Compétence : " + e.getMessage());
			}


		}
	}*/

	/*
		@FXML
    private TableView<DPS> tableViewDPS;

    @FXML
    private TableColumn<DPS, String> columnDPS;

    @FXML
    private TableColumn<DPS, String> columnCompReq;

    @FXML
    private TableColumn<DPS, String> columnSecAffect;

    private final ObservableList<DPS> dpsList = FXCollections.observableArrayList();
	
	public void initialize() {

		System.out.println("columnDPS is null? " + (columnDPS == null));
		System.out.println("columnCompReq is null? " + (columnCompReq == null));
		System.out.println("columnSecAffect is null? " + (columnSecAffect == null));
		System.out.println("tableViewDPS is null? " + (tableViewDPS == null));

        // Dispositif de secours → site
        columnDPS.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getALieuDans().getNom())
        );

        // Compétence requise → sport (à adapter si besoin)
        columnCompReq.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getConcerne().getNom())
        );

        // Secouristes affectés → valeur fictive ou champ à ajouter dans DPS
        columnSecAffect.setCellValueFactory(cellData ->
            new SimpleStringProperty("Jean, Marie") // Remplacer par une vraie propriété si disponible
        );

        tableViewDPS.setItems(dpsList);

        // Données de test
        dpsList.add(new DPS(
            1L,
            10,
            12,
            new Journee(10,5,2025), 
            new Site("1", "Fott", 12.00F, 12.00F), 
            new Sport("12","Football")
        ));
    }*/
	

	/**
	 ***********************************
	 * Integration des valeur dans les tables view 
	 ***********************************
	 */

	public void viewAllSecouristes(){
		try{
			DAOSecouriste daoSecouriste = new DAOSecouriste(null);
			List<Secouriste> listSec = daoSecouriste.readAll();

			for(Secouriste s : listSec){
				data.add(s);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Erreur lors de l'affichage des secouristes : " + e.getMessage());
		}

		idSecTable.setCellValueFactory(new PropertyValueFactory<Secouriste, Long>("id"));
		nomSecTable.setCellValueFactory(new PropertyValueFactory<Secouriste, String>("nom"));
		prenomSecTable.setCellValueFactory(new PropertyValueFactory<Secouriste, String>("prenom"));
		dateNaisSecTable.setCellValueFactory(new PropertyValueFactory<Secouriste, String>("date de naissance"));
		emailSecTable.setCellValueFactory(new PropertyValueFactory<Secouriste, String>("email"));
		telSecTable.setCellValueFactory(new PropertyValueFactory<Secouriste, String>("tel"));
		adresseSecTable.setCellValueFactory(new PropertyValueFactory<Secouriste, String>("adresse"));
	}
}