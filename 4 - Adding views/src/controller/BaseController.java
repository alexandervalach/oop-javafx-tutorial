package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import model.ErrorDialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

abstract class BaseController implements Initializable {

  @FXML
  protected GridPane app;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  /**
   * Switches between different scenes
   * @param view scene to be viewed
   * */
  public void switchScene(String view) {
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
