package jere99.chess.frontEnd;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * a button on a PawnChangeGUI object
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class PawnChangeButton extends JButton implements ActionListener {
	
	/**
	 * the text on the button which is the name of the piece it corresponds to
	 */
	protected final String DISPLAY_NAME;
	
	/**
	 * creates a button as part of a PawnChangeGUI object
	 * @param gui the GUI that this button is a part of
	 * @param piece the text to be put on the button which is the name of a piece
	 */
	public PawnChangeButton(String display_name) {
		DISPLAY_NAME = display_name;
		
		setText(DISPLAY_NAME);
		
		//creates a new Action Listener
		addActionListener(this);
	}
	
	/**
	 * Runs when the button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		((PawnChangeGUI) getParent().getParent().getParent().getParent().getParent()).buttonClick(this);
	}
	
}
