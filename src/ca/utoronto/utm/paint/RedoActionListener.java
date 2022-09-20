package ca.utoronto.utm.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The RedoActionListener gets called when the user clicks the "Redo" option
 * from the JMenuBar. This allows the user redo a change that they made.
 */
public class RedoActionListener implements ActionListener {
	//constructor for the undostack and paintmodel
	private UndoStack undostack; 
	private PaintModel paintmodel; 
	
	public RedoActionListener(UndoStack undostack, PaintModel paintmodel) {
		//hookup the undostack and paintmodel
		this.undostack = undostack; 
		this.paintmodel = paintmodel;
	}	
	public void actionPerformed(ActionEvent e) {
		// If there is at least one item in the stack, put it back into shapes
		if (this.undostack.isEmpty()!= true) {
			this.paintmodel.addShape(this.undostack.pop());
		}
	}
}