package m19.users;

import java.io.Serializable;

public class UserBehaviour implements Serializable {
    //atributes
    private int _maxWorks;

    //methods
    public int getMaxWorks() {
        return _maxWorks;
    }

    public void setMaxWorks(int maxWorks) {
        _maxWorks = maxWorks;
    }
}