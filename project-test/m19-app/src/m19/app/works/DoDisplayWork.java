package m19.app.works;

import m19.app.exceptions.NoSuchWorkException;
import m19.exceptions.WorkNotFoundException;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;


/**
 * 4.3.1. Display work.
 */
public class DoDisplayWork extends Command<LibraryManager> {

  private Input<Integer> _id;
  /**
   * @param receiver
   */
  public DoDisplayWork(LibraryManager receiver) {
    super(Label.SHOW_WORK, receiver);
    _id = _form.addIntegerInput(Message.requestWorkId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {
      if(_id.value() < 0) throw new WorkNotFoundException();

      _display.popup(_receiver.displayWork(_id.value()).toString());
    } catch(WorkNotFoundException e) {throw new NoSuchWorkException(_id.value());}
  }
  
}
