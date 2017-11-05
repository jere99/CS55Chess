package jere99.chess.reference;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * A class that stores all the Labels used by the project.
 * 
 * @author JeremiahDeGreeff
 */
public class Labels {
	
	/**
	 * the main display component of the StartupScreenGUI: holds the background image
	 */
	public static final JLabel STARTUP_SCREEN = new JLabel(new ImageIcon(Labels.class.getResource(Paths.STARTUP_SCREEN_LABEL)));
	/**
	 * the main display component of the SettingsMenuGUI: holds the background image
	 */
	public static final JLabel SETTINGS_MENU = new JLabel(new ImageIcon(Labels.class.getResource(Paths.SETINGS_MENU_LABEL)));
	
}
