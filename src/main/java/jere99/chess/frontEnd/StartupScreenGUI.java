package jere99.chess.frontEnd;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import jere99.chess.backEnd.Game;

/**
 * The GUI for the startup screen
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
public class StartupScreenGUI {
	/**
	 * Variable to keep track of whether or not a settings menu has already been created
	 * to prevent multiple settings menus from existing simultaneously
	 */
	private static boolean settingsMenuCreated = false;
	/**
	 * the color for the white pieces
	 */
	private static String whiteColor = "white";
	/**
	 * the color for the black pieces
	 */
	private static String blackColor = "black";
	/**
	 * the main display component of the GUI: holds the background image
	 */
	private JLabel myLabel = new JLabel(new ImageIcon(this.getClass().getResource("/startup_screen.png")));
	/**
	 * the JFrame for the window
	 */
	protected static JFrame frame;
	
	/**
	 * Creates a new window with all functional buttons and correct background
	 */
	public StartupScreenGUI() {
		//Name the window
		frame = new JFrame("Chromatic Chess");
		
		//Sets the size, (width, height)
		frame.setSize(1127,865);

		//Allows window to be resized
		frame.setResizable(false);

		//Default program end
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

		//Background image
		frame.setContentPane(myLabel);

		//Setting JFrame to Absolute Layout so that elements may be positioned
		frame.setLayout(null);

		//Adding the buttons
		StartupScreenButton newGame = new StartupScreenButton(this, "New Game");
		StartupScreenButton exit = new StartupScreenButton(this, "Exit");
		StartupScreenButton settings = new StartupScreenButton(this, "Color Scheme");
		
		//Configuring locations and sizes of buttons
		//.setBounds(x,y,width,height)
		newGame.setBounds(585, 145, 250, 33);
		settings.setBounds(585, 215, 250, 33);
		exit.setBounds(585, 285, 250, 33);

		//Add the buttons to the JFrame
		frame.add(newGame);
		frame.add(settings);
		frame.add(exit);
		
		//Last step: Set window to be visible
		frame.setVisible(true);
	}
	
	/**
	 * Changes the color for the white pieces
	 * @param color the color to change to
	 */
	public static void setWhiteColor(String color) {
		whiteColor = color;
	}
	
	/**
	 * Changes the color for the black pieces
	 * @param color the color to change to
	 */
	public static void setBlackColor(String color) {
		blackColor = color;
	}
	
	/**
	 * Allows for a new SettingsMenuGUI to be created after the current one has closed
	 */
	public static void SettingsMenuClosed() {
		settingsMenuCreated = false;
	}
	
	/**
	 * This method is run every time a button is clicked
	 * @param button the button that was clicked
	 */
	public void buttonClick(StartupScreenButton button) {
		//If the "New Game" button is clicked
		if(button.getDescription().equals("New Game")) {
			//Start the game
			newGame();
			//Dispose of the GUI
			frame.dispose();
		}
		//If the "Settings" button is clicked
		else if (button.getDescription().equals("Color Scheme") && settingsMenuCreated == false) {
			//Create a new SettingsMenu GUI
			new SettingsMenuGUI();
			//Prevent a second SettingsMenu from being created
			settingsMenuCreated = true;
		}
		//If the "Exit" button is clicked
		else if (button.getDescription().equals("Exit"))
			//Terminate the program
			System.exit(0);
	}
	
	/**
	 * Creates a new game
	 */
	public static void newGame() {
		//loads the correct piece images
		BoardButton.setWhiteColor(whiteColor);
		BoardButton.setBlackColor(blackColor);
		//Confirms in console
		System.out.println("\nNew Game");
		//Creates new Game
		new Game();
		System.out.println("\nWhite's Turn");
	}
}
