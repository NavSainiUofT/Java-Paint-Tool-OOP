package ca.utoronto.utm.paint;

import java.util.ArrayList;

public class Scribble extends Shape{
	protected ArrayList<Point> scribble;
	
	/**
	 * @param Point origin
	 * initializes an instance of scribble with the origin at Point origin
	 */
	public Scribble(Point origin) {
		this.scribble = new ArrayList<>();
		addPoint(origin);
	}
	
	/**
	 * @param Point p
	 * adds the given point to the ArrayList
	 */
	public void addPoint(Point p) {
		this.scribble.add(p);
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<Point> getScribble() {
		return this.scribble;
	}

	@Override
	public boolean getPointInShape(Point p) {
		int range;
		if (this.lineThickness/2>PaintModel.minRange) {
			range = this.lineThickness;
		} else {
			range = PaintModel.minRange;
		}
		for(Point sp: this.scribble) {
			if (Point.getDistance(p, sp) <= range) {
				return true;
			}
		}
		return false;
	}
	@Override
	public void setDragShift(Point p) {
		this.dragShift = p;
	}
	
	@Override
	public void shiftShape(Point draggedPoint) {
		int dx = draggedPoint.getX()-this.dragShift.getX();
		int dy = draggedPoint.getY()-this.dragShift.getY();
		for (Point p: this.scribble) {
			p.setX(p.getX()+dx);
			p.setY(p.getY()+dy);
		}
		this.dragShift = draggedPoint;
		setChanged();
		notifyObservers();
	}
	
	public Shape copyShape() {
		Scribble copiedScribble = new Scribble(this.scribble.get(0).getCopy());
		for (int i=1; i<this.scribble.size();i++) {
			copiedScribble.addPoint(this.scribble.get(i).getCopy());
		}
		return copyAttributes(copiedScribble);
	}
}
