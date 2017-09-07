package jere99.chess.frontEnd;

import java.awt.Color;

import jere99.chess.reference.Colors;
import jere99.chess.reference.Labels;
import jere99.chess.reference.Settings;

/**
 * The GUI for the settings menu.
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class SettingsMenuGUI extends GenericLabelGUI {

	/**
	 * The buttons on this GUI.
	 */
	private final SettingsMenuButton[] buttons = new SettingsMenuButton[6];
	/**
	 * The width of the frame.
	 */
	private static final int FRAME_WIDTH = 700;
	/**
	 * The height of the frame.
	 */
	private static final int FRAME_HEIGHT = 700;
	/**
	 * The width of the buttons on this GUI.
	 */
	private static final int BUTTON_WIDTH = 100;
	/**
	 * The height of the buttons on this GUI.
	 */
	private static final int BUTTON_HEIGHT = 100;
	/**
	 * The horizontal spacing between the edge of the frame and the buttons.
	 */
	private static final int HORIZONTAL_OFFSET = 90;
	/**
	 * The vertical spacing between the buttons.
	 */
	private static final int VERTICAL_OFFSET = 150;
	
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
			buttons[c.ordinal()] = new SettingsMenuButton(c);
			buttons[c.ordinal()].setBounds(c.isWhite() ? HORIZONTAL_OFFSET : FRAME_WIDTH - HORIZONTAL_OFFSET - BUTTON_WIDTH, (c.ordinal() % (Colors.values().length / 2) + 1) * VERTICAL_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT);
			add(buttons[c.ordinal()]);
		}
		//null is the MainMenu Button
		SettingsMenuButton MainMenu = new SettingsMenuButton(null);
		MainMenu.setBounds(300, 600, 150, 50);
		add(MainMenu);
		
		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * Changes the settings depending on which button has been clicked.
	 * Should be called every time a button on this SettingsMenuGUI is clicked.
	 * 
	 * @param b the button that was clicked
	 */
	@Override
	protected void buttonClick(GenericButton b) {
		SettingsMenuButton button = (SettingsMenuButton) b;
		//If "Main Menu" button is clicked
		if (button.color == null) {
			//Disposes of Settings Menu GUI
			dispose();
			//Resets static variable so that the window may be re-created
			StartupScreenGUI.SettingsMenuClosed();
		} else {
			//Confirmation in the Console
			System.out.println("Setting default " + (button.color.isWhite() ? "white" : "black") + " color to: " + button.color.toString().toLowerCase());
			//Resets the background color of the old selection of the same color to white
			buttons[Settings.getColor(button.color.isWhite()).ordinal()].setBackground(Color.WHITE);
			//Sets the background color of the new piece to green
			button.setBackground(Color.GREEN);
			//Updates Settings
			Settings.update(button.color);
		}
	}
	
	/**
	 * A button on a SettingsMenuGUI.
	 * 
	 * @author Kevin
	 * @author JeremiahDeGreeff
	 */
	private class SettingsMenuButton extends GenericLabelButton {
		
		/**
		 * The color of the piece which this button represents.
		 */
		private final Colors color;
		
		/**
		 * @param color the color which clicking this button would select - null indicates the Main Menu button
		 */
		private SettingsMenuButton(Colors color) {
			this.color = color;
			
			//Necessary for buttons to work on OSX
			setOpaque(true);
			setBorderPainted(false);
			
			//Set the background to white
			setBackground(Color.white);
			
			//Set the text and color for the button that returns to the Startup Screen
			if(color == null) {
				setText("Main Menu");
				setForeground(Color.cyan);
			} else
			//Set the necessary images to the correct buttons
				setIcon(color.getPawn());
			
			//Create a new Action Listener
			addActionListener(this);
		}
		
	}
	
}
