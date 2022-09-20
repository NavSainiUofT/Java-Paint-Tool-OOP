package ca.utoronto.utm.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//action listener for the undo button to organize code
/**
 * The UndoActionListener gets called when the user clicks the "Undo" option
 * from the JMenuBar. This allows the user to undo any changes that they made.
 */
public class UndoActionListener implements ActionListener {
	private UndoStack undostack; //create a UndoStack
	private PaintModel paintmodel; //create the paintmodel
	
	public UndoActionListener(UndoStack undostack, PaintModel paintmodel) {
		//declare the undostack and paintmodel
		this.undostack = undostack; 
		this.paintmodel = paintmodel;
	}
	
	public void actionPerformed(ActionEvent e) {
		//Checks if there is at least one shape in the paintmodel		
		if (this.paintmodel.getShapes().size()!=0) {
			//removes the shape from the paint model and stores it in the undo stack 
			this.undostack.push(this.paintmodel.removeShapeReturn());
		}
	}
}