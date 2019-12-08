package m19.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import m19.users.User;

public abstract class UserBehaviour implements Serializable {
    //atributes
    private int _maxWorks;
    private List<Integer> _days;

    //methods
    public int getMaxWorks() {
        return _maxWorks;
    }

    public void setMaxWorks(int maxWorks) {
        _maxWorks = maxWorks;
    }

    public void setDays(List<Integer> days) {
        _days = new ArrayList<Integer>(days);
    }

    public int getDaysTillDeadline(int copies) {
        int n = 0;

        if(copies == 1) n = 0;
        else if(copies <=5) n = 1;
        else if(copies > 5) n = 2;

        return _days.get(n);
    }

    public abstract void updateBehaviour(User user);

}