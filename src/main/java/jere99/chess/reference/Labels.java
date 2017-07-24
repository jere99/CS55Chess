package jere99.chess.reference;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * a class which stores the Labels used by the project
 * 
 * @author JeremiahDeGreeff
 */
public class Labels {
	
	/**
	 * the main display component of the StartupScreenGUI: holds the background image
	 */
	public static final JLabel STARTUP_SCREEN = new JLabel(new ImageIcon(Labels.class.getResource("/startup_screen.png")));
	/**
	 * the main display component of the SettingsMenuGUI: holds the background image
	 */
	public static final JLabel SETTINGS_MENU = new JLabel(new ImageIcon(Labels.class.getResource("/settings_menu.png")));
	
}
