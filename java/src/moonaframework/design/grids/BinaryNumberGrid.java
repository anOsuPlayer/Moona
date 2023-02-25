package moonaframework.design.grids;

import moonaframework.util.exception.CoordinateOutOfRangeException;

public class BinaryNumberGrid extends BinaryGrid<Integer> {

	public void setDefaultValue1(int newValue1) {
		super.replace(value1, newValue1);
		this.value1 = Integer.valueOf(newValue1);
	}
	
	public void setDefaultValue2(int newValue2) {
		super.replace(value2, newValue2);
		this.value2 = Integer.valueOf(newValue2);
	}
	
	public void set(int value, int x, int y) throws CoordinateOutOfRangeException {
		if (!isAcceptable(value)) {
			throw new UnsupportedOperationException("Unable to set a non-default value.");
		}
		super.set(Integer.valueOf(value), x, y);
	}
	
	public void setDefaultValue(int value) {
		super.setDefaultValue(Integer.valueOf(value));
	}
	
	public void replace(int value1, int value2) {
		super.replace(Integer.valueOf(value1), Integer.valueOf(value2));
	}
	
	public void fill(int value) {
		super.fill(Integer.valueOf(value));
	}
	
	public boolean isAcceptable(int value) {
		return (value1.equals(Integer.valueOf(value)) || value2.equals(Integer.valueOf(value)));
	}
	
	public boolean has(int value) {
		return super.has(Integer.valueOf(value));
	}
	
	public BinaryNumberGrid(int width, int height, int value1, int value2) throws IllegalArgumentException {
		super(new Integer[height][width], Integer.valueOf(value1), Integer.valueOf(value2));
		if (value1 == value2) {
			throw new IllegalArgumentException("The two default values cannot be equal.");
		}
		this.value1 = Integer.valueOf(value1); this.value2 = Integer.valueOf(value2);
		super.setDefaultValue(value1);
		super.fill(value1);
	}
	public BinaryNumberGrid(int width, int height) throws IllegalArgumentException {
		this(width, height, 0, 1);
	}
	public BinaryNumberGrid() throws IllegalArgumentException {
		this(NumberGrid.DEFAULT_WIDTH, NumberGrid.DEFAULT_HEIGHT, 0, 1);
	}
}
