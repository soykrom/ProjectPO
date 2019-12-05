package m19.app.main;

import m19.exceptions.MissingFileAssociationException;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<LibraryManager> {
  private Input<String> _filename;

  /**
   * @param receiver
   */
  public DoSave(LibraryManager receiver) {
    super(Label.SAVE, receiver);
    _filename = _form.addStringInput(Message.newSaveAs());  
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    try {
      _receiver.save();
    } catch(IOException e) {e.printStackTrace();}
      catch(MissingFileAssociationException e) {
        try {
          _form.parse();
          _receiver.saveAs(_filename.value());
        } catch(IOException f) {f.printStackTrace();}
          catch(MissingFileAssociationException f) {f.printStackTrace();}
      }
  }
}
