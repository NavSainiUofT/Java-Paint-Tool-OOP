package ca.utoronto.utm.paint;

public class Circle extends Shape {
	private Point centre;
	private int radius;
	
	/**
	 * @param Point center
	 * creates a new circle with a center at Point center
	 */
	public Circle(Point centre){
		this.centre = centre;
		this.radius = 0;
	}

	/**
	 * @return Point
	 * returns the center point of the circle
	 */
	public Point getCentre() {
		return centre;
	}

	/**
	 * @param Point center
	 * changed center of circle to new point
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
		setChanged();
		notifyObservers();
	}

	/**
	 * @return int
	 * returns the current radius of the circle
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * @param int radius
	 * changes radius to given int
	 */
	public void setRadius(int radius) {
		this.radius = radius;
		setChanged();
		notifyObservers();
	}
	
	@Override
	public boolean getPointInShape(Point p) {
		int pRadius = Point.getDistance(p, getCentre());
		int range;
		if (this.lineThickness/2>PaintModel.minRange) {
			range = this.lineThickness;
		} else {
			range = PaintModel.minRange;
		}
		if (this.getIsFilled()) {
			return pRadius <= getRadius()+(range/2);
		}
		return pRadius <= getRadius()+(range/2) && pRadius >= getRadius()-(range/2);
	}
	
	@Override
	public void setDragShift(Point p) {
		this.dragShift = new Point(p.getX()-this.centre.getX(), p.getY()-this.centre.getY());
	}
	
	@Override
	public void shiftShape(Point p) {
		int newX = p.getX()-this.dragShift.getX();
		int newY = p.getY()-this.dragShift.getY();
		setCentre(new Point(newX, newY));
	}
	
	@Override
	//returns a copied version of this instance of circle
	public Shape copyShape() {
		Circle copiedCircle = new Circle(this.centre.getCopy());
		copiedCircle.setRadius(this.radius);
		return copyAttributes(copiedCircle);
	}

}
