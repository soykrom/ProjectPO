package m19.requests;

import java.io.Serializable;
import m19.users.User;
import m19.works.Work;

public class Request implements Serializable {
    //Atributes
    private User _user;
    private Work _work;
    private int _days;

    public Request(User user, Work work, int days) {
        _user = user;
        _work = work;
        _days = days;
    }

    public User getUser() {
        return _user;
    }

    public Work getWork() {
        return _work;
    }

    public int getDays() {
        return _days;
    }

    public boolean changeDeadline(int days) {
        _days -= days;

        return _days < 0;
    }
}