package jere99.chess.frontEnd;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * An abstract JFrame which all GUIs in this project extend.
 * 
 * @author JeremiahDeGreeff
 * @see GenericPanelGUI
 * @see GenericLabelGUI
 */
@SuppressWarnings("serial")
public abstract class GenericGUI extends JFrame implements ActionListener {
	
	/**
	 * Performs generic initialization for a JFrame.
	 * 
	 * @param title the title of the window
	 */
	protected GenericGUI(String title) {
		//Name the window
		super(title);
		
		//Prevents window from being resized
		setResizable(false);
				
		//Default program end
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * An abstract JButton which all buttons in this project extend.
	 * 
	 * @author JeremiahDeGreeff
	 */
	protected abstract class GenericButton extends JButton {
		
		/**
		 * Performs generic initialization for a JButton.
		 * 
		 * @param l the object who should listen for this button to be clicked
		 */
		protected GenericButton(ActionListener l) {
			setOpaque(true);
			setBorderPainted(false);
			
			//creates a new Action Listener
			addActionListener(l);
		}
		
	}
	
}
