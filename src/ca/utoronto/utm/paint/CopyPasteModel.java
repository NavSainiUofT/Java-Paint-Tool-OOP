package ca.utoronto.utm.paint;
// another singleton class which can keep track of the line thickness of shapes

public class CopyPasteModel {
	private final static CopyPasteModel first_instance = new CopyPasteModel();
	private Shape copiedShape;
	
	/**
	 * @return CopyPasteModel
	 */
	public static CopyPasteModel getInstance() {
		return first_instance;
	}

	/**
	 * return the shape that was copied
	 * @return
	 */
	public Shape getCopiedShape() {
		return this.copiedShape;
	}

	/**
	 * Change the copied shape to a new shape
	 * @param shape
	 */
	public void copyShape(Shape shape) {
		if (shape!=null) {
			this.copiedShape = shape.copyShape();
		} else {
			this.copiedShape = null;
		}
	}

}