package jere99.chess.frontEnd;

import java.awt.Color;

import jere99.chess.reference.Colors;
import jere99.chess.reference.Labels;
import jere99.chess.reference.Settings;

/**
 * The GUI for the settings menu
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class SettingsMenuGUI extends GenericLabelGUI {

	/**
	 * the buttons on this GUI
	 */
	private final SettingsMenuButton[] BUTTONS = new SettingsMenuButton[6];
	/**
	 * the width of the frame
	 */
	private static final int FRAME_WIDTH = 700;
	/**
	 * the height of the frame
	 */
	private static final int FRAME_HEIGHT = 700;
	/**
	 * the width of the buttons on this GUI
	 */
	private static final int BUTTON_WIDTH = 100;
	/**
	 * the height of the buttons on this GUI
	 */
	private static final int BUTTON_HEIGHT = 100;
	/**
	 * the horizontal spacing between the edge of the frame and the buttons
	 */
	private static final int HORIZONTAL_OFFSET = 90;
	/**
	 * the vertical spacing between the buttons
	 */
	private static final int VERTICAL_OFFSET = 150;
	
	/**
	 * Creates window for changing settings
	 */
	protected SettingsMenuGUI() {
		//Name the window
		super("Color Scheme");

		//Sets the size, (width, height)
		setSize(FRAME_WIDTH, FRAME_HEIGHT);

		//Disables the "X" button on the top corner of the JFrame Dialog
		//This is needed because if the user "X's" out of the Settings window instead of clicking "Main Menu",
		//The instance variable that allows another settings menu to be created will not be reset,
		//thus another settings menu cannot be created
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		//Background Image
		setContentPane(Labels.SETTINGS_MENU);

		//Creating the buttons
		//Customizing the buttons' locations & sizes (x,y,width,height)
		//Adding the buttons to the JFrame
		for(Colors c : Colors.values()) {
			BUTTONS[c.ordinal()] = new SettingsMenuButton(c);
			BUTTONS[c.ordinal()].setBounds(c.isWhite() ? HORIZONTAL_OFFSET : FRAME_WIDTH - HORIZONTAL_OFFSET - BUTTON_WIDTH, (c.ordinal() % (Colors.values().length / 2) + 1) * VERTICAL_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT);
			add(BUTTONS[c.ordinal()]);
		}
		//null is the MainMenu Button
		SettingsMenuButton MainMenu = new SettingsMenuButton(null);
		MainMenu.setBounds(300, 600, 150, 50);
		add(MainMenu);
		
		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * This method is run every time a button is clicked
	 * @param b the button that was clicked
	 */
	@Override
	protected void buttonClick(GenericButton b) {
		SettingsMenuButton button = (SettingsMenuButton) b;
		//If "Main Menu" button is clicked
		if (button.COLOR == null) {
			//Disposes of Settings Menu GUI
			dispose();
			//Resets static variable so that the window may be re-created
			StartupScreenGUI.SettingsMenuClosed();
		} else {
			//Confirmation in the Console
			System.out.println("Setting default " + (button.COLOR.isWhite() ? "white" : "black") + " color to: " + button.COLOR.toString().toLowerCase());
			//Resets the background color of the old selection of the same color to white
			BUTTONS[Settings.getColor(button.COLOR.isWhite()).ordinal()].setBackground(Color.WHITE);
			//Sets the background color of the new piece to green
			button.setBackground(Color.GREEN);
			//Updates Settings
			Settings.update(button.COLOR);
		}
	}
	
	/**
	 * Buttons that appear on the SettingsMenuGUI
	 * 
	 * @author Kevin
	 * @author JeremiahDeGreeff
	 */
	private class SettingsMenuButton extends GenericLabelButton {
		
		/**
		 * the color of the piece which this button represents
		 */
		protected final Colors COLOR;
		
		/**
		 * Creates a button for the SettingsMenuGUI
		 * @param color the color which clicking this button would select,
		 * null color indicates the Main Menu button
		 */
		protected SettingsMenuButton(Colors color) {
			COLOR = color;
			
			//Necessary for buttons to work on OSX
			setOpaque(true);
			setBorderPainted(false);
			
			//Set the background to white
			setBackground(Color.white);
			
			//Set the text and color for the button that returns to the Startup Screen
			if(COLOR == null) {
				setText("Main Menu");
				setForeground(Color.cyan);
			} else
			//Set the necessary images to the correct buttons
				setIcon(COLOR.getPawn());
			
			//Create a new Action Listener
			addActionListener(this);
		}
		
	}
	
}
