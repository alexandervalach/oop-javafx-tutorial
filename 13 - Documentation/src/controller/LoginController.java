package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.ErrorDialog;
import model.Identity;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controls login actions
 */
public class LoginController extends BaseController {
  @FXML
  private PasswordField password;

  @FXML
  private Button authenticate;

  @FXML
  private TextField username;

  @FXML
  private GridPane app;

  private final ErrorDialog errorDialog;

  /**
   * Constructor
   */
  public LoginController () {
    errorDialog = new ErrorDialog();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    super.initialize(url, resourceBundle);

    authenticate.defaultButtonProperty().bind(authenticate.focusedProperty());
    authenticate.setOnAction((e) -> login());
  }

  /**
   * Checks user credentials and switches scenes if correct ones are given
   */
  private void login() {
    if (Objects.equals("admin", username.getText()) && Objects.equals("admin", password.getText())) {
      identity = Identity.getInstance(username.getText());
      this.switchScene("dashboard");
    } else {
      username.clear();
      password.clear();
      errorDialog.display("Problém s prihlásením", "Nesprávne meno alebo heslo");
    }
  }
}
