package frontEnd;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import backEnd.Game;

/**
 * a GUI that represents a chess board
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class BoardGUI extends JFrame{

	/**
	 * the main display component of the GUI
	 */
	private JPanel p = new JPanel();
	/**
	 * the game that this GUI represents
	 */
	private final Game GAME;
	/**
	 * 2D array of all the buttons on this GUI
	 */
	private BoardButton[][] buttons = new BoardButton[8][8];
	/**
	 * the first button that the user clicks
	 */
	private BoardButton firstClick;
	/**
	 * true if no square has been selected yet, false otherwise
	 */
	private boolean isFirstClick = true;

	
	/**
	 * Default constructor for the GUI of the chess board
	 * Creates a window with an 8x8 grid layout
	 * Initializes all 64 of the buttons with correct background colors
	 * @param board the board that this GUI represents
	 */
	public BoardGUI(Game game)
	{		
		//Name the window
		super("Chess");
		
		GAME = game;
		
		//Sets the size, (width, height)
		setSize(800,800);

		//Allows window to be resized
		setResizable(false);

		//Default program end
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Creating new grid layout
		p.setLayout(new GridLayout(8,8));

		//Add the buttons
		for (int i = 0; i < 8; i ++)
		{
			for(int j = 0; j < 8; j ++)
			{
				buttons[i][j] = new BoardButton(i, j, this);
				updateSquare(i, j);
				p.add(buttons[i][j]);
			}
		}

		add(p);

		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * @return the board this GUI represents
	 */
	public Game getGame()
	{
		return GAME;
	}
	
	/**
	 * @param row the desired row (0 - 7)
	 * @param col the desired column (0 - 7)
	 * @return the button at position [row][col]
	 */
	public BoardButton getButtonAt(int row, int col)
	{
		return buttons[row][col];
	}
	public void buttonClick(BoardButton button)
	{
		if(isFirstClick)
			firstClick(button);
		else
			secondClick(button);
	}
	
	/**
	 * if the first click is valid stores it and prepares for second click
	 * @param button the button which was clicked
	 * @return if the click is valid
	 */
	private boolean firstClick(BoardButton button)
	{
		//Prints the coordinates of the selected button
		System.out.println("First Click: " + button.getRow() + ", " + button.getColumn());
		
		//If piece exists
		if(GAME.getBoard().getPiece(button.getRow(), button.getColumn()) != null)
		{
			//If piece is correct color
			if(GAME.getBoard().getPiece(button.getRow(), button.getColumn()).isWhite() == GAME.isWhiteTurn())
			{
				//valid click: the next click will run the second click method
				firstClick = button;
				firstClick.highlight();
				isFirstClick = false;
				return true;
			}
			//If color of piece is invalid
			else
			{
				System.out.println("wrong color");
				return false;
			}
		}
		//If square is empty
		else
		{
			System.out.println("empty square");
			return false;
		}
	}
	
	/**
	 * moves the piece from the first click to the square of the second click if it is a valid move
	 * @param button the button which was clicked
	 */
	private void secondClick(BoardButton button)
	{
		//Prints the coordinates of the selected button
		System.out.println("Second Click: " + button.getRow() + ", " + button.getColumn());
		
		//If the second square clicked is a valid spot for the piece from the first click to move to
		if (GAME.getBoard().getPiece(firstClick.getRow(), firstClick.getColumn()).move(button.getRow(), button.getColumn()))
		{
			System.out.println("valid move");
			//Switches to the next turn
			GAME.nextTurn();
		}
		//If the second square clicked is not a valid spot for the piece from the first click to move to
		else
		{	
			System.out.println("invalid move");
		}
		
		//Reset the background color of the first button
		firstClick.resetColor();

		//Reset the first click
		isFirstClick = true;
	}
	
	/**
	 * updates the icon of the square at the passed coordinates based on what is currently present on the board
	 * @param row the row of the square to be updated
	 * @param column the column of the square to be updated
	 */
	public void updateSquare(int row, int column)
	{
		System.out.println("Updating image at: " + row + ", " + column);
		String id = "";
		if(GAME.getBoard().getPiece(row, column) != null)
			id = GAME.getBoard().getPiece(row, column).getPieceID();
		buttons[row][column].setIcon(BoardButton.idToIcon(id));
	}
}
