package m19.app.requests;

import m19.exceptions.RuleUnsuccessfulException;
import m19.app.exceptions.RuleFailedException;
import m19.app.exceptions.NoSuchUserException;
import m19.app.exceptions.NoSuchWorkException;
import m19.exceptions.UserNotFoundException;
import m19.exceptions.WorkNotFoundException;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;


/**
 * 4.4.1. Request work.
 */
public class DoRequestWork extends Command<LibraryManager> {

  private Input<Integer> _userID;
  private Input<Integer> _workID;
  private Input<String> _response;


  /**
   * @param receiver
   */
  public DoRequestWork(LibraryManager receiver) {
    super(Label.REQUEST_WORK, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    int days = 0;
    _form.clear();

    _userID = _form.addIntegerInput(Message.requestUserId());
    _workID = _form.addIntegerInput(Message.requestWorkId());

    _form.parse();

    try {
      days = _receiver.requestWork(_userID.value(), _workID.value());
    } catch(UserNotFoundException e) {throw new NoSuchUserException(_userID.value());}
      catch(WorkNotFoundException e) {throw new NoSuchWorkException(_workID.value());}
      catch(RuleUnsuccessfulException e) {
        if(e.getRuleNumber() != 3) throw new RuleFailedException(_userID.value(), _workID.value(), e.getRuleNumber());
        _form.clear();

        _response = _form.addStringInput(Message.requestReturnNotificationPreference());

        _form.parse();

        _receiver.requestNotification(_response.value(), _userID.value(), _workID.value());

        _form.clear();

        return;
      }
    
    _display.popup(Message.workReturnDay(_workID.value(), days));

    _form.clear();
  }

}
