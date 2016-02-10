package client;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 30;
	public static int x = 10;
	public static int y = 50;
	public static int xa = 3;
	public static int ya = 3;
	static private Game game;

	public Ball(Game game) {
		this.game= game;
	}

	public void paint(Graphics2D g) {
                g.setColor(Color.RED);
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
}