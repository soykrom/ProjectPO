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

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.Serializable;
import java.io.FileReader;
import java.util.TreeMap;
import m19.users.User;
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

  private int _lastID;

  private Map<Integer, User> _users;

  //private Map<Integer, Work> _works;
  // FIXME define contructor(s)
  public Library() {
    _date = 0;
    _lastID = 0;
    _users = new TreeMap<Integer, User>();
    //_works = new TreeMap<Integer, Work>();
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
            break;
          case "BOOK":
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

  public User getUser(int id) {
    return _users.get(id);    
  }

  public Map<Integer, User> getAllUsers() {
    return _users;
  }
  
  public int addUser(String name, String email) throws UserRegistrationFailException {
    if(name == null || email == null) throw new UserRegistrationFailException();

    User newUser = new User(_lastID, name, email);

    _users.put(_lastID, newUser);

    return _lastID++;
  }

}
