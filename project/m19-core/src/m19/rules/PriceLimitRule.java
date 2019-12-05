package m19.rules;

import m19.exceptions.RuleUnsuccessfulException;
import m19.users.GoodBehaviour; 
import m19.users.User;
import m19.works.Work;

public class PriceLimitRule implements Rule {
    private User _user;
    private Work _work;

    public PriceLimitRule(User user, Work work) {
        _user = user;
        _work = work;
    }

    public void validate() throws RuleUnsuccessfulException {
        if(!(_user.getBehaviour() instanceof GoodBehaviour) && _work.getPrice() > 25) 
            throw new RuleUnsuccessfulException(6);
    }
}