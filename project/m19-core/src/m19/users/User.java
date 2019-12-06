package m19.users;

import m19.exceptions.WorkDoesntBelongToUserException;
import m19.requests.Request;
import java.io.Serializable;
import java.util.Comparator;
import java.util.ArrayList;
import java.text.Collator;
import java.util.Observer;
import java.util.TreeMap;
import java.util.Locale;
import java.util.List;
import m19.works.Work;
import java.util.Map;

public class User implements Serializable, Observer {
    //Atributes
    private int _userID;

    private String _name;

    private String _email;

    private boolean _status;
    
    private UserBehaviour _behaviour;

    private int _fine;

    private List<Request> _requests;
    
    private List<Notification> _notifications;

    public static final Comparator<User> USER_COMPARATOR = new Comparator<User>() {
        public int compare(User u1, User u2) {
            Locale locale = Locale.getDefault();

            Collator collator = Collator.getInstance(locale);

            if(!u1.getName().equals(u2.getName()))
                return collator.compare(u1.getName(), u2.getName());
            
            return (u1.getUserID() - u2.getUserID());
        }
    };

    //Methods
    public User(int id, String name, String email) {
        _userID = id;
        _name = name;
        _email = email;
        _status = true;
        _behaviour = new NormalBehaviour();
        _fine = 0;
        _requests = new ArrayList<Request>();
        _notifications = new ArrayList<Notification>();
    }

    public int getUserID() {
        return _userID;
    }

    public String getName() {
        return _name;
    }

    public String getMail() {
        return _email;
    }

    public UserBehaviour getBehaviour() {
        return _behaviour;
    }
    
    public int getMaxWorks() {
        return _behaviour.getMaxWorks();
    }

    public List<Request> getRequests() {
        return _requests;
    }

    public int getNumberRequests() {
        return _requests.size();
    }
    
    public boolean getStatus() {
        return _status;
    }

    public void setStatus(boolean status) {
        _status = status;
    }

    public boolean searchRequests(Work work) {
        for(Request r : _requests) {
            if(r.getWork().equals(work))
                return true;
        }
        return false;
    }

    public Request getRequest(Work work) throws WorkDoesntBelongToUserException {
        for(Request r : _requests) {
            if(r.getWork().equals(work))
                return r;
        }

        throw new WorkDoesntBelongToUserException();
    }

    public void addRequest(Request request) {
        _requests.add(request);
    }

    public void removeRequest(Request request) {
        _requests.remove(request);
    }

    public int getDaysTillDeadline(int copies) {
        return _behaviour.getDaysTillDeadline(copies);
    }

    @Override
    public void update(Observable work, Object arg) {
        if(work instanceof Work) {
            Work newWork = (Work) work;
            _notifications.add(new Notification(newWork.toString()));
        }
    }
    
    public void changeDeadlines(int days) {
        for(Request request : _requests) {
            if(request.changeDeadline(days))
                _status = false;
        }
    }
    
    @Override
    public String toString() {
        if(_status)
            return _userID + " - " + _name + " - " + _email + " - " + _behaviour + " - " + "ACTIVO";

        return _userID + " - " + _name + " - " + _email + " - " + _behaviour + " - " + "SUSPENSO - EUR " + _fine;    
    }
}   