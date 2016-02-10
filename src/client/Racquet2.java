package client;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import client.Game;

public class Racquet2 {
	private static final int Y = 20;
	private static final int WIDTH = 100;
	private static final int HEIGHT = 30;
	public static int x;
	public static int xa;
	private Game game;

	public Racquet2(Game game) {
		this.game = game;
	}

	public void paint(Graphics2D g) {
		g.fillRect(x, Y, WIDTH, HEIGHT);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}
        public Rectangle getBoundss() {
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}
        
	public int getTopY() {
		return Y;
	}
}