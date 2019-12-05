package m19.rules;

import m19.exceptions.RuleUnsuccessfulException;
import m19.works.Work;

public class AllCopiesRequestedRule implements Rule {
    private Work _work;

    public AllCopiesRequestedRule(Work work) {
        _work = work;
    }

    public void validate() throws RuleUnsuccessfulException {
        if(_work.getLibraryCopies() == 0) throw new RuleUnsuccessfulException(3);
    }
}