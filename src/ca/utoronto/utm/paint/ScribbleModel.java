package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

class ScribbleModel extends Observable{
	int i=0;
	// list of points in the scribble shape
	private ArrayList<ArrayList<Point>> scribbles;
	
	
	/**
	 * Constructor which initializes an empty array of points
	 */
	public ScribbleModel(){
		// Method of JComponent
		scribbles = new ArrayList<ArrayList<Point>>();
	}
	
	/** 
	 * Return all the points in this scribble shape
	 * @return ArrayList - List of all points in Scribble
	 */
	public ArrayList<ArrayList<Point>> getScribbles() {
		return this.scribbles;
	}
	
	/**
	 * Add a new point to a scribble shape
	 * @param p - Point 
	 */
	public void addPoint(Point p) {
		this.scribbles.get(this.scribbles.size()-1).add(p);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * creates a new scribble shape with an empty array list of points
	 */
	public void newScribble() {
		this.scribbles.add(new ArrayList<Point>());
	}
}
