package m19.rules;

import m19.exceptions.RuleUnsuccessfulException;

public interface Rule {
    public void validate() throws RuleUnsuccessfulException;
}