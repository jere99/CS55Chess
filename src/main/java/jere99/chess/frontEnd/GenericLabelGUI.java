package jere99.chess.frontEnd;

import java.awt.event.ActionEvent;

/**
 * An abstract GenericGUI which uses a JLabel.
 * Any GUI which uses a JLabel in this project extends this class.
 * @author JeremiahDeGreeff
 * @see GenericPanelGUI
 */
@SuppressWarnings("serial")
public abstract class GenericLabelGUI extends GenericGUI {
	
	/**
	 * creates a GenericLabelGUI with an indicated title
	 * @param title the title of the window
	 */
	protected GenericLabelGUI(String title) {
		//Name the window
		super(title);
		
		//Set JFrame to Absolute Layout so that elements may be positioned
		setLayout(null);
	}
	
	/**
	 * This method will be called whenever a button on this GUI is clicked
	 * @param b the button which has been clicked
	 */
	protected abstract void buttonClick(GenericButton b);
	
	/**
	 * An abstract JButton which all buttons which are on GUIs that extend GenerivLabelGUI in this project extend.
	 * @author JeremiahDeGreeff
	 */
	protected abstract class GenericLabelButton extends GenericButton {
		
		/**
		 * Runs when the button is clicked
		 */
		public void actionPerformed(ActionEvent e) {
			((GenericLabelGUI)getParent().getParent().getParent().getParent()).buttonClick(this);
		}
		
	}

}
