package ca.utoronto.utm.paint;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * @author alex
 * Interface for handling various actions associated with different shapes. Implements strategic design pattern.
 * This interface goes on to be implemented to each shape, and more.
 */

// This strategy was done to reduce querying of the model and long code - both poor coding practices.
// These methods (save for paintComponent) all collate to the mouse listener / mouse movement listener interface.
// Although only a handful are actually used, these are here to make the code modular.
public interface PaintStrategy {

	/** Handles clicking and dragging of a mouse - note, to instigate a drag is to also perform a single 'mouse press'
	 * @param cursorPoint: location of the mouse when drag was recognized
	 */
	public void mouseDragged(Point cursorPoint);

	/** Handles mouse movement in a certain JPanel/JFrame
	 * @param cursorPoint: location of the mouse in quasi real-time 
	 */
	public void mouseMoved(Point cursorPoint);

	public void mouseClicked(Point cursorPoint);

	/** Handles a mouse pressed. Distinct from mouse click because the mouse does not necessarily have to be released.
	 * @param e: MouseEvent with attributes such as coordinates and recognising double clicks
	 */
	// not cursorPoint like everything else as one of the classes that implements this (polyline) needs to recognize doubleClicks
	public void mousePressed(MouseEvent e);

	public void mouseReleased(Point cursorPoint);

	public void mouseEntered(Point cursorPoint);

	public void mouseExited(Point cursorPoint);
	
	/** Handles the painting of a shape. 
	 * @param g2d: 2D Graphic
	 * @param s: Some shape to be painted in the Graphic2D
	 */
	public void paintComponent(Graphics2D g2d, Shape s);
}
