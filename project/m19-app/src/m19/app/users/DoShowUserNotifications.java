package m19.app.users;

// FIXME import core concepts
// FIXME import ui concepts

/**
 * 4.2.3. Show notifications of a specific user.
 */
public class DoShowUserNotifications extends Command<LibraryManager> {

  // FIXME define input fields

  /**
   * @param receiver
   */
  public DoShowUserNotifications(LibraryManager receiver) {
    super(Label.SHOW_USER_NOTIFICATIONS, receiver);
    // FIXME initialize input fields
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    // FIXME implement command
  }

}
