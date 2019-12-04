package m19.app.works;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;
import m19.works.Work;
import java.util.List;

/**
 * 4.3.3. Perform search according to miscellaneous criteria.
 */
public class DoPerformSearch extends Command<LibraryManager> {

  private Input<String> _term;

  /**
   * @param m
   */
  public DoPerformSearch(LibraryManager m) {
    super(Label.PERFORM_SEARCH, m);
    _term = _form.addStringInput(Message.requestSearchTerm());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();

    List<Work> works = _receiver.performSearch(_term.value());

    for(Work work: works)
      _display.addLine(work.toString());

    _display.display();
    
  }
  
}
