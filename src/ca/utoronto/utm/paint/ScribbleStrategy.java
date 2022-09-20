package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author alex
 * Class for handling the painting of scribbles.
 */
public class ScribbleStrategy implements PaintStrategy {

	@Override
	public void mouseDragged(Point cursorPoint) {
		Scribble scribble = (Scribble)PaintModel.getPaintModel().peek();
		scribble.addPoint(new Point(cursorPoint.getX(),cursorPoint.getY()));
	}

	@Override
	public void mouseMoved(Point cursorPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(Point cursorPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point cursorPoint = new Point(e.getX(), e.getY());
		PaintModel.getPaintModel().newShape(new Scribble(cursorPoint));
		
	}

	@Override
	public void mouseReleased(Point cursorPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(Point cursorPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(Point cursorPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintComponent(Graphics2D g2d, Shape shape) {
		Scribble s = (Scribble)shape;
		ArrayList<Point> scribbleInstance = s.getScribble();
		for(int i=0;i<scribbleInstance.size()-1; i++){
			Point p1=scribbleInstance.get(i);
			Point p2=scribbleInstance.get(i+1);
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
		if (s.getIsSelected()) {
			g2d.setColor(new Color(0,0,255,128));
			g2d.setStroke(new BasicStroke(s.getLineThickness()+2));
			for(int i=0;i<scribbleInstance.size()-1; i++){
				Point p1=scribbleInstance.get(i);
				Point p2=scribbleInstance.get(i+1);
				g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			}
		}
	}

}
