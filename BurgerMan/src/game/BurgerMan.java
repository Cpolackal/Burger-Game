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
	private  Plate plate;
	private int speed = 2; // velocity for movement

	public BurgerMan() {
		super("BurgerMan!", 800, 600);
		this.setFocusable(true);
		this.requestFocus();
		
		

        //Define a rectangle shape
		Point[] rectangle = { new Point(0, 0), new Point(50, 0), new Point(50, 10), new Point(0, 10) };
		// Initialize tomato at position (200, 200), no rotations, speed 2
		tom = new Tomato(rectangle, new Point(200, 0), 0, 2);
		
		//Defines a trapezoid shape
		Point[] trapezoid = { new Point(0, 0), new Point(70, 0), new Point(65, 10), new Point(5, 10) };
		// intialize a new plate at 200, 200 with no rotation and no speed 9
		plate = new Plate(trapezoid, new Point(200, 530), 0, 9);
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
        //don’t change for now
		counter++;
		brush.setColor(Color.white);
		brush.drawString("Counter is " + counter, 10, 10); // (10, 10) is the position of the counter
		
		Polygon[] elem = {tom, plate};
		
		tom.paint(brush);
		plate.paint(brush);
		tom.move();
		plate.move();
		plate.collide(tom);
	

	}
	


	public static void main(String[] args) {
		BurgerMan a = new BurgerMan();
		a.repaint();
		
		
		
		
	}
}
