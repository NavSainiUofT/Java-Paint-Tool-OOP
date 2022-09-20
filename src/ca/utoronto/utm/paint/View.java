package ca.utoronto.utm.paint;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;

/**
 * This is the top level View+Controller, it contains other aspects of the View+Controller.
 * @author arnold
 *
 */
public class View extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static View view = new View();
	
	// The components that make this up
	private PaintPanel paintPanel;
	private OptionsPanel optionsPanel;
	private UndoStack undostack = new UndoStack();

	/** 
	 * Constructor that attaches all major panels of the frame
	 */
	private View() {
		super("Paint"); // set the title and do other JFrame init
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(createMenuBar());
		Container c=this.getContentPane();
		this.paintPanel = PaintPanel.getPaintPanel();
		this.optionsPanel = new OptionsPanel(this);
		c.add(this.optionsPanel,BorderLayout.WEST);
		c.add(this.paintPanel, BorderLayout.CENTER);
		this.pack();
		view = this;
	}
	
	
	public void init() {
		this.setVisible(true);
	}
	
	/** 
	 * return the View Class instance
	 * @return
	 */
	public static View getView() {
		return view;
	}
	
	/**
	 * Return the Options panel which has all tools
	 * @return OptionsPanel
	 */
	public OptionsPanel getOptionsPanel() {
		return optionsPanel;
	}

	/**
	 * Create a menu bar
	 * 
	 * @return JMenuBar
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;
		JMenu help;
		JMenuItem helpItem_Instructions;
		JMenuItem helpItem_About;

		
		menu = new JMenu("File");
		

		// a group of JMenuItems
		// Also add action listeners to them
		
		
		menuItem = new JMenuItem("New");
		NewActionListener newAction = new NewActionListener(PaintModel.getPaintModel());
		menuItem.addActionListener(newAction);
		
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Edit");

		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		CutActionListener cut = new CutActionListener(); 
		menuItem.addActionListener(cut);
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		CopyActionListener copy = new CopyActionListener();
		menuItem.addActionListener(copy);
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		PasteActionListener paste = new PasteActionListener();
		menuItem.addActionListener(paste);
		menu.add(menuItem);		
		
		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo");
		UndoActionListener undo = new UndoActionListener(undostack,PaintModel.getPaintModel());
		menuItem.addActionListener(undo);
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo");
		RedoActionListener redo = new RedoActionListener(undostack,PaintModel.getPaintModel());
		menuItem.addActionListener(redo);
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		//Create a help option to the JMenu
		help = new JMenu("Help");
		
		//a group of JMenuItems
		helpItem_Instructions = new JMenuItem("Instructions");
		HelpActionListener help_instructions = new HelpActionListener();		
		helpItem_Instructions.addActionListener(help_instructions);
		
		helpItem_About = new JMenuItem("About");
		HelpActionListener help_about = new HelpActionListener();		
		helpItem_About.addActionListener(help_about);
		
		help.add(helpItem_Instructions);
		help.add(helpItem_About);
		menuBar.add(help);
		
		//return the menubar
		return menuBar;
	}

}
