package ca.utoronto.utm.paint;

import java.util.ArrayList;

public class DotShape extends Shape {
	ArrayList<Point> dotShape;
	
	public DotShape(Point origin) {
		dotShape = new ArrayList<>();
		addNewDot(origin);
	}
	
	public void addNewDot(Point p) {
		this.dotShape.add(p);
	}
	
	public ArrayList<Point> getDotShape() {
		return this.dotShape;
	}

}
