package m19.app.users;

import m19.app.exceptions.UserIsActiveException;
import m19.app.exceptions.NoSuchUserException;
import m19.exceptions.UserNotFoundException;
import m19.exceptions.ActiveUserException;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;

/**
 * 4.2.5. Settle a fine.
 */
public class DoPayFine extends Command<LibraryManager> {

  private Input<Integer> _id;

  /**
   * @param receiver
   */
  public DoPayFine(LibraryManager receiver) {
    super(Label.PAY_FINE, receiver);
    _id = _form.addIntegerInput(Message.requestUserId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {
      _receiver.payFine(_id.value());
    } catch(UserNotFoundException e) {throw new NoSuchUserException(_id.value());}
      catch(ActiveUserException e) {throw new UserIsActiveException(_id.value());}
  }

}
