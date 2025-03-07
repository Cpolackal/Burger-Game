package game;

import java.awt.event.*;

import game.Point;

import java.awt.*;

public class Buns extends Polygon implements KeyListener {
	private static int ySpeed;
	private boolean right;
	private boolean left;
	private Color color;
	private static int xSpeed;

	public Buns(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		ySpeed = 4;
		xSpeed = 10;
	}

	void paint(Graphics brush) {
		Color lightBrown = new Color(222, 184, 135);
		brush.setColor(lightBrown);
		Point[] points = this.getPoints();
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];

		for (int i = 0; i < points.length; i++) {
			xPoints[i] = (int) points[i].x;
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
	
	public void collide(Polygon other) {
		for(int i = 0; i < other.getPoints().length; i++) {
			if(this.contains(other.getPoints()[i])){
				other.setSpeed(0);
			}
		}
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
}
