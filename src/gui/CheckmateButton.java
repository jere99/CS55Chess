/**
 * 
 */
package gui;
import javax.swing.JButton;

import driver.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** 
 * @author Kevin, Jeremiah
 * 
 * a button on a CheckmateGUI object
 */
@SuppressWarnings("serial")
public class CheckmateButton extends JButton implements ActionListener
{
	
	/**
	 * the GUI that this button is a part of
	 */
	private CheckmateGUI gui;
	/**
	 * the text on the button
	 */
	private String displayName;
	
	/**
	 * Creates a new button and sets instance variables
	 * 
	 * @param displayName the text to be put on the button
	 * @param gui the GUI that this button is a part of
	 */
	public CheckmateButton(String displayName, CheckmateGUI gui)
	{
		super();
		this.displayName = displayName;
		this.setText(displayName);
		this.gui = gui;

		//creates a new Action Listener
		this.addActionListener(this);

	}

	
	/**
	 * Run when the button is clicked
	 * Causes the desired action to occur
	 */
	public void actionPerformed(ActionEvent e)
	{
		//if "New Game" is clicked
		if(displayName.equals("New Game"))
		{
			gui.getBoardGUI().dispose(); //close old game
			gui.dispose(); //close this GUI
			System.out.println("\n\n\n"); //create space in console between old game and new game
			StartupScreen.newGame(); //start a new game
		}
		//if "Exit" is clicked
		else if (displayName.equals("Exit"))
		{
			gui.getBoardGUI().dispose(); //close old game
			gui.dispose(); //close this GUI
			System.exit(0); //terminate everything
		}
		//if "Main Menu" is clicked
		else if (displayName.equals("Main Menu"))
		{
			gui.getBoardGUI().dispose(); //close old game
			gui.dispose(); //close this GUI
			StartupScreen.main(null); //create a new startup screen
		}
	}

}
