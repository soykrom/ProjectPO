package m19.app.requests;

import m19.app.exceptions.WorkNotBorrowedByUserException;
import m19.exceptions.WorkDoesntBelongToUserException;
import m19.app.exceptions.NoSuchUserException;
import m19.app.exceptions.NoSuchWorkException;
import m19.exceptions.UserNotFoundException;
import m19.exceptions.WorkNotFoundException;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;


/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Command<LibraryManager> {

  private Input<Integer> _userID;
  private Input<Integer> _workID;
  
  /**
   * @param receiver
   */
  public DoReturnWork(LibraryManager receiver) {
    super(Label.RETURN_WORK, receiver);
    _userID = _form.addIntegerInput(Message.requestUserId());
    _workID = _form.addIntegerInput(Message.requestWorkId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {
      _receiver.returnWork(_userID.value(), _workID.value());
    } catch(UserNotFoundException e) {throw new NoSuchUserException(_userID.value());}
      catch(WorkNotFoundException e) {throw new NoSuchWorkException(_workID.value());}
      catch(WorkDoesntBelongToUserException e) {throw new WorkNotBorrowedByUserException(_workID.value(), _userID.value());}
  }

}
