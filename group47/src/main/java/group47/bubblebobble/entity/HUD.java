package group47.bubblebobble.entity;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HUD {

	private Player player;
	private BufferedImage image;
	private Font font;

	public HUD(Player p) {
		player = p;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(
					"/HUD/Bubble_Heart.png"));
			font = new Font("Arial", Font.PLAIN, 20);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, null);
		g.drawImage(image, 30, 0, null);
		g.setFont(font);
	}
}
