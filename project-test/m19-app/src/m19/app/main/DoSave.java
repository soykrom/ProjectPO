package m19.app.main;

import m19.exceptions.MissingFileAssociationException;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;
// FIXME import core concepts
// FIXME import ui concepts

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
      if(_receiver.getFilename().isEmpty()) {
        _form.parse();
        _receiver.saveAs(_filename.value());
      } else {
        _receiver.save();
      }
    } catch(MissingFileAssociationException e) {e.printStackTrace();}
      catch(IOException e) {e.printStackTrace();}
  }
}
