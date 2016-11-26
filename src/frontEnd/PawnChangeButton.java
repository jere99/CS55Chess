package frontEnd;

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
	 * the GUI that this button is a part of
	 */
	private final PawnChangeGUI GUI;
	/**
	 * the text on the button which is the name of the piece it corresponds to
	 */
	private final String PIECE_NAME;

	
	/**
	 * @param gui the GUI that this button is a part of
	 * @param piece the text to be put on the button which is the name of a piece
	 */
	public PawnChangeButton(PawnChangeGUI gui, String piece) {
		super();
		GUI = gui;
		PIECE_NAME = piece;
		this.setText(PIECE_NAME);
		
		//creates a new Action Listener
		this.addActionListener(this);
	}
	
	/**
	 * @return the text on the button which is the name of the piece it corresponds to
	 */
	public String getPieceName() {
		return PIECE_NAME;
	}
	
	/**
	 * Runs when the button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		GUI.buttonClick(this);
	}
}
