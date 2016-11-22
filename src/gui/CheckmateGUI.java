/**
 * 
 */
package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** 
 * @author Kevin, Jeremiah
 * 
 * a GUI that pops up when the game has ended
 */
@SuppressWarnings("serial")
public class CheckmateGUI extends JFrame{

	/**
	 * the main display component of the GUI
	 */
	private JPanel p = new JPanel();
	
	/**
	 * array of all the buttons on this GUI
	 */
	private CheckmateButton[] buttons = new CheckmateButton[3];
	
	/**
	 * the GUI of the completed game
	 */
	private BoardGUI boardGUI;
	
	/**
	 * creates a pop-up window after checkmate occurs
	 * 
	 * @param boardGUI the GUI of the completed game
	 * @param winner which player has won: either "White" or "Black"
	 */
	public CheckmateGUI(BoardGUI boardGUI, String winner)
	{

		//Name the window
		super(winner + " wins!");
		
		this.boardGUI = boardGUI;

		//Sets the size of the window
		setSize(500,75);

		//Allows window to be resized
		setResizable(false);

		//Default program end
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Create the necessary buttons
		buttons[0] = new CheckmateButton("New Game", this);
		buttons[1] = new CheckmateButton("Main Menu", this);
 		buttons[2] = new CheckmateButton("Exit", this);

		//Add the necessary buttons to the JPanel
		for (CheckmateButton e : buttons)
			p.add(e);

		//Implements the JPanel to the GUI
		add(p);

		//Last step: Set window to be visible
		setVisible(true);

	}
	
	/**
	 * @return the GUI of the completed game
	 */
	public BoardGUI getBoardGUI()
	{
		return boardGUI;
	}
}
