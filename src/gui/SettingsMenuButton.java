package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Buttons that appear on the SettingsMenuGUI
 * 
 * @author Kevin
 */
@SuppressWarnings("serial")
public class SettingsMenuButton extends JButton implements ActionListener{
	
	/**
	 * icon for button to choose white
	 */
	private static ImageIcon whitePawn = new ImageIcon(StartupButton.class.getResource("white_p.png"));
	/**
	 * icon for button to choose golden
	 */
	private static ImageIcon goldenPawn = new ImageIcon(StartupButton.class.getResource("golden_p.png"));
	/**
	 * icon for button to choose clear
	 */
	private static ImageIcon clearPawn = new ImageIcon(StartupButton.class.getResource("clear_p.png"));
	/**
	 * icon for button to choose black
	 */
	private static ImageIcon blackPawn = new ImageIcon(StartupButton.class.getResource("black_p.png"));
	/**
	 * icon for button to choose blue
	 */
	private static ImageIcon bluePawn = new ImageIcon(StartupButton.class.getResource("blue_p.png"));
	/**
	 * icon for button to choose walnut
	 */
	private static ImageIcon walnutPawn = new ImageIcon(StartupButton.class.getResource("walnut_p.png"));
	/**
	 * text displayed on the button, also used to identify the button
	 */
	private String desc;
	
	public SettingsMenuButton(String desc)
	{
		//Sets instance variable for name
		this.desc = desc;
		
		//Necessary for buttons to work on OSX
		setOpaque(true);
		setBorderPainted(false);
		
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

		//Creates a new Action Listener
		this.addActionListener(this);
	}
	
	/**
	 * Method is run whenever a button on the Startup Screen is clicked
	 * It has varied functions based on what the icon is as determined by its desc instance variable
	 */
	public void actionPerformed(ActionEvent e) 
	{
			//If "Main Menu" button is clicked
			if (desc.equals("Main Menu"))
			{
				//Disposes of Settings Menu GUI
				SettingsMenuGUI.frame.dispose();
				//Resets static variable so that the window may be re-created
				StartupScreen.settingsMenuCreated = false;
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
				BoardButton.setWColor(desc);
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
				BoardButton.setWColor(desc);
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
				BoardButton.setWColor(desc);
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
				BoardButton.setBColor(desc);
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
				BoardButton.setBColor(desc);
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
				BoardButton.setBColor(desc);
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
