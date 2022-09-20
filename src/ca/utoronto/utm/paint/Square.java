package ca.utoronto.utm.paint;

//creates a rectangle with a corner value which it the point where the rectangles starts
//as well as a length and width
public class Square extends Rectangle {

	public Square(Point origin) {
		super(origin);
	}
	
	/* (non-Javadoc)
	 * @param Point p
	 * sets the top left corner of square 
	 */
	@Override
	public void setTopLeft(Point p) {
		super.setTopLeft(p);
		int newLength = Math.min(Math.abs(p.getX()-this.origin.getX()),Math.abs(p.getY()-this.origin.getY()));
		this.topLeft.setX(Math.max(this.origin.getX()-newLength, this.topLeft.getX()));
		this.topLeft.setY(Math.max(this.origin.getY()-newLength, this.topLeft.getY()));
		setChanged();
		notifyObservers();
	}
	/**
	 * @return int min
	 * returns the smallest of the two height and width of the square
	 */
	public int getSquareLength() {
		return Math.min(this.height, this.width);
	}

	@Override
	public boolean getPointInShape(Point p) {
		int range;
		if (this.lineThickness/2>PaintModel.minRange) {
			range = this.lineThickness;
		} else {
			range = PaintModel.minRange;
		}
		boolean minx = p.getX() >= this.topLeft.getX() - (range/2);
		boolean maxx = p.getX() <= this.topLeft.getX() + getSquareLength() + (range/2);
		boolean miny = p.getY() >= this.topLeft.getY() - (range/2);
		boolean maxy = p.getY() <= this.topLeft.getY()+ getSquareLength() + (range/2);
		if (this.isFilled) {
			return minx && maxx && miny&&maxy;
		} else {
			boolean inNorthEdge = p.getY() >= this.topLeft.getY()-(range/2) && 
					p.getY() <= this.topLeft.getY()+(range/2);
			boolean inEastEdge = p.getX() >= this.topLeft.getX()-(range/2)+getSquareLength() && 
					p.getX() <=this.topLeft.getX()+(range/2)+getSquareLength();
			boolean inSouthEdge = p.getY() >= this.topLeft.getY()-(range/2)+getSquareLength() && 
					p.getY() <= this.topLeft.getY()+(range/2)+getSquareLength();
			boolean inWestEdge = p.getX() >= this.topLeft.getX()-(range/2) && 
					p.getX() <=this.topLeft.getX()+(range/2);
			return ((inNorthEdge | inSouthEdge) && minx && maxx) | ((inEastEdge | inWestEdge) && miny && maxy);
		}	
	}
	
	/* (non-Javadoc)
	 * @return Shape
	 * returns a copy of this instance
	 */
	public Shape copyShape() {
		Square copiedSquare = new Square(this.origin.getCopy());
		copiedSquare.setDimensions(this.width, this.height);
		copiedSquare.setTopLeft(this.topLeft.getCopy());
		return copyAttributes(copiedSquare);
	}

}
