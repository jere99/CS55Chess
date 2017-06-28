package main.java.jere99.chess.frontEnd;

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
	 * The GUI that this button is a part of
	 */
	private final StartupScreenGUI GUI;
	/**
	 * text displayed on the button, also used to identify the button
	 */
	private final String DESCRIPTION;

	public StartupScreenButton(StartupScreenGUI gui, String desc)
	{
		GUI = gui;
		DESCRIPTION = desc;
		
		//Necessary for buttons to work on OSX
		setOpaque(true);
		setBorderPainted(false);

		//Set the button to show the text
		setText(desc);
		//Sets Foreground/Background colors
		setForeground(Color.CYAN);
		setBackground(Color.white);

		//Creates a new Action Listener
		this.addActionListener(this);
	}
	
	/**
	 * @return the text displayed on the button to identify the button
	 */
	public String getDescription() {
		return DESCRIPTION;
	}

	/**
	 * Method is run whenever a button on the Startup Screen is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		GUI.buttonClick(this);
	}
}
