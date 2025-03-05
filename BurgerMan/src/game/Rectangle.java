package game;

import java.awt.*;

public class Rectangle extends Polygon {
	private int rWidth, rHeight; // w and h of rectangle
	private Color color;

	public Rectangle(Point[] inShape, Point inPosition, double inRotation, int rWidth, int rHeight) {
		super(inShape, inPosition, inRotation);
		this.rWidth = rWidth;
		this.rHeight = rHeight;

	}

	void paint(Graphics brush) {

	}

	// we might not need this, putting this here just in case
	public Color getColor() {
		return color;
	}

}
