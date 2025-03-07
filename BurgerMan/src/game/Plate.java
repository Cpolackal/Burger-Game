package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;


public class Plate extends Polygon implements KeyListener{

	  

	private static int speed = 10;
	private boolean right;
	private boolean left;
	private int height;
	private Color color;
	
	public Plate(Point[] inShape, Point inPosition, double inRotation, int height) {
		super(inShape, inPosition, inRotation);
		this.right = false;
		this.left = false;
		this.height = height;
		// right and left are for keyboard inputs.
		 

	}

	void paint(Graphics brush) {
		

		
		brush.setColor(Color.WHITE); // Set plate color
		Point[] points = this.getPoints(); // call getPoints on Polygon class, returns an array of points
		int[] xPoints = new int[points.length]; // create int array of values stored as xPoints, for x coord
		int [] yPoints = new int[points.length];
		
		for (int i = 0; i < points.length; i++) {
			xPoints[i] = (int) points[i].x; // (int) because it tells me to
			yPoints[i] = (int) points[i].y;
		} 
		
		brush.fillPolygon(xPoints, yPoints, points.length);
	}

	public void move() {
		// called from the paint method in Burgerman, this makes the plate move 
		// back and forth depending on keys
		if(right) {
			this.position.x += speed;
			
		}
		
		if(left) {
			this.position.x -= speed;
		}
	
		
	
	}
	
	

	// If the player goes off the screen they get wrapped around
	public void wrap() {
		if(this.position.x <= -60) {
			this.position.x = 800;
		}else if(this.position.x >= 800) {
			this.position.x = -60;
		}
	}
	
	
	// This implements keyboard input
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
	
	
	public int getHeight() {
		return this.height;
	}
	
	public void setSpeed(int speed) {
		
	}
	
	// we might not need this, putting this here just in case
	public Color getColor() {
		return color;
	
}
	
}
