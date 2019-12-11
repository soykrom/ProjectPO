package m19.users;

import java.util.ArrayList;
import java.util.List;
import m19.users.User;

public class GoodBehaviour extends UserBehaviour {
    //atributes
    private final int MAXWORKS = 5;
    private final List<Integer> DAYS;

    //methods 
    public GoodBehaviour() {
        setMaxWorks(MAXWORKS);
        
        DAYS = new ArrayList<Integer>() {{
                    add(8); 
                    add(15); 
                    add(30);
                }};

        setDays(DAYS);
    }

    @Override
    public void updateBehaviour(User user) {
        if(user.getDeliveries().get(user.getDeliveries().size() - 1) == 0) user.setBehaviour(new NormalBehaviour());
    }

    @Override
    public String toString() {
        return "CUMPRIDOR";
    }
}