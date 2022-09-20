package ca.utoronto.utm.paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The NewActionListener deletes all the shapes on canvas making a brand new 
 * paintpanel, reseting the canvas to blank white.
 */
public class NewActionListener implements ActionListener{
	private PaintModel paintModel; 
	//create a paintpanel
	
	public NewActionListener (PaintModel paintModel) {
		this.paintModel = paintModel; 
		//hookup the panel with the model		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// if user clicks on the "New" option
		if (e.getActionCommand() == "New") {
			//Remove all shapes in the panel 
			this.paintModel.removeAllShapes();
		}		
	}
}
