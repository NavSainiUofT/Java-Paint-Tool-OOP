package ca.utoronto.utm.paint;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * @author alex
 * Class for handling the selection of different shape objects
 */
public class SelectToolStrategy implements PaintStrategy{
	boolean dragging;

	@Override
	public void mouseDragged(Point cursorPoint) {
		if (PaintModel.getPaintModel().getSelectedShape()!=null && this.dragging) {
			PaintModel.getPaintModel().moveSelectedShapes(cursorPoint);
		}
	}

	@Override
	public void mouseMoved(Point cursorPoint) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(Point cursorPoint) {
		// TODO Auto-generated method stub
		
		
		// ask the paint panel for the top most shape at this point
		
		//Shape selected = PaintPanel.getPaintPanel().getComponentAt(cursorPoint);

	}
	
	@Override
	public void mousePressed(MouseEvent e) { 
		Point cursorPoint = new Point(e.getX(), e.getY());
		Shape shape = PaintModel.getPaintModel().getShapeAtPoint(cursorPoint);
		if (shape!= null) {
			this.dragging = true;
			PaintModel.getPaintModel().addSelectedShape(shape);
			PaintModel.getPaintModel().setDragShift(cursorPoint);
		} else {
			PaintModel.getPaintModel().addSelectedShape(null);
			this.dragging = false;
		}
	}

	@Override
	public void mouseReleased(Point cursorPoint) {
		this.dragging = false;
	}

	@Override
	public void mouseEntered(Point cursorPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(Point cursorPoint) {
		this.dragging = false; 
		
	}

	@Override
	public void paintComponent(Graphics2D g2d, Shape s) {
		// TODO Auto-generated method stub
		
	}

}
