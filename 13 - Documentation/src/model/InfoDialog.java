package model;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 * Class defines info modal dialog
 */
public class InfoDialog {
  /**
   * Method renders and shows dialog
   * @param title string window title
   * @param message string message - content of dialog
   */
  public void display(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.initModality(Modality.APPLICATION_MODAL);
    alert.setTitle(title);
    alert.setHeaderText("Info");
    alert.setContentText(message);
    alert.showAndWait();
  }
}
