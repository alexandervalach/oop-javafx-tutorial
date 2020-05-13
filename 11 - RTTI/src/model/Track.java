package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/***
 * Definujeme si najprv model Track
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

  public String getTitle() {
    return title;
  }

  // Do tabuľky vstupuje StringProperty
  public StringProperty getTitleProperty() {
    // StringProperty je abstraktná trieda, preto ju inicializujeme pomocou SimpleStringProperty - konkrétna trieda
    return new SimpleStringProperty(title);
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getArtist() {
    return artist;
  }

  // Do tabuľky vstupuje StringProperty
  public StringProperty getArtistProperty() {
    // StringProperty je abstraktná trieda, preto ju inicializujeme pomocou SimpleStringProperty - konkrétna trieda
    return new SimpleStringProperty(artist);
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

}
