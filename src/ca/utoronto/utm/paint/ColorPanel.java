package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ColorPanel extends JPanel implements MouseListener, MouseMotionListener, Observer {
	Color hoveringColor;
	JLabel selectedColorLabel;
	int diameter;
	int selectorSize;
	Boolean mouseIsInColorPanel = false;
	Point centre;
	Point hovering;
	Point blankSpaceTop = new Point(200, 0);
	Point greyScaleSize = new Point(200, 20);
	Point blankSpaceMiddle = new Point(200,20);
	Point circleSize = new Point(200,200);
	
	/**
	 * Constrtuctor, Initialize panel dimensions
	 */
	public ColorPanel() {
		this.setPreferredSize(new Dimension(Math.max(greyScaleSize.getX(), circleSize.getX()),
			greyScaleSize.getY()+blankSpaceTop.getY()+circleSize.getY()+blankSpaceMiddle.getY()));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	/**
	 *  Paint the Color Wheel onto the panel with different mixes of RBG Colors
	 *  
	 */
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        updateDimensions();
        Graphics2D g2d = (Graphics2D) g;
		int[] rgb = {248,8,8};
		int arcAngle = 1;
		
		// 0 to 360 because it is an arc going around 360 degrees
		// Fills the arc with despected values
        for (int i=0; i<360; i++) {
    			int colorDown = i/120;
    			int colorUp = colorDown+1;
    			// the arc is split up into different sections, mainly to split up the 
    			// RGB colors.
    			if (colorUp>2) {
    				colorUp = 0;
    			}
    			
        		g2d.setColor(new Color(rgb[0],rgb[1],rgb[2]));
        		int startAngle = i;
	        g2d.fillArc(0, blankSpaceTop.getY()+greyScaleSize.getY()+blankSpaceMiddle.getY(),
	        		this.diameter, this.diameter, startAngle, arcAngle);
	        if (rgb[colorUp] == 248) {
	        		rgb[colorDown]-= 4;
	        } else {
	    			rgb[colorUp]+= 4;
	        }
        }
        // fill the greyscale rectangle for black-white color shades
        for (int i=0; i<=200; i++) {
        		g2d.setColor(getColorFromGreyScale(i));
        		g2d.fillRect(i, blankSpaceTop.getY(), 1, greyScaleSize.getY());
        }
        
        
        if (mouseIsInColorPanel && (getMouseIsInWheel() || getMouseIsInGreyScale())) {
			g2d.setColor(this.hoveringColor);
			int x = this.hovering.getX()-(this.selectorSize/2);
			int y = this.hovering.getY()-(this.selectorSize/2);
			g2d.fillOval(x,y,this.selectorSize, this.selectorSize);
			g2d.setColor(Color.BLACK);
			g2d.drawOval(x,y,this.selectorSize, this.selectorSize);
        }

        // Disposing saves memory, helps the program consume less memory
        g2d.dispose();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
		mouseClicked(e);
	}
	
	/**
	 * Update the dimensions of the colorPanel
	 */
	private void updateDimensions() {
		this.diameter = Math.min(this.getWidth(), this.getHeight());
		this.centre = new Point(this.diameter/2,(this.diameter/2)+
			blankSpaceTop.getY()+greyScaleSize.getY()+blankSpaceMiddle.getY());
		this.selectorSize = this.diameter/10;
	}

	/** 
	 * Show a magnified versions of the color being hovered over from the color wheel 
	 * or the gray scale 
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		this.hovering = new Point(e.getX(),e.getY());
		if (getMouseIsInWheel()) {
			this.hoveringColor = getColorFromWheel();
		} else if (getMouseIsInGreyScale()){
			this.hoveringColor = getColorFromGreyScale(hovering.getX());
		}
		repaint();
	}
	
	/**
	 * Check whether the mouse is in the wheel
	 * @return boolean - True if mouse is in the color wheel
	 */
	private Boolean getMouseIsInWheel() {
		double deltaX = (this.hovering.getX()-this.centre.getX());
		double deltaY = -(this.hovering.getY()-this.centre.getY());
		int radius = (int)(Math.pow(Math.pow(deltaX,2)+(Math.pow(deltaY,2)),0.5));
		return radius<=(this.diameter/2);
	}
	/**
	 * Check is the mouse is in the grayscale color selector
	 * @return boolean - true if mouse is in Grayscale color selector
	 */
	private Boolean getMouseIsInGreyScale() {
		 return this.hovering.getY()<=greyScaleSize.getY() && this.hovering.getX()<=greyScaleSize.getX();
	}
	
	/**
	 * Get the color selected from the wheel
	 * @return Color - the color selected from the wheel
	 */
	private Color getColorFromWheel() {
		double deltaX = (this.hovering.getX()-this.centre.getX());
		double deltaY = -(this.hovering.getY()-this.centre.getY());
		int angle = (int)Math.toDegrees(Math.atan2(deltaY,deltaX));
		if (angle<0) {
			angle+=360;
		}
		int[] rgb = {248,8,8};
        for (int i=0; i<angle; i++) {
    			int colorDown = i/120;
    			int colorUp = colorDown+1;
    			if (colorUp>2) {
    				colorUp = 0;
    			}
	        if (rgb[colorUp] == 248) {
	        		rgb[colorDown]-= 4;
	        } else {
	    			rgb[colorUp]+= 4;
	        }
        }
        return new Color(rgb[0],rgb[1],rgb[2]);
	}
	
	/**
	 * Return the color selected from the grey scale
	 * @param scale - int value of the scale of the gradient 
	 * @return Color - the color selected form grayscale 
	 */
	private Color getColorFromGreyScale(int scale) {
		int greyScaleColorInt = (int)((255/2)*(Math.sin((scale*Math.PI/200)-Math.PI/2)+1)); 
		// grey scale has a tiny sliver for users to select white
		// for a linear gradient
		// but if I use an increasing function with changing concavity
		// I can inflate the representation of the basic polar colors
		// white and black. I took the sine function, but I need
		// to translate it s.t. the magnitude of its codomain is 255 and
		// it starts at 0 for x=0. This is done by an amplitude of 255/2,
		// vertical shift of 255/2 and a rightward shift of pi/2
		// [(amplitude)[sin(i*x)-|rightward shift|]]+vertical shift
		// => f(i) = ((255/2)*(Math.sin((i*Math.PI/200)-Math.PI/2)))+255/2) 
		// for f: (0,200) |-> (0,255) and is a monotonic increasing function with
		// changing concavity in the middle.
		return new Color(greyScaleColorInt, greyScaleColorInt, greyScaleColorInt);
		
	}
	
	/**
	 * Sets the color picked form the color wheel of the grayscale
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		this.hovering = new Point(e.getX(),e.getY());
		if (getMouseIsInWheel()) {
			ColorWheelModel.getColorWheelModel().setColorSelected(getColorFromWheel());
			PaintModel.getPaintModel().setColorSelected(getColorFromWheel());
		} else if (getMouseIsInGreyScale()) {
			ColorWheelModel.getColorWheelModel().setColorSelected(getColorFromGreyScale(this.hovering.getX()));
			PaintModel.getPaintModel().setColorSelected(getColorFromGreyScale(this.hovering.getX()));
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.mouseIsInColorPanel = true;
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		this.mouseIsInColorPanel = false;
		repaint();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
		
	}
}
