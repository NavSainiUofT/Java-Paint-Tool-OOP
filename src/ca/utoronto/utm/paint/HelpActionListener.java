package ca.utoronto.utm.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The HelpActionListener gets called when the user clicks the "help" option 
 * from the JMenuBar. This opens up either the "Intructions" dialog box or the "About" 
 * dialog box.
 */
public class HelpActionListener extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void actionPerformed(ActionEvent e) {	
		//Checks to see if the user clicks the "Instructions" option
		if (e.getActionCommand() == "Instructions") {	
			//Opens a dialog box displaying the instructions
			JOptionPane.showMessageDialog(null,"Shape Selection: " + "\n" + 
					"To select a shape, click on one of the shape buttons from the left panel.\n"
					+ "Options include circle, square, rectangle, scribble and polyline. " + "\n"
					+ "\n" + "Colour Selection: "+ 
					"\n" + 
					"To select a colour choose a colour of your preference from the circular colour pallet. "
					+ "\n" + "\n" + "Thickenss Selection:"  + "\n" + "To select the thickness use the slider"
					+ " to move left(low thickness) or right(high thickness). \n" + "\n" + "Fll Bucket tool: \n" 
					+ "Once clicked, select a colour then select the area in which you want to fill"
					+ " in with the color \n" + "\n" + "Eraser tool: \n"  + "Select the thickness of"
							+ " the eraser by using the thickness slider, then erase any shape/line of your desire \n"
					+"\n" + "Fill \n" + "To draw shapes that are already filled in, select the shape"
							+ " then select the color and draw the shape"
 					
					, "Instructions", JOptionPane.INFORMATION_MESSAGE);				
		} 
		//Checks if the user clicks the "About" option
		else if (e.getActionCommand() == "About") {
			//Opens a dialog box displaying details of the developers of the application
			JOptionPane.showMessageDialog(null, "The paint application was developed in November 2017 \n" + 
					"by 4 developers at the University of Toronto.", 
					"About", JOptionPane.INFORMATION_MESSAGE);	
		}
	}
}
