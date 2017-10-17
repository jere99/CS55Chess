package jere99.chess.frontEnd;

import javax.swing.JPanel;

/**
 * An abstract GenericGUI which uses a JPanel.
 * Any GUI which uses a JPanel in this project extends this class.
 * 
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public abstract class GenericPanelGUI extends GenericGUI {
	
	/**
	 * The main display component of the GUI.
	 */
	protected final JPanel panel = new JPanel();
	
	/**
	 * Performs generic initialization for a JPanel based JFrame.
	 * 
	 * @param title the title of the window
	 */
	public GenericPanelGUI(String title) {
		//Name the window
		super(title);
		
		//Add the JPanel to the GUI
		add(panel);
	}
	
}
