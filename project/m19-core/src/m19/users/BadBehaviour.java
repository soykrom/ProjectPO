package m19.users;

import java.util.ArrayList;
import java.util.List;

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
    public String toString() {
        return "FALTOSO";
    }
}