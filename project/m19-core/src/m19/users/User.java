package m19.users;

import m19.requests.Request;
import java.io.Serializable;
import java.util.Comparator;
import java.util.ArrayList;
import java.text.Collator;
import java.util.TreeMap;
import java.util.Locale;
import java.util.List;
import java.util.Map;

public class User implements Serializable {
    //Atributes
    private int _userID;

    private String _name;

    private String _email;

    private boolean _status;
    
    private UserBehaviour _behaviour;

    private int _fine;

    private List<Request> _requests;

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

    @Override
    public String toString() {
        if(_status)
            return _userID + " - " + _name + " - " + _email + " - " + _behaviour + " - " + "ACTIVO";

        return _userID + " - " + _name + " - " + _email + " - " + _behaviour + " - " + "SUSPENSO - EUR " + _fine;    
    }
}   