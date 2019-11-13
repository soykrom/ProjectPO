package m19;

// FIXME import system types
import m19.exceptions.*;
import java.io.IOException;
import java.io.Serializable;

// FIXME import project (core) types

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201901101348L;

  // FIXME define attributes
  private int _date;

  private int _lastID;


  // FIXME define contructor(s)
  public Library() {
    _date = 0;
    _lastID = 1;
  }
  // FIXME define methods

  /**
   * Read the text input file at the beginning of the program and populates the
   * instances of the various possible types (books, DVDs, users).
   * 
   * @param filename
   *          name of the file to load
   * @throws BadEntrySpecificationException
   * @throws IOException
   */

  void importFile(String filename) throws BadEntrySpecificationException, IOException {
    // FIXME implement method
  }

}
