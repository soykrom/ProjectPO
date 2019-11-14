package m19.app.users;

import m19.app.exceptions.UserRegistrationFailedException;
import m19.exceptions.UserRegistrationFailException;
import pt.tecnico.po.ui.DialogException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;
// FIXME import core concepts
// FIXME import ui concepts

/**
 * 4.2.1. Register new user.
 */
public class DoRegisterUser extends Command<LibraryManager> {

  // FIXME define input fields
  Input<String> _name;
  Input<String> _email;

  /**
   * @param receiver
   */
  public DoRegisterUser(LibraryManager receiver) {
    super(Label.REGISTER_USER, receiver);
    _name = _form.addStringInput(Message.requestUserName());
    _email = _form.addStringInput(Message.requestUserEMail());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    
    try {
      _receiver.addUser(_name.value(), _email.value());
    } catch(UserRegistrationFailException e) {throw new UserRegistrationFailedException(_name.value(), _email.value());}
    _receiver.changeSaved();
  }
}