package ca.utoronto.utm.paint;
public class Paint {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Paint();
			}
		});
	}
	//made some changes
	PaintModel model; // Model
	View view; // View+Controller

	public Paint() {
		// Create MVC components and hook them together

		// Model
		// View+Controller
		View.getView().init();
	}
}
