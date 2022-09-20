package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * @author alex
 * PaintStrategy to handle various circle painting aspects.
 */
public class CircleStrategy implements PaintStrategy {
	
	@Override
	public void mouseDragged(Point cursorPoint) {
		Circle circle = (Circle)PaintModel.getPaintModel().peek();
		circle.setRadius(Point.getDistance(cursorPoint, circle.getCentre()));
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
		PaintModel.getPaintModel().newShape(new Circle(cursorPoint));
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
		Circle c = (Circle)s;
		int radius = c.getRadius();
		int x = (-1*radius)+c.getCentre().getX();
		int y = (-1*radius)+c.getCentre().getY();
		int width = radius*2;
		int height = width;
		g2d.drawOval(x, y, width, height); // draw the circle outline
		if (c.getIsFilled()) {
			g2d.fillOval(x, y, width, height); // if it's designated 'filled', fill an oval with the same dimensions as the outline
		}
		if (c.getIsSelected()) { // if it's selected, draw blue highlights
			g2d.setColor(new Color(0,0,255,128)); // translucent blue (the 4th parameter is alpha - opacity)
			int lineThickness = c.getLineThickness();
			if (c.getIsFilled()) {
				g2d.setStroke(new BasicStroke(1)); 
				if (lineThickness%2!=0) { // line thickness will be divided by 2, as line thickness exudes inward and outward;
										// but if it has a remainder, make it even.
					lineThickness+=1;
				}
				g2d.drawOval((x-lineThickness/2), (y-lineThickness/2), width+lineThickness, height+lineThickness); // draw a thin outline on the outside if it's filled
			} else {
				g2d.setStroke(new BasicStroke(lineThickness+2));
				g2d.drawOval(x, y, width, height); // just do a thick outline on top if it's not filled
			}
		}
	}

}
