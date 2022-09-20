package ca.utoronto.utm.paint;

//creates a rectangle with a corner value which it the point where the rectangles starts
//as well as a length and width
public class Rectangle extends Shape {
	protected Point origin;
	protected int height;
	protected int width;
	protected Point topLeft;
	
	//initializes the rectangle with the given properties
	/**
	 * @param Point origin
	 * initializes the rectangle
	 */
	public Rectangle(Point origin){
		this.width = 0;
		this.height = 0;
		this.origin = origin.getCopy();
		this.topLeft = origin.getCopy();
	}
	//returns the initial point of the rectangle
	/**
	 * @return Point
	 * returns the top left point of the rectangle
	 */
	public Point getTopLeft() {
		return this.topLeft;
	}
	
	/**
	 * @return Point
	 * returns the origin point of the rectangle
	 */
	public Point getOrigin() {
		return this.origin;
	}
	//changes the initial point of the rectangle
	/**
	 * @param Point newTopLeft
	 * sets the top left of the rectangle to the given point newTopLeft
	 */
	public void setTopLeft(Point newTopLeft) {
		int newX = Math.min(origin.getX(), newTopLeft.getX());
		int newY = Math.min(origin.getY(), newTopLeft.getY());
		this.topLeft.setX(newX);
		this.topLeft.setY(newY);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @param Point p
	 * sets the origin point to the given point p
	 */
	public void setOrigin(Point p) {
		this.origin = p;
	}
	
	//returns the width value
	/**
	 * @return int width
	 * returns the current width of the rectangle
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * @return int height
	 * returns the current height of the rectangle
	 */
	public int getHeight() {
		return this.height;
	}
	//changes width and below changes length of rectangle
	/**
	 * @param width
	 * @param height
	 * sets the width and height to the given integers
	 */
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
		setChanged();
		notifyObservers();
	}
	/* (non-Javadoc)
	 * @see ca.utoronto.utm.paint.Shape#getPointInShape(ca.utoronto.utm.paint.Point)
	 */

	@Override
	public boolean getPointInShape(Point p) {
		int range;
		if (this.lineThickness/2>PaintModel.minRange) {
			range = this.lineThickness;
		} else {
			range = PaintModel.minRange;
		}
		boolean minx = p.getX() >= this.topLeft.getX() - (range/2);
		boolean maxx = p.getX() <= this.topLeft.getX() + this.width + (range/2);
		boolean miny = p.getY() >= this.topLeft.getY() - (range/2);
		boolean maxy = p.getY() <= this.topLeft.getY()+ this.height + (range/2);
		if (this.isFilled) {
			return minx && maxx && miny&&maxy;
		} else {
			boolean inNorthEdge = p.getY() >= this.topLeft.getY()-(range/2) &&
					p.getY() <= this.topLeft.getY()+(range/2);
			boolean inEastEdge = p.getX() >= this.topLeft.getX()-(range/2)+this.width && 
					p.getX() <=this.topLeft.getX()+(range/2)+this.width;
			boolean inSouthEdge = p.getY() >= this.topLeft.getY()-(range/2)+this.height 
					&& p.getY() <= this.topLeft.getY()+(range/2)+this.height;
			boolean inWestEdge = p.getX() >= this.topLeft.getX()-(range/2) && 
					p.getX() <=this.topLeft.getX()+(range/2);
			return ((inNorthEdge | inSouthEdge) && minx && maxx) | ((inEastEdge | inWestEdge) && miny && maxy);
		}	
	}
	
	/* (non-Javadoc)
	 * @see ca.utoronto.utm.paint.Shape#setDragShift(ca.utoronto.utm.paint.Point)
	 * Used to set the shift of the selector tool
	 */
	@Override
	public void setDragShift(Point p) {
		this.dragShift = new Point(p.getX()-this.topLeft.getX(), p.getY()-this.topLeft.getY());
	}
	
	/* (non-Javadoc)
	 * @see ca.utoronto.utm.paint.Shape#shiftShape(ca.utoronto.utm.paint.Point)
	 */
	@Override
	public void shiftShape(Point p) {
		int newX = p.getX()-this.dragShift.getX();
		int newY = p.getY()-this.dragShift.getY();
		this.topLeft = new Point(newX, newY);
		setChanged();
		notifyObservers();
	}
	
	/* (non-Javadoc)
	 * @see ca.utoronto.utm.paint.Shape#copyShape()
	 */
	@Override
	public Shape copyShape() {
		Rectangle copiedRectangle = new Rectangle(this.origin.getCopy());
		copiedRectangle.setDimensions(this.width, this.height);
		copiedRectangle.setTopLeft(this.topLeft.getCopy());
		return copyAttributes(copiedRectangle);
	}
}

