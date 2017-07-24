package jere99.chess.frontEnd;

import javax.swing.JButton;

import jere99.chess.reference.Pieces;

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
	protected final Pieces PIECE;
	
	/**
	 * creates a button as part of a PawnChangeGUI object
	 * @param gui the GUI that this button is a part of
	 * @param piece the text to be put on the button which is the name of a piece
	 */
	public PawnChangeButton(Pieces piece, boolean isWhite) {
		PIECE = piece;
		
		setIcon(PIECE.getIcon(isWhite));
		
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
