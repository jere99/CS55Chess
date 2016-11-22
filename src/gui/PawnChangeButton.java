/**
 * 
 */
package gui;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** 
 * @author Kevin, Jeremiah
 * 
 * a button on a PawnChangeGUI object
 */
@SuppressWarnings("serial")
public class PawnChangeButton extends JButton implements ActionListener{
	
	/**
	 * the GUI that this button is a part of
	 */
	private PawnChangeGUI gui;
	/**
	 * the text on the button which is the name of a piece
	 */
	private String piece;

	
	/**
	 * Creates a new button and sets instance variables
	 * 
	 * @param gui the GUI that this button is a part of
	 * @param piece the text to be put on the button which is the name of a piece
	 */
	public PawnChangeButton(PawnChangeGUI gui, String piece)
	{
		super();
		this.gui = gui;
		this.piece = piece;
		this.setText(piece);
		
		//creates a new Action Listener
		this.addActionListener(this);
	}

	
	/**
	 * Run when the button is clicked
	 * Promotes the pawn the selected piece
	 */
	public void actionPerformed(ActionEvent e)
	{
		//if "Queen" is clicked
		if(piece.equals("Queen"))
			System.out.println("Promotting to Queen");
		//if "Knight" is clicked
		else if(piece.equals("Knight"))
			System.out.println("Promotting to Knight");
		//if "Rook" is clicked
		else if(piece.equals("Rook"))
			System.out.println("Promotting to Rook");
		//if "Bishop" is clicked
		else if(piece.equals("Bishop"))
			System.out.println("Promotting to Bishop");
		
		//promotes the pawn into the selected piece
		gui.getBoard().pawnChange(gui.getRow(), gui.getCol(), piece, gui.getBoardGUI());
		
		//closes this GUI
		gui.dispose();
	}
}
