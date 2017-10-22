package jere99.chess.frontEnd;

import java.awt.event.ActionListener;

import javax.swing.Icon;
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
	protected class GenericButton extends JButton {
		
		/**
		 * Performs generic initialization for a JButton.
		 * 
		 * @param l the object who should listen for this button to be clicked
		 */
		protected GenericButton(ActionListener l) {
			this(null, null, l);
		}
		
		/**
		 * Performs generic initialization for a JButton.
		 * 
		 * @param text the text of the button
		 * @param l the object who should listen for this button to be clicked
		 */
		protected GenericButton(String text, ActionListener l) {
			this(text, null, l);
		}
		
		/**
		 * Performs generic initialization for a JButton.
		 * 
		 * @param icon the Icon image to display on the button
		 * @param l the object who should listen for this button to be clicked
		 */
		protected GenericButton(Icon icon, ActionListener l) {
			this(null, icon, l);
		}
		/**
		 * Performs generic initialization for a JButton.
		 * 
		 * @param text the text of the button
		 * @param icon the Icon image to display on the button
		 * @param l the object who should listen for this button to be clicked
		 */
		protected GenericButton(String text, Icon icon, ActionListener l) {
			super(text, icon);
			addActionListener(l);
			
			setOpaque(true);
			setBorderPainted(false);
		}
		
	}
	
}
