package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.Observable;

public class ColorWheelModel extends Observable {
	private static ColorWheelModel colorWheelModel = new ColorWheelModel();
	// Initial color selected is black 
	Color colorSelected = Color.BLACK;

	/**
	 * 
	 * @return ColorWheelModel
	 */
	public static ColorWheelModel getColorWheelModel() {
		return colorWheelModel;
	}
	
	/**
	 * Change the color selected
	 * @param c
	 */
	public void setColorSelected(Color c) {
		this.colorSelected = c;
		setChanged();
		notifyObservers();
	}
	
	/**
	 *
	 * @return colorSelected - the color that is selected
	 */
	public Color getColorSelected() {
		return this.colorSelected;
	}
}
