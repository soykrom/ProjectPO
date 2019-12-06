package m19.users;

import java.util.ArrayList;
import java.util.List;

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
    public String toString() {
        return "NORMAL";
    }
}