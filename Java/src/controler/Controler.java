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
/**
 * Controller
 */
public class Controler {

	private Stage stage;



	public Controler(){
		System.out.println("controler");
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void goToPageAdminAcceuil(){
		System.out.println("goToPageAdminAcceuil");
		this.goTo("file:../fxml/pageFxml/Administateur/PageAdminAcceuil.fxml");
		stage.setResizable(true);
	}

	private void goTo(String fichier){
		try {
			FXMLLoader loader = new FXMLLoader(new URL(url));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Controller.stage.setScene(scene);
			Controller.stage.centerOnScreen();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	public void versPageLogin(ActionEvent e){
		
		System.out.println("login");
		this.changerDePage("file:../ressources/Login.fxml");
		stage.setResizable(false);

	}
	
	
	public void versPageAccueil(ActionEvent ev){
		System.out.println("accueil");
		this.changerDePage("file:../ressources/Accueil.fxml");
		stage.setResizable(true);
	}
	
	public void versPageCreation(ActionEvent ev){
		System.out.println("creation");
		this.changerDePage("file:../ressources/Creation.fxml");
		stage.setResizable(false);
	}
	
	public void versPageAdmin(ActionEvent ev){
		System.out.println("admin");
		this.changerDePage("file:../ressources/Admin.fxml");
		stage.setResizable(true);
	}
	
	
	private void changerDePage(String url) {
		try {
			FXMLLoader loader = new FXMLLoader(new URL(url));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Controller.stage.setScene(scene);
			Controller.stage.centerOnScreen();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}**/
}