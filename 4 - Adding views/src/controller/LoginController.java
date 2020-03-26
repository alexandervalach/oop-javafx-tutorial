package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.ErrorDialog;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController extends BaseController {
  @FXML
  private PasswordField password;

  @FXML
  private Label messageBox;

  @FXML
  private Button authenticate;

  @FXML
  private TextField username;

  @FXML
  private GridPane app;

  private ErrorDialog errorDialog;

  public LoginController () {
    errorDialog = new ErrorDialog();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    assert app != null : "fx:id\"app\" can not be created.";
    assert authenticate != null : "fx:id=\"authenticate\" can not be created.";
    assert username != null : "fx:id=\"username\" can not be created.";
    assert password != null : "fx:id=\"password\" can not be created.";
    assert messageBox != null : "fx:id=\"messageBox\" can not be created.";

    authenticate.defaultButtonProperty().bind(authenticate.focusedProperty());
    authenticate.setOnAction((e) -> login());
  }

  /**
   * Checks user credentials
   * */
  private void login() {
    if (Objects.equals("admin", username.getText()) && Objects.equals("admin", password.getText())) {
      this.switchScene("Dashboard");
    } else {
      username.clear();
      password.clear();
      errorDialog.display("Problém s prihlásením", "Nesprávne meno alebo heslo");
    }
  }
}
