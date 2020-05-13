package model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Class extends object output stream
 * It appends data to existing file when serialized
 * It does not change serialization header
 */
public class AppendingObjectOutputStream extends ObjectOutputStream {

  /**
   * Constructor
   * @param out write object stream
   * @throws IOException from parent constructor
   */
  public AppendingObjectOutputStream(OutputStream out) throws IOException {
    super(out);
  }

  /**
   * Resets header instead of rewriting it
   */
  @Override
  protected void writeStreamHeader() throws IOException {
    reset();
  }

}
