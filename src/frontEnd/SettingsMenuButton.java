package frontEnd;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Buttons that appear on the SettingsMenuGUI
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class SettingsMenuButton extends JButton implements ActionListener {
	/**
	 * icon for button to choose white
	 */
	private static ImageIcon whitePawn = new ImageIcon(StartupScreenButton.class.getResource("resources/white_p.png"));
	/**
	 * icon for button to choose golden
	 */
	private static ImageIcon goldenPawn = new ImageIcon(StartupScreenButton.class.getResource("resources/golden_p.png"));
	/**
	 * icon for button to choose clear
	 */
	private static ImageIcon clearPawn = new ImageIcon(StartupScreenButton.class.getResource("resources/clear_p.png"));
	/**
	 * icon for button to choose black
	 */
	private static ImageIcon blackPawn = new ImageIcon(StartupScreenButton.class.getResource("resources/black_p.png"));
	/**
	 * icon for button to choose blue
	 */
	private static ImageIcon bluePawn = new ImageIcon(StartupScreenButton.class.getResource("resources/blue_p.png"));
	/**
	 * icon for button to choose walnut
	 */
	private static ImageIcon walnutPawn = new ImageIcon(StartupScreenButton.class.getResource("resources/walnut_p.png"));
	/**
	 * the text displayed on the button, also used to identify the button
	 */
	private final String DESCRIPTION;
	
	public SettingsMenuButton(String desc) {
		DESCRIPTION = desc;
		
		//Necessary for buttons to work on OSX
		setOpaque(true);
		setBorderPainted(false);
		
		//Sets the background to white
		setBackground(Color.white);
		
		//Sets the necessary images to the correct buttons
		if(DESCRIPTION.equals("White"))
			this.setIcon(whitePawn);
		else if(DESCRIPTION.equals("Golden"))
			this.setIcon(goldenPawn);
		else if(DESCRIPTION.equals("Clear"))
			this.setIcon(clearPawn);
		else if(DESCRIPTION.equals("Black"))
			this.setIcon(blackPawn);
		else if(DESCRIPTION.equals("Walnut"))
			this.setIcon(walnutPawn);
		else if(DESCRIPTION.equals("Blue"))
			this.setIcon(bluePawn);
		//Sets the colors for the button that returns to the Startup Screen
		else if (DESCRIPTION.equals("Main Menu")) {
			setText("Main Menu");
			setBackground(Color.white);
			setForeground(Color.cyan);
		}

		//Creates a new Action Listener
		this.addActionListener(this);
	}
	
	/**
	 * @return the text displayed on the button to identify the button
	 */
	public String getDescription() {
		return DESCRIPTION;
	}
	
	/**
	 * Method is run whenever a button on the Settings Menu is clicked
	 */
	public void actionPerformed(ActionEvent e)  {
		SettingsMenuGUI.buttonClick(this);
	}
}
