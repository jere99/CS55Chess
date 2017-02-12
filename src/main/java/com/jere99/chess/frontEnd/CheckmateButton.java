package main.java.com.jere99.chess.frontEnd;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * a button on a CheckmateGUI object
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class CheckmateButton extends JButton implements ActionListener {
	/**
	 * the GUI that this button is a part of
	 */
	private final CheckmateGUI GUI;
	/**
	 * the text on the button
	 */
	private final String DISPLAY_NAME;
	
	/**
	 * @param displayName the text to be put on the button
	 * @param gui the GUI that this button is a part of
	 */
	public CheckmateButton(String displayName, CheckmateGUI gui) {
		super();
		DISPLAY_NAME = displayName;
		this.setText(displayName);
		GUI = gui;

		//creates a new Action Listener
		this.addActionListener(this);
	}

	/**
	 * @return the text on the button
	 */
	public String getDisplayName() {
		return DISPLAY_NAME;
	}
	
	/**
	 * Runs when the button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		GUI.buttonClick(this);
	}

}
