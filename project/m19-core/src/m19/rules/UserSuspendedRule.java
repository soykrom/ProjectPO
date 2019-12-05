package m19.rules;

import m19.exceptions.RuleUnsuccessfulException;
import m19.users.User;

public class UserSuspendedRule implements Rule {
    private User _user;

    public UserSuspendedRule(User user) {
        _user = user;
    }

    public void validate() throws RuleUnsuccessfulException {
        if(!(_user.getStatus())) throw new RuleUnsuccessfulException(2);
    }
}