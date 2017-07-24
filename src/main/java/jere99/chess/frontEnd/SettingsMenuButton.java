package jere99.chess.frontEnd;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import jere99.chess.reference.Colors;

/**
 * Buttons that appear on the SettingsMenuGUI
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class SettingsMenuButton extends JButton implements ActionListener {
	
	/**
	 * the color of the piece which this button represents
	 */
	private final Colors COLOR;
	
	/**
	 * Creates a button for the SettingsMenuGUI
	 * @param color the color which clicking this button would select,
	 * null color indicates the Main Menu button
	 */
	protected SettingsMenuButton(Colors color) {
		COLOR = color;
		
		//Necessary for buttons to work on OSX
		setOpaque(true);
		setBorderPainted(false);
		
		//Set the background to white
		setBackground(Color.white);
		
		//Set the text and color for the button that returns to the Startup Screen
		if(COLOR == null) {
			setText("Main Menu");
			setForeground(Color.cyan);
		} else
		//Set the necessary images to the correct buttons
			setIcon(COLOR.getPawn());
		
		//Create a new Action Listener
		addActionListener(this);
	}
	
	/**
	 * @return the text displayed on the button to identify the button
	 */
	protected Colors getColor() {
		return COLOR;
	}
	
	/**
	 * Method is run whenever this SettingsMenuButton is clicked
	 */
	public void actionPerformed(ActionEvent e)  {
		((SettingsMenuGUI)getParent().getParent().getParent().getParent()).buttonClick(this);
	}
	
}
