package moonaframework.design.grids;

import moonaframework.design.geometry.CoordinateOutOfRangeException;
import moonaframework.design.geometry.bidimensional.BidimensionalBounds.IntegralBounds2D;
import moonaframework.design.geometry.bidimensional.BidimensionalSize.IntegralSize2D;
import moonaframework.design.geometry.bidimensional.Dimensional2DType.IntegralDimensional2D;
import moonaframework.util.exception.NullArgumentException;

public class Grid<T> implements IntegralDimensional2D {

	protected final T[][] board;
	
	public T[][] getBoard() {
		return board;
	}
	
	public @Override int getWidth() {
		return board[0].length;
	}	
	public @Override int getHeight() {
		return board.length;
	}
	
	public @Override IntegralSize2D getSize() {
		return new IntegralSize2D(getWidth(), getHeight());
	}
	public @Override IntegralBounds2D getBounds() {
		return new IntegralBounds2D(this);
	}
	
	public GridSpot<T> getPoint(int x, int y) {
		return new GridSpot<>(this, x, y);
	}
	public boolean belongs(GridSpot<T> gb) {
		return (gb.getBaseGrid().equals(this));
	}
	
	public T get(int x, int y) throws CoordinateOutOfRangeException {
		if (!isContained(x, y)) {
			throw new CoordinateOutOfRangeException();
		}
		return board[y][x];
	}
	public void set(T value, int x, int y) throws CoordinateOutOfRangeException {
		if (!isContained(x, y)) {
			throw new CoordinateOutOfRangeException();
		}
		board[y][x] = value;
	}
	
	public void copy(int x1, int y1, int x2, int y2) throws CoordinateOutOfRangeException {
		if (!isContained(x1, y1) || !isContained(x2, y2)) {
			throw new CoordinateOutOfRangeException();
		}
		board[y2][x2] = board[y1][x1];
	}
	public void move(int x1, int y1, int x2, int y2) throws CoordinateOutOfRangeException {
		copy(x1, y1, x2, y2);
		board[y1][x1] = defaultValue;
	}
	public void swap(int x1, int y1, int x2, int y2) throws CoordinateOutOfRangeException {
		if (!isContained(x1, y1) || !isContained(x2, y2)) {
			throw new CoordinateOutOfRangeException();
		}
		T tmp = board[y2][x2];
		board[y2][x2] = board[y1][x1];
		board[y1][x1] = tmp;
	}
	
	private T defaultValue;
	
	public T getDefaultValue() {
		return this.defaultValue;
	}
	public void setDefaultValue(T value) {
		this.defaultValue = value;
	}
	
	public void replace(T value1, T value2) {
		for (T[] arr : board) {
			for (int i = 0; i < arr.length; i++) {
				arr[i] = (arr[i].equals(value1)) ? value2 : value1;
			}
		}
	}
	
	public void fill(T value) {
		for (T[] arr : board) {
			for (int i = 0; i < arr.length; i++) {
				arr[i] = value;
			}
		}
	}
	public void clear() {
		fill(defaultValue);
	}
	
	public boolean isContained(int x, int y) {
		return (isInXRange(x) && isInYRange(y));
	}
	
	public boolean isInXRange(int x) {
		return (x >= 0 || x < getWidth());
	}
	public boolean isInYRange(int y) {
		return (y >= 0 || y < getHeight());
	}
	
	public boolean has(T value) {
		for (T[] arr : board) {
			for (T t : arr) {
				if (t == null) {
					if (value == null) {
						return true;
					}
				}
				else if (t.equals(value)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Grid(T[][] board) throws NullArgumentException, IllegalArgumentException {
		if (board == null) {
			throw new NullArgumentException("The board's base cannot be null.");
		}
		this.board = board;
		if (this.board.length == 0 || this.board[0].length == 0) {
			throw new IllegalArgumentException("The Grid's dimensions cannot be less than 1.");
		}
	}
}
