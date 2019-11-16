package m19.works;

public class DVD extends Work {
    //atributes
    private String _producer;
    private String _IGAC;

    public DVD(int id, String title, String producer, int price, String category, String IGAC, int copies) {
        super(id, title, price, category, copies);
        _producer = producer;
        _IGAC = IGAC;
    }

    public String getProducer() {
        return _producer;
    }

    public String getIGAC() {
        return _IGAC;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + "DVD - " + getWorkID() + " - " + getPrice() + " - " + getCategory().getDisplayed() + " - " + _producer + " - " + _IGAC; 
    }
}