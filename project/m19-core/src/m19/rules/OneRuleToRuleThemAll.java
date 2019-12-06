package m19.rules;

import m19.exceptions.RuleUnsuccessfulException;
import m19.requests.Request;
import m19.users.User;
import m19.works.Work;

public class OneRuleToRuleThemAll implements Rule {
    private Request _request;

    public OneRuleToRuleThemAll(Request request) {
        _request = request;
    }

    @Override
    public void validate() throws RuleUnsuccessfulException {
        
        (new RequestWorkTwiceRule(_request.getUser(), _request.getWork())).validate();
        (new UserSuspendedRule(_request.getUser())).validate();
        (new AllCopiesRequestedRule(_request.getWork())).validate();
        (new RequestLimitRule(_request.getUser())).validate();
        (new ReferenceWorkRule(_request.getWork())).validate();
        (new PriceLimitRule(_request.getUser(), _request.getWork())).validate();
          
        //All throw RuleUnsuccessfulException. Gotta be careful with number 3
    }

}