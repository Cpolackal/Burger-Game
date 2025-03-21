package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import game.Rectangle.Spatula;

/**
 * A functional interface for drawing text on a graphical component. The
 * implementation should define how the text is rendered using the provided
 * Graphics object.
 * <p>
 * This interface is intended to be used with a lambda expression to specify
 * custom text rendering behavior.
 * 
 *
 * @param brush   the Graphics object used for drawing
 * @param message the text message to be displayed
 * @param x       the x-coordinate where the text should be drawn
 * @param y       the y-coordinate where the text should be drawn
 * @see Graphics
 */
@FunctionalInterface
interface Headers {
	void draw(Graphics brush, String message, int x, int y);
}

/**
 * An abstract class for detecting when an object is caught and displaying a
 * message. This class is intended to be implemented as an anonymous class.
 * 
 *
 * @param brush   the Graphics object used for drawing
 * @param message the text to display
 * @param x       the x-coordinate for the message
 * @param y       the y-coordinate for the message
 * @param r       the Rectangle representing a burger ingredient
 * @see Graphics
 * @see Rectangle
 */
abstract class Caught {
	abstract void isCaught();
}

/**
 * The Burgerman class is a game where the player catches falling ingredients to
 * build a burger matching the guest's order. It extends Game and handles
 * rendering, ingredient movement, collision detection, and game over
 * conditions. The player uses a Plate to catch ingredients. If the wrong
 * ingredient is caught, the game ends.
 * <p>
 * The class uses an ArrayList of Rectangle objects for ingredients and a
 * Scoreboard to track progress.
 * 
 *
 * @see Game
 * @see Plate
 * @see Rectangle
 * @see Scoreboard
 */
class BurgerMan extends Game {
	static int counter = 0;
	private Plate plate;
	private ArrayList<Rectangle> ingredients;
	private ArrayList<Rectangle> playerBurger;
	private Scoreboard scoreboard;
	private Random random = new Random();
	private boolean gameOver = false;
	private int level = 1;
	private Rectangle.Spatula r;

	/**
	 * Constructs a new instance of the BurgerMan game. Initializes the game window,
	 * ingredient lists, scoreboard, and player-controlled plate. Also sets up the
	 * game focus and key listener for player movement.
	 */

	public BurgerMan() {
		super("BurgerMan!", 800, 600);
		this.setFocusable(true);
		this.requestFocus();
		// Initialize ingredient lists
		ingredients = new ArrayList<>();
		playerBurger = new ArrayList<>();
		scoreboard = new Scoreboard();
		scoreboard.generateExpectedList(level); // generate goal burger
		// Initialize plate at position (200, 200), no rotations, 50 W and 30 H
		plate = new Plate(new Point(350, 550));
		this.r = new Rectangle.Spatula(new Point(710, 180));

		this.addKeyListener(plate);
	}

