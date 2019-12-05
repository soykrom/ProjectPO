package m19.rules;

import m19.exceptions.RuleUnsuccessfulException;
import m19.users.User;

public class RequestLimitRule implements Rule {
    private User _user;

    public RequestLimitRule(User user) {
        _user = user;
    }

    public void validate() throws RuleUnsuccessfulException {
        if(_user.getNumberRequests() == _user.getBehaviour().getMaxWorks()) throw new RuleUnsuccessfulException(4);
    }
}