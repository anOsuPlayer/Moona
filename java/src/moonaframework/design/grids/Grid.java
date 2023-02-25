package moonaframework.design.grids;

import moonaframework.design.bidimensional.Dimensional2D;
import moonaframework.util.exception.CoordinateOutOfRangeException;
import moonaframework.util.exception.NullArgumentException;

public class Grid<T> implements Dimensional2D<Integer> {

	private final T[][] board;
	
	public @Override Integer getWidth() {
		return Integer.valueOf(board[0].length);
	}
	
	public @Override Integer getHeight() {
		return Integer.valueOf(board.length);
	}
	
	public GridBox<T> getPoint(int x, int y) {
		return new GridBox<>(this, x, y);
	}
	public boolean belongs(GridBox<T> gb) {
		return (gb.getBaseGrid().equals(this));
	}
	
	public T valueAt(int x, int y) throws CoordinateOutOfRangeException {
		if (isContained(x, y)) {
			throw new CoordinateOutOfRangeException();
		}
		return board[y][x];
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
	
	public Grid(T[][] board) throws NullArgumentException, IllegalArgumentException {
		if (board == null) {
			throw new NullArgumentException("The board's base cannot be null.");
		}
		this.board = board;
		if (this.board.length == 0 || this.board[0].length == 0) {
			throw new NullArgumentException();
		}
	}
}
