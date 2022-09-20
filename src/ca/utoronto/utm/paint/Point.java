package ca.utoronto.utm.paint;

public class Point {
	int x, y;
	
	public Point(int x, int y){
		this.x=x; this.y=y;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	
	public Point getCopy() {
		return new Point(this.x,this.y);
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object object) {
		return object.getClass()==Point.class && ((Point)object).getX()==this.x && ((Point)object).getY()==this.y;
	}
	
	public static int getDistance(Point a, Point b) {
		double deltaX = (a.getX()-b.getX());
		double deltaY = -1*(a.getY()-b.getY());
		return (int)(Math.pow(Math.pow(deltaX,2)+(Math.pow(deltaY,2)),0.5));
	}
}
