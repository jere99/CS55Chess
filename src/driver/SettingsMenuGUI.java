package driver;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class SettingsMenuGUI {

	/**
	 * the main display component of the GUI: holds the background image
	 */
	private JLabel myLabel = new JLabel(new ImageIcon(this.getClass().getResource("st2.png")));

	/**
	 * the JFrame for the window
	 */
	protected static JFrame frame;

	/**
	 * the button to return to the main menu
	 */
	protected static StartupButtons mainMenu = new StartupButtons("Main Menu");
	/**
	 * the button to select white
	 */
	protected static StartupButtons white = new StartupButtons("White");
	/**
	 * the button to select golden
	 */
	protected static StartupButtons golden = new StartupButtons("Golden");
	/**
	 * the button to select clear
	 */
	protected static StartupButtons clear = new StartupButtons("Clear");
	/**
	 * the button to select blue
	 */
	protected static StartupButtons blue = new StartupButtons("Blue");
	/**
	 * the button to select black
	 */
	protected static StartupButtons black = new StartupButtons("Black");
	/**
	 * the button to select walnut
	 */
	protected static StartupButtons walnut = new StartupButtons("Walnut");
	
	/**
	 * Creates window with correct buttons and background
	 */
	public SettingsMenuGUI()
	{
		//Name the window
		frame = new JFrame("Color Scheme");

		//Sets the size, (width, height)
		frame.setSize(700,700);

		//Allows window to be resized
		frame.setResizable(false);

		//Background Image
		frame.setContentPane(myLabel);

		//Setting JFrame to Absolute Layout so that elements may be positioned
		frame.setLayout(null);

		//Customizing the buttons' locations & sizes
		//(x,y,width,height)
		mainMenu.setBounds(300, 600, 150, 50);
		
		white.setBounds(90, 150, 100, 100);
		clear.setBounds(90, 300, 100, 100);
		golden.setBounds(90, 450, 100, 100);

		black.setBounds(510, 150, 100, 100);
		walnut.setBounds(510, 300, 100, 100);
		blue.setBounds(510, 450, 100, 100);

		//Add the buttons to the JFrame
		frame.add(mainMenu);
		frame.add(white);
		frame.add(clear);
		frame.add(golden);
		frame.add(black);
		frame.add(walnut);
		frame.add(blue);

		//Disables the "X" button on the top corner of the JFrame Dialog
		//This is needed because if the user "X's" out of the Settings window instead of clicking "Main Menu",
		//The instance variable that allows another settings menu to be created will not be reset,
		//thus another settings menu cannot be created
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		//Last step: Set window to be visible
		frame.setVisible(true);
	}
}
