package m19.exceptions;

public class LateDeliveryException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901101348L;

    private int _days;

    public LateDeliveryException(int days) {
        _days = days;
    }
    
    public int getFine() {
        return _days * (-5);
    }
}