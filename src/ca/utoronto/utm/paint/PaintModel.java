package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
	private static PaintModel paintModel = new PaintModel();
	private ArrayList<Shape> shapes;
	private Color colorSelected;
	private Shape selectedShape;
	private boolean fill;
	public static int minRange=10;
	
	/**
	 * Constructor
	 */
	private PaintModel() {
		this.shapes =new ArrayList<Shape>();
		this.colorSelected = Color.BLACK;
	}
	
	public static PaintModel getPaintModel() {
		return paintModel;
	}
	
	/**
	 * 
	 * @param p - Point from where to drag a shape
	 */
	public void setDragShift(Point p) {
		selectedShape.setDragShift(p);
	}
	
	/**
	 * 
	 * @return selectedShape - Shape that is selected
	 */
	public Shape getSelectedShape() {
		return selectedShape;
	}
	
	/**
	 * Change whether the fill checkbox is checked off
	 */
	public void toggleFill() {
		this.fill = !(this.fill);
	}
	
	/**
	 * Create a new shape 
	 * 
	 * @param newShape - New shape to be added
	 */
	public void newShape(Shape newShape) {
		
		// set color, line thickness, fill color/nofill for each shape
		newShape.setIsFilled(this.fill);
		newShape.addObserver(PaintPanel.getPaintPanel());
		newShape.setColor(this.colorSelected);
		newShape.setLineThickness(LineThickness.getInstance().getThickness());
		this.shapes.add(newShape);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Move a selected shape at a point 
	 * 
	 * @param p - Point from which to move a shape
	 */
	public void moveSelectedShapes(Point p) {
		selectedShape.shiftShape(p);
	}
	
	/**
	 * Add a selected shape to the list of shapes
	 * 
	 * @param newSelectedShape
	 */
	public void addSelectedShape(Shape newSelectedShape) {
		if (this.selectedShape!=null) {
			this.selectedShape.setIsSelected(false);
		}
		if (newSelectedShape!=null) {
			newSelectedShape.setIsSelected(true);
			this.selectedShape = newSelectedShape;
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * Return last shape drawn 
	 * @return Shape - The last shape added
	 */
	public Shape peek() {
		return this.shapes.get(this.shapes.size()-1);
	}
	
	/**
	 * add a new shape to the list of shapes
	 * 
	 * @param s - Shape to be added
	 */
	public void addShape(Shape s) {
		this.shapes.add(s);
		s.addObserver(PaintPanel.getPaintPanel());
		setChanged();
		notifyObservers();
	}
	
	/**
	 * 
	 * @return shapes - Array list of shapes
	 */
	public ArrayList<Shape> getShapes(){
		return this.shapes;
	}
	
	/**
	 * Return the color that is selected
	 * 
	 * @return Color - the selected color
	 */
	public Color getColorSelected() {
		return this.colorSelected;
	}
	
	/**
	 * Set the color selected
	 * @param c - Color to be selected
	 */
	public void setColorSelected(Color c) {
		this.colorSelected = c;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Remove the last shape added to the 
	 * @return s -Shape to that was removed
	 */
	public Shape removeShapeReturn() {
		Shape s = this.shapes.get(this.shapes.size()-1);
		this.shapes.remove(this.shapes.size()-1);
		this.setChanged();
		this.notifyObservers();
		return s;
	}
	
	/**
	 * Return the top most shape at point p
	 * 
	 * @param p - Point from which to return the shape
	 * @return Shape - The top shape returned at point p
	 */
	public Shape getShapeAtPoint(Point p) {
		for(int i = this.shapes.size()-1;i>-1; i-- ) {
			if(this.shapes.get(i).getPointInShape(p)) {
				return this.shapes.get(i);
				
			}
		}
		
		return null;
	}
	/**
	 * Remove all shapes from list of shapes
	 */
	public void removeAllShapes() {
		while(shapes.size() != 0) {
			this.shapes.remove(this.shapes.size()-1);
			this.setChanged();
			this.notifyObservers();
		}
		
	}
	public void update() {
		this.setChanged();
		this.notifyObservers();
	}
		
}