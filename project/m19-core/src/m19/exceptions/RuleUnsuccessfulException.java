package m19.exceptions;

public class RuleUnsuccessfulException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901101348L;

    private int _ruleNumber;

    public RuleUnsuccessfulException(int ruleNumber) {
        _ruleNumber = ruleNumber;
    }

    public int getRuleNumber() {
        return _ruleNumber;
    }
}