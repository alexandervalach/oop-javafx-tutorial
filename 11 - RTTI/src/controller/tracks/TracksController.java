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
import model.ErrorDialog;
import model.Track;

import java.io.*;
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
  ErrorDialog errorDialog;

  public TracksController() {
    errorDialog = new ErrorDialog();
  }

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

    try {
      syncTracks();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

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
    dashboard.defaultButtonProperty().bind(dashboard.focusedProperty());
    dashboard.setOnAction((e) -> switchScene("dashboard"));

    logout.defaultButtonProperty().bind(logout.focusedProperty());
    logout.setOnAction((e) -> logout());

    addTrack.defaultButtonProperty().bind(addTrack.focusedProperty());
    addTrack.setOnAction(e -> {
      try {
        showAddDialog();
      } catch (IOException ex) {
        errorDialog.display("Chyba s oknami", "Nepodarilo sa otvorit nove okno");
      }
    });

    refreshBtn.defaultButtonProperty().bind(refreshBtn.focusedProperty());
    refreshBtn.setOnAction(e -> {
      try {
        syncTracks();
      } catch (IOException ex) {
        ex.printStackTrace();
      } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
      }
    });
  }

  private void showAddDialog() throws IOException {
    // System.out.println("Zobrazi sa Add Track Dialog okno");
    FXMLLoader loader = new FXMLLoader();

    loader.setLocation(getLocation("tracks/add"));
    GridPane pane = loader.load();
    Stage addDialog = new Stage();
    Scene scene = new Scene(pane);

    AddController controller = loader.getController();
    controller.setDialogStage(addDialog);

    addDialog.setTitle("Pridať skladbu");
    addDialog.setScene(scene);
    addDialog.showAndWait();
  }

  private void syncTracks () throws IOException, ClassNotFoundException {
    String dataURL = rootURL + "/data/tracks.txt";

    if (fileExists(dataURL)) {
      ObjectInputStream is = new ObjectInputStream(new FileInputStream(dataURL));

      tracksTable.getItems().removeAll(tracksList);

      try {
        while (true) {
          Track track = (Track) is.readObject();
          System.out.println(track.getTitle() + ", " + track.getArtist());
          tracksList.add(track);
        }
      } catch (EOFException e) {
        System.out.println("Koniec suboru");
      }

      tracksTable.setItems(tracksList);
    }

    /*
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
     */
  }
}
