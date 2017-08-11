package jere99.chess.frontEnd;

import jere99.chess.backEnd.Game;
import jere99.chess.reference.Pieces;

/**
 * a GUI pops up when a pawn is to be promoted
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class PawnChangeGUI extends GenericPanelGUI {
	
	/**
	 * the Game in which this promotion is occurring
	 */
	private final Game GAME;
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
	public PawnChangeGUI(int row, int column, Game game, boolean isWhite) {
		//Name the window
		super("Pawn Promotion");
		
		ROW = row;
		COLUMN = column;
		GAME = game;
		
		//Set the size of the window
		setSize(352, 112);
		
		//Create the necessary buttons and add them to the JPanel
		for(int i = 1; i <= 4; i++)
			PANEL.add(new PawnChangeButton(Pieces.values()[i], isWhite));
		
		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * This method is run every time a button is clicked
	 * @param b the button that was clicked
	 */
	@Override
	public void buttonClick(GenericButton b) {
		PawnChangeButton button = (PawnChangeButton) b;
		System.out.println("Promoting to " + button.PIECE.toString().toLowerCase());
		
		//promote the pawn into the selected piece
		GAME.pawnChange(ROW, COLUMN, button.PIECE);
		
		//close this GUI
		dispose();
	}
	
	/**
	 * a button on a PawnChangeGUI object
	 * 
	 * @author Kevin
	 * @author JeremiahDeGreeff
	 */
	private class PawnChangeButton extends GenericPanelButton {
		
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
		}
		
	}
	
}
