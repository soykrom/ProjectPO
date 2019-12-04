package m19.app.works;

import pt.tecnico.po.ui.Command;
import m19.LibraryManager;
import m19.works.Work;
import java.util.List;

/**
 * 4.3.2. Display all works.
 */
public class DoDisplayWorks extends Command<LibraryManager> {

  /**
   * @param receiver
   */
  public DoDisplayWorks(LibraryManager receiver) {
    super(Label.SHOW_WORKS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    List<Work> works = _receiver.getAllWorks();

    for(Work work : works)
      _display.addLine(work.toString());

    _display.display();
  }
}
