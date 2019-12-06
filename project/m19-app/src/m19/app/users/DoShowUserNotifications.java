package m19.app.users;

import m19.app.exceptions.NoSuchUserException;
import m19.exceptions.UserNotFoundException;
import pt.tecnico.po.ui.DialogException;
import m19.notifications.Notification;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;
import java.util.List;

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
      List<Notification> notifs = _receiver.getAllNotifications(_id.value());

      for(Notification notification : notifs)
        _display.addLine(notification.getNotification());
    } catch(UserNotFoundException e) {throw new NoSuchUserException(_id.value());} //mudar exception

    _display.display();
  }

}