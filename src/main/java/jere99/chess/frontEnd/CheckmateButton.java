package jere99.chess.frontEnd;

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
	 * the text on the button
	 */
	protected final String DISPLAY_NAME;
	
	/**
	 * @param displayName the text to be put on the button
	 */
	protected CheckmateButton(String displayName) {
		DISPLAY_NAME = displayName;
		
		setText(displayName);

		//creates a new Action Listener
		this.addActionListener(this);
	}
	
	/**
	 * Runs when the button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		((CheckmateGUI) getParent().getParent().getParent().getParent().getParent()).buttonClick(this);
	}

}
