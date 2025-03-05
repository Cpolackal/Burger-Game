package game;

import java.awt.*;

import game.Point;

public class Rectangle extends Polygon {
	private int speed;
	private Color color;

	public Rectangle(Point[] inShape, Point inPosition, double inRotation, int speed) {
		super(inShape, inPosition, inRotation);
		this.speed = speed;
	

	}

	void paint(Graphics brush) {
		
		brush.setColor(Color.RED); // Set rectangle color
		Point[] points = this.getPoints(); // call getPoints on Polygon class, returns an array of points
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
		this.position.y += speed; // set to 2 for now
	}
	
	

	// we might not need this, putting this here just in case
	public Color getColor() {
		return color;
	}

}
