package ca.utoronto.utm.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The CopyActionListener is called when the user clicks the "copy" option
 * from the JMenuBar. This allows the user to copy any selected shape using the
 * select tool.
 */
public class CopyActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (PaintPanel.getPaintPanel().getStrategy().getClass() == SelectToolStrategy.class) {
			//gets the shape the user has selected using the select tool
			
			Shape selectedShape = PaintModel.getPaintModel().getSelectedShape();			
			//copy the image on the screen if user has selected a valid shape
			
			if (selectedShape != null) {				
				//Stores the shape as the copy shape
				CopyPasteModel.getInstance().copyShape(selectedShape);
			}
		}
	}
}
