package jere99.chess.reference;

import javax.swing.ImageIcon;

import jere99.chess.backEnd.pieces.*;

/**
 * an enum which represents all the pieces in a chess game
 * @author JeremiahDeGreeff
 */
public enum Pieces {
	
	KING(King.class),
	QUEEN(Queen.class),
	ROOK(Rook.class),
	BISHOP(Bishop.class),
	KNIGHT(Knight.class),
	PAWN(Pawn.class);
	
	/**
	 * the class from which an object of this piece is created
	 */
	private final Class<? extends Piece> TYPE;
	/**
	 * the current ImageIcon for a white version of this piece
	 */
	private ImageIcon white;
	/**
	 * the current ImageIcon for a black version of this piece
	 */
	private ImageIcon black;
	
	
	Pieces(Class<? extends Piece> type) {
		TYPE = type;
	}
	
	/**
	 * @return the class from which an object of this piece is created
	 */
	public Class<? extends Piece> getType() {
		return TYPE;
	}
	
	/**
	 * @param isWhite {@code true} for the white variant, {@code false} for the black variant
	 * @return the current ImageIcon for this piece
	 */
	public ImageIcon getIcon(boolean isWhite) {
		return isWhite ? white : black;
	}
	
	/**
	 * @param piece the piece whose icon will be returned
	 * @return an ImageIcon that represents the passed piece
	 */
	public static ImageIcon getIcon(Piece piece) {
		for(Pieces p : values())
			if(piece.getClass().equals(p.TYPE))
				return p.getIcon(piece.isWhite());
		return null;
	}
	
	/**
	 * loads all the piece ImageIcons based on the settings
	 */
	public static void loadIcons() {
		System.out.println("Loading icons");
		for(Pieces p : values()) {
			p.white = new ImageIcon(Pieces.class.getResource(Paths.PIECES + Settings.getWhiteColor() + "/" + p.toString().toLowerCase() + ".png"), "white " + p.toString().toLowerCase());
			p.black = new ImageIcon(Pieces.class.getResource(Paths.PIECES + Settings.getBlackColor() + "/" + p.toString().toLowerCase() + ".png"), "black " + p.toString().toLowerCase());
		}
	}
	
}
