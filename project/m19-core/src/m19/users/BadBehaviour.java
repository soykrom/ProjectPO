package m19.users;

public class BadBehaviour extends UserBehaviour {
    //atributes
    private final int MAXWORKS = 1;

    //methods 
    public BadBehaviour() {
        setMaxWorks(MAXWORKS);
    }

    public String toString() {
        return "FALTOSO";
    }
}