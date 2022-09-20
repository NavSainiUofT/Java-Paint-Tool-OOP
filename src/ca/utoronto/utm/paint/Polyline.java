package ca.utoronto.utm.paint;

public class Polyline extends Scribble {

	public Polyline(Point origin) {
		super(origin);
	}
	
	/**
	 * @param Point p
	 * changes the end of the draw to point p
	 */
	public void changeTail(Point p) {
		this.scribble.get(this.scribble.size()-1).setX(p.getX());
		this.scribble.get(this.scribble.size()-1).setY(p.getY());
		this.setChanged();
		this.notifyObservers();
	}
	
	@Override
	public boolean getPointInShape(Point p) {
		int range;
		if (this.lineThickness/2>PaintModel.minRange) {
			range = this.lineThickness;
		} else {
			range = PaintModel.minRange;
		}
		for(int i=0;i<this.scribble.size()-1; i++){
			Point p1=this.scribble.get(i);
			Point p2=scribble.get(i+1);
			for (Point poL: getPointsOnLine(p1,p2)) {
				if (Point.getDistance(p, poL)<=range) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param origin
	 * @param endPoint
	 * @return ArrayList <Point>
	 * returns all the points in the polyline
	 */
	public Point[] getPointsOnLine(Point origin, Point endPoint) {
		int width = Math.abs(endPoint.getX()-origin.getX());
		int height = Math.abs(endPoint.getX()-origin.getX());
		int size = Math.max(width, height);
		Point[] pointsOnLine = new Point[size];
		int rise = endPoint.getY() - origin.getY();
		int run = endPoint.getX() - origin.getX();
		for (int i=0; i<size; i++) {
			int x = (int)(((run*i)/size)+origin.getX());
			int y = (int)(((rise*i)/size)+origin.getY());
			pointsOnLine[i] = new Point(x,y);
		}
		return pointsOnLine;
	}
	
	@Override
	public Shape copyShape() {
		Polyline copiedPolyline = new Polyline(this.scribble.get(0).getCopy());
		for (int i=1; i<this.scribble.size();i++) {
			copiedPolyline.addPoint(this.scribble.get(i).getCopy());
		}
		return copyAttributes(copiedPolyline);
	}
}
