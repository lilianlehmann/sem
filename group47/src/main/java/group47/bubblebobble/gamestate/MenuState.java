package group47.bubblebobble.gamestate;

import group47.bubblebobble.main.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * The Class MenuState, which extends the super class GameState.
 */
public class MenuState extends GameState {

	/** The current choice. */
	private int currentChoice = 0;

	/** The options. */
	private String[] options = { "Start", "Help", "Quit" };

	/** The font. */
	private Font font;

	// ** The Background */
	private String bg = "/Backgrounds/BubbleBobble_Logo.gif";

	/** The image. */
	private BufferedImage image;

	/**
	 * Instantiates a new menu state.
	 *
	 * @param gsm
	 *            the gamestatemanager
	 */
	public MenuState(GameStateManager gsm) {

		this.gsm = gsm;

		try {
			image = ImageIO.read(getClass().getResourceAsStream(bg));

			font = new Font("Arial", Font.PLAIN, 40);

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

		// draw the background image
		int x = (GamePanel.WIDTH - image.getWidth(null)) / 2;
		int y = (GamePanel.HEIGHT - image.getHeight(null)) / 5;
		g.drawImage(image, x, y, null);

		// draw menu options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.white);
			}

			g.drawString(options[i], 370, 420 + i * 50);
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
	 * keyReleased
	 */
	@Override
	public void keyReleased(int k) {
	}

}
