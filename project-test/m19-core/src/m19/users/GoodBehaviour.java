package m19.users;

public class GoodBehaviour extends UserBehaviour {
    //atributes
    private final int MAXWORKS = 5;

    //methods 
    public GoodBehaviour() {
        setMaxWorks(MAXWORKS);
    }

    public String toString() {
        return "CUMPRIDOR";
    }
}