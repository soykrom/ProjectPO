package m19;

// FIXME import system types?
import m19.exceptions.MissingFileAssociationException;
import m19.exceptions.BadEntrySpecificationException;
import m19.exceptions.UserRegistrationFailException;
import m19.exceptions.FailedToOpenFileException;
import m19.exceptions.UserNotFoundException;
import m19.exceptions.WorkNotFoundException;
import m19.exceptions.ImportFileException;
import java.lang.ClassNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import m19.users.User;
import m19.works.Work;


/**
 * The façade class.
 */
public class LibraryManager {

  private String _filename;
  private boolean _saveStatus; //Controls if there has been any changes
  private Library _library;

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

    changeSaved(false);
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

    changeSaved(false);
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

    changeSaved(true);
  }

  public String getFilename() {
    return _filename;
  }

  public void changeSaved(boolean newStatus) {
    _saveStatus = newStatus;
  }

  public int getDate() {
    return _library.getDate();
  }

  public void advanceDate(int delta) { //input has already been validated
    int newDate = _library.getDate() + delta;

    _library.setDate(newDate);

    changeSaved(true);
  }

  public int addUser(String name, String email) throws UserRegistrationFailException {
    int id = _library.addUser(name, email);
    changeSaved(true);

    return id;
  }

  public User displayUser(int id) throws UserNotFoundException {
    return _library.getUser(id);
  }

  public List<User> getAllUsers() {
    List<User> list = new ArrayList<User>(_library.getAllUsers().values());
    
    return list;
  }

  public Work displayWork(int id) throws WorkNotFoundException {
    return _library.getWork(id);
  }

  public List<Work> getAllWorks() {
    List<Work> list = new ArrayList<Work>(_library.getAllWorks().values());

    return list;
  }
}
