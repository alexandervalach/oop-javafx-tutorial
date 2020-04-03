package controller.tracks;

import controller.BaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Track;

import java.net.URL;
import java.util.ResourceBundle;

public class AllController extends BaseController
{
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

  public AllController() {
    tracksList = FXCollections.observableArrayList();
    tracksList.add(new Track("Tame Me Home, Country Roads", "John Denver"));
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

    registerListeners();
  }

  private void registerListeners () {
    dashboard.defaultButtonProperty().bind(dashboard.focusedProperty());
    dashboard.setOnAction((e) -> switchScene("dashboard"));

    logout.defaultButtonProperty().bind(logout.focusedProperty());
    logout.setOnAction((e) -> this.logout());
  }
}
