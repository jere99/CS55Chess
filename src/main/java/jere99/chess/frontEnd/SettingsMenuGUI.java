package jere99.chess.frontEnd;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jere99.chess.reference.Colors;
import jere99.chess.reference.Labels;
import jere99.chess.reference.Settings;

/**
 * The GUI for the settings menu.
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 * @see Settings
 */
@SuppressWarnings("serial")
public class SettingsMenuGUI extends GenericGUI {

	/**
	 * The buttons on this GUI.
	 */
	private final GenericButton[] buttons = new GenericButton[6];
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
		//The instance variable that allows another settings menu to be created will not be reset, thus another settings menu cannot be created
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//Background Image
		setContentPane(Labels.SETTINGS_MENU);
		
		//Set JFrame to Absolute Layout so that elements may be positioned
		setLayout(null);
		
		//Create the buttons
		//Customize the buttons' locations & sizes (x,y,width,height)
		//Add the buttons to the JFrame
		for(Colors c : Colors.values()) {
			buttons[c.ordinal()] = new GenericButton(c.getPawn(), this);
			buttons[c.ordinal()].setBounds(c.isWhite() ? HORIZONTAL_OFFSET : FRAME_WIDTH - HORIZONTAL_OFFSET - BUTTON_WIDTH, (c.ordinal() % (Colors.values().length / 2) + 1) * VERTICAL_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT);
			add(buttons[c.ordinal()]);
		}
		//Set button background colors
		updateButtons();
		
		//Create button to return to main menu
		GenericButton MainMenu = new GenericButton("Main Menu", new MainMenuListener());
		MainMenu.setBackground(Color.WHITE);
		MainMenu.setForeground(Color.CYAN);
		MainMenu.setBounds(300, 600, 150, 50);
		add(MainMenu);
		
		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * Changes the settings depending on which button has been clicked.
	 * Will be called every time a pawn button on this SettingsMenuGUI is clicked.
	 * 
	 * @param e the ActionEvent corresponding to a button click on this GUI
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Colors color = Colors.getColorOfPawnIcon(((GenericButton)e.getSource()).getIcon());
		//Confirmation in the Console
		System.out.println("Setting default " + (color.isWhite() ? "white" : "black") + " color to: " + color.toString().toLowerCase());
		//Update Settings
		Settings.update(color);
		//Update Button background colors
		updateButtons();
	}
	
	/**
	 * Sets the background color of selected buttons to be green and that of unselected buttons to be white.
	 */
	private void updateButtons() {
		for(int i = 0; i < buttons.length; i++)
			buttons[i].setBackground(Settings.getColor(i < 3) == Colors.values()[i] ? Color.GREEN : Color.WHITE);
	}
	
	/**
	 * Defines a listener for the Main Menu Button.
	 * 
	 * @author JeremiahDeGreeff
	 */
	private class MainMenuListener implements ActionListener {
		
		/**
		 * Closes this GUI.
		 * Will be called every time the Main Menu button this SettingsMenuGUI is clicked.
		 * 
		 * @param e the ActionEvent corresponding to a button click on this GUI
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			//Dispose of Settings Menu GUI
			dispose();
			//Reset static variable so that the window may be re-created
			StartupScreenGUI.SettingsMenuClosed();
		}
		
	}
	
}
