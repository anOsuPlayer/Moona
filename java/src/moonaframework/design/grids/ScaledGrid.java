package moonaframework.design.grids;

public class ScaledGrid<T> extends Grid<T> {

	private final int scale;
	
	public int getScale() {
		return this.scale;
	}
	
	public ScaledGrid(T[][] board, int scale) throws IllegalArgumentException {
		super(board);
		if (scale < 1) {
			throw new IllegalArgumentException("The given scale cannot be less than 1.");
		}
		this.scale = scale;
	}
}
