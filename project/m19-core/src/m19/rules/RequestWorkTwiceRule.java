package m19.rules;

import m19.exceptions.RuleUnsuccessfulException;
import m19.users.User;
import m19.works.Work;

public class RequestWorkTwiceRule implements Rule {
    private User _user;
    private Work _work;

    public RequestWorkTwiceRule(User user, Work work) {
        _user = user;
        _work = work;
    }

    public void validate() throws RuleUnsuccessfulException {
        //if(_user.searchRequests(_work)) throw new RuleUnsuccessfulException(1);
    }
}