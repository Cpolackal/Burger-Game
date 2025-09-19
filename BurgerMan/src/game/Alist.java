package game;

/**
 * Progress manager.
 * Interface for managing the player's progress in building a burger.
 * <p>
 * This interface defines the methods for generating the expected list of ingredients, 
 * adding ingredients to the player's burger, and checking whether the player has 
 * successfully completed the burger.
 */
interface Alist {
	void generateExpectedList(int level);
	/
	void addPlayerIngredient(Rectangle ingredient);

	boolean checkPlayerProgress();
}