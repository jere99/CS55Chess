package jere99.chess.frontEnd;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/** 
 * a button that represents a square on the chess board of a BoardGUI object
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class BoardButton extends JButton implements ActionListener {
	/**
	 * icon for a white pawn
	 */
	private static ImageIcon wPawn;
	/**
	 * icon for a black pawn
	 */
	private static ImageIcon bPawn;
	/**
	 * icon for a white knight
	 */
	private static ImageIcon wKnight;
	/**
	 * icon for a black knight
	 */
	private static ImageIcon bKnight;
	/**
	 * icon for a white rook
	 */
	private static ImageIcon wRook;
	/**
	 * icon for a black rook
	 */
	private static ImageIcon bRook;
	/**
	 * icon for a white bishop
	 */
	private static ImageIcon wBishop;
	/**
	 * icon for a black bishop
	 */
	private static ImageIcon bBishop;
	/**
	 * icon for a white queen
	 */
	private static ImageIcon wQueen;
	/**
	 * icon for a black queen
	 */
	private static ImageIcon bQueen;
	/**
	 * icon for a white king
	 */
	private static ImageIcon wKing;
	/**
	 * icon for a black king
	 */
	private static ImageIcon bKing;
	
	/**
	 * this buttons's row on the BoardGUI
	 */
	private int row;
	/**
	 * this buttons's column on the BoardGUI
	 */
	private int column;
	/**
	 * the GUI that this button is a part of
	 */
	private BoardGUI gui;
	
	
	/**
	 * Creates a new button and gives it the correct initial values
	 * 
	 * @param row row on GUI
	 * @param col column on GUI
	 * @param gui the GUI that this button is a part of
	 * @param board the board that the GUI represents
	 */
	public BoardButton(int row, int column, BoardGUI gui) {
		super();
		this.row = row;
		this.column = column;
		this.gui = gui;

		//Set Background Colors
		resetColor();
		
		//Necessary for buttons to work on MACs
		setOpaque(true);
		setBorderPainted(false);
		
		//creates a new Action Listener
		this.addActionListener(this);
	}
	
	/**
	 * @return this buttons's row on the BoardGUI
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * @return this buttons's column on the BoardGUI
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * This method is run every time the button is clicked
	 * It calls the buttonClick method on the GUI
	 * @param e the click which triggers the method
	 */
	public void actionPerformed(ActionEvent e) {
		gui.buttonClick(this);
	}
	
	/**
	 * highlights this button
	 */
	public void highlight() {
		setBackground(Color.YELLOW);
	}
	
	/**
	 * Sets the button's background color to the default
	 */
	public void resetColor() {
		if((row + column) % 2 == 0)
			setBackground(Color.WHITE);
		else
			setBackground(Color.BLACK);
	}

	/**
	 * @param id the id of a piece
	 * @return the imageIcon associated with the given id
	 */
	public static ImageIcon idToIcon(String id)
	{
		if(id.equals("wPawn")) return wPawn;
		if(id.equals("bPawn")) return bPawn;
		if(id.equals("wKnight")) return wKnight;
		if(id.equals("bKnight")) return bKnight;
		if(id.equals("wRook")) return wRook;
		if(id.equals("bRook")) return bRook;
		if(id.equals("wBishop")) return wBishop;
		if(id.equals("bBishop")) return bBishop;
		if(id.equals("wQueen")) return wQueen;
		if(id.equals("bQueen")) return bQueen;
		if(id.equals("wKing")) return wKing;
		if(id.equals("bKing")) return bKing;
		return null;
	}
	
	/**
	 * sets the color for the white pieces
	 * @param color the color to change to
	 */
	public static void setWhiteColor(String color)
	{
		wPawn = new ImageIcon(BoardButton.class.getResource("/" + color + "/pawn.png"), "wPawn");
		wKnight = new ImageIcon(BoardButton.class.getResource("/" + color + "/knight.png"), "wKnight");
		wRook = new ImageIcon(BoardButton.class.getResource("/" + color + "/rook.png"), "wRook");
		wBishop = new ImageIcon(BoardButton.class.getResource("/" + color + "/bishop.png"), "wBishop");
		wQueen = new ImageIcon(BoardButton.class.getResource("/" + color + "/queen.png"), "wQueen");
		wKing = new ImageIcon(BoardButton.class.getResource("/" + color + "/king.png"), "wKing");
	}
	
	/**
	 * sets the color for the black pieces
	 * @param color the color to change to
	 */
	public static void setBlackColor(String color)
	{
		bPawn = new ImageIcon(BoardButton.class.getResource("/" + color + "/pawn.png"), "bPawn");
		bKnight = new ImageIcon(BoardButton.class.getResource("/" + color + "/knight.png"), "bKnight");
		bRook = new ImageIcon(BoardButton.class.getResource("/" + color + "/rook.png"), "bRook");
		bBishop = new ImageIcon(BoardButton.class.getResource("/" + color + "/bishop.png"), "bBishop");
		bQueen = new ImageIcon(BoardButton.class.getResource("/" + color + "/queen.png"), "bQueen");
		bKing = new ImageIcon(BoardButton.class.getResource("/" + color + "/king.png"), "bKing");
	}
}
