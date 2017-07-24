package jere99.chess.frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

/**
 * Buttons that appear on the StartupScreenGUI
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class StartupScreenButton extends JButton implements ActionListener {

	/**
	 * text displayed on the button, also used to identify the button
	 */
	protected final String DISPLAY_NAME;

	/**
	 * creates a button for the StartupScreenGUI
	 * @param displayName the text to be displayed on the button
	 */
	public StartupScreenButton(String displayName) {
		DISPLAY_NAME = displayName;
		
		//Necessary for buttons to work on OSX
		setOpaque(true);
		setBorderPainted(false);

		//Set the button to show the text
		setText(DISPLAY_NAME);
		
		//Set Foreground/Background colors
		setForeground(Color.CYAN);
		setBackground(Color.white);

		//Create a new Action Listener
		addActionListener(this);
	}

	/**
	 * Method is run whenever this StartupScreenButton is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		((StartupScreenGUI)getParent().getParent().getParent().getParent()).buttonClick(this);
	}
	
}
