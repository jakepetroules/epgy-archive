/* 
 * comp285 EditorMenuHandler class
 */
import java.awt.*;
import java.awt.event.*;
/**
 * The EditorMenuHandler that handles the events generated by the 
 * menu of the Editor class.
 * @author   Ian A Mason.
 * @version  1.0 beta
 * @date 7/02/01
 * @see  java.awt.event.ActionListener
 * @see  java.awt.event.ItemListener
 */
class EditorMenuHandler implements ActionListener, ItemListener
{
    /**
     * This is the name of the Editor instance whose events this EditorMenuHandler instance is
     * listening to. It will need to ask it to perform certain tasks according to what 
     * type of event it hears has happened.
     */
    private Editor editor;
    
    /**
     * Holds text for cut/copy/paste operations.
     */
    private String buffer;
    
    /**
     * This constructs a EditorMenuHandler instance who handles the events of the
     * particular Editor instance.
     */
    protected EditorMenuHandler(Editor editor)
    {
    	this.editor =  editor;
    }
    
    /**
     * This here is where all the events of interest get handled. It will be here
     * that you will have to ask  the editor to do the appropriate things.
     * @see  java.awt.event.ActionListener
     */
    public void actionPerformed(ActionEvent ae)
    {
		FileDialog filedialog;
		final SearchDialog searchDialog;
		String arg = (String)ae.getActionCommand();
		
		// the Open ... case
		if (arg.equals(Editor.fileLabels[0]))
		{
		    if(Editor.VERBOSE)
		    {
		    	System.err.println(Editor.fileLabels[0] + " has been selected");
		    }
		    
		    filedialog = new FileDialog(editor, "Open File Dialog", FileDialog.LOAD);
		    filedialog.setVisible(true);
		    String file = filedialog.getDirectory() + filedialog.getFile();
		    if (file != null)
		    {
		    	editor.setEditorText(editor.getFileContents(file));
		    }
		    
		    if (Editor.VERBOSE)
		    { 
				System.err.println("Exited filedialog.setVisible(true);");
				System.err.println("Open file = " + filedialog.getFile());
				System.err.println("Open directory = " + filedialog.getDirectory());
		    }
		}
		
		// the Save ... case
		if (arg.equals(Editor.fileLabels[1]))
		{
		    if (Editor.VERBOSE)
		    {
				System.err.println(Editor.fileLabels[1] + " has been selected");
		    }
		    
		    filedialog = new FileDialog(editor, "Save File Dialog", FileDialog.SAVE); 
		    filedialog.setVisible(true);
		    String file = filedialog.getDirectory() + filedialog.getFile();
		    if (file != null)
		    {
			    editor.saveTextFile(file, editor.getEditorText());
		    }
		    
		    if (Editor.VERBOSE)
		    {
				System.err.println("Exited filedialog.setVisible(true);");
				System.err.println("Save file = " + filedialog.getFile());
				System.err.println("Save directory = " + filedialog.getDirectory());
		    }
		}
		
		// the Search ... case
		if (arg.equals(Editor.fileLabels[2]))
		{
		    if (Editor.VERBOSE)
		    {
		    	System.err.println(Editor.fileLabels[2] + " has been selected");
		    }
		    searchDialog = new SearchDialog(editor); 
		    searchDialog.setVisible(true);
		    String searchString = searchDialog.getText();
		    if (searchString != null && searchString.length() > 0)
		    {
		    	int start = editor.getEditorText().indexOf(searchString);
		    	int strLength = searchString.length();
		    	editor.setHighlight(start, strLength);
		    }
		    
		    if (Editor.VERBOSE)
		    {
		    	System.err.println("searchDialog.show(); has exited");
		    }
		}
		
		// the Quit ... case
		if (arg.equals(Editor.fileLabels[3]))
		{
		    if (Editor.VERBOSE)
		    {
		    	System.err.println(Editor.fileLabels[3] + " has been selected");
		    }
		    
		    System.exit(0);
		}
		
		// the Cut case	
		if (arg.equals(Editor.editLabels[0]))
		{
		    if (Editor.VERBOSE)
		    {
		    	System.err.println(Editor.editLabels[0] + " has been selected");
		    }
		    
		    buffer = editor.getSelectedText();
		    editor.deleteSelectedText();
		}
		
		// the Copy case
		if (arg.equals(Editor.editLabels[1]))
		{
		    if (Editor.VERBOSE)
		    {
		    	System.err.println(Editor.editLabels[1] + " has been selected");
		    }
		    
		    buffer = editor.getSelectedText();
		}
		
		// the Paste case
		if (arg.equals(Editor.editLabels[2]))
		{
		    if (Editor.VERBOSE)
		    {
		    	System.err.println(Editor.editLabels[2] + " has been selected");
		    }
		    
		    editor.insertTextAt(buffer);
		}
    }
    
    /**
     * This needs to be here since we need to implement the ItemListener interface
     * @see java.awt.event.ItemListener
     */
    public void itemStateChanged(ItemEvent ie)
    {
    	// Shouldn't need to do anything here.
    }
}





