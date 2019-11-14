package m19;

// FIXME import system types
import m19.exceptions.MissingFileAssociationException;
import m19.exceptions.BadEntrySpecificationException;
import m19.exceptions.UserRegistrationFailException;
import m19.exceptions.FailedToOpenFileException;
import m19.exceptions.ImportFileException;
import java.lang.ClassNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
// FIXME import project (core) types

/**
 * The façade class.
 */
public class LibraryManager {

  private String _filename;
  private boolean _saveStatus; //Controls if there has been any changes
  private Library _library;

  // FIXME define contructor(s)
  public LibraryManager() {
    _saveStatus = false;
    _filename = "";
    _library = new Library();
  }
  public LibraryManager(String filename) {
    _saveStatus = false;
    _filename = filename;
    _library = new Library();
  }
  // FIXME define methods

  /**
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void save() throws MissingFileAssociationException, IOException {
    ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));

    oos.writeObject(_library);
    oos.close();
  }

  /**
   * @param filename
   * @throws MissingFileAssociationException
   * @throws IOException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, IOException {
    if(!_saveStatus) return; //if there haven't been changes, exits

    _filename = filename;
    save();
  }

  /**
   * @param filename
   * @throws FailedToOpenFileException
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public void load(String filename) throws FailedToOpenFileException, IOException, ClassNotFoundException {
    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));

    _library = (Library) ois.readObject();
    ois.close();
  }

  /**
   * @param datafile
   * @throws ImportFileException
   */
  public void importFile(String datafile) throws ImportFileException {
    try {
      _library.importFile(datafile);
    } catch (IOException | BadEntrySpecificationException e) {
      throw new ImportFileException(e);
    }
  }

  public String getFilename() {
    return _filename;
  }

  public void changeSaved() {
    _saveStatus = (_saveStatus ? false : true);
  }

  public int getDate() {
    return _library.getDate();
  }

  public void setDate(int date) { 
    _library.setDate(date);
  }

  public void advanceDate(int delta) { //input has already been validated
    int newDate = _library.getDate() + delta;

    _library.setDate(newDate);
  }

  public void addUser(String name, String email) throws UserRegistrationFailException {
    _library.addUser(name, email);

  }
}
