package controller.tracks;

import controller.BaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ErrorDialog;
import model.Track;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TracksController extends BaseController
{
  @FXML
  private Button refreshBtn;

  @FXML
  private Button addTrackBtn;

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
  private Button dashboardBtn;

  @FXML
  private Button playBtn;

  @FXML
  private Button stopBtn;

  private ObservableList<Track> tracksList;
  private Track track;
  ErrorDialog errorDialog;

  public TracksController() {
    super();
    errorDialog = new ErrorDialog();
    tasksQueue = new ArrayList<>(3);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    super.initialize(url, resourceBundle);

    title.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());
    artist.setCellValueFactory(cellData -> cellData.getValue().getArtistProperty());

    tracksList = FXCollections.observableArrayList();

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
    // Home (back to dashboard) button
    dashboardBtn.defaultButtonProperty().bind(dashboardBtn.focusedProperty());
    dashboardBtn.setOnAction((e) -> switchScene("dashboard"));

    // Add track button
    addTrackBtn.defaultButtonProperty().bind(addTrackBtn.focusedProperty());
    addTrackBtn.setOnAction(e -> {
      try {
        showAddDialog();
      } catch (IOException ex) {
        errorDialog.display("Chyba s oknami", "Nepodarilo sa otvorit nove okno");
      }
    });

    // Refresh data button
    refreshBtn.defaultButtonProperty().bind(refreshBtn.focusedProperty());
    // Pouzity lambda vyraz
    refreshBtn.setOnAction(e -> {
      try {
        syncTracks();
      } catch (IOException ex) {
        ex.printStackTrace();
      } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
      }
    });

    // Náš play button
    playBtn.defaultButtonProperty().bind(playBtn.focusedProperty());
    playBtn.setOnAction(e -> {

    });

    playBtn.defaultButtonProperty().bind(playBtn.focusedProperty());
    playBtn.setOnAction(e -> {
      Track track = tracksTable.getSelectionModel().getSelectedItem();

      Task<Void> task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
          Media hit = new Media(new File("data/" + track.getFilename()).toURI().toString());
          MediaPlayer player = new MediaPlayer(hit);
          player.play();

          while (true) {
            if (isDone() || isCancelled()) {
              player.stop();
              break;
            }
          }

          return null;
        }
      };

      Thread thread = new Thread(task);
      thread.setDaemon(true);
      thread.start();
      tasksQueue.add(thread);
    });

    stopBtn.defaultButtonProperty().bind(stopBtn.focusedProperty());
    stopBtn.setOnAction(e -> {
      for (Thread thread : tasksQueue) {
        if (thread.getState() == Thread.State.RUNNABLE) {
          thread.stop();
        }
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
          // System.out.println(track.getTitle() + ", " + track.getArtist());
          tracksList.add(track);
        }
      } catch (EOFException e) {
        // System.out.println("Koniec suboru");
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
