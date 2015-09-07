package group47.bubblebobble.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * The Class MenuState, which extends the super class GameState.
 */
public class MenuState extends GameState {

	/** The current choice. */
	private int currentChoice = 0;

	/** The options. */
	private String[] options = { "Start", "Help", "Quit" };

	/** The title color. */
	private Color titleColor;

	/** The title font. */
	private Font titleFont;

	/** The font. */
	private Font font;

	/**
	 * Instantiates a new menu state.
	 *
	 * @param gsm
	 *            the gamestatemanager
	 */
	public MenuState(GameStateManager gsm) {

		this.gsm = gsm;

		try {

			// TODO ADD BACKGROUND

			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);

			font = new Font("Arial", Font.PLAIN, 12);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Init
	 */
	@Override
	public void init() {
	}

	/*
	 * Update
	 */
	@Override
	public void update() {
		// bg.update();
	}

	/*
	 * Draws everything of the menu screen
	 */
	@Override
	public void draw(Graphics2D g) {

		// TODO draw the background image

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Bubble Bobble", 80, 70);

		// draw menu options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 145, 140 + i * 15);
		}

	}

	/**
	 * Select between different menu options.
	 */
	private void select() {
		if (currentChoice == 0) {
			// TODO LEVEL1STATE
		}
		if (currentChoice == 1) {
			// TODO HELPSTATE
		}
		if (currentChoice == 2) {
			System.exit(0);
		}
	}

	/*
	 * Lets you scroll through menu options with up and down keys
	 */
	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GameState.GameState#keyReleased(int)
	 */
	@Override
	public void keyReleased(int k) {
	}

}
