package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.InfoDialog;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends BaseController {
  @FXML
  private GridPane app;

  @FXML
  private Button tracks;

  @FXML
  private Button logout;

  // private InfoDialog infoDialog;

  public DashboardController () {
    // infoDialog = new InfoDialog();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    super.initialize(url, resourceBundle);

    assert app != null : "fx:id=\"app\" cannot be created";
    assert tracks != null : "fx:id=\"tracks\" cannot be created";
    assert logout != null : "fx:id=\"logout\" cannot be created";

    tracks.defaultButtonProperty().bind(tracks.focusedProperty());
    logout.defaultButtonProperty().bind(logout.focusedProperty());

    tracks.setOnAction((e) -> {
      this.switchScene("Tracks/All");
    });

    logout.setOnAction((e) -> {
      this.switchScene("Login");
    });
  }
}
