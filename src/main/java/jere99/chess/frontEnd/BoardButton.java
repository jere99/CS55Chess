package jere99.chess.frontEnd;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/** 
 * a button that represents a square on the chess board of a BoardGUI object
 * 
 * @author Kevin
 * @author JeremiahDeGreeff
 */
@SuppressWarnings("serial")
public class BoardButton extends JButton implements ActionListener {

	/**
	 * this buttons's row on the BoardGUI
	 */
	protected final int ROW;
	/**
	 * this buttons's column on the BoardGUI
	 */
	protected final int COLUMN;


	/**
	 * Creates a new button and gives it the correct initial values
	 * @param row the buttons's row on the BoardGUI
	 * @param col the buttons's column on the BoardGUI
	 */
	protected BoardButton(int row, int column) {
		ROW = row;
		COLUMN = column;

		//Set Background Colors
		resetColor();

		//Necessary for buttons to work on OSX
		setOpaque(true);
		setBorderPainted(false);

		//creates a new Action Listener
		addActionListener(this);
	}

	/**
	 * Sets the button's background color to the default
	 */
	protected void resetColor() {
		if((ROW + COLUMN) % 2 == 0)
			setBackground(Color.WHITE);
		else
			setBackground(Color.BLACK);
	}

	/**
	 * highlights this button
	 */
	protected void highlight() {
		setBackground(Color.YELLOW);
	}

	/**
	 * This method is run every time the button is clicked
	 * It calls the buttonClick method on the GUI
	 * @param e the click which triggers the method
	 */
	public void actionPerformed(ActionEvent e) {
		((BoardGUI)getParent().getParent().getParent().getParent().getParent()).buttonClick(this);
	}

}
