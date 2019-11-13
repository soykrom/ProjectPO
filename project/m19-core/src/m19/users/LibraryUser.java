package m19;
import java.util.Map;
import java.util.TreeMap;


public class LibraryUser {
    //Atributes
    private int _userID;

    private String _name;

    private String _mail;

    private boolean _status;
    
    private UserBehaviour _behaviour;
    //private requests;

    //Methods
    public LibraryUser(int id, String name, String mail) {
        _userID = id;
        _name = name;
        _mail = mail;
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
        return _mail;
    }

    public boolean getStatus() {
        return _status;
    }

    public void setStatus(boolean status) {
        _status = status;
    }
}   