package jere99.chess.frontEnd;

import java.awt.event.ActionEvent;
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
public abstract class GenericGUI extends JFrame {
	
	/**
	 * Performs generic initialization for a JFrame.
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
	 * This method should be called whenever a button on this GUI is clicked.
	 * 
	 * @param b the button which has been clicked
	 */
	protected abstract void buttonClick(GenericButton b);
	
	/**
	 * An abstract JButton which all buttons in this project extend.
	 * 
	 * @author JeremiahDeGreeff
	 */
	protected abstract class GenericButton extends JButton implements ActionListener {
		
		/**
		 * Performs generic initialization for a JButton.
		 */
		protected GenericButton() {
			setOpaque(true);
			setBorderPainted(false);
			
			//creates a new Action Listener
			addActionListener(this);
		}
		
		/**
		 * Runs when the button is clicked.
		 */
		public abstract void actionPerformed(ActionEvent e);
	}
	
}
