package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;

public class Eraser extends Scribble{
	
	
	/**
	 * @param Point origin
	 * creates a new instance of an erased space at the Point origin
	 */
	public Eraser(Point origin) {
		super(origin);
		this.color = Color.WHITE;	
		
	}
	
	/* (non-Javadoc)
	 * @param Point p
	 * adds a point to the Array list of points which represent the erased space
	 */
	public void addPoint(Point p){
		this.scribble.add(p);
		setChanged();
		notifyObservers();
	}

	/**
	 * @return ArrayList <Point>
	 * returns the scribble of the eraser
	 */
	public ArrayList<Point> getEraser() {
		return this.scribble;
	}	
	/* (non-Javadoc)
	 * @Color c
	 * sets the colour to c but eraser is always white so omitted
	 */
	@Override
	public void setColor(Color c) {
		return;
	}
	
	/* (non-Javadoc)
	 * @param boolean b
	 * if the shape is selected by the selection tool it sets it as the currently selected shape
	 * this may be ommited for eraser that is why we return nothing
	 */
	@Override
	public void setIsSelected(boolean b) {
		return;
	}
	
	public boolean getPointInShape(Point p){
		return false;
	}
}



