package ca.utoronto.utm.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;

/**
 * The SHapeButtonsActionListener gets called when the user selects a shape. 
 * This allows the user to select any shape of their preference changing the 
 * drawn shape according to the button selected.
 */
public class ShapeButtonsActionListener implements ActionListener {
	//create the optionspanel, buttons and and paintstrategy
	OptionsPanel optionsPanel;
	JButton button;
	PaintStrategy paintStrat;
	
	public ShapeButtonsActionListener(OptionsPanel optionsPanel, JButton button, PaintStrategy paintStrat) {
		//declare up the optionspanel/button/painstrat
		this.optionsPanel = optionsPanel;
		this.button = button;
		this.paintStrat = paintStrat;
	}
	
	public void actionPerformed(ActionEvent e) {
		//create a hashmap with keys = buttons and values = PaintStrategy 
		HashMap<JButton, PaintStrategy> buttonStrategyMap = this.optionsPanel.getButtonStrategyMap();
		PaintPanel.getPaintPanel().setPaintStrategy(buttonStrategyMap.get(this.button));
		for (JButton key: buttonStrategyMap.keySet()) { 
			//Make button with current mode faded when pressed 
			key.setEnabled(true);
			key.setFocusPainted(false);
		}
		this.button.setEnabled(false);		
	}
}
