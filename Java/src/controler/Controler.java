package controler;

/**
 * Controller
 */
public class Controler {

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