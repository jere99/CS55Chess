package jere99.chess.frontEnd;

import jere99.chess.backEnd.Game;
import jere99.chess.reference.Pieces;

/**
 * A GUI that pops up when a pawn is to be promoted.
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class PawnChangeGUI extends GenericPanelGUI {
	
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
	public PawnChangeGUI(int row, int column, Game game) {
		//Name the window
		super("Pawn Promotion");
		
		this.row = row;
		this.column = column;
		this.game = game;
		
		//Set the size of the window
		setSize(352, 112);
		
		//Create the necessary buttons and add them to the JPanel
		for(int i = 1; i <= 4; i++)
			panel.add(new PawnChangeButton(Pieces.values()[i], game.isWhiteTurn()));
		
		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * Promotes the pawn to the selected piece.
	 * Should be called every time a button on this PawnChangeGUI is clicked.
	 * 
	 * @param b the button that was clicked
	 */
	@Override
	public void buttonClick(GenericButton b) {
		PawnChangeButton button = (PawnChangeButton) b;
		System.out.println("Promoting to " + button.piece.toString().toLowerCase());
		
		//promote the pawn into the selected piece
		game.pawnChange(row, column, button.piece);
		
		//close this GUI
		dispose();
	}
	
	/**
	 * A button on a PawnChangeGUI object.
	 * 
	 * @author Kevin
	 * @author JeremiahDeGreeff
	 */
	private class PawnChangeButton extends GenericPanelButton {
		
		/**
		 * The text on the button which is the name of the piece it corresponds to.
		 */
		private final Pieces piece;
		
		/**
		 * @param piece the text to be put on the button which is the name of a piece
		 * @param isWhite true if the piece being promoted is white, false if it is black
		 */
		private PawnChangeButton(Pieces piece, boolean isWhite) {
			this.piece = piece;
			
			setIcon(piece.getIcon(isWhite));
		}
		
	}
	
}
