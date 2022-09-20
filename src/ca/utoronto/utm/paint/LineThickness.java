package ca.utoronto.utm.paint;
import ca.utoronto.utm.paint.LineThickness;

// another singleton class which can keep track of the line thickness of shapes

public class LineThickness {

	private final static LineThickness first_instance = new LineThickness();
	private int thickness;
	/**
	 * Constructor which initializes the line thickness to 1
	 * 
	 */
	private LineThickness() {
		this.thickness = 1;
	}
	
	/**
	 * Return the only insatance of this class
	 * @return LineThickness - First instance of the object
	 */
	public static LineThickness getInstance() {
		// returns the only one instance created of the class
		return first_instance;
	}
	

	/**
	 * return the current line thickness selected
	 * @return int thickness - the line thickness currently selected
	 */
	public int getThickness() {
		return thickness;
	}

	/**
	 * Set the line thickness when it gets changed
	 * @param int thickness
	 */
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

}
