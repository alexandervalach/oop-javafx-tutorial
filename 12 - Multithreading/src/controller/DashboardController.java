package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.InfoDialog;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends BaseController {
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

    tracks.defaultButtonProperty().bind(tracks.focusedProperty());
    logout.defaultButtonProperty().bind(logout.focusedProperty());

    tracks.setOnAction((e) -> { this.switchScene("tracks/all"); });
    logout.setOnAction((e) -> { this.logout(); });
  }
}
