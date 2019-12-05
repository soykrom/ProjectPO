package m19.rules;

import m19.exceptions.RuleUnsuccessfulException;
import m19.works.Work;

public class ReferenceWorkRule implements Rule {
    private Work _work;

    public ReferenceWorkRule(Work work) {
        _work = work;
    }

    public void validate() throws RuleUnsuccessfulException {
        if(!(_work.getCategory().getRequestable())) throw new RuleUnsuccessfulException(5);
    }
}