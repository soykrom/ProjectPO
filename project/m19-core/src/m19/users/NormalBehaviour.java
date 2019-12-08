package m19.users;

import java.util.ArrayList;
import java.util.List;
import m19.users.User;


public class NormalBehaviour extends UserBehaviour {
    //atributes
    private final int MAXWORKS = 3;
    private final List<Integer> DAYS;

    //methods
    public NormalBehaviour() {
        setMaxWorks(MAXWORKS);
        
        DAYS = new ArrayList<Integer>() {{
                    add(3); 
                    add(8); 
                    add(15);
                }};

        setDays(DAYS);
    }

    @Override
    public void updateBehaviour(User user) {
        List<Integer> lastDeliveries = user.getDeliveries();
        int sum = 0;

        //System.out.println("1 ; " + lastDeliveries.size());
        if(lastDeliveries.size() < 3) return;

        //System.out.println("2");
        for(int i = lastDeliveries.size() - 1; i > lastDeliveries.size() - 4; i--) {
            sum += lastDeliveries.get(i);
        }   
        
        //System.out.println("3 ; " + sum);

        if(sum == 0) user.setBehaviour(new BadBehaviour());

        //System.out.println("4");
        if(lastDeliveries.size() < 5) return;

        //System.out.println("5");
        for(int i = lastDeliveries.size() - 4; i > lastDeliveries.size() - 6; i--) {
            sum += lastDeliveries.get(i);
        }

        //System.out.println("6 ; " + sum);
        if(sum == 5) user.setBehaviour(new GoodBehaviour());
    }

    @Override
    public String toString() {
        return "NORMAL";
    }
}