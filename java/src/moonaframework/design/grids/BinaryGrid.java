package moonaframework.design.grids;

import moonaframework.util.exception.CoordinateOutOfRangeException;

public class BinaryGrid<T> extends Grid<T> {

	protected T value1;
	
	public T getDefaultValue1() {
		return this.value1;
	}
	public void setDefaultValue1(T newValue1) {
		super.replace(value1, newValue1);
		this.value1 = newValue1;
	}
	
	protected T value2;
	
	public T getDefaultValue2() {
		return this.value2;
	}
	public void setDefaultValue2(T newValue2) {
		super.replace(value2, newValue2);
		this.value1 = newValue2;
	}
	
	public @Override void set(T value, int x, int y) throws CoordinateOutOfRangeException, UnsupportedOperationException {
		if (!isAcceptable(value)) {
			throw new UnsupportedOperationException("Unable to set a non-default value.");
		}
		super.set(value, x, y);
	}
	
	public void setValue1(int x, int y) throws CoordinateOutOfRangeException {
		if (!isContained(x, y)) {
			throw new CoordinateOutOfRangeException();
		}
		super.set(value1, x, y);
	}
	public void setValue2(int x, int y) throws CoordinateOutOfRangeException {
		if (!isContained(x, y)) {
			throw new CoordinateOutOfRangeException();
		}
		super.set(value2, x, y);
	}
	
	public void flip(int x, int y) throws CoordinateOutOfRangeException {
		if (!isContained(x, y)) {
			throw new CoordinateOutOfRangeException();
		}
		if (board[y][x].equals(value1)) { 
			board[y][x] = value2;
		}
		else {
			board[y][x] = value1;
		}
	}
	public void flipAll() {
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[0].length; x++) {
				if (board[y][x].equals(value1)) { 
					board[y][x] = value2;
				}
				else {
					board[y][x] = value1;
				}
			}
		}
	}
	
	public @Override void setDefaultValue(T value) throws UnsupportedOperationException {
		if (!isAcceptable(value)) {
			throw new UnsupportedOperationException("Unable to change with a non-default value.");
		}
		super.setDefaultValue(value);
	}
	
	public @Override void replace(T value1, T value2) throws UnsupportedOperationException {
		if (!isAcceptable(value1) || !isAcceptable(value2)) {
			throw new UnsupportedOperationException("Unable to replace with a non-default value.");
		}
		super.replace(value1, value2);
	}
	
	public @Override void fill(T value) throws UnsupportedOperationException {
		if (!isAcceptable(value)) {
			throw new UnsupportedOperationException("Unable to fill with a non-default value.");
		}
		super.fill(value);
	}
	
	public void evenToValue1() {
		super.fill(value1);
	}
	public void evenToValue2() {
		super.fill(value2);
	}
	
	public boolean isAcceptable(T value) {
		return (value.equals(value1) || value.equals(value2));
	}
	
	public @Override boolean has(T value) {
		return super.has(value);
	}
	
	public BinaryGrid(T[][] board, T value1, T value2) throws IllegalArgumentException {
		super(board);
		if (value1.equals(value2)) {
			throw new IllegalArgumentException("The two default values cannot be equal.");
		}
		this.value1 = value1; this.value2 = value2;
		super.setDefaultValue(value1);
		super.fill(value1);
	}
}
