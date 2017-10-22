package jere99.chess.frontEnd;

import java.awt.event.ActionEvent;

import jere99.chess.backEnd.Game;
import jere99.chess.reference.Pieces;

/**
 * A GUI that pops up when a pawn is to be promoted.
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class PawnPromotionGUI extends GenericPanelGUI {
	
	/**
	 * The width of the frame.
	 */
	private static final int FRAME_WIDTH = 352;
	/**
	 * The height of the frame.
	 */
	private static final int FRAME_HEIGHT = 112;
	
	/**
	 * The Game in which this promotion is occurring.
	 */
	private final Game game;
	/**
	 * The row where this promotion is occurring.
	 */
	private final int row;
	/**
	 * The column where this promotion is occurring.
	 */
	private final int column;
	
	/**
	 * @param row the row where this promotion is occurring
	 * @param column the column where this promotion is occurring
	 * @param game the game in which this promotion is occurring
	 */
	public PawnPromotionGUI(int row, int column, Game game) {
		//Name the window
		super("Pawn Promotion");
		
		this.row = row;
		this.column = column;
		this.game = game;
		
		//Set the size of the window
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		//Create the necessary buttons and add them to the JPanel
		for(int i = 1; i <= 4; i++)
			panel.add(new GenericButton(Pieces.values()[i].getIcon(game.isWhiteTurn()), this));
		
		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * Promotes the pawn to the selected piece.
	 * Will be called every time a button on this PawnPromotionGUI is clicked.
	 * 
	 * @param e the ActionEvent corresponding to a button click on this GUI
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Pieces piece = Pieces.getPieceOfIcon(((GenericButton) e.getSource()).getIcon());
		System.out.println("Promoting to " + piece.toString().toLowerCase());
		
		//promote the pawn into the selected piece
		game.pawnPromotion(row, column, piece);
		
		//close this GUI
		dispose();
	}
	
}
