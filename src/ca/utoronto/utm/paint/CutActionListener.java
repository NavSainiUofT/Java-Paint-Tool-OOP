package ca.utoronto.utm.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The CutActionListener gets called when the user clicks the "cut" option
 * from the JMenuBar. This allows the user to remove the image current shape 
 * selected and paste it elsewhere on the screen.
 */
public class CutActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {		 
		if (PaintPanel.getPaintPanel().getStrategy().getClass() == SelectToolStrategy.class) {
			//gets the shape from the paintpanel
			Shape selectedShape = PaintModel.getPaintModel().getSelectedShape();
			
			PaintModel.getPaintModel().getShapes().remove(selectedShape);
			//removes the shape from the paintpanel
			
			PaintModel.getPaintModel().update(); 
			//Update the view after removing the shape
			
			if (selectedShape!=null) { //If user selected a shape
				//stores the shape that's been cut as the copy shape 
				CopyPasteModel.getInstance().copyShape(selectedShape);
			}
		}
	}
}
