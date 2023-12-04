package dad.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.login.auth.AuthService;
import dad.login.auth.FileAuthService;
import dad.login.auth.LdapAuthService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginController implements Initializable {
	
	@FXML
	private Button accederButton;

	@FXML
	private Button cancelarButton;

	@FXML
	private PasswordField contraText;

	@FXML
	private CheckBox LdapCheck;

	@FXML
	private TextField usuarioText;
	
	@FXML
	private VBox view;

	private LoginModel model = new LoginModel();
	private AuthService authService;

	public LoginController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		usuarioText.textProperty().bindBidirectional(model.usuarioProperty());
		contraText.textProperty().bindBidirectional(model.contraseñaProperty());
		LdapCheck.selectedProperty().bindBidirectional(model.ldapProperty());
		accederButton.setOnAction(e -> login());
		cancelarButton.setOnAction(e -> cancelar());

	}

	public void login() {
		boolean autenticado = false;

		if (model.isLdap())
			authService = new LdapAuthService();
		else
			authService = new FileAuthService();

		try {
			autenticado = authService.login(model.getUsuario(), model.getContraseña());
		} catch (Exception e) {
			autenticacionIncorrecta();
		}
		if (autenticado) {
			autenticacionCorrecta();
			System.exit(0);
		} else
			autenticacionIncorrecta();

	}

	private void autenticacionCorrecta() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Iniciar sesión");
		alert.setHeaderText("Acceso permitido");
		alert.setContentText("Las creedenciales de acceso son válidas");
		alert.showAndWait();
	}

	private void autenticacionIncorrecta() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Iniciar sesión");
		alert.setHeaderText("Acceso denegado");
		alert.setContentText("El usuario y/o la contraseña son incorrectas");
		alert.showAndWait();
	}

	public void cancelar() {
		System.exit(0);
	}

	public VBox getView() {
		return view;
	}

}
