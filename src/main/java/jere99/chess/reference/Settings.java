package jere99.chess.reference;

/**
 * a class which stores the selected colors for both players
 * 
 * @author JeremiahDeGreeff
 */
public class Settings {

	/**
	 * the color selected for the white player
	 * default is {@code Colors.WHITE}
	 */
	private static Colors whiteColor = Colors.WHITE;
	/**
	 * the color selected for the black player
	 * default is {@code Colors.BLACK}
	 */
	private static Colors blackColor = Colors.BLACK;

	/**
	 * @return the color selected for the white player
	 */
	public static Colors getWhiteColor() {
		return whiteColor;
	}
	/**
	 * @return the color selected for the black player
	 */
	public static Colors getBlackColor() {
		return blackColor;
	}
	/**
	 * @param isWhite {@code true} if the color selected for the white player should be returned,
	 * {@code false} if the color selected for the black player should be returned
	 * @return the color selected for the player corresponding to the passed boolean
	 */
	public static Colors getColor(boolean isWhite) {
		return isWhite ? whiteColor : blackColor;
	}
	
	/**
	 * Changes the color for whichever team the color passed belongs to
	 * @param color the color to change to
	 */
	public static void update(Colors color) {
		if(color.isWhite())
			whiteColor = color;
		else
			blackColor = color;
				
	}
	
}
