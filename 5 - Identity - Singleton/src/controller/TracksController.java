package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TracksController extends BaseController
{
  @FXML
  private GridPane app;

  @FXML
  private Button dashboard;

  @FXML
  private Button logout;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    super.initialize(url, resourceBundle);

    assert app != null : "fx:id=\"app\" could not be created.";
    assert dashboard != null : "fx:id=\"dashboard\" could not be created.";
    assert logout != null : "fx:id=\"logout\" could not be created.";

    dashboard.defaultButtonProperty().bind(dashboard.focusedProperty());
    dashboard.setOnAction((e) -> switchScene("Dashboard"));

    logout.defaultButtonProperty().bind(logout.focusedProperty());
    logout.setOnAction((e) -> this.logout());
  }
}
