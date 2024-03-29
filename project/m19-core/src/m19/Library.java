package m19;

import m19.exceptions.WorkDoesntBelongToUserException;
import m19.exceptions.MissingFileAssociationException;
import m19.exceptions.BadEntrySpecificationException;
import m19.exceptions.UserRegistrationFailException;
import m19.exceptions.RuleUnsuccessfulException;
import m19.exceptions.RuleUnsuccessfulException;
import m19.exceptions.FailedToOpenFileException;
import m19.exceptions.FailedToOpenFileException;
import m19.exceptions.LateDeliveryException;
import m19.exceptions.UserNotFoundException;
import m19.exceptions.WorkNotFoundException;
import m19.exceptions.ActiveUserException;
import m19.exceptions.ImportFileException;
import java.lang.ClassNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;

import m19.rules.OneRuleToRuleThemAll;
import m19.notifications.Notification;
import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.util.Collections;
import java.io.Serializable;
import m19.requests.Request;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.TreeMap;
import java.util.List;
import m19.users.User;
import m19.works.Work;
import m19.works.Book;
import m19.works.DVD;
import java.util.Map;

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201901101348L;

  /**  Current date */
  private int _date;

  /** Last user's ID number */
  private int _lastUserID;

  /** Last work's ID number */
  private int _lastWorkID;

  /** Mapping between ID number and its correspondent user */
  private Map<Integer, User> _users;

  /** Mapping between ID number and its correspondent work */
  private Map<Integer, Work> _works;

  /** Controls if there has been any changes */
  private boolean _saveStatus; 

   /**
   * Constructor.
   *
   * initializes parameters _date, _lastUserID and _lastWorkID at 0
   */       
  public Library() {
    _date = 0;
    _lastUserID = 0;
    _users = new TreeMap<Integer, User>();
    _lastWorkID = 0;
    _works = new TreeMap<Integer, Work>();
    _saveStatus = false;
  }

  /**
   * Read the text input file at the beginning of the program and populates the
   * instances of the various possible types (books, DVDs, users).
   * 
   * @param filename
   *          name of the file to load.
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
      catch(IOException e) {e.printStackTrace();} 
      catch(UserRegistrationFailException e) {e.printStackTrace();}

    _saveStatus = true;
  }

  public boolean getSaveStatus() {
    return _saveStatus;
  }

  public void setSaveStatus(boolean status) {
    _saveStatus = status;
  }

  /** 
   * @return the current date.
   */
  public int getDate() {
    return _date;
  }
  
  /**
   * @param date
   *          the current date.
   */
  public void setDate(int date) {
    _date = date;
  }

  /**
   * @param delta
   *  the number of days to advance
   */
  public void advanceDate(int delta) {
    if(delta <= 0) return;

    _date += delta;

    changeDeadlines(delta);

    _saveStatus = true;
  }

  /**
   * @param id
   *          user's ID number.
   *     
   * @throws UserNotFoundException
   * 
   * @return the user correspondent to the given ID number.
   */
  public User getUser(int id) throws UserNotFoundException {
    if(id > _users.size() - 1 || id < 0) throw new UserNotFoundException();

    return _users.get(id);    
  }

  /** 
   * Return all users.
   * 
   * @return a list of all users.
   */
  public List<User> getAllUsers() {
    List<User> list = new ArrayList<User>(_users.values());

    Collections.sort(list, User.USER_COMPARATOR);
    return list;
  }
  
  /**
   * @param name
   *          user's name.
   * @param email
   *          user's email.
   * 
   * @throws UserRegistrationFailException
   * 
   * @return new user's ID number.
   */
  public int addUser(String name, String email) throws UserRegistrationFailException {
    if(name.isEmpty() || email.isEmpty()) throw new UserRegistrationFailException();
    User newUser = new User(_lastUserID, name, email);

    _users.put(_lastUserID, newUser);

    _saveStatus = true;

    return _lastUserID++;
  }

  /**
   * @param id 
   *          user's ID number.
   * 
   * @throws WorkNotFoundException
   * 
   * @return work correspondent to the given ID number.
   */
  public Work getWork(int id) throws WorkNotFoundException {
    if(id > _works.size() - 1 || id < 0) throw new WorkNotFoundException();

    return _works.get(id);
  }

  /**
   * Create and register DVD.
   * 
   * Incrementation of ID number.
   * 
   * @param title 
   *          new DVD's title.
   * @param producer
   *          new DVD's producer.
   * @param price
   *          new DVD's price.
   * @param category
   *          new DVD's category.
   * @param IGAC
   *          new DVD's IGAC.
   * @param copies
   *          new DVD's number of copies
   */
  public void addDVD(String title, String producer, int price, String category, String IGAC, int copies) {
    DVD newDVD = new DVD(_lastWorkID, title, producer, price, category, IGAC, copies);

    _works.put(_lastWorkID, newDVD);

    _lastWorkID++;
  }

  /**
   * Create and register Book.
   * 
   * Incrementation of ID number.
   * 
   * @param title 
   *          new Book's title.
   * @param producer
   *          new Book's author.
   * @param price
   *          new Book's price.
   * @param category
   *          new Book's category.
   * @param IGAC
   *          new Book's ISBN.
   * @param copies
   *          new Book's number of copies
   */
  public void addBook(String title, String author, int price, String category, String ISBN, int copies) {
    Book newBook = new Book(_lastWorkID, title, author, price, category, ISBN, copies);

    _works.put(_lastWorkID, newBook);

    _lastWorkID++;
  }

  /** 
   * Return all  works.
   * 
   * @return a list of all works.
   */
  public List<Work> getAllWorks() {
    List<Work> list = new ArrayList<Work>(_works.values());

    return list;
  }

  /**
   * Return the list containing all the works that have the search term
   * 
   * @param term
   *          Search term
   * 
   * @return a list of all works that have the search term in the specified atributes.
   */
  public List<Work> performSearch(String term) {
    List<Work> list = new ArrayList<Work>();

    for(Work work : _works.values()) {
      if(searchWork(work, term.toLowerCase())) {
        list.add(work);
      }
    }

    return list;
  }

  /**
   * Handles the fine payment process
   * 
   * @param userID
   *        the users ID
   * 
   * @throws UserNotFoundException
   * @throws ActiveUserException
   *
   */
  public void payFine(int userID) throws UserNotFoundException, ActiveUserException {
    User user = getUser(userID);
    
    if(user.getStatus()) throw new ActiveUserException();

    user.setFine(0);
    if(!(user.delayedRequests())) user.setStatus(true);

    _saveStatus = true;
  }

  /**
   * Checks if work has search term in specific fields
   * 
   * @param work
   *          work to be checked
   * 
   * @param term
   *          Search term
   * 
   * @return a boolean according to the search result
   */
  public boolean searchWork(Work work, String term) {
    return work.searchTitle(term) || work.searchFields(term);
  }

  /**
   * Handles the process of a request
   * 
   * @param userID
   *        ID of the user that is requesting a work
   * 
   * @param workID
   *        ID of the requested work
   * 
   * @throws UserNotFoundException
   * @throws WorkNotFoundException
   * @throws RuleUnsuccessfulException
   * 
   * @return the number of days until the deadline
   */
  public int requestWork(int userID, int workID) throws UserNotFoundException, WorkNotFoundException, RuleUnsuccessfulException {
    User user = getUser(userID);
    Work work = getWork(workID);

    int days = user.getDaysTillDeadline(work.getTotalCopies());

    Request request = new Request(user, work, days);

    (new OneRuleToRuleThemAll(request)).validate();

    work.decrementLibraryCopies();
    
    user.addRequest(request);

    _saveStatus = true;

    return _date + days;
  }

  public void requestNotification(String response, int userID, int workID) {
    if(response.equals("s")) {
      User user = _users.get(userID);
      Work work = _works.get(workID);
  
      work.addObserver(user);
    }

    _saveStatus = true;
  }

  /**
   * Subtracts from de days remaing until the deadline, the days advanced
   * 
   * @param days
   *        Number of days advanced
   */
  public void changeDeadlines(int days) {
    for(User user : _users.values()) {
      user.changeDeadlines(days);
    }
  }

  /**
   * Handles the process of a return
   * 
   * @param userID
   *        ID of the user that is requesting a work
   * 
   * @param workID
   *        ID of the requested work
   * 
   * @throws UserNotFoundException
   * @throws WorkNotFoundException
   * @throws WorkDoesntBelongToUserException
   * 
   */
  public void returnWork(int userID, int workID) throws UserNotFoundException, WorkNotFoundException, WorkDoesntBelongToUserException, LateDeliveryException {
    User user = getUser(userID);
    Work work = getWork(workID);

    Request r = user.getRequest(work);

    user.removeRequest(r);
    user.getBehaviour().updateBehaviour(user);

    work.incrementLibraryCopies();

    _saveStatus = true;
    if(r.getDays() < 0) {
      user.updateFine(r.getDays()); 
      throw new LateDeliveryException(user.getFine());
    }
  }

  public void requestPayment(String response, int userID) {
    if(response.equals("s")) {
      User user = _users.get(userID);

      user.setFine(0);
      if(!(user.delayedRequests())) user.setStatus(true);
    }

    _saveStatus = true;
  }

  public List<Notification> getAllNotifications(int userID) throws UserNotFoundException {
    User user = getUser(userID);

    _saveStatus = true;

    return user.getNotifications();
  }

}