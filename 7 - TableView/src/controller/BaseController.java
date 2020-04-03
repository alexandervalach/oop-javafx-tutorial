package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import model.Identity;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

abstract class BaseController implements Initializable {

  @FXML
  protected GridPane app;

  protected Identity identity;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    identity = Identity.getInstance();
  }

  /**
   * Logs out user
   */
  protected void logout() {
    identity.logOut();
    identity = Identity.getInstance();
    switchScene( "Login");
  }

  /**
   * Switches between different scenes
   * @param view scene to be viewed
   * */
  public void switchScene(String view) {
    /*
    if (identity != null) {
      System.out.println("Identity: " + identity.getUsername());
    } else {
      System.out.println("No identity");
    }
    */

    try {
      // Odstránime obsah view, ktorý je momentálne zobrazený
      app.getChildren().clear();
      // Vymeníme za nový obsah
      app.getChildren().add(FXMLLoader.load(getClass().getResource("../view/" + view + ".fxml")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
