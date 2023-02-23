package moonaframework.design.patterns;

public class Grid {

	private static int DEFAULT_WIDTH = 10;
	
	public static int getDefaultWidth() {
		return Grid.DEFAULT_WIDTH;
	}
	public static void setDefaultWidth(int width) {
		if (width < 0) {
			throw new IllegalArgumentException("The Grid's width cannot be negative");
		}
		Grid.DEFAULT_WIDTH = width;
	}
	
	private final int width;
	
	public int getWidth() {
		return this.width;
	}
	
	private static int DEFAULT_HEIGHT = 10;
	
	public static int getDefaultHeight() {
		return Grid.DEFAULT_HEIGHT;
	}
	public static void setDefaultHeight(int height) throws IllegalArgumentException {
		if (height < 0) {
			throw new IllegalArgumentException("The Grid's height cannot be negative");
		}
		Grid.DEFAULT_HEIGHT = height;
	}
	
	private final int height;
	
	public int getHeight() {
		return this.height;
	}
	
	public Grid() {
		this.width = DEFAULT_WIDTH; this.height = DEFAULT_HEIGHT;
	}
	public Grid(int width, int height) throws IllegalArgumentException {
		if (width < 0 || height < 0) {
			throw new IllegalArgumentException("The Grid's width or height cannot be negative.");
		}
		this.width = width; this.height = height;
	}
}
