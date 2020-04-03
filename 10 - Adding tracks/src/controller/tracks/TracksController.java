package controller.tracks;

import controller.BaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Track;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class TracksController extends BaseController
{
  @FXML
  private Button refreshBtn;

  @FXML
  private Button addTrack;

  @FXML
  private Label trackTitle;

  @FXML
  private Label trackArtist;

  @FXML
  private TableView<Track> tracksTable;

  @FXML
  private TableColumn<Track, String> title;

  @FXML
  private TableColumn<Track, String> artist;

  @FXML
  private Button dashboard;

  @FXML
  private Button logout;

  private ObservableList<Track> tracksList;
  private Track track;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    super.initialize(url, resourceBundle);

    title.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
    artist.setCellValueFactory(cellData -> cellData.getValue().getArtistProperty());

    tracksList = FXCollections.observableArrayList();

    /*
    tracksList.add(new Track("Take Me Home, Country Roads", "John Denver"));
    tracksList.add(new Track("Iridescent", "Linkin Park"));
    tracksList.add(new Track("Three Little Birds", "Bob Marley & The Wailers"));
    tracksList.add(new Track("Aj tak sme frajeri", "Peter Nagy, Indigo"));
     */

    syncTracks();

    if (tracksList != null) {
      tracksTable.setItems(tracksList);
    } else {
      tracksTable.managedProperty().bind(tracksTable.visibleProperty());
    }

    showDetails(null);

    tracksTable.getSelectionModel().selectedItemProperty().addListener(
      (observable, oldValue, newValue) -> {
        showDetails(newValue);
        track = newValue;
    });

    registerListeners();
  }

  private void showDetails(Track track) {
    if (track != null) {
      trackTitle.setText(track.getTitle());
      trackArtist.setText(track.getArtist());
    } else {
      trackTitle.setText("");
      trackArtist.setText("");
    }
  }

  private void registerListeners () {
    addTrack.defaultButtonProperty().bind(addTrack.focusedProperty());
    addTrack.setOnAction((e) -> showAdd());

    dashboard.defaultButtonProperty().bind(dashboard.focusedProperty());
    dashboard.setOnAction((e) -> switchScene("dashboard"));

    logout.defaultButtonProperty().bind(logout.focusedProperty());
    logout.setOnAction((e) -> logout());

    refreshBtn.defaultButtonProperty().bind(refreshBtn.focusedProperty());
    refreshBtn.setOnAction((e) -> syncTracks());
  }

  /**
   * Shows add dialog using fxml file
   */
  public void showAdd() {
    FXMLLoader loader = new FXMLLoader();

    try {
      loader.setLocation(getLocation("tracks/add"));

      GridPane pane = loader.load();
      Scene scene = new Scene(pane);
      Stage addDialog = new Stage();

      addDialog.setTitle("Pridať skladbu");
      // addDialog.initOwner((Stage) trackTitle.getScene().getWindow());

      AddController controller = loader.getController();
      controller.setDialogStage(addDialog);

      addDialog.setScene(scene);
      addDialog.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void syncTracks () {
    String fileURL = rootURL + "/data/tracks.txt";

    if (fileExists(fileURL)) {
      tracksTable.getItems().removeAll(tracksList);

      try {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileURL));
        Track track;

        while (true) {
          track = (Track) is.readObject();
          // System.out.println(track.getTitle() + ", " + track.getArtist());
          tracksList.add(track);
        }
      } catch (EOFException e) {
        // System.out.println("Koniec suboru");
      } catch (ClassNotFoundException | IOException e) {
        e.printStackTrace();
      }

      tracksTable.setItems(tracksList);
    }
  }
}
