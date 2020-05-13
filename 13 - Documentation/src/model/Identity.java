package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Objects that represents identity of logged user
 * Several constructors because of different usage (initializing, getting instance, updating)
 * Singleton design pattern (private constructors and static fields)
 */
public class Identity {
  protected boolean loggedIn;
  protected IntegerProperty id;
  protected StringProperty username;
  protected StringProperty password;
  private static Identity instance = null;

  /**
   * Private constructor
   * @param id integral identifier
   * @param username string identity username
   */
  private Identity(Integer id, String username) {
    this.id = new SimpleIntegerProperty(id);
    this.username = new SimpleStringProperty(username);
  }

  /**
   * Another constructor using only a username
   * @param username string identity username
   */
  private Identity(String username) {
    this.username = new SimpleStringProperty(username);
  }

  /**
   * Public singleton constructor which returns instance
   * Do not write to instance from multiple threads
   * @return single instance Identity
   */
  public static Identity getInstance() {
    return instance;
  }

  public static Identity getInstance(Integer id, String username) {
    if (instance == null) {
      instance = new Identity(id, username);
    }
    return instance;
  }

  public static Identity getInstance(String username) {
    if (instance == null) {
      instance = new Identity(username);
    }
    return instance;
  }

  public IntegerProperty getIdProperty() {
    return id;
  }

  public int getId() {
    return id.get();
  }

  public StringProperty getUsernameProperty() {
    return username;
  }

  public String getUsername() {
    return username.get();
  }

  public void setUsername(String username) {
    this.username.set(username);
  }

  public StringProperty getPasswordProperty() {
    return password;
  }

  public String getPassword() {
    return password.get();
  }

  public void setPassword(String password) {
    this.password.set(password);
  }

  /**
   * Sign out user
   */
  public void logOut() {
    loggedIn = false;
    instance = null;
  }

  /**
   * Check if user is logged
   * @return if user is logged
   */
  public boolean isUserLogged() {
    return loggedIn;
  }
}
