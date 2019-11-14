package m19.users;
import java.util.Map;
import java.util.TreeMap;


public class User {
    //Atributes
    private int _userID;

    private String _name;

    private String _email;

    private boolean _status;
    
    private UserBehaviour _behaviour;
    //private requests;

    //Methods
    public User(int id, String name, String email) {
        _userID = id;
        _name = name;
        _email = email;
        _status = true;
        _behaviour = new NormalBehaviour();
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
}   