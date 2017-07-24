package jere99.chess.reference;

import javax.swing.ImageIcon;

/**
 * a class which stores the icons used by the project
 * 
 * @author JeremiahDeGreeff
 */
public class Icons {

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
	 * accessor method for the icons stored in this class
	 * @param id the id of a piece
	 * @return the imageIcon associated with the given id
	 */
	public static ImageIcon getIcon(String id) {
		switch(id) {
		case "wPawn": return wPawn;
		case "bPawn": return bPawn;
		case "wKnight": return wKnight;
		case "bKnight": return bKnight;
		case "wRook": return wRook;
		case "bRook": return bRook;
		case "wBishop": return wBishop;
		case "bBishop": return bBishop;
		case "wQueen": return wQueen;
		case "bQueen": return bQueen;
		case "wKing": return wKing;
		case "bKing": return bKing;
		default: return null;
		}
	}
	
	/**
	 * loads all the icons based on the settings
	 */
	public static void loadIcons() {
		String wColor = Settings.getWhiteColor().toString();
		String bColor = Settings.getBlackColor().toString();

		wPawn = new ImageIcon(Icons.class.getResource(Paths.PIECES + wColor + "/pawn.png"), "wPawn");
		wKnight = new ImageIcon(Icons.class.getResource(Paths.PIECES + wColor + "/knight.png"), "wKnight");
		wRook = new ImageIcon(Icons.class.getResource(Paths.PIECES + wColor + "/rook.png"), "wRook");
		wBishop = new ImageIcon(Icons.class.getResource(Paths.PIECES + wColor + "/bishop.png"), "wBishop");
		wQueen = new ImageIcon(Icons.class.getResource(Paths.PIECES + wColor + "/queen.png"), "wQueen");
		wKing = new ImageIcon(Icons.class.getResource(Paths.PIECES + wColor + "/king.png"), "wKing");

		bPawn = new ImageIcon(Icons.class.getResource(Paths.PIECES + bColor + "/pawn.png"), "bPawn");
		bKnight = new ImageIcon(Icons.class.getResource(Paths.PIECES + bColor + "/knight.png"), "bKnight");
		bRook = new ImageIcon(Icons.class.getResource(Paths.PIECES + bColor + "/rook.png"), "bRook");
		bBishop = new ImageIcon(Icons.class.getResource(Paths.PIECES + bColor + "/bishop.png"), "bBishop");
		bQueen = new ImageIcon(Icons.class.getResource(Paths.PIECES + bColor + "/queen.png"), "bQueen");
		bKing = new ImageIcon(Icons.class.getResource(Paths.PIECES + bColor + "/king.png"), "bKing");
	}
	
}
