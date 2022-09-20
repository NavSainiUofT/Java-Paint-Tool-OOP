package ca.utoronto.utm.paint;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

@SuppressWarnings("serial")
public class OptionsPanel extends JPanel implements Observer {
	private View view; // So we can talk to our parent or other components of the view
	private HashMap<JButton, PaintStrategy> buttonStrategyMap; 
	private JTextField colorSelectedLabel;
	private ColorPanel colorPanel;
	
	/**
	 * constructor adds panels to certain sections of the frame
	 * @param view
	 */
	public OptionsPanel(View view) {
		this.view=view;
		this.setLayout(new BorderLayout());
		this.add(getShapeButtonsPanel(), BorderLayout.NORTH);
		this.add((getColorWheelColorSelectedPanel()),BorderLayout.CENTER);
		this.add(getSliderFillPanel(),BorderLayout.SOUTH);
	}
	
	/**
	 * Input all the neccessary buttons from into a JPanel
	 * @return JPanel
	 */
	private JPanel getShapeButtonsPanel() {
		// shapeButtons holds all the modes available, shapes and the tools
		JPanel shapeButtons = new JPanel(new GridLayout(8,1));
		ImageIcon[] icons = getIcons();
		PaintStrategy[] paintStrategies = {new CircleStrategy(), new RectangleStrategy(), new SquareStrategy(),
				new ScribbleStrategy(), new PolylineStrategy(), new PaintBucketStrategy(), 
				new EraserStrategy(), new SelectToolStrategy()};
		this.buttonStrategyMap = new HashMap<>();
		
		
		// assign action listeners here
		// along with the strategy they will use to be implemented
		
		for (int i=0; i<icons.length; i++) {
			JButton button = new JButton(new ImageIcon(icons[i].getImage().getScaledInstance(35, 35,  java.awt.Image.SCALE_SMOOTH)));
			shapeButtons.add(button);
			button.setFocusPainted(false);
			button.addActionListener(new ShapeButtonsActionListener(this,button,paintStrategies[i]));		
			buttonStrategyMap.put(button, paintStrategies[i]);
			}
		return shapeButtons;
	}
	
	/**
	 * Assign Icon Files to certain images 
	 * @return ImageIcon 
	 */
	private ImageIcon[] getIcons() {
		ImageIcon circle_image = new ImageIcon(OptionsPanel.class.getResource("Image1.png"), "Circle");
		ImageIcon rectangle_image = new ImageIcon(OptionsPanel.class.getResource("Image2.png"), "Rectangle");
		ImageIcon square_image = new ImageIcon(OptionsPanel.class.getResource("Image3.png"), "Square");		
		ImageIcon squiggle_image = new ImageIcon(OptionsPanel.class.getResource("Image4.png"), "Squiggle");
		ImageIcon polyline_image = new ImageIcon(OptionsPanel.class.getResource("Image5.png"), "Polyline");
		ImageIcon paintBucketIcon = new ImageIcon(OptionsPanel.class.getResource("paintBucketIcon.png"),"paintBucketIcon");
		ImageIcon eraserIcon = new ImageIcon(OptionsPanel.class.getResource("eraserIcon.png"),"eraseIcon");
		ImageIcon selectIcon = new ImageIcon(OptionsPanel.class.getResource("cursor_PNG78.png"),"selectIcon");
		ImageIcon[] icons = {circle_image, rectangle_image, square_image, squiggle_image, polyline_image, 
				paintBucketIcon, eraserIcon, selectIcon};
		
		return icons;
	}
	
	/**
	 * Create the colorWheel GUI
	 * @return JPanel
	 */
	private JPanel getColorWheelColorSelectedPanel() {
		JPanel colorWheelColorSelected = new JPanel(new BorderLayout());	
		// label dimensions
		this.colorSelectedLabel = new JTextField();
		this.colorSelectedLabel.setOpaque(true);
		this.colorSelectedLabel.setBackground(PaintModel.getPaintModel().getColorSelected());
		this.colorSelectedLabel.setEditable(false);
		this.colorPanel = new ColorPanel();
		// create a grid layout wiht one label
		JPanel colorSelectedPanel = new JPanel(new GridLayout(1,1));
		JLabel currentcolor = new JLabel("Color: ", JLabel.CENTER);
		currentcolor.setFont(new Font("Papyrus-Bold", Font.ITALIC, 16));
		
		// add all elements to the panels
		colorSelectedPanel.add(currentcolor);
		colorSelectedPanel.add(this.colorSelectedLabel);
		colorWheelColorSelected.add(colorSelectedPanel,BorderLayout.NORTH);
		colorWheelColorSelected.add(colorPanel,BorderLayout.SOUTH);
		ColorWheelModel.getColorWheelModel().addObserver(this);
		
		// the final colorWheel Panel 
		return colorWheelColorSelected;
	}
	
	/**
	 * 
	 * @return ColorPanel - JPanel with the color selectors
	 */
	public ColorPanel getColorPanel() {
		return this.colorPanel;
	}
	
	/**
	 * Create a JPanel with a slider, Label and Checkbox for adjusting the LineThickness, Fill
	 * and label to display the color selected
	 * 
	 * @return JPanel 
	 */
	private JPanel getSliderFillPanel() {
		
		// Asigning all dimensions and action listeners
		// adding the elements into the panel as well
		
		
		JPanel sliderFillPanel = new JPanel(new GridLayout(3,1));
		JLabel thickness = new JLabel("Thickness: ", JLabel.CENTER);
		thickness.setFont(new Font("Papyrus-Bold", Font.ITALIC, 16));
		JSlider slider = new JSlider(1,20,1);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		
		slider.addChangeListener(new SliderListener(slider));
	    LineThickness.getInstance().setThickness(slider.getValue());
	    sliderFillPanel.add(thickness);
	    sliderFillPanel.add(slider);
		JCheckBox checkBox = new JCheckBox("Fill");
		checkBox.addActionListener(new FillActionListener(this.view));
		sliderFillPanel.add(checkBox);
		
		return sliderFillPanel;
	}

	public HashMap<JButton, PaintStrategy> getButtonStrategyMap() {
		return this.buttonStrategyMap;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		ColorWheelModel colorWheelModel = (ColorWheelModel)arg0;
		this.colorSelectedLabel.setBackground(colorWheelModel.getColorSelected());
		repaint();
	}
	

	

	
}
