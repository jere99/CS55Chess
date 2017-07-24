package jere99.chess.reference;

import javax.swing.ImageIcon;

/**
 * an enum which stores the available color for the chess pieces
 * 
 * @author JeremiahDeGreeff
 */
public enum Colors {
	
	WHITE("white", true),
	CLEAR("clear", true),
	GOLDEN("golden", true),
	BLACK("black", false),
	WALNUT("walnut", false),
	BLUE("blue", false);
	
	/**
	 * a String representation of this element
	 */
	private final String name;
	/**
	 * {@code true} if this element is for the white player,
	 * {@code false} if this element is for the black player
	 */
	private final boolean isWhite;
	/**
	 * the icon that is used in the settings screen to select this color
	 */
	private final ImageIcon pawn;
	
	Colors(String name, boolean isWhite) {
		this.name = name;
		this.isWhite = isWhite;
		this.pawn = new ImageIcon(Colors.class.getResource(Paths.PIECES + name + "/pawn.png"));
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * @return {@code true} if this element is for the white player,
	 * {@code false} if this element is for the black player
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
	 * returns the element corresponding to the passed String
	 * @param name the name of the element
	 * @return the elment corresponding to the name
	 */
	public Colors getColorsFromName(String name) {
		for(Colors c : Colors.values())
			if(c.name == name)
				return c;
		return null;
	}
	
}
