package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

class BurgerMan extends Game {
	static int counter = 0;
	private Rectangle rect;
	private int speed = 2; // velocity for movement

	public BurgerMan() {
		super("BurgerMan!", 800, 600);
		this.setFocusable(true);
		this.requestFocus();

        //Define a rectangle shape
		Point[] shape = { new Point(0, 0), new Point(50, 0), new Point(50, 30), new Point(0, 30) };

		// Initialize rectangle at position (200, 200), no rotations, 50 W and 30 H
		rect = new Rectangle(shape, new Point(200, 200), 0, 50, 30);
	}

	public void paint(Graphics brush) {
		// background don’t change for now
		brush.setColor(Color.black);
		brush.fillRect(0, 0, width, height);

		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted
//don’t change for now
		counter++;
		brush.setColor(Color.white);
		brush.drawString("Counter is " + counter, 10, 10); // (10, 10) is the position of the counter

		brush.setColor(Color.RED); // Set rectangle color
		Point[] points = rect.getPoints(); // call getPoints on Polygon class, returns an array of points
		int[] xPoints = new int[points.length]; // create int array of values stored as xPoints, for x coord
		int[] yPoints = new int[points.length];

		// this goes through the corners of the rect,
		for (int i = 0; i < points.length; i++) {
			xPoints[i] = (int) points[i].x; // (int) because it tells me to
			yPoints[i] = (int) points[i].y;
		}

		// use Graphics utilities to fill the polygon
		brush.fillPolygon(xPoints, yPoints, points.length);

		// Move the rectangle downward every frame
		rect.position.y += speed; // set to 2 for now

	}

	public static void main(String[] args) {
		BurgerMan a = new BurgerMan();
		a.repaint();
	}
}
