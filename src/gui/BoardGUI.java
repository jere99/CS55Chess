package gui;

import board.*;
import pieces.*;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	 * 2D array of all the buttons on this GUI
	 */
	private BoardButton[][] buttons = new BoardButton[8][8];

	/**
	 * the board that this GUI represents
	 */
	private Board board;
	
	/**
	 * Default constructor for the GUI of the chess board
	 * Creates a window with an 8x8 grid layout
	 * Initializes all 64 of the buttons with correct background colors
	 * @param board the board that this GUI represents
	 */
	public BoardGUI(Board board)
	{		
		//Name the window
		super("Chess");
		
		this.board = board;
		
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
				buttons[i][j] = new BoardButton(i, j, this, board);
				p.add(buttons[i][j]);
			}
		}

		add(p);

		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * @param row the desired row (0 - 7)
	 * @param col the desired column (0 - 7)
	 * @return the button at position [row][col]
	 */
	public BoardButton getButton(int row, int col)
	{
		return buttons[row][col];
	}
	
	/**
	 * moves a rook during a castle
	 * @param rookRow the original row of the rook
	 * @param rookCol the original column of the rook
	 */
	public void castleRook(int rookRow, int rookCol)
	{
		System.out.println("valid move");
		System.out.println("Castling");
		if(rookCol == 0)
		{	
			board.getPiece(rookRow, 7).move(rookRow, 5);
			System.out.println("Setting image at " + rookRow + ", " + 3 + " to image from " + rookRow + ", " + 0);
			getButton(rookRow, 3).setIcon(getButton(rookRow, 0).getIcon());					
			System.out.println("Removing image at " + rookRow + ", " + 0);
			getButton(rookRow, 0).setIcon(null);
			((Rook)(board.getPiece(rookRow, 5))).rookMove();
		}
		if(rookCol == 7)
		{	
			board.getPiece(rookRow, 0).move(rookRow, 3);
			System.out.println("Setting image at " + rookRow + ", " + 5 + " to image from " + rookRow + ", " + 7);
			getButton(rookRow, 5).setIcon(getButton(rookRow, 7).getIcon());
			System.out.println("Removing image at " + rookRow + ", " + 7);
			getButton(rookRow, 7).setIcon(null);
			((Rook)(board.getPiece(rookRow, 3))).rookMove();
		}
	}
}
