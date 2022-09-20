package ca.utoronto.utm.paint;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("serial")
// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/
// testing change


public class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener  {
	private static PaintPanel paintPanel = new PaintPanel(); // Singleton
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private PaintStrategy paintStrategy;
	
	/** 
	 * Set dimensions for the main paint Panel where shapes can be drawn
	 */
	private PaintPanel(){
		//set all dimensions for the paint panel 
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.model = PaintModel.getPaintModel();
		this.model.addObserver(this);
		paintPanel = this;
	}
	
	/**
	 * return the Paint panel
	 * @return PaintPanel
	 */
	public static PaintPanel getPaintPanel() {
		return paintPanel;
	}
	
	/**
	 *  Set the strategy for paint 
	 */
	public void setPaintStrategy(PaintStrategy ps) {
		this.paintStrategy = ps;
	}
	
	/**
	 * Goes through the current list of shapes and repaints all the 
	 * shapes onto the paint panel again
	 */
	public void paintComponent(Graphics g) {
		
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g;

		ArrayList<Shape> shapes = this.model.getShapes();
		// go through  all the shapes and repaint them onto the paint panel
		for(Shape s: shapes){
			int num = s.getLineThickness();
			g2d.setStroke(new BasicStroke(num));
			int alpha;
			if (s.getIsSelected()) {
				alpha = 128;
			} else {
				alpha = 255;
			}
			g2d.setColor(new Color(s.getColor().getRed(), s.getColor().getGreen(), s.getColor().getBlue(), alpha));
			PaintStrategy drawStrat;
			
			// implement the strategy correspondign to the shape
			if (s.getClass() == Scribble.class) {
				drawStrat = new ScribbleStrategy();
		
			} else if(s.getClass() == Circle.class) {
				drawStrat = new CircleStrategy();

			} else if(s.getClass() == Rectangle.class) {
				drawStrat = new RectangleStrategy();
				
			} else if(s.getClass() == Polyline.class) {
				drawStrat = new PolylineStrategy();
				
			} else if(s.getClass() == DotShape.class) {
				drawStrat = new PaintBucketStrategy();
				
			} else if(s.getClass() == Eraser.class) {
				drawStrat = new EraserStrategy();
			} else {
				drawStrat = new SquareStrategy();
			}
			// paints the component using the set strategy
			
			drawStrat.paintComponent(g2d, s);
		}
		
		// we dispose it so it takes up less memory 
		g2d.dispose();
	}

	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (!(this.paintStrategy==null)) {
			Point cursorPoint = new Point(e.getX(), e.getY());
			this.paintStrategy.mouseMoved(cursorPoint);
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (!(this.paintStrategy==null)) {
			Point cursorPoint = new Point(e.getX(), e.getY());
			this.paintStrategy.mouseDragged(cursorPoint);
		}
	}

	// MouseListener below
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (!(this.paintStrategy==null)) {
			this.paintStrategy.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	/** 
	 * Return the paint strategy
	 * @return
	 */
	public PaintStrategy getStrategy() {
		return this.paintStrategy;
	}
}
