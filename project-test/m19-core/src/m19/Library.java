package m19;

// FIXME import system types
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

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.Serializable;
import java.io.FileReader;
import java.util.TreeMap;
import m19.users.User;
import m19.works.Work;
import m19.works.Book;
import m19.works.DVD;
import java.util.Map;

// FIXME import project (core) types

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201901101348L;

  // FIXME define attributes
  private int _date;

  private int _lastUserID;

  private int _lastWorkID;

  private Map<Integer, User> _users;

  private Map<Integer, Work> _works;

  // FIXME define contructor(s)
  public Library() {
    _date = 0;
    _lastUserID = 0;
    _users = new TreeMap<Integer, User>();
    _lastWorkID = 0;
    _works = new TreeMap<Integer, Work>();
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
    int nLine = 0;

    try {
      BufferedReader rdIn = new BufferedReader(new FileReader(filename));
      String btLine;

      while((btLine = rdIn.readLine()) != null) {
        String line = new String(btLine.getBytes(), "UTF-8");

        nLine++;

        if(line.charAt(0) == '#') continue; //ignores comments

        String[] split = line.split(":");
        
        switch(split[0]) {
          case "USER":
            addUser(split[1], split[2]);
            break;
          case "DVD":
            addDVD(split[1], split[2], Integer.parseInt(split[3]), split[4], split[5], Integer.parseInt(split[6]));
            break;
          case "BOOK":
            addBook(split[1], split[2], Integer.parseInt(split[3]), split[4], split[5], Integer.parseInt(split[6]));
            break;
          default:
            throw new BadEntrySpecificationException(split[0]);
        } 
      }

      rdIn.close();
    } catch(BadEntrySpecificationException e) {e.printStackTrace();}
      catch(UserRegistrationFailException e) {e.printStackTrace();}
  }

  public int getDate() {
    return _date;
  }

  public void setDate(int date) {
    _date = date;
  }

  public User getUser(int id) throws UserNotFoundException {
    if(id > _users.size() - 1) throw new UserNotFoundException();

    return _users.get(id);    
  }

  public Map<Integer, User> getAllUsers() {
    return _users;
  }
  
  public int addUser(String name, String email) throws UserRegistrationFailException {
    if(name == null || email == null) throw new UserRegistrationFailException();

    User newUser = new User(_lastUserID, name, email);

    _users.put(_lastUserID, newUser);

    return _lastUserID++;
  }

  public Work getWork(int id) throws WorkNotFoundException {
    if(id > _works.size() - 1) throw new WorkNotFoundException();

    return _works.get(id);
  }

  public int addDVD(String title, String producer, int price, String category, String IGAC, int copies) {
    DVD newDVD = new DVD(_lastWorkID, title, producer, price, category, IGAC, copies);

    _works.put(_lastWorkID, newDVD);

    return _lastWorkID++;
  }

  public int addBook(String title, String author, int price, String category, String ISBN, int copies) {
    Book newBook = new Book(_lastWorkID, title, author, price, category, ISBN, copies);

    _works.put(_lastWorkID, newBook);

    return _lastWorkID++;
  }

  public Map<Integer, Work> getAllWorks() {
    return _works;
  }

}
