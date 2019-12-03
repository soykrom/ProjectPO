package m19.users;

public class NormalBehaviour extends UserBehaviour {
    //atributes
    private final int MAXWORKS = 3;

    //methods
    public NormalBehaviour() {
        setMaxWorks(MAXWORKS);
    }

    @Override
    public String toString() {
        return "NORMAL";
    }
}