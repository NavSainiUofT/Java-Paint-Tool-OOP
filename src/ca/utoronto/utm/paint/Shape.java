package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.Observable;

public class Shape extends Observable{
	protected Color color;
	protected int lineThickness;
	protected boolean isFilled;
	private boolean isSelected;
	protected Point dragShift;
	
	/**
	 * returns the color of this shape
	 * @return
	 */

	/**
	 * @return String
	 * returns the current colour of the shape
	 */
	public Color getColor() {
		return this.color;
	}
	

	/**
	 * @param Boolean b
	 * sets whether the shape is filled or not
	 */
	public void setIsFilled(Boolean b) {
		this.isFilled = b;
		setChanged();
		notifyObservers();
	}
	/**
	 * @param Boolean b
	 * sets the shape to filled or not corresponding to true or false
	 */
	public Boolean getIsFilled() {
		return this.isFilled;
	}
	
	/**
	 * Set the color of this Shape
	 * 
	 * @param color - the color of the shape
	 */
	public void setColor(Color color) {
		this.color = color;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * return the size of line thickness
	 * 
	 * @return line - the line thickness
	 */
	public int getLineThickness() {
		return this.lineThickness;
	}
	
	/**
	 * set the line thickness of this shape
	 * 
	 * @param line
	 */
	public void setLineThickness(int line) {
		this.lineThickness = line;
		setChanged();
		notifyObservers();
	}
	public boolean getPointInShape(Point p){
		return false;
	}

	public void setIsSelected(boolean b) {
		this.isSelected = b;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * @return boolean
	 * returns true or false depending if the shape is selected
	 */
	public boolean getIsSelected() {
		return this.isSelected;
	}
	
	/**
	 * @param Point p
	 * moves the instance of this current shape to the new origin Point p
	 */
	public void shiftShape(Point p) {
	}
	
	public void setDragShift(Point p) {
	}
	
	public Point getDragShift() {
		return this.dragShift;
	}
	
	public Shape copyShape() {
		return new Shape();
	}
	/**
	 * @param Shape s
	 * @return Shape
	 * takes a shape and returns a copy of its attributes coded in a new Shape
	 */
	public Shape copyAttributes(Shape s) {
		s.setIsFilled(this.isFilled);
		s.setColor(this.color);
		s.setLineThickness(this.lineThickness);
		return s;
	}

}
