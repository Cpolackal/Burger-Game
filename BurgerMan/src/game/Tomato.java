package game;

import java.awt.*;
import java.awt.event.*;

import game.Point;

public class Tomato extends Polygon implements KeyListener {
	private static int ySpeed;
	private static int xSpeed;
	private boolean right;
	private boolean left;
	private int height;
	private Color color;
	
	

	public Tomato(Point[] inShape, Point inPosition, double inRotation, int height) {
		super(inShape, inPosition, inRotation);
		ySpeed = 4;
		xSpeed = 10;
		this.height = height;
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

	}

	public void move() {

		if (this.position.y < 10) {
			this.position.y += ySpeed;
		} else if (this.position.y < 70) {
			this.rotation += 24;
			this.position.y += ySpeed;
		} else {
			this.position.y += ySpeed;
		}
		if (ySpeed == 0) {
			if (right) {
				this.position.x += xSpeed;

			}

			if (left) {
				this.position.x -= xSpeed;
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}

	}


	public void keyTyped(KeyEvent e) {

	}
	
	public int getHeight() {
		return this.height;
	}

	public void wrap() {
		if (this.position.x <= -60) {
			this.position.x = 800;
		} else if (this.position.x >= 800) {
			this.position.x = -60;
		}
	}

	
	
	
	public void setSpeed(int speed) {
		ySpeed = speed;
	}

	// we might not need this, putting this here just in case
	public Color getColor() {
		return color;
	}

}
