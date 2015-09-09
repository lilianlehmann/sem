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
public class GameOverState extends GameState {

	/** The current choice. */
	private int currentChoice = 0;

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
	public GameOverState(GameStateManager gsm) {

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
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		// draw the background image
		int x = (GamePanel.WIDTH - image.getWidth(null)) / 2;
		int y = (GamePanel.HEIGHT - image.getHeight(null)) / 5;
		g.drawImage(image, x, y, null);

		// draw game over options
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("Game Over", 320, 450);
		g.drawString("Press enter to restart", 210, 500);

	}

	/*
	 * Lets you scroll through menu options with up and down keys
	 */
	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
	}

	/*
	 * keyReleased
	 */
	@Override
	public void keyReleased(int k) {
	}

}
