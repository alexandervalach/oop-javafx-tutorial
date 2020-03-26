package model;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class InfoDialog {
  public void display(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.initModality(Modality.APPLICATION_MODAL);
    alert.setTitle(title);
    alert.setHeaderText("Info");
    alert.setContentText(message);
    alert.showAndWait();
  }
}
