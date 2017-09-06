package jere99.chess.frontEnd;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

/**
 * An abstract GenericGUI which uses a JPanel.
 * Any GUI which uses a JPanel in this project extends this class.
 * @author JeremiahDeGreeff
 * @see GenericLabelGUI
 */
@SuppressWarnings("serial")
public abstract class GenericPanelGUI extends GenericGUI {
	
	/**
	 * the main display component of the GUI
	 */
	protected final JPanel panel = new JPanel();
	
	/**
	 * creates a GenericPanelGUI with an indicated title
	 * @param title the title of the window
	 */
	public GenericPanelGUI(String title) {
		//Name the window
		super(title);
		
		//Add the JPanel to the GUI
		add(panel);
	}
	
	/**
	 * This method will be called whenever a button on this GUI is clicked
	 * @param b the button which has been clicked
	 */
	protected abstract void buttonClick(GenericButton b);
	
	/**
	 * An abstract JButton which all buttons that are on GUIs which extend GenericPanelGUI in this project extend.
	 * @author JeremiahDeGreeff
	 */
	protected abstract class GenericPanelButton extends GenericButton {
		
		/**
		 * Runs when the button is clicked
		 */
		public void actionPerformed(ActionEvent e) {
			((GenericPanelGUI)getParent().getParent().getParent().getParent().getParent()).buttonClick(this);
		}
		
	}
	
}
