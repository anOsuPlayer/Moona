package moonaframework.design.grids;

import moonaframework.util.exception.NullArgumentException;

public class Board<T> extends AbstractGrid {

	private final T[][] board;
	
	public @Override Integer getWidth() {
		return Integer.valueOf(board[0].length);
	}
	
	public @Override Integer getHeight() {
		return Integer.valueOf(board.length);
	}
	
	public @Override Grid baseGrid() {
		return new Grid(board[0].length, board.length);
	}
	
	public Board(T[][] board) throws NullArgumentException, IllegalArgumentException {
		if (board == null) {
			throw new NullArgumentException("The board's base cannot be null.");
		}
		this.board = board;
		if (this.board.length == 0 || this.board[0].length == 0) {
			throw new NullArgumentException();
		}
	}
}
