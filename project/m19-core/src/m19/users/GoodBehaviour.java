package m19.users;

import java.util.ArrayList;
import java.util.List;

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
    public String toString() {
        return "CUMPRIDOR";
    }
}