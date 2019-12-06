package m19;

import m19.exceptions.WorkDoesntBelongToUserException;
import m19.exceptions.MissingFileAssociationException;
import m19.exceptions.BadEntrySpecificationException;
import m19.exceptions.UserRegistrationFailException;
import m19.exceptions.FailedToOpenFileException;
import m19.exceptions.RuleUnsuccessfulException;
import m19.exceptions.LateDeliveryException;
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
    if(_filename.isEmpty()) throw new MissingFileAssociationException();

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
    try {
      ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
      
      _library = (Library) ois.readObject();
      ois.close();
    } catch(IOException e) {throw new FailedToOpenFileException(filename);}

    _filename = filename;

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

  public void advanceDate(int delta) {
    changeSaved(true);

    _library.advanceDate(delta);

  }

  public int addUser(String name, String email) throws UserRegistrationFailException {
    changeSaved(true);

    return _library.addUser(name, email);
  }

  public User displayUser(int id) throws UserNotFoundException {
    return _library.getUser(id);
  }

  public List<User> getAllUsers() {    
    return _library.getAllUsers();
  }

  public Work displayWork(int id) throws WorkNotFoundException {
    return _library.getWork(id);
  }

  public List<Work> getAllWorks() {
    return _library.getAllWorks();
  }

  public List<Work> performSearch(String term) {
    return _library.performSearch(term);
  }

  public int requestWork(int userID, int workID) throws UserNotFoundException, WorkNotFoundException, RuleUnsuccessfulException {
    return _library.requestWork(userID, workID);
  }

  public void notificationHandler(String response) {}

  public List<Notification> getAllNotifications(int userID) {
    return _library.getAllNotifications(userID);
  }

  public void addObserver(Work work, User user) {
    _library.addObserver(work, user);
  }
  
  public void returnWork(int userID, int workID) throws UserNotFoundException, WorkNotFoundException, WorkDoesntBelongToUserException, LateDeliveryException {
    _library.returnWork(userID, workID);
  }
}
