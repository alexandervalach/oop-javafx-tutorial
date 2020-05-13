package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;

/***
 * Definition of Track model which implements serializable to enable persistance
 */
public class Track implements Serializable {
  // Attribúty nech sú typu String
  private String title;
  private String artist;
  private String filename;

  public Track(String title, String artist, String filename) {
    this.title = title;
    this.artist = artist;
    this.filename = filename;
  }

  /**
   * Gets title
   * @return String
   */
  public String getTitle() {
    return title;
  }

  // Do tabuľky vstupuje StringProperty

  /**
   * Returns title as a string property
   * @return StringProperty
   */
  public StringProperty getTitleProperty() {
    // StringProperty je abstraktná trieda, preto ju inicializujeme pomocou SimpleStringProperty - konkrétna trieda
    return new SimpleStringProperty(title);
  }

  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns an artist
   * @return String
   */
  public String getArtist() {
    return artist;
  }

  /**
   * Input is StringProperty
   * @return
   */
  public StringProperty getArtistProperty() {
    // StringProperty je abstraktná trieda, preto ju inicializujeme pomocou SimpleStringProperty - konkrétna trieda
    return new SimpleStringProperty(artist);
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getFilename() {
    return filename;
  }

  public StringProperty getFilenameProperty() {
    return new SimpleStringProperty(filename);
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

}
