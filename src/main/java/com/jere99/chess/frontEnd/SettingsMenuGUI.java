package main.java.com.jere99.chess.frontEnd;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The GUI for the settings menu
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
public class SettingsMenuGUI {
	/**
	 * the main display component of the GUI: holds the background image
	 */
	private JLabel myLabel = new JLabel(new ImageIcon(this.getClass().getResource("/resources/settings_menu.png")));
	/**
	 * the JFrame for the window
	 */
	protected static JFrame frame;
	/**
	 * the button to return to the main menu
	 */
	private static SettingsMenuButton mainMenu = new SettingsMenuButton("Main Menu");
	/**
	 * the button to select white
	 */
	private static SettingsMenuButton white = new SettingsMenuButton("White");
	/**
	 * the button to select golden
	 */
	private static SettingsMenuButton golden = new SettingsMenuButton("Golden");
	/**
	 * the button to select clear
	 */
	private static SettingsMenuButton clear = new SettingsMenuButton("Clear");
	/**
	 * the button to select blue
	 */
	private static SettingsMenuButton blue = new SettingsMenuButton("Blue");
	/**
	 * the button to select black
	 */
	private static SettingsMenuButton black = new SettingsMenuButton("Black");
	/**
	 * the button to select walnut
	 */
	private static SettingsMenuButton walnut = new SettingsMenuButton("Walnut");
	
	/**
	 * Creates window for changing settings
	 */
	public SettingsMenuGUI() {
		//Name the window
		frame = new JFrame("Color Scheme");

		//Sets the size, (width, height)
		frame.setSize(700,700);

		//Allows window to be resized
		frame.setResizable(false);

		//Background Image
		frame.setContentPane(myLabel);

		//Setting JFrame to Absolute Layout so that elements may be positioned
		frame.setLayout(null);

		//Customizing the buttons' locations & sizes
		//(x,y,width,height)
		mainMenu.setBounds(300, 600, 150, 50);
		
		white.setBounds(90, 150, 100, 100);
		clear.setBounds(90, 300, 100, 100);
		golden.setBounds(90, 450, 100, 100);

		black.setBounds(510, 150, 100, 100);
		walnut.setBounds(510, 300, 100, 100);
		blue.setBounds(510, 450, 100, 100);

		//Add the buttons to the JFrame
		frame.add(mainMenu);
		frame.add(white);
		frame.add(clear);
		frame.add(golden);
		frame.add(black);
		frame.add(walnut);
		frame.add(blue);

		//Disables the "X" button on the top corner of the JFrame Dialog
		//This is needed because if the user "X's" out of the Settings window instead of clicking "Main Menu",
		//The instance variable that allows another settings menu to be created will not be reset,
		//thus another settings menu cannot be created
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		//Last step: Set window to be visible
		frame.setVisible(true);
	}
	
	/**
	 * This method is run every time a button is clicked
	 * @param button the button that was clicked
	 */
	public static void buttonClick(SettingsMenuButton button) {
		//If "Main Menu" button is clicked
		if (button.getDescription().equals("Main Menu")) {
			//Disposes of Settings Menu GUI
			frame.dispose();
			//Resets static variable so that the window may be re-created
			StartupScreenGUI.SettingsMenuClosed();
		}		

		//If a button for the white pieces is clicked
		else if (button.getDescription().equals("White") || button.getDescription().equals("Clear") || button.getDescription().equals("Golden")) {
			//Resets the background color of all the white pieces
			resetBackgroundColorWhitePieces();
			//Sets the background color of the selected button to green
			button.setBackground(Color.green);
			//Confirmation in the Console
			System.out.println("Setting Default White color to: " + button.getDescription());
			//Setting correct static variable in the "Button" class, which configures the colors of the pieces
			StartupScreenGUI.setWhiteColor(button.getDescription());
		}
		
		//If a button for the black pieces is clicked
		else if (button.getDescription().equals("Black") || button.getDescription().equals("Walnut") || button.getDescription().equals("Blue")) {
			//Resets the background color of all the black pieces
			resetBackgroundColorBlackPieces();
			//Sets the background color of the selected button to green
			button.setBackground(Color.green);
			//Confirmation in the Console
			System.out.println("Setting Default Black color to: " + button.getDescription());
			//Setting correct static variable in the "Button" class, which configures the colors of the pieces
			StartupScreenGUI.setBlackColor(button.getDescription());
		}
	}
	
	/**
	 * sets all the backgrounds of the white piece options to white
	 */
	private static void resetBackgroundColorWhitePieces() {
		white.setBackground(Color.white);
		clear.setBackground(Color.white);
		golden.setBackground(Color.white);
	}
	
	/**
	 * sets all the backgrounds of the black piece options to white
	 */
	private static void resetBackgroundColorBlackPieces() {
		black.setBackground(Color.white);
		walnut.setBackground(Color.white);
		blue.setBackground(Color.white);
	}
}
