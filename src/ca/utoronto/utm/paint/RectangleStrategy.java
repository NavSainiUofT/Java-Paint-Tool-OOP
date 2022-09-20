package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * @author alex
 * Class for handling the painting of a rectangle
 */
public class RectangleStrategy implements PaintStrategy{

	@Override
	public void mouseDragged(Point cursorPoint) {
		Rectangle rectangle = (Rectangle)PaintModel.getPaintModel().peek();
		int width = Math.abs(cursorPoint.getX() - rectangle.getOrigin().getX());
		int height = Math.abs(cursorPoint.getY() - rectangle.getOrigin().getY());
		rectangle.setDimensions(width, height);
		rectangle.setTopLeft(cursorPoint);
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
		PaintModel.getPaintModel().newShape(new Rectangle(cursorPoint));
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
	public void paintComponent(Graphics2D g2d, Shape s) {
		Rectangle r = (Rectangle)s;
		int topLeftX = r.getTopLeft().getX(); int topLeftY = r.getTopLeft().getY();
		int width = r.getWidth(); int height = r.getHeight();
		g2d.draw(new Rectangle2D.Double(topLeftX, topLeftY, width, height));
		
		if (r.getIsFilled()) {
			g2d.fillRect(topLeftX, topLeftY, width, height);
		}
		if (s.getIsSelected()) {
			g2d.setColor(new Color(0,0,255,128));
			int lineThickness = s.getLineThickness();
			if (s.getIsFilled()) {
				if (lineThickness/2!=0) {
					lineThickness+=1;
				}
				g2d.setStroke(new BasicStroke(1));
				g2d.drawRect(topLeftX-(lineThickness/2), topLeftY-(lineThickness/2), width+lineThickness, height+lineThickness);
			} else {
				g2d.setStroke(new BasicStroke(lineThickness+2));
				g2d.draw(new Rectangle2D.Double(topLeftX, topLeftY, width, height));
			}
		}
	}

}
