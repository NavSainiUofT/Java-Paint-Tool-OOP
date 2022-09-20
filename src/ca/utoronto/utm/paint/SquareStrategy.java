package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * @author alex
 * Class for handling the painting of square objects
 */
public class SquareStrategy implements PaintStrategy {

	@Override
	public void mouseDragged(Point cursorPoint) {
		Square square = (Square)PaintModel.getPaintModel().peek();
		int length = Math.abs(cursorPoint.getX() - square.getOrigin().getX());
		int width = Math.abs(cursorPoint.getY() - square.getOrigin().getY());
		square.setDimensions(length, width);
		square.setTopLeft(cursorPoint);
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
		PaintModel.getPaintModel().newShape(new Square(cursorPoint));
		
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
		Square s = (Square)shape;
		int topLeftX = s.getTopLeft().getX(); int topLeftY = s.getTopLeft().getY();
		int sideLength = s.getSquareLength();
		g2d.draw(new Rectangle2D.Double(topLeftX, topLeftY, sideLength, sideLength));
		if (s.getIsFilled()) {
			g2d.fillRect(topLeftX, topLeftY, sideLength, sideLength);
		}
		if (s.getIsSelected()) {
			g2d.setColor(new Color(0,0,255,128));
			int lineThickness = s.getLineThickness();
			if (s.getIsFilled()) {
				if (lineThickness/2!=0) {
					lineThickness+=1;
				}
				g2d.setStroke(new BasicStroke(1));
				g2d.drawRect(topLeftX-(lineThickness/2), topLeftY-(lineThickness/2), sideLength+lineThickness, sideLength+lineThickness);
			} else {
				g2d.setStroke(new BasicStroke(lineThickness+2));
				g2d.draw(new Rectangle2D.Double(topLeftX, topLeftY, sideLength, sideLength));
			}
		}
	}

}
