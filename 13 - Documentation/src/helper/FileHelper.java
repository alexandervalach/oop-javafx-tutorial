package helper;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Class contains file helper static methods
 */
public class FileHelper {
  /**
   * Gets a URL for existing file
   * @param url string url
   * @param view string name of fxml file
   * @return URL
   * @throws MalformedURLException when malformed URL was given
   */
  public static URL getLocation (String url, String view) throws MalformedURLException {
    return Paths.get(url + view + ".fxml").toUri().toURL();
  }

  /**
   * Checks if file exists using file descriptor
   * @param url string url to file
   * @return true if file exists
   */
  public static boolean fileExists (String url) {
    File file = new File(url);
    return file.exists();
  }
}
