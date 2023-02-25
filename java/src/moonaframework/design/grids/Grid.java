package moonaframework.design.grids;

public class Grid extends AbstractGrid {

	private static int DEFAULT_WIDTH = 10;
	
	public static int getDefaultWidth() {
		return Grid.DEFAULT_WIDTH;
	}
	public static void setDefaultWIDTH(int width) throws IllegalArgumentException {
		if (width < 0) {
			throw new IllegalArgumentException("The Grid's default height cannot be negative.");
		}
		Grid.DEFAULT_WIDTH = width;
	}
	
	private final int width;
	
	public @Override Integer getWidth() {
		return Integer.valueOf(width);
	}
	
	private static int DEFAULT_HEIGHT = 10;
	
	public static int getDefaultHeihgt() {
		return Grid.DEFAULT_HEIGHT;
	}
	public static void setDefaultHeihgt(int height) throws IllegalArgumentException {
		if (height < 0) {
			throw new IllegalArgumentException("The Grid's default height cannot be negative.");
		}
		Grid.DEFAULT_HEIGHT = height;
	}
	
	private final int height;
	
	public @Override Integer getHeight() {
		return Integer.valueOf(height);
	}
	
	public @Override Grid baseGrid() {
		return this;
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
