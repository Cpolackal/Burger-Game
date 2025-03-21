package game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The Plate class represents a movable plate object that can be controlled by
 * the player. It extends the {@link Rectangle} class and implements the
 * {@link KeyListener} interface for handling keyboard input to move the plate
 * left and right.
 * <p>
 * This class provides methods for moving the plate based on user input and
 * ensuring that the plate wraps around the screen when it goes off the edges.
 * 
 * 
 * @see Rectangle
 */
public class Plate extends Rectangle implements KeyListener {

	private boolean right;
	private boolean left;
	private int plateSpeed = 8; // velocity for movement

	/**
	 * Constructs a new Plate object at the specified position.
	 * 
	 * 
	 * @param position The initial position of the plate.
	 */
	public Plate(Point position) {
		super(new Point[] { new Point(0, 0), new Point(100, 0), new Point(100, 15), new Point(0, 15) }, position, 0,
				100, 15, Color.WHITE);

		this.right = false;
		this.left = false;

	}

	/**
	 * Moves the plate based on the player's input.
	 * <p>
	 * This method is called from the paint method in Burgerman to move the plate
	 * back and forth depending on whether the left or right keys are pressed. If
	 * the right flag is true, the plate moves to the right by the specified
	 * plateSpeed. If the left flag is true, the plate moves to the left by the same
	 * speed.
	 * 
	 */
	public void move() {
		if (right) {
			this.position.x += plateSpeed;
		}

		if (left) {
			this.position.x -= plateSpeed;
		}
	}

	/**
	 * Wraps the plate around the screen if it moves off the left or right edge.
	 * <p>
	 * This method checks the position of the plate. If the plate moves past the
	 * left edge (x <= -60), it is reset to the right edge (x = 800). If the plate
	 * moves past the right edge (x >= 800), it is reset to the left edge (x = -60),
	 * creating a wrapping effect.
	 */
	public void wrap() {
		if (this.position.x <= -60) {
			this.position.x = 800;
		} else if (this.position.x >= 800) {
			this.position.x = -60;
		}
	}

	/**
	 * Invoked when a key has been pressed.
	 * <p>
	 * Sets the direction of movement for the player based on the key pressed. If
	 * the right arrow key is pressed, the player moves to the right, and if the
	 * left arrow key is pressed, the player moves to the left.
	 *
	 * @param e The {@link KeyEvent} containing information about the key that was
	 *          pressed.
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}

	}

	/**
	 * Invoked when a key has been released.
	 * <p>
	 * Stops the movement of the player in the direction of the released key.
	 * 
	 *
	 * @param e The {@link KeyEvent} containing information about the key that was
	 *          released.
	 */
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

}