	/**
	 * Renders the game screen, updating the state, score, and handling collisions.
	 * <p>
	 * The method continuously redraws the background, updates the score, and
	 * handles ingredient movement. It checks for collisions between falling
	 * ingredients and the plate, adding caught ingredients to the player's burger.
	 * If the wrong ingredient is caught, the game ends with a "Game Over" message.
	 * The method also spawns ingredients at random intervals and updates their
	 * positions.
	 *
	 * @param brush The Graphics object used for rendering game elements.
	 */
	public void paint(Graphics brush) {
		// Background color
		brush.setColor(Color.black);
		brush.fillRect(0, 0, width, height);
		// If game over, display message and stop updating
		if (gameOver) {
			brush.setColor(Color.red);
			brush.drawString("Game Over", width / 2 - 40, height / 2);
			return;
		}
		// Debug counter
		counter++;
		brush.setColor(Color.white);
		r.paint(brush);

		/**
		 * Functional interface for drawing text on the screen using a lambda
		 * expression.
		 * <p>
		 * The drawText lambda renders text at specified coordinates using a
		 * {@link Graphics} object. It is used to draw game labels like "Guest Order"
		 * and "Your Burger" at designated positions.
		 *
		 * @param brush   The {@link Graphics} object to draw the text.
		 * @param message The text message to display.
		 * @param x       The x-coordinate for the text position.
		 * @param y       The y-coordinate for the text position.
		 */
		Headers drawText = (g, message, x, y) -> g.drawString(message, x, y);
		// lambda
		drawText.draw(brush, "Guest Order ", 50, 40);
		drawText.draw(brush, "Your Burger", 700, 40);
		drawText.draw(brush, "Level " + level, 700, 10);

		/**
		 * Creates an anonymous implementation of the {@link Caught} class and overrides
		 * the isCaught() method.
		 * <p>
		 * This anonymous class defines an implementation of the isCaught() method,
		 * which calls the move() method of the Spatula object. The functionality of
		 * this method is to execute the movement action when the object is caught.
		 * 
		 */
		Caught caught = new Caught() {
			void isCaught() {

				r.move();

			}
		};

		// Draw expected burger on the top left
		drawExpectedBurger(brush);
		// Draw player's collected burger on the top right
		drawPlayerBurger(brush);
		// Move the plate left or right
		plate.move();
		plate.wrap();
		// Ingredient spawn rate
		if (random.nextInt(100) < 2) {
			spawnRandomIngredient();
		}

		// Move and draw ingredients
		for (int i = 0; i < ingredients.size(); i++) {
			Rectangle ingredient = ingredients.get(i);
			ingredient.position.y += 4; // dropping
			// Check for collision with plate
			if (checkCollision(ingredient, plate)) {
				scoreboard.addPlayerIngredient(ingredient);
				playerBurger.add(ingredient); // Add ingredient to player's burger

				if (!scoreboard.checkPlayerProgress()) {
					gameOver = true; // Still ends if a wrong ingredient is placed
					return;
				} else if (scoreboard.getPlayer().isEmpty()) {
					levelUp(); // Handle level increase
				}

				brush.setFont(new Font("Arial", Font.BOLD, 30));
				caught.isCaught();

				ingredients.remove(i);
				i--;
			} else if (ingredient.position.y > height) {
				ingredients.remove(i); // Remove ingredient if it falls below the canvas
				i--;
			}
			// Draw ingredient
			brush.setColor(ingredient.getColor());
			brush.fillRect((int) ingredient.position.x, (int) ingredient.position.y, (int) ingredient.getWidth(),
					(int) ingredient.getHeight());
		}

		if (playerBurger.size() == scoreboard.getExpected().size() && scoreboard.checkPlayerProgress()) {
			levelUp();
		} // increase level when burger is matched
			// Draw plate
		brush.setColor(plate.getColor());
		Point[] points = plate.getPoints();
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			xPoints[i] = (int) points[i].x;
			yPoints[i] = (int) points[i].y;
		}
		brush.fillPolygon(xPoints, yPoints, points.length);
		// don't change
		repaint();
	}

	/**
	 * Increases the player's level, clears their burger, and generates the expected
	 * scoreboard list for the new level.
	 * <p>
	 * This method increments the level by 1, clears the playerBurger, and then
	 * calls generateExpectedList to update the expected list of scores based on the
	 * new level.
	 *
	 * 
	 * @see Scoreboard#generateExpectedList
	 */
	private void levelUp() {
		level++;
		playerBurger.clear();
		scoreboard.generateExpectedList(level);
	}

	/**
	 * Draws the expected burger in the top-left corner of the screen.
	 * <p>
	 * Iterates through the expected ingredients and renders each one, stacking them
	 * vertically.
	 *
	 * @param brush The Graphics object used to draw the ingredients.
	 * @see Rectangle#getColor()
	 * @see Rectangle#getWidth()
	 * @see Rectangle#getHeight()
	 */
	private void drawExpectedBurger(Graphics brush) {
		int x = 50;
		int y = 50;
		for (Rectangle ingredient : scoreboard.getExpected()) {
			brush.setColor(ingredient.getColor());
			brush.fillRect(x, y, (int) ingredient.getWidth(), (int) ingredient.getHeight());
			y += ingredient.getHeight(); // Stack ingredients
		}
	}

	/**
	 * Draws the player's collected burger in the top-right corner of the screen.
	 * <p>
	 * Iterates through the player's burger ingredients and renders each one,
	 * stacking them vertically.
	 *
	 * @param brush The {@link Graphics} object used to draw the ingredients.
	 * @see Rectangle#getColor()
	 * @see Rectangle#getWidth()
	 * @see Rectangle#getHeight()
	 */
	private void drawPlayerBurger(Graphics brush) {
		int x = width - 100;
		int y = 50;
		for (Rectangle ingredient : playerBurger) {
			brush.setColor(ingredient.getColor());
			brush.fillRect(x, y, (int) ingredient.getWidth(), (int) ingredient.getHeight());
			y += ingredient.getHeight(); // Stack ingredients
		}
	}

	/**
	 * Returns the width of the game window.
	 *
	 * @return The width of the window.
	 */

	public int getWidth() {
		return this.width;
	}

	/**
	 * Spawns a random ingredient at a random x-position and adds it to the
	 * ingredients list.
	 * <p>
	 * The ingredient is randomly chosen from a set of predefined types (Bun, Patty,
	 * Lettuce, Tomato, Cheese). The ingredient is positioned at the top of the
	 * screen.
	 */

	private void spawnRandomIngredient() {
		int x = random.nextInt(width - 100);
		int ingredientType = random.nextInt(5);
		Rectangle ingredient;
		switch (ingredientType) {
		case 0:
			ingredient = new Rectangle.Bun(new Point(x, 0));
			break;
		case 1:
			ingredient = new Rectangle.Patty(new Point(x, 0));
			break;
		case 2:
			ingredient = new Rectangle.Lettuce(new Point(x, 0));
			break;
		case 3:
			ingredient = new Rectangle.Tomato(new Point(x, 0));
			break;
		default:
			ingredient = new Rectangle.Cheese(new Point(x, 0));
			break;
		}
		ingredients.add(ingredient);
	}

	/**
	 * Checks if two Rectangle objects collide by verifying if any point of the
	 * first rectangle is contained within the second rectangle.
	 *
	 * @param r1 the first Rectangle
	 * @param r2 the second Rectangle
	 * @return true if there is a collision, otherwise false.
	 * @see Rectangle
	 */

	private boolean checkCollision(Rectangle r1, Rectangle r2) {
		for (int i = 0; i < r1.getPoints().length; i++) {
			if (r2.contains(r1.getPoints()[i])) {

				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		BurgerMan a = new BurgerMan();
		a.repaint();
	}
}
