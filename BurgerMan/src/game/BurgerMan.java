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
	private Tomato tom;
	private Plate plate;
	private Buns bottom;
	private Buns top;

	public BurgerMan() {
		super("BurgerMan!", 800, 600);
		this.setFocusable(true);
		this.requestFocus();
		
		
		// Making a new semicircle for buns
		Point[] semiCircle = { new Point(-5, 10), new Point(5, 12), 
				new Point(15, 14), new Point(25, 15),
				new Point(35, 14), new Point(45, 12), new Point(55, 10), 
				new Point(55, 0), new Point(-5, 0) };
		int rand = (int) (Math.random() * 726) + 20;
		
		// making the top and bottom buns (top is just rotated 180 degrees)
		bottom = new Buns(semiCircle, new Point(rand, -3), 0, 15);
		top = new Buns(semiCircle, new Point(rand, -3), 180, 15);
		// addKeyListener allow the buns to follow keyboard input when they have collided
		// with the plate (see proj4 doc for more info)
		this.addKeyListener(bottom);
		this.addKeyListener(top);

		// Define a rectangle shape
		Point[] rectangle = { new Point(0, 0), new Point(50, 0), 
				new Point(50, 10), new Point(0, 10) };
		// Initialize tomato at position (200, 200), no rotations, speed 2
		rand = (int) (Math.random() * 726) + 20;
		tom = new Tomato(rectangle, new Point(rand, -200), 0, 10);
		// addKeyListener allow the tomato to follow keyboard input when they have collided
		// with the plate (see proj4 doc for more info)
		this.addKeyListener(tom);

		// Defines a trapezoid shape
		Point[] trapezoid = { new Point(0, 0), new Point(70, 0), 
				new Point(65, 10), new Point(5, 10) };
		// intialize a new plate at 200, 200 with no rotation and no speed 9
		plate = new Plate(trapezoid, new Point(200, 530), 0, 10);
		// addKeyListener allow the plate to follow keyboard input (see proj4 doc for more info)
		this.addKeyListener(plate);

	}

	public Plate getPlate() {
		return this.plate;
	} 

	public void paint(Graphics brush) {
		// background don’t change for now
		brush.setColor(Color.black);
		brush.fillRect(0, 0, width, height);

		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted
		// don’t change for now
		counter++;
		brush.setColor(Color.white);
		brush.drawString("Counter is " + plate.position.x, 10, 10); // (10, 10) is the position of the counter

		Polygon[] elem = { tom, plate, bottom};
		
		
		
		// This is a loop to call each method for the elements in the elem array
		for(Polygon p : elem) {
			p.paint(brush);
			p.move();
			p.wrap();
		// Since each element collides with the next, this loop makes sure
		// that for each element, we check whether it is colliding with any
		// other element.
			for(int i = 0; i < elem.length; i++) {
				if(!(p.getClass().equals(elem[i].getClass()))){
				p.collide(elem[i]);
				}
			}
			
		}
	
		
		
	
	}

	public static void main(String[] args) {
		BurgerMan a = new BurgerMan();
		a.repaint();

	}
}
