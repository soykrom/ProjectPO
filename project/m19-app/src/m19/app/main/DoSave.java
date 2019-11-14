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
  Input<String> _filename;

  /**
   * @param receiver
   */
  public DoSave(LibraryManager receiver) {
    super(Label.SAVE, receiver);

    if(receiver.getFilename().equals("")) 
      _filename = _form.addStringInput(Message.newSaveAs());  
    else
      _filename = _form.addStringInput(Message.saveAs());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    
    try {
      _receiver.saveAs(_filename.value());
    } catch(MissingFileAssociationException e) {e.printStackTrace();}
      catch(IOException e) {e.printStackTrace();}

    _receiver.changeSaved();
  }

}
