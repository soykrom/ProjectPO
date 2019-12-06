package m19.app.users;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Command;
import m19.LibraryManager;

// FIXME import core concepts
// FIXME import ui concepts

/**
 * 4.2.3. Show notifications of a specific user.
 */
public class DoShowUserNotifications extends Command<LibraryManager> {

  private Input<Integer> _id;

  /**
   * @param receiver
   */
  public DoShowUserNotifications(LibraryManager receiver) {
    super(Label.SHOW_USER_NOTIFICATIONS, receiver);
    _id = _form.addIntegerInput(Message.requestUserId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {
      for(Notification notification : _receiver.getAllNotifications(_id.value()){
        _display.popup(notification.getNotification());
      }
    } catch(UserNotFoundException e) {throw new NoSuchUserException(_id.value());} //mudar exception
  }

}
