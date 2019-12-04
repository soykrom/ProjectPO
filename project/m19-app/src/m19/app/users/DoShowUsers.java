package m19.app.users;

import pt.tecnico.po.ui.Command;
import java.util.Collections;
import m19.LibraryManager;
import m19.users.User;
import java.util.List;

/**
 * 4.2.4. Show all users.
 */
public class DoShowUsers extends Command<LibraryManager> {
  /**
   * @param receiver
   */
  public DoShowUsers(LibraryManager receiver) {
    super(Label.SHOW_USERS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    List<User> users = _receiver.getAllUsers();

    Collections.sort(users, User.USER_COMPARATOR);

    for(User user : users)      
      _display.addLine(user.toString());

    _display.display();
  }
}
