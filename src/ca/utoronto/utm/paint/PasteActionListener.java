package ca.utoronto.utm.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The PasteActionListener gets called when the user clicks the "paste"
 * option from the JMenuBar. This allows the user to paste the shape that they 
 * copied/cut.
 */
public class PasteActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {		
		CopyPasteModel copyPasteModel = CopyPasteModel.getInstance();
		//create an instance of the copyPasteModel
		if (PaintPanel.getPaintPanel().getStrategy().getClass() == SelectToolStrategy.class) {
			Shape copiedShape = copyPasteModel.getCopiedShape();
			//gets the copied shape using the select tool
			if (copiedShape!=null) {
				copiedShape.setDragShift(new Point(0,0));
				//allows user to drag the shape
				copiedShape.shiftShape(new Point(20,20));
				//pastes the shape 20 units down and to the right
				PaintModel.getPaintModel().addShape(copiedShape);
				//adds the shape to the paintpanel
				copyPasteModel.copyShape(null); 
			}
		}
	}
}