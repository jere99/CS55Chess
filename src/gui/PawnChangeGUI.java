package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.ImageIcon;
//import javax.swing.JLabel;
import board.*;

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
	private Board board;
	/**
	 * the row where this promotion is occurring
	 */
	private int row;
	/**
	 * the column where this promotion is occurring
	 */
	private int col;
	/**
	 * the GUI that represents the board
	 */
	private BoardGUI boardGUI;
	
	
	/**
	 * creates a pop-up window which asks the user what piece they would like to promote their pawn into
	 * 
	 * @param row the row where this promotion is occurring
	 * @param col the column where this promotion is occurring
	 * @param board the board on which this promotion is occurring
	 * @param boardGUI the GUI that represents the board
	 */
	public PawnChangeGUI(int row, int col, Board board, BoardGUI boardGUI)
	{
		//Name the window
		super("Pawn Promotion");

		//saves the location of this pawnChange
		this.row = row;
		this.col = col;
		this.board = board;
		this.boardGUI = boardGUI;
		
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
	 * @return the row where this promotion is occurring
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * @return the column where this promotion is occurring
	 */
	public int getCol()
	{
		return col;
	}
	
	/**
	 * @return the board on which this promotion is occurring
	 */
	public Board getBoard()
	{
		return board;
	}
	
	/**
	 * @return the GUI that represents the board
	 */
	public BoardGUI getBoardGUI()
	{
		return boardGUI;
	}
}
