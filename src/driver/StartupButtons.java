/**
 * 
 */
package driver;
import gui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Buttons that appear on the StartupScreen AND SettingsMenuGUI
 * 
 * @author Kevin
 */
@SuppressWarnings("serial")
public class StartupButtons extends JButton implements ActionListener{

	/**
	 * icon for button to choose white
	 */
	private static ImageIcon whitePawn = new ImageIcon(StartupButtons.class.getResource("whitep.png"));
	/**
	 * icon for button to choose golden
	 */
	private static ImageIcon goldenPawn = new ImageIcon(StartupButtons.class.getResource("goldenp.png"));
	/**
	 * icon for button to choose clear
	 */
	private static ImageIcon clearPawn = new ImageIcon(StartupButtons.class.getResource("clearp.png"));
	/**
	 * icon for button to choose black
	 */
	private static ImageIcon blackPawn = new ImageIcon(StartupButtons.class.getResource("blackp.png"));
	/**
	 * icon for button to choose blue
	 */
	private static ImageIcon bluePawn = new ImageIcon(StartupButtons.class.getResource("bluep.png"));
	/**
	 * icon for button to choose walnut
	 */
	private static ImageIcon walnutPawn = new ImageIcon(StartupButtons.class.getResource("walnutp.png"));
	
	/**
	 * text displayed on the button, also used to identify the button
	 */
	private String desc;


	/**
	 * Variable to make sure there isn't already a settings menu
	 * so that multiple settings menus can't be created
	 */
	private static boolean settingsMenuCreated = false;

	public StartupButtons(String desc)
	{
		//Sets instance variable for name
		this.desc = desc;
		
		//Necessary for buttons to work on MACs
		setOpaque(true);
		setBorderPainted(false);
				
		//If the button is on the Startup Screen
		if(desc.equals("New Game") || desc.equals("Exit") || desc.equals("Color Scheme"))
		{
			//Set the button to show the text
			setText(desc);
			//Sets Foreground/Background colors
			setForeground(Color.CYAN);
			setBackground(Color.white);
		}

		//If the button is part of the Settings menu
		else
		{
			//Sets the background to white
			setBackground(Color.white);
			
			//Sets the necessary images to the correct buttons
			if(desc.equals("White"))
				this.setIcon(whitePawn);
			else if(desc.equals("Golden"))
				this.setIcon(goldenPawn);
			else if(desc.equals("Clear"))
					this.setIcon(clearPawn);
			else if(desc.equals("Black"))
				this.setIcon(blackPawn);
			else if(desc.equals("Walnut"))
				this.setIcon(walnutPawn);
			else if(desc.equals("Blue"))
				this.setIcon(bluePawn);
			
			//Sets the colors for the button that returns to the Startup Screen
			else if (desc.equals("Main Menu"))
			{
				setText("Main Menu");
				setBackground(Color.white);
				setForeground(Color.cyan);
			}
		}

		//Creates a new Action Listener
		this.addActionListener(this);
	}

	/**
	 * Method is run whenever a button on the Startup Screen is clicked
	 * It has varied functions based on what the icon is as determined by its desc instance variable
	 */
	public void actionPerformed(ActionEvent e) 
	{
		
		//STARTUPSCREEN Buttons
			//If the "New Game" button is clicked
			if(desc.equals("New Game"))
			{
				//Start the game
				StartupScreen.newGame();
				//Dispose of the GUI
				StartupScreen.frame.dispose();
			}
	
			//If the "Settings" button is clicked
			else if (desc.equals("Color Scheme") && settingsMenuCreated == false)
			{
				//Create a new SettingsMenu GUI
				new SettingsMenuGUI();
				//Prevent a second SettingsMenu from being created
				settingsMenuCreated = true;
			}
	
			//If the "Exit" button is clicked
			else if (desc.equals("Exit"))
				//Terminate the program
				System.exit(0);
		
		
		
		//SETTINGS_MENU Buttons
			//If "Main Menu" button is clicked
			else if (desc.equals("Main Menu"))
			{
				//Disposes of Settings Menu GUI
				SettingsMenuGUI.frame.dispose();
				//Resets static variable so that the window may be re-created
				settingsMenuCreated = false;
			}		
	
			//If "Clear" button is clicked
			else if (desc.equals("Clear"))
			{
				//Resets the background color of all the white pieces
				resetBackgroundColorWhitePieces();
				//Sets the background color of the selected button to green
				setBackground(Color.green);
				//Confirmation in the Console
				System.out.println("Setting Default White color to: Clear");
				//Setting correct static variable in the "Button" class, which configures the colors of the pieces
				Button.setWColor(desc);
			}
	
			//If "White" button is clicked
			else if (desc.equals("White"))
			{
				//Resets the background color of all the white pieces
				resetBackgroundColorWhitePieces();
				//Sets the background color of the selected button to green
				setBackground(Color.green);
				//Confirmation in the Console
				System.out.println("Setting Default White color to: White");
				//Setting correct static variable in the "Button" class, which configures the colors of the pieces
				Button.setWColor(desc);
			}
	
			//If "Golden" button is clicked
			else if (desc.equals("Golden"))
			{
				//Resets the background color of all the white pieces
				resetBackgroundColorWhitePieces();
				//Sets the background color of the selected button to green
				setBackground(Color.green);
				//Confirmation in the Console
				System.out.println("Setting Default White color to: Golden");
				//Setting correct static variable in the "Button" class, which configures the colors of the pieces
				Button.setWColor(desc);
			}
	
			//If "Black" button is clicked
			else if (desc.equals("Black"))
			{
				//Resets the background color of all the black pieces
				resetBackgroundColorBlackPieces();
				//Sets the background color of the selected button to green
				setBackground(Color.green);
				//Confirmation in the Console
				System.out.println("Setting Default Black color to: Black");
				//Setting correct static variable in the "Button" class, which configures the colors of the pieces
				Button.setBColor(desc);
			}
	
			//If "Walnut" button is clicked
			else if (desc.equals("Walnut"))
			{
				//Resets the background color of all the black pieces
				resetBackgroundColorBlackPieces();
				//Sets the background color of the selected button to green
				setBackground(Color.green);
				//Confirmation in the Console
				System.out.println("Setting Default Black color to: Walnut");
				//Setting correct static variable in the "Button" class, which configures the colors of the pieces
				Button.setBColor(desc);
			}
	
			//If "Blue" button is clicked
			else if (desc.equals("Blue"))
			{
				//Resets the background color of all the black pieces
				resetBackgroundColorBlackPieces();
				//Sets the background color of the selected button to green
				setBackground(Color.green);
				//Confirmation in the Console
				System.out.println("Setting Default Black color to: Blue");
				//Setting correct static variable in the "Button" class, which configures the colors of the pieces
				Button.setBColor(desc);
			}
	}

	/**
	 * Private helper method that sets all the backgrounds of the white piece options to white
	 */
	private void resetBackgroundColorWhitePieces()
	{
		SettingsMenuGUI.white.setBackground(Color.white);
		SettingsMenuGUI.clear.setBackground(Color.white);
		SettingsMenuGUI.golden.setBackground(Color.white);
	}
	
	/**
	 * Private helper method that sets all the backgrounds of the black piece options to white
	 */
	private void resetBackgroundColorBlackPieces()
	{
		SettingsMenuGUI.black.setBackground(Color.white);
		SettingsMenuGUI.walnut.setBackground(Color.white);
		SettingsMenuGUI.blue.setBackground(Color.white);
	}
}
