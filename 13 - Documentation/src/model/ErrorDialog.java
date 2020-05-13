package model;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 * Initializes a new dialog
 */
public class ErrorDialog {
  public void display(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.initModality(Modality.APPLICATION_MODAL);
    alert.setTitle(title);
    alert.setHeaderText("Nastala chyba");
    alert.setContentText(message);
    alert.showAndWait();
  }
}
