package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;


public class Plate extends Polygon implements KeyListener{

	  

	private static int speed = 9;
	private boolean forward;
	private boolean right;
	private boolean left;
	private Color color;

	public Plate(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		this.forward = false;
		this.right = false;
		this.left = false;
		

	}

	void paint(Graphics brush) {
		
		Point[] points = this.getPoints(); // call getPoints on Polygon class, returns an array of points
		int[] xPoints = new int[points.length]; // create int array of values stored as xPoints, for x coord
		int[] yPoints = new int[points.length];

		
		brush.setColor(Color.WHITE); // Set plate color
		points = this.getPoints(); // call getPoints on Polygon class, returns an array of points
		xPoints = new int[points.length]; // create int array of values stored as xPoints, for x coord
		yPoints = new int[points.length];
		
		for (int i = 0; i < points.length; i++) {
			xPoints[i] = (int) points[i].x; // (int) because it tells me to
			yPoints[i] = (int) points[i].y;
		}
		
		brush.fillPolygon(xPoints, yPoints, points.length);
	}

	public void move() {
		// called from the paint method, this makes the plate move back and forth depending on keys
		if(right) {
			this.position.x += speed;
			
		}
		
		if(left) {
			this.position.x -= speed;
		}
	
		
	
	}
	
	
	public void collide(Polygon other) {
		for(int i = 0; i < other.getPoints().length; i++) {
			if(this.contains(other.getPoints()[i])){
				if(other instanceof Tomato) { 
					Tomato t = (Tomato)other;
					t.setSpeed(0);
				}
			}
		}
	}
	
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			right = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			left = true;
		}

	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			right = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			left = false;
		}

		
	}
	
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	// we might not need this, putting this here just in case
	public Color getColor() {
		return color;
	
}
	
}
