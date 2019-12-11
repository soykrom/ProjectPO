package m19.users;

import java.util.ArrayList;
import java.util.List;
import m19.users.User;

public class BadBehaviour extends UserBehaviour {
    //atributes
    private final int MAXWORKS = 1;
    private final List<Integer> DAYS;

    //methods 
    public BadBehaviour() {
        setMaxWorks(MAXWORKS);
        
        DAYS = new ArrayList<Integer>() {{
                    add(2); 
                    add(2); 
                    add(2);
                }};

        setDays(DAYS);
    }

    @Override
    public void updateBehaviour(User user) {
        List<Integer> lastDeliveries = user.getDeliveries();

        for(int i = lastDeliveries.size() - 1; i > lastDeliveries.size() - 4; i--) {
            if(lastDeliveries.get(i) == 0)
                return;
        }   
        
        user.setBehaviour(new NormalBehaviour());
    }

    @Override
    public String toString() {
        return "FALTOSO";
    }
}