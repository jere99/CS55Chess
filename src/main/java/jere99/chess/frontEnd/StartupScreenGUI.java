package jere99.chess.frontEnd;

import java.awt.Color;
import java.awt.event.ActionEvent;

import jere99.chess.backEnd.Game;
import jere99.chess.reference.Labels;

/**
 * The GUI for the startup screen.
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class StartupScreenGUI extends GenericGUI {
	
	/**
	 * The width of the frame.
	 */
	private static final int FRAME_WIDTH = 1127;
	/**
	 * The height of the frame.
	 */
	private static final int FRAME_HEIGHT = 865;
	/**
	 * The width of the buttons on this GUI.
	 */
	private static final int BUTTON_WIDTH = 250;
	/**
	 * The height of the buttons on this GUI.
	 */
	private static final int BUTTON_HEIGHT = 30;
	/**
	 * The horizontal spacing between the edge of the frame and the buttons.
	 */
	private static final int HORIZONTAL_OFFSET = 585;
	/**
	 * The vertical spacing between the top of the frame and the first button.
	 */
	private static final int VERTICAL_OFFSET = 145;
	/**
	 * The vertical spacing between the buttons.
	 */
	private static final int VERTICAL_SPACING = 70;
	/**
	 * Keeps track of whether or not a settings menu has already been created in order to prevent multiple settings menus from existing simultaneously.
	 */
	private static boolean settingsMenuCreated = false;
	
	public StartupScreenGUI() {
		//Name the window
		super("Chromatic Chess");
		
		//Sets the size, (width, height)
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		//Background image
		setContentPane(Labels.STARTUP_SCREEN);
		
		//Set JFrame to Absolute Layout so that elements may be positioned
		setLayout(null);
		
		//Creating the buttons
		GenericButton[] buttons = {
				new GenericButton("New Game", this),
				new GenericButton("Exit", this),
				new GenericButton("Color Scheme", this)};
		
		//Configuring locations and sizes of buttons (x,y,width,height)
		//Adding the buttons to the JFrame
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].setBounds(HORIZONTAL_OFFSET, VERTICAL_OFFSET + VERTICAL_SPACING * i, BUTTON_WIDTH, BUTTON_HEIGHT);
			buttons[i].setForeground(Color.CYAN);
			buttons[i].setBackground(Color.WHITE);
			add(buttons[i]);
		}
		
		//Last step: Set window to be visible
		setVisible(true);
	}
	
	/**
	 * Starts a new Game, opens a new SettingsMenuGUI, or terminates the program depending on which button has been clicked.
	 * Will be called every time a button on this StartupScreenGUI is clicked.
	 * 
	 * @param e the ActionEvent corresponding to a button click on this GUI
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		switch(e.getActionCommand()) {
		case "New Game":
			new Game(); //Start the game
			dispose(); //Dispose of the GUI
			break;
		case "Color Scheme":
			if(!settingsMenuCreated) {
				new SettingsMenuGUI(); //Create a new SettingsMenu GUI
				settingsMenuCreated = true; //Prevent a second SettingsMenu from being created
			}
			break;
		case "Exit":
			System.out.println("Terminating program");
			System.exit(0); //Terminate the program
		}
	}
	
	/**
	 * Allows for a new SettingsMenuGUI to be created after the current one has closed.
	 */
	protected static void SettingsMenuClosed() {
		settingsMenuCreated = false;
	}

}
