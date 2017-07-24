package jere99.chess.reference;

import javax.swing.ImageIcon;

/**
 * an enum which stores the available color for the chess pieces
 * 
 * @author JeremiahDeGreeff
 */
public enum Colors {
	
	WHITE(true),
	CLEAR(true),
	GOLDEN(true),
	BLACK(false),
	WALNUT(false),
	BLUE(false);
	
	/**
	 * {@code true} if this element is for the white player,
	 * {@code false} if this element is for the black player
	 */
	private final boolean IS_WHITE;
	/**
	 * the icon that is used in the settings screen to select this color
	 */
	private final ImageIcon pawn;
	
	Colors(boolean isWhite) {
		IS_WHITE = isWhite;
		pawn = new ImageIcon(Colors.class.getResource(Paths.PIECES + toString().toLowerCase() + "/pawn.png"));
	}
	
	/**
	 * @return {@code true} if this element is for the white player, {@code false} if this element is for the black player
	 */
	public boolean isWhite() {
		return IS_WHITE;
	}
	
	/**
	 * @return the icon that is used in the settings screen to select this color
	 */
	public ImageIcon getPawn() {
		return pawn;
	}
	
}
