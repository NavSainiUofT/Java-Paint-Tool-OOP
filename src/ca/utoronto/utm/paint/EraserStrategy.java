package ca.utoronto.utm.paint;

import java.awt.event.MouseEvent;

/** 
 * @author alex
 * This class handles eraser drawing. As eraser is essentially a scribble, it extends scribleStrat.
 */
public class EraserStrategy extends ScribbleStrategy {

	@Override
	public void mousePressed(MouseEvent e) {
		Point cursorPoint = new Point(e.getX(), e.getY());
		PaintModel.getPaintModel().newShape(new Eraser(cursorPoint)); 
		// need to  change this method because scribble strat creates a scribble instance, not an eraser
	}
}
