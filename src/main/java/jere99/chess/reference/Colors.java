package jere99.chess.reference;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * An enum that stores the available color for the chess pieces.
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
	private final boolean isWhite;
	/**
	 * the icon that is used in the settings screen to select this color
	 */
	private final ImageIcon pawn;
	
	Colors(boolean isWhite) {
		this.isWhite = isWhite;
		pawn = new ImageIcon(Colors.class.getResource(Paths.PIECES + toString().toLowerCase() + "/pawn.png"));
	}
	
	/**
	 * @return {@code true} if this element is for the white player, {@code false} if this element is for the black player
	 */
	public boolean isWhite() {
		return isWhite;
	}
	
	/**
	 * @return the icon that is used in the settings screen to select this color
	 */
	public ImageIcon getPawn() {
		return pawn;
	}
	
	/**
	 * Determines the color of a pawn Icon.
	 * Intended to be used for the SettingsMenuGUI.
	 * 
	 * @param icon the Icon to look for
	 * @return the element of this enum with the same pawn icon, null if no such element exists
	 */
	public static Colors getColorOfPawnIcon(Icon icon) {
		for(Colors color : values())
			if(color.pawn == icon)
				return color;
		return null;
	}
	
}
