package controller.tracks;

import controller.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AppendingObjectOutputStream;
import model.Track;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController extends BaseController {
  @FXML
  private TextField title;

  @FXML
  private TextField artist;

  @FXML
  private Button save;

  @FXML
  private Button cancel;

  private Stage dialog;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    super.initialize(url, resourceBundle);

    save.defaultButtonProperty().bind(save.focusedProperty());
    cancel.defaultButtonProperty().bind(cancel.focusedProperty());

    save.setOnAction(e -> {
      try {
        addTrack();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });

    cancel.setOnAction(e -> closeDialog());

    /*
    save.setOnAction(e -> {
      try {
        // saveTrack();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });

    cancel.setOnAction(e -> handleCancel());
    */
  }

  @FXML
  private void addTrack() throws IOException {
    Track track = new Track(title.getText(), artist.getText());
    String dataURL = rootURL + "/data/tracks.txt";

    if (fileExists(dataURL)) {
      AppendingObjectOutputStream as = new AppendingObjectOutputStream(new FileOutputStream(dataURL, true));
      as.writeObject(track);
      as.close();
    } else {
      ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(dataURL));
      os.writeObject(track);
      os.close();
    }

    dialog.close();
  }

  /**
   * Closes modal window.
   * Called when clear button is clicked.
   */
  @FXML
  private void closeDialog() {
    this.dialog.close();
  }

  public void setDialogStage(Stage dialog) {
    this.dialog = dialog;
  }
}
