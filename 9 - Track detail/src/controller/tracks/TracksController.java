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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class TracksController extends BaseController
{
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

  public TracksController() {
    tracksList = FXCollections.observableArrayList();
    tracksList.add(new Track("Take Me Home, Country Roads", "John Denver"));
    tracksList.add(new Track("Iridescent", "Linkin Park"));
    tracksList.add(new Track("Three Little Birds", "Bob Marley & The Wailers"));
    tracksList.add(new Track("Aj tak sme frajeri", "Peter Nagy, Indigo"));
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    super.initialize(url, resourceBundle);

    title.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
    artist.setCellValueFactory(cellData -> cellData.getValue().getArtistProperty());

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
    logout.setOnAction((e) -> this.logout());
  }
}
