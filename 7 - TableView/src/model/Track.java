package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Track {
  private String title;
  private String artist;

  public Track () {

  }

  public Track(String title, String artist) {
    this.title = title;
    this.artist = artist;
  }

  public String getTitle() {
    return title;
  }

  public StringProperty getTitleProperty() {
    return new SimpleStringProperty(title);
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getArtist() {
    return artist;
  }

  public StringProperty getArtistProperty() {
    return new SimpleStringProperty(artist);
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }
}
