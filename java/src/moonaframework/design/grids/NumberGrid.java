package moonaframework.design.grids;

import moonaframework.util.exception.CoordinateOutOfRangeException;

public class NumberGrid extends Grid<Integer> {

	private static int DEFAULT_WIDTH = 10;
	
	public static int getDefaultWidth() {
		return NumberGrid.DEFAULT_WIDTH;
	}
	public static void setDefaultWidth(int width) {
		NumberGrid.DEFAULT_WIDTH = width;
	}
	
	private static int DEFAULT_HEIGHT = 10;
	
	public static int getDefaultHeight() {
		return NumberGrid.DEFAULT_HEIGHT;
	}
	public static void setDefaultHeight(int height) {
		NumberGrid.DEFAULT_HEIGHT = height;
	}
	
	public void set(int value, int x, int y) throws CoordinateOutOfRangeException {
		super.set(Integer.valueOf(value), x, y);
	}
	
	public void setDefaultValue(int value) {
		super.setDefaultValue(Integer.valueOf(value));
	}
	
	public void fill(int value) {
		super.fill(Integer.valueOf(value));
	}
	
	public NumberGrid() {
		super(new Integer[DEFAULT_HEIGHT][DEFAULT_WIDTH]);
	}
	public NumberGrid(int width, int height) throws IllegalArgumentException {
		super(new Integer[height][width]);
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException("The NumberGrid's dimensions cannot be less than one.");
		}
	}
}
