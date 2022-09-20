package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * @author alex
 * Class for filling in different areas on the PaintPanel with the same color
 */
public class PaintBucketStrategy implements PaintStrategy {
	
	@Override
	public void mouseDragged(Point cursorPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(Point cursorPoint) {
		
	}

	@Override
	public void mouseClicked(Point cursorPoint) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point cursorPoint = new Point(e.getX(), e.getY());
		int paintPanelWidth = (int)PaintPanel.getPaintPanel().getBounds().getWidth();
		int paintPanelHeight = (int)PaintPanel.getPaintPanel().getBounds().getHeight();
		BufferedImage bufferedImage = new BufferedImage(paintPanelWidth, paintPanelHeight,
	            BufferedImage.TYPE_INT_ARGB); //takes what's effectively a screenshot which can be analysed pixel by pixel
		PaintPanel.getPaintPanel().paint(bufferedImage.getGraphics());
		Color fillColor = new Color(bufferedImage.getRGB(cursorPoint.getX(), cursorPoint.getY())); //get the pixel we're trying to fill
		DotShape dotShape = new DotShape(cursorPoint);
		getFillPoints(dotShape, bufferedImage, cursorPoint, fillColor);
		PaintModel.getPaintModel().newShape(dotShape);
		// we want to set the shape after, because DotShape does not actually setChanged
		// and notifyObservers when dots are added to improve runtime, as there are many dots added
	}

	
	/** Helper function to help find points
	 * @param dotShape
	 * @param bufferedImage
	 * @param cursorPoint
	 * @param fillColor
	 */
	private void getFillPoints(DotShape dotShape, BufferedImage bufferedImage, Point cursorPoint, Color fillColor) {
		
		// while-loop to circumvent stack overflow
		boolean[][] yXCheckedColor = new boolean[bufferedImage.getHeight()][]; 
		//store as boolean array because arraylists take too long
		// to iterrate over thousands of points, and it's quick to simply index y,x rather than search every point in an array
		Stack<Point> pointStack = new Stack<>();
		pointStack.add(cursorPoint);
		Point currentPoint = cursorPoint;
		Point previousPoint;
		Point newPoint;
		while (!(pointStack.isEmpty())) {
			previousPoint = currentPoint;
			currentPoint = pointStack.pop();
			for (int dx=-1; (dx<2) ; dx++) {
				for (int dy=-1; dy<2; dy++) {
					// cartesian product of {-1,0,1}x{-1,0,1}
					if (Math.abs(dx)!=Math.abs(dy)) {
						//dont include 0,0 - and don't consider pixels connected by corners to be adjacent
						newPoint = new Point(currentPoint.getX()+dx, currentPoint.getY()+dy);
						if (newPoint.getX()>0 && newPoint.getY()>0 && 
								newPoint.getX()<bufferedImage.getWidth() && 
								newPoint.getY()<bufferedImage.getHeight() && 
								!(newPoint.equals(previousPoint))) {
									//simply keep track of previous point to avoid iterating through the thousands of existing dots
									if (yXCheckedColor[newPoint.getY()]==null) {
										yXCheckedColor[newPoint.getY()] = new boolean[bufferedImage.getWidth()];
									}
									if (!(yXCheckedColor[newPoint.getY()][newPoint.getX()])) {
										Color bufferedImageColor = new Color(bufferedImage.getRGB(newPoint.getX(), newPoint.getY()));
										if (fillColor.equals(bufferedImageColor)) {
											dotShape.addNewDot(newPoint);
											pointStack.add(newPoint);
											}
										yXCheckedColor[newPoint.getY()][newPoint.getX()] = true;
										}
									}
								}
					}
				}
			}
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
		DotShape ds = (DotShape)s;
		for (Point d: ds.getDotShape()) {
			g2d.fillRect(d.getX(),d.getY(), 1, 1); // fill each pixel as a 1x1 rectangle
		}
	}

}
