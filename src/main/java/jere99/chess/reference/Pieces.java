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
	
	private final Class<? extends Piece> TYPE;
	
	private ImageIcon white;
	private ImageIcon black;
	
	Pieces(Class<? extends Piece> type) {
		TYPE = type;
	}
	
	/**
	 * @param piece the piece whose icon will be returned
	 * @return an ImageIcon that represents the passed piece
	 */
	public static ImageIcon getIcon(Piece piece) {
		for(Pieces p : values())
			if(piece.getClass().equals(p.TYPE))
				return piece.isWhite() ? p.white : p.black;
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
