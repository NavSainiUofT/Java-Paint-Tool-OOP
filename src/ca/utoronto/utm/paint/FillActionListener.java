package ca.utoronto.utm.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The FillActionListener is called when the user checks/clicks the
 * "fill" option on the OptionsPanel. This allows the users to draw shapes
 * that are filled in with solid colors.
 */
public class FillActionListener implements ActionListener{	
	PaintModel paintModel; //Create the paintmodel
	
	public FillActionListener(View view) {
		//gets the constructors of the paintmodel
		this.paintModel = PaintModel.getPaintModel(); 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//changes the checkbox to checked for the fill checkbox
		paintModel.toggleFill();
	}
}
