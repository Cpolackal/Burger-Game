package game;

import java.awt.*;

import game.Point;
import game.Rectangle;

/**
 * Represents a rectangle shape with a specified width, height, and color.
 * <p>
 * This class extends {@link Polygon} and provides methods for retrieving the
 * dimensions (width and height) and color of the rectangle.
 * 
 * @see Polygon
 */
public class Rectangle extends Polygon {
	private int rWidth, rHeight; // w and h of rectangle
	private Color color;

	/**
	 * Constructs a new Rectangle with the given parameters.
	 * <p>
	 * Initializes the shape, position, rotation, dimensions, and color of the
	 * rectangle.
	 * 
	 * 
	 * @param inShape    The shape of the rectangle as an array of {@link Point}.
	 * @param inPosition The position of the rectangle.
	 * @param inRotation The rotation of the rectangle.
	 * @param rWidth     The width of the rectangle.
	 * @param rHeight    The height of the rectangle.
	 * @param color      The color of the rectangle.
	 */
	public Rectangle(Point[] inShape, Point inPosition, double inRotation, int rWidth, int rHeight, Color color) {
		super(inShape, inPosition, inRotation);
		this.rWidth = rWidth;
		this.rHeight = rHeight;
		this.color = color;
	}

	/**
	 * Gets the width of the rectangle.
	 * 
	 * @return The width of the rectangle.
	 */
	public double getWidth() {
		return rWidth;
	}

	/**
	 * Gets the height of the rectangle.
	 * 
	 * @return The height of the rectangle.
	 */
	public double getHeight() {
		return rHeight;
	}

	/**
	 * Gets the color of the rectangle.
	 * 
	 * @return The color of the rectangle.
	 */
	public Color getColor() {
		return color;
	}

	void paint(Graphics brush) {

	}

	/**
	 * Represents a spatula, a specialized type of {@link Polygon}.
	 * <p>
	 * This class defines the shape and painting logic for a spatula, which consists
	 * of a blade and a handle.
	 */
	public static class Spatula extends Polygon {
		public Spatula(Point position) {
			super(new Point[] {
					
					new Point(0, 0), new Point(30, 0), new Point(30, 30), new Point(20, 30),

					
					new Point(20, 100), new Point(10, 100), new Point(10, 30), 
					new Point(0, 30) }, position, 0);

		}

		void paint(Graphics brush) {
			/**
			 * Constructs a new Spatula at the specified position.
			 * 
			 * @param position The position of the spatula.
			 */
			brush.setColor(Color.DARK_GRAY); 
			Point[] points = this.getPoints(); 
			int[] xPoints = new int[points.length]; 
			int[] yPoints = new int[points.length];

		
			for (int i = 0; i < points.length; i++) {
				xPoints[i] = (int) points[i].x; 
				yPoints[i] = (int) points[i].y;
			}

		
			brush.fillPolygon(xPoints, yPoints, points.length);

		}
	    /**
         * Rotates the spatula by 72 degrees.
         */
		public void move() {

			this.rotation += 72;

		}
	}
	 /**
     * Represents a bun in the burger.
     * <p>
     * This class defines the shape and appearance of the top or bottom bun of a burger.
     */
	public static class Bun extends Rectangle {
		 /**
         * Constructs a new Bun at the specified position.
         * 
         * @param position The position of the bun.
         */
		public Bun(Point position) {
			super(new Point[] { new Point(0, 0), new Point(60, 0), new Point(60, 20), 
					new Point(0, 20) }, position, 0,
					60, 20, new Color(255, 204, 153)); // Light brown color
		}
	}
	/**
     * Represents a tomato ingredient in the burger.
     * <p>
     * This class defines the shape and appearance of the tomato slice in the burger.
     */
	public static class Tomato extends Rectangle {
		 /**
         * Constructs a new Tomato at the specified position.
         * 
         * @param position The position of the tomato slice.
         */
		public Tomato(Point position) {
			super(new Point[] { new Point(0, 0), new Point(55, 0), new Point(55, 10), 
					new Point(0, 10) }, position, 0,
					55, 10, new Color(255, 0, 0)); // red
		}
	}
	 /**
     * Represents a cheese ingredient in the burger.
     * <p>
     * This class defines the shape and appearance of the cheese slice in the burger.
     */

	public static class Cheese extends Rectangle {
		/**
         * Constructs a new Cheese at the specified position.
         * 
         * @param position The position of the cheese slice.
         */
		public Cheese(Point position) {
			super(new Point[] { new Point(0, 0), new Point(50, 0), new Point(50, 10), 
					new Point(0, 10) }, position, 0,
					50, 10, new Color(255, 223, 0)); // Yellow cheese
		}
	}
	/**
     * Represents a lettuce ingredient in the burger.
     * <p>
     * This class defines the shape and appearance of the lettuce in the burger.
     * </p>
     */
	public static class Lettuce extends Rectangle {
		public Lettuce(Point position) {
			/**
	         * Constructs a new Lettuce at the specified position.
	         * 
	         * @param position The position of the lettuce slice.
	         */
			super(new Point[] { new Point(0, 0), new Point(65, 0), new Point(65, 10), 
					new Point(0, 10) }, position, 0,65, 10, new Color(50, 205, 50)); // Green lettuce

		}
	}
	/**
     * Represents a patty ingredient in the burger.
     * <p>
     * This class defines the shape and appearance of the patty in the burger.
     */
	public static class Patty extends Rectangle {
		public Patty(Point position) {
			/**
	         * Constructs a new patty at the specified position.
	         * 
	         * @param position The position of the patty slice.
	         */
			super(new Point[] { new Point(0, 0), new Point(50, 0), new Point(50, 15), 
					new Point(0, 15) }, position, 0,
					50, 15, new Color(139, 69, 19)); // Dark brown patty
		}
	}

}
