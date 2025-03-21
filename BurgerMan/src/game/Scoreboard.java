package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Scoreboard class manages the expected ingredients for a burger and the
 * player's progress in assembling the burger. It implements the Alist interface
 * and provides methods for generating expected ingredient lists, adding player
 * ingredients, and checking the player's progress in completing the burger.
 * <p>
 * The class generates a list of ingredients for the burger based on the current
 * level, with a bun at both ends and random middle ingredients (patty, lettuce,
 * tomato, or cheese). The player must add the correct ingredients in the right
 * order to complete the burger and move to the next level.
 * 
 * 
 * @see Alist
 * @see Rectangle
 */
class Scoreboard implements Alist {
	private ArrayList<Rectangle> expected;
	private ArrayList<Rectangle> player;
	private Random random;

	/**
	 * Constructs a new Scoreboard object, initializing the expected and player
	 * ingredient lists and the random generator.
	 * <p>
	 * This constructor sets up the initial state of the Scoreboard by creating new
	 * Arraylist objects for storing the expected and player ingredients, and
	 * initializing the Random object used for random ingredient generation.
	 */
	public Scoreboard() {
		expected = new ArrayList<>();
		player = new ArrayList<>();
		random = new Random();
	}

	
	/**
	 * Clears the list of expected ingredients, resetting the expected burger sequence.
	 */
	public void clearExpected() {
		expected.clear();
	}
	
	/**
	 * Generates the expected list of ingredients for the burger based on the given level.
	 * <p>
	 * The burger always starts and ends with a bun. The number of middle ingredients
	 * increases with the level, and they are randomly selected from patty, lettuce, tomato,
	 * and cheese.
	 *
	 * @param level The current level, which determines the number of middle ingredients.
	 */
	@Override
	public void generateExpectedList(int level) {
		expected.clear();
		expected.add(new Rectangle.Bun(new Point(0, 0))); // First ingredient must be a bun

		for (int i = 1; i < level + 4; i++) { // Generate middle ingredients
			int ingredientType = random.nextInt(4) + 1; // Exclude bun (0)
			Rectangle ingredient;
			switch (ingredientType) {
			case 1:
				ingredient = new Rectangle.Patty(new Point(0, 0));
				break;
			case 2:
				ingredient = new Rectangle.Lettuce(new Point(0, 0));
				break;
			case 3:
				ingredient = new Rectangle.Tomato(new Point(0, 0));
				break;
			default:
				ingredient = new Rectangle.Cheese(new Point(0, 0));
				break;
			}
			expected.add(ingredient);
		}

		expected.add(new Rectangle.Bun(new Point(0, 0))); // Last ingredient must be a bun
	}

	/**
	 * Adds an ingredient to the player's burger.
	 * <p>
	 * This method adds the specified {@link Rectangle} ingredient to the player's
	 * list of ingredients, which they are using to assemble their burger.
	 * 
	 * 
	 * @param ingredient The {@link Rectangle} object representing the ingredient to
	 *                   add.
	 */
	@Override
	public void addPlayerIngredient(Rectangle ingredient) {
		player.add(ingredient);
	}

	/**
	 * Checks the player's progress in completing the burger.
	 * <p>
	 * This method compares the player's ingredients with the expected ingredients.
	 * If the player has added all ingredients correctly in order, they complete the
	 * burger, and the level is increased. If there is a mismatch, the player has
	 * not completed the burger correctly.
	 * 
	 * @return true if the player is progressing or has completed the burger
	 *         correctly, false otherwise.
	 */
	@Override
	public boolean checkPlayerProgress() {

		if (player.size() == expected.size()) {
			for (int i = 0; i < player.size(); i++) {
				if (!player.get(i).getClass().equals(expected.get(i).getClass())) {
					return false;
				}
			}

			player.clear(); // Clear player burger
			generateExpectedList(expected.size() - 2 + 1); // Increase ingredient count
			return true; // Continue playing
		}
		return true;
	}

	/**
	 * Returns the list of expected ingredients.
	 * <p>
	 * This method returns the list of {@link Rectangle} ingredients that are
	 * expected to be added to the burger in the correct order.
	 * 
	 * @return The list of expected {@link Rectangle} ingredients for the burger.
	 */
	public ArrayList<Rectangle> getExpected() {
		return expected;
	}

	/**
	 * Returns the list of ingredients added by the player.
	 * <p>
	 * This method returns the list of {@link Rectangle} ingredients that the player
	 * has added to their burger.
	 * 
	 * @return The list of {@link Rectangle} ingredients added by the player.
	 */
	public ArrayList<Rectangle> getPlayer() {
		return player;
	}
}
