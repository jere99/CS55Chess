package jere99.chess.frontEnd;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jere99.chess.backEnd.Board;

/**
 * a GUI pops up when a pawn is to be promoted
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class PawnChangeGUI extends JFrame{
	
	/**
	 * the main display component of the GUI
	 */
	private JPanel p = new JPanel();
	/**
	 * array of all the buttons on this GUI
	 */
	private PawnChangeButton[] buttons;
	/**
	 * the board on which this promotion is occurring
	 */
	private final Board BOARD;
	/**
	 * the row where this promotion is occurring
	 */
	private final int ROW;
	/**
	 * the column where this promotion is occurring
	 */
	private final int COLUMN;
	
	
	/**
	 * creates a pop-up window which asks the user what piece they would like to promote their pawn into
	 * @param row the row where this promotion is occurring
	 * @param column the column where this promotion is occurring
	 * @param board the board on which this promotion is occurring
	 */
	public PawnChangeGUI(int row, int column, Board board) {
		//Name the window
		super("Pawn Promotion");

		//saves the location of this pawnChange
		ROW = row;
		COLUMN = column;
		BOARD = board;
		
		//Sets the size of the window
		setSize(400,75);

		//Allows window to be resized
		setResizable(false);

		//Default program end
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Create the necessary buttons
		buttons = new PawnChangeButton[4];
		buttons[0] = new PawnChangeButton(this, "Knight");
		buttons[1] = new PawnChangeButton(this, "Bishop");
		buttons[2] = new PawnChangeButton(this, "Rook");
		buttons[3] = new PawnChangeButton(this, "Queen");

		//Add the necessary buttons to the JPanel
		for (PawnChangeButton e : buttons)
			p.add(e);
		
		//Implements the JPanel to the GUI
		add(p);
		
		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * This method is run every time a button is clicked
	 * @param button the button that was clicked
	 */
	public void buttonClick(PawnChangeButton button) {
		//if "Queen" is clicked
		if(button.getPieceName().equals("Queen"))
			System.out.println("Promotting to Queen");
		//if "Knight" is clicked
		else if(button.getPieceName().equals("Knight"))
			System.out.println("Promotting to Knight");
		//if "Rook" is clicked
		else if(button.getPieceName().equals("Rook"))
			System.out.println("Promotting to Rook");
		//if "Bishop" is clicked
		else if(button.getPieceName().equals("Bishop"))
			System.out.println("Promotting to Bishop");

		//promote the pawn into the selected piece
		BOARD.pawnChange(ROW, COLUMN, button.getPieceName());

		//close this GUI
		dispose();
	}
}
