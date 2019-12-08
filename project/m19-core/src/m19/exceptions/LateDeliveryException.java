package m19.exceptions;

public class LateDeliveryException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901101348L;

    private int _fine;

    public LateDeliveryException(int fine) {
        _fine = fine;
    }
    
    public int getFine() {
        return _fine;
    }
}