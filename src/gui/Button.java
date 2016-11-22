/**
 * 
 */
package gui;
import board.*;
import pieces.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

/** 
 * @author Kevin, Jeremiah
 * 
 * a button that represents a square on the chess board of a BoardGUI object
 */
@SuppressWarnings("serial")
public class Button extends JButton implements ActionListener{
	
	/**
	 * icon for a white pawn
	 */
	private static ImageIcon wPawn = new ImageIcon(Button.class.getResource("white_p.png"), "wPawn");
	/**
	 * icon for a black pawn
	 */
	private static ImageIcon bPawn = new ImageIcon(Button.class.getResource("black_p.png"), "bPawn");
	/**
	 * icon for a white knight
	 */
	private static ImageIcon wKnight = new ImageIcon(Button.class.getResource("white_n.png"), "wKnight");
	/**
	 * icon for a black knight
	 */
	private static ImageIcon bKnight = new ImageIcon(Button.class.getResource("black_n.png"), "bKnight");
	/**
	 * icon for a white rook
	 */
	private static ImageIcon wRook = new ImageIcon(Button.class.getResource("white_r.png"), "wRook");
	/**
	 * icon for a black rook
	 */
	private static ImageIcon bRook = new ImageIcon(Button.class.getResource("black_r.png"), "bRook");
	/**
	 * icon for a white bishop
	 */
	private static ImageIcon wBishop = new ImageIcon(Button.class.getResource("white_b.png"), "wBishop");
	/**
	 * icon for a black bishop
	 */
	private static ImageIcon bBishop = new ImageIcon(Button.class.getResource("black_b.png"), "bBishop");
	/**
	 * icon for a white queen
	 */
	private static ImageIcon wQueen = new ImageIcon(Button.class.getResource("white_q.png"), "wQueen");
	/**
	 * icon for a black queen
	 */
	private static ImageIcon bQueen = new ImageIcon(Button.class.getResource("black_q.png"), "bQueen");
	/**
	 * icon for a white king
	 */
	private static ImageIcon wKing = new ImageIcon(Button.class.getResource("white_k.png"), "wKing");
	/**
	 * icon for a black king
	 */
	private static ImageIcon bKing = new ImageIcon(Button.class.getResource("black_k.png"), "bKing");
	
	
	/**
	 * whether or not a button is has already been selected
	 * false if a button has been selected, true otherwise
	 */
	private static boolean firstClick = true;
	/**
	 * the row of the button that was selected on the first click
	 */
	private static int fRow;
	/**
	 * the column of the button that was selected on the first click
	 */
	private static int fCol;
	/**
	 * the piece associated with the button that was selected on the first click
	 */
	private static Piece curPiece;
	
	
	/**
	 * this buttons's row on the BoardGUI
	 */
	private int row;
	/**
	 * this buttons's column on the BoardGUI
	 */
	private int col;
	/**
	 * the GUI that this button is a part of
	 */
	private BoardGUI gui;
	/**
	 * the board that the GUI represents
	 */
	private Board board;
	

	/**
	 * Creates a new button and gives it the correct initial values
	 * 
	 * @param row row on GUI
	 * @param col column on GUI
	 * @param gui the GUI that this button is a part of
	 * @param board the board that the GUI represents
	 */
	public Button(int row, int col, BoardGUI gui, Board board)
	{
		super();
		this.row = row;
		this.col = col;
		this.board = board;
		this.gui = gui;

		//Set Background Colors
		resetColor();
		
		//Necessary for buttons to work on MACs
		setOpaque(true);
		setBorderPainted(false);
		
		//Set Default Pieces
		if(row == 1)
			setIcon(bPawn);
		else if (row == 6)
			setIcon(wPawn);
		else if (row == 0)
		{
			if (col == 0 || col == 7)
				setIcon(bRook);
			else if (col == 1 || col == 6)
				setIcon(bKnight);
			else if (col == 2 || col == 5)
				setIcon(bBishop);
			else if (col == 3)
				setIcon(bQueen);
			else if (col == 4)
				setIcon(bKing);
		}
		else if(row == 7)
		{
			if (col == 0 || col == 7)
				setIcon(wRook);
			else if (col == 1 || col == 6)
				setIcon(wKnight);
			else if (col == 2 || col == 5)
				setIcon(wBishop);
			else if (col == 3)
				setIcon(wQueen);
			else if (col == 4)
				setIcon(wKing);
		}
		
		//creates a new Action Listener
		this.addActionListener(this);
	}

	
	/**
	 * This method is run every time the button is clicked
	 * If move selected is valid, the move is made and the images are updated
	 * If move is invalid, no changes are made to the board
	 * 
	 * @param e the click which triggers causes the method
	 */
	public void actionPerformed(ActionEvent e)
	{
		//If this is the first click of the selection
		if(firstClick)
		{
			//Prints the coordinates of the selected button
			System.out.println("First Click: " + row + ", " + col);
			
			
			//If piece exists
			if(board.getPiece(row, col) != null)
			{
				//Checks color of piece
				if(board.getPiece(row, col).isWhite() == board.isWhiteTurn())
				{
					//Saves the row, column, and piece of the first click
					fRow = row;
					fCol = col;
					curPiece = board.getPiece(fRow, fCol);
					
					//Highlights the square that is selected
					setBackground(Color.YELLOW);
					
					//First click is now false; next time the method is run, it runs second click
					firstClick = false;
				}
				//If color of piece is invalid
				else
				{
					System.out.println("wrong color");
				}
			}
			//If square is empty
			else
			{
				System.out.println("empty square");
			}	
		}
		//If this is the second click of the selection
		else
		{
			
			//Prints the coordinates of the selected button
			System.out.println("Second Click: " + row + ", " + col);
			
			//If the second square clicked is a valid spot for the piece to move to
			if (curPiece.move(row, col))
			{
				System.out.println("valid move");
				
				//Moves the image of the button to the new spot
				System.out.println("Setting image at " + row + ", " + col + " to image from " + fRow + ", " + fCol);
				setIcon(gui.getButton(fRow, fCol).getIcon());
				
				//Removes the image at the old button
				System.out.println("Removing image at " + fRow + ", " + fCol);
				gui.getButton(fRow, fCol).setIcon(null);
				
				Piece moved = board.getPiece(row, col);
				
				//Prevents the piece from being involved in castling later
				if(moved instanceof King && !((King)moved).hasMoved())
					((King) board.getPiece(row, col)).kingMove();
				if(moved instanceof Rook && !((Rook)moved).hasMoved())
					((Rook) board.getPiece(row, col)).rookMove();
				
				//Tests if the move was a castle and if it was moves the rook
				if(moved instanceof King && fCol == 4 && col == 6)
					gui.castleRook(row, 7);
				if(moved instanceof King && fCol == 4 && col == 2)
					gui.castleRook(row, 0);
				
				
				//Creates a GUI for pawn promotion
				if(moved instanceof Pawn && (row == 0 || row == 7))
					new PawnChangeGUI(row, col, board, gui);
				//Tests if the move has put the opposing king in check
				else
					detectCheck();
			}
			//If the second square clicked is not a valid spot for the piece to move to
			else
				System.out.println("invalid move");
			
			//Resets the background color of the first button
			gui.getButton(fRow, fCol).resetColor();
			
			//Resets the first click
			firstClick = true;
		}
	}
	
	/**
	 * Sets the button's background color to the default
	 */
	public void resetColor()
	{
		if((row + col) % 2 == 0)
			setBackground(Color.WHITE);
		else
			setBackground(Color.BLACK);
	}

	/**
	 * @param name the name of the desired piece
	 * @param isWhite the color of the desired piece: true if white, false if black
	 * @return the imageIcon associated with the given input
	 */
	public static ImageIcon stringToIcon(String name, boolean isWhite)
	{
		if(name.equals("Queen"))
			if(isWhite)
				return wQueen;
			else
				return bQueen;
		else if(name.equals("Knight"))
			if(isWhite)
				return wKnight;
			else
				return bKnight;
		else if(name.equals("Rook"))
			if(isWhite)
				return wRook;
			else
				return bRook;
		else if(name.equals("Bishop"))
			if(isWhite)
				return wBishop;
			else
				return bBishop;
		return null;
	}
	
	public void detectCheck()
	{
		if(board.getPiece(row, col).isWhite() && board.kingChecked(board.getBKing()))
		{
			System.out.println("The Black king is in check!");
			if(board.checkmate(board.getBKing()))
			{
				System.out.println("Checkmate!\nWhite Wins");
				new CheckmateGUI(gui, "White");
				return;
			}
		}
		else if(!board.getPiece(row, col).isWhite() && board.kingChecked(board.getWKing()))
		{
			System.out.println("The White king is in check!");
			if(board.checkmate(board.getWKing()))
			{
				System.out.println("Checkmate!\nBlack Wins");
				new CheckmateGUI(gui, "Black");
				return;
			}
		}
		board.turnOver(); //Switches to the next turn
	}
	
	/**
	 * changes the color for the white pieces
	 * @param color the color to change to
	 */
	public static void setWColor(String color)
	{
		System.out.println(color);
		wPawn = new ImageIcon(Button.class.getResource(color + "_p.png"), "wPawn");
		wKnight = new ImageIcon(Button.class.getResource(color + "_n.png"), "wKnight");
		wRook = new ImageIcon(Button.class.getResource(color + "_r.png"), "wRook");
		wBishop = new ImageIcon(Button.class.getResource(color + "_b.png"), "wBishop");
		wQueen = new ImageIcon(Button.class.getResource(color + "_q.png"), "wQueen");
		wKing = new ImageIcon(Button.class.getResource(color + "_k.png"), "wKing");
	}
	
	/**
	 * changes the color for the black pieces
	 * @param color the color to change to
	 */
	public static void setBColor(String color)
	{
		bPawn = new ImageIcon(Button.class.getResource(color + "_p.png"), "bPawn");
		bKnight = new ImageIcon(Button.class.getResource(color + "_n.png"), "bKnight");
		bRook = new ImageIcon(Button.class.getResource(color + "_r.png"), "bRook");
		bBishop = new ImageIcon(Button.class.getResource(color + "_b.png"), "bBishop");
		bQueen = new ImageIcon(Button.class.getResource(color + "_q.png"), "bQueen");
		bKing = new ImageIcon(Button.class.getResource(color + "_k.png"), "bKing");
	}
}
