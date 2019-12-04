package m19.users;

import java.io.Serializable;
import java.util.Comparator;
import java.text.Collator;
import java.util.TreeMap;
import java.util.Locale;
import java.util.Map;

public class User implements Serializable {
    //Atributes
    private int _userID;

    private String _name;

    private String _email;

    private boolean _status;
    
    private UserBehaviour _behaviour;

    private int _fine;

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