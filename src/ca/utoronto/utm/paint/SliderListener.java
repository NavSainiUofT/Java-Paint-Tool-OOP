package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The SliderListener gets called when the user uses the slider interface. This 
 * allows the user to use the slider for the thickness of shapes and for the
 * eraser.
 */
public class SliderListener implements ChangeListener {
		private JSlider slider; //create a slider
		
		public SliderListener(JSlider slider) {
			this.slider = slider; //decalre the slider
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			//set the slider to the LineThickness method
			LineThickness.getInstance().setThickness(this.slider.getValue());			
		}
}
