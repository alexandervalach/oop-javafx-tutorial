package core;

import model.User;
import security.Hash;

import java.io.*;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    ArrayList<User> users = new ArrayList<User>();
    users.add(new User("admin", "admin"));
    users.add(new User("root", "root"));
    users.add(new User("user", "user"));

    // Pridáme bezpečnosť tým, že heslo hašujeme
    for (User user : users) {
      System.out.println("Username: " + user.getUsername() + ", Password: " + Hash.md5(user.getPassword()));
    }

    // Uložíme objekty
    saveObjects(users);
    ArrayList<User> deserializedUsers = loadObjects();

    if (deserializedUsers != null) {
      for (User user : deserializedUsers) {
        System.out.println("Username: " + user.getUsername() + ", Password: " + Hash.md5(user.getPassword()));
      }
    } else {
      System.out.println("Nie je co deserializovat");
    }
  }

  private static void saveObjects(ArrayList<User> users) {
    try {
      System.out.println("Serializujem data...");

      FileOutputStream fileOutputStream = new FileOutputStream("users.txt");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

      /*
      // We can serialize each object of array list
      for (User user : users) {
        objectOutputStream.writeObject(user);
      }
      */

      objectOutputStream.writeObject(users);
      objectOutputStream.flush();
      objectOutputStream.close();

      System.out.println("Hotovo");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static ArrayList<User> loadObjects() {
    try {
      System.out.println("Deserializujem data...");

      FileInputStream fileInputStream = new FileInputStream("users.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      ArrayList<User> users = (ArrayList<User>) objectInputStream.readObject();

      System.out.println("Hotovo");

      objectInputStream.close();

      return users;
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
