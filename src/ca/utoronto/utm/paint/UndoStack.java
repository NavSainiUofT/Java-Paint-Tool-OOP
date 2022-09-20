package ca.utoronto.utm.paint;

import java.util.ArrayList;

public class UndoStack {
	// store al shapes into this stack
	private ArrayList<Shape> shapes;
	
	/**
	 * Constructor which initializes setting the Undo Stack to empty
	 * 
	 */
	public UndoStack() {
		this.shapes = new ArrayList<Shape>(); 
	}
	
	/**
	 * Add shapes to the stack as they are created
	 * @param s - Shape to be added
	 */
	public void push(Shape s) {
		this.shapes.add(s);
	}
	
	/**
	 * Check if the Stack is empty, if not pop the first item
	 * @return Shape - The shape returns from the top of the stack
	 */
	public Shape pop() {
		if (this.isEmpty() == false) {
		Shape shape = this.shapes.get((this.shapes.size()-1));
		this.shapes.remove((this.shapes.size()-1));
		return shape;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Check whether this stack is empty
	 * @return boolean - True iff the stack is empty
	 */
	public boolean isEmpty() {
		return this.shapes.isEmpty();
	}
}
