package m19.requests;

import java.io.Serializable;
import m19.users.User;
import m19.works.Work;

public class Request implements Serializable {
    //Atributes
    private User _user;
    private Work _work;

    public Request(User user, Work work) {
        _user = user;
        _work = work;
    }

    public User getUser() {
        return _user;
    }

    public Work getWork() {
        return _work;
    }
}