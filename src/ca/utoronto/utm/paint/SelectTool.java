package ca.utoronto.utm.paint;


// Select tool is a singleton Class which keeps track of the shape that is selected

public class SelectTool {
	private final static SelectTool first_instance = new SelectTool();
	private Shape shapeselected;
	private PaintPanel panel;
	
	/**
	 * constructor
	 */
	public SelectTool() {
		
	}
	
	/**
	 * Select the only instance of this class
	 * @return SelectTool
	 */
	public static SelectTool getInstance() {
		return first_instance;
	}
	
	/**
	 * Return the shape that is selected
	 * @return Shape
	 */
	public Shape getShapeSelected() {
		return shapeselected;
	}
	
	/**
	 * Change the Shape that is selected
	 * @param shape
	 */
	public void setShapeSelected(Shape shape) {
		this.shapeselected = shape;
	}
}
