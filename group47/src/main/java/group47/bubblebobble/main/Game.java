package group47.bubblebobble.main;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Class Game sets up the JFrame window, in which the game will be played.
 * The class JFrame is an extended version of java.awt.Frame that adds support
 * for the JFC/Swing component architecture.
 * 
 */
public class Game {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		JFrame window = new JFrame("Bubble Bobble");

		// The contentpane inside the JFrame Window (a JPanel)
		window.setContentPane(new JPanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Our first version won't be resizable
		window.setResizable(false);
		window.pack();
		// Start JFrame in middle of screen
		window.setLocationRelativeTo(null);
		window.setVisible(true);

	}

}
