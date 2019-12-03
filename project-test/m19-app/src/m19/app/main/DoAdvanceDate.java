package m19.app.main;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;
// FIXME import core concepts
// FIXME import ui concepts

/**
 * 4.1.3. Advance the current date.
 */
public class DoAdvanceDate extends Command<LibraryManager> {

  private Input<Integer> _delta;

  /**
   * @param receiver
   */
  public DoAdvanceDate(LibraryManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    _delta = _form.addIntegerInput(Message.requestDaysToAdvance());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();

    if(_delta.value() <= 0) return; //Exits if amount inputed isn't positive

    _receiver.advanceDate(_delta.value());
    //requestHandler();
    //notificationHandler();
  }
}
