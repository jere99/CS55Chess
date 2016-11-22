package driver;
import board.*;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The GUI for the startup screen
 * 
 * @author Kevin
 */
public class StartupScreen
{
	
	/**
	 * the main display component of the GUI: holds the background image
	 */
	private JLabel myLabel = new JLabel(new ImageIcon(this.getClass().getResource("BG2.png")));
	
	/**
	 * the JFrame for the window
	 */
	protected static JFrame frame;

	/**
	 * Main method for the project
	 */
	public static void main(String[] args) 
	{
		
		//Creating new window
		new StartupScreen();

	}

	/**
	 * Creates a new menu with all functional buttons and correct background
	 */
	public StartupScreen()
	{	

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
		StartupButtons newGame = new StartupButtons("New Game");
		StartupButtons exit = new StartupButtons("Exit");
		StartupButtons settings = new StartupButtons("Color Scheme");
		
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
	 * creates a new game
	 */
	public static void newGame() {
		//Creates new Board
		new Board();
		//Confirms in console
		System.out.println("New Game\nWhite's turn:");
	}
}
