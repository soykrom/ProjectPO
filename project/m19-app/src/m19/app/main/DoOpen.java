package m19.app.main;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Command;
import m19.LibraryManager;
import m19.exceptions.FailedToOpenFileException;
import m19.app.exceptions.FileOpenFailedException;
import java.io.IOException;

// FIXME import core concepts
// FIXME import ui concepts

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<LibraryManager> {

  // FIXME define input fields if needed

  /**
   * @param receiver
   */
  public DoOpen(LibraryManager receiver) {
    super(Label.OPEN, receiver);
    // FIXME initialize input fields if needed
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    /*
    try {
      // FIXME implement command
    } catch (FailedToOpenFileException fnfe) {
      throw new FileOpenFailedException(fnfe.getName());
    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }
    */
  }

}
