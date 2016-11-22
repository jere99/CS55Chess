package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

/**
 * Buttons that appear on the StartupScreenGUI
 * 
 * @author Kevin
 */
@SuppressWarnings("serial")
public class StartupButton extends JButton implements ActionListener{
	
	/**
	 * text displayed on the button, also used to identify the button
	 */
	private String desc;

	public StartupButton(String desc)
	{
		//Sets instance variable for name
		this.desc = desc;
		
		//Necessary for buttons to work on OSX
		setOpaque(true);
		setBorderPainted(false);

		//Set the button to show the text
		setText(desc);
		//Sets Foreground/Background colors
		setForeground(Color.CYAN);
		setBackground(Color.white);

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
			else if (desc.equals("Color Scheme") && StartupScreen.settingsMenuCreated == false)
			{
				//Create a new SettingsMenu GUI
				new SettingsMenuGUI();
				//Prevent a second SettingsMenu from being created
				StartupScreen.settingsMenuCreated = true;
			}
	
			//If the "Exit" button is clicked
			else if (desc.equals("Exit"))
				//Terminate the program
				System.exit(0);
	}
}
