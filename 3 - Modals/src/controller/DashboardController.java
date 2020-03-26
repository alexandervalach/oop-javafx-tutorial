package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import model.InfoDialog;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends BaseController {
  @FXML
  private GridPane app;

  private InfoDialog infoDialog;

  public DashboardController () {
    infoDialog = new InfoDialog();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    super.initialize(url, resourceBundle);
    infoDialog.display("Vitajte", "John Denver - Country Roads");
  }
}
