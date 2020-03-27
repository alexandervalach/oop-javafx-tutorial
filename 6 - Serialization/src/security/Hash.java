package security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
  public static String md5(String password)
  {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");

      byte[] messageDigest = md.digest(password.getBytes());

      BigInteger number = new BigInteger(1, messageDigest);

      // Convert message digest into hex value
      String hash = number.toString(16);

      /*
      while (hash.length() < 32) {
        hash = "0" + hash;
      }
      */
      return hash;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return "";
  }
}
