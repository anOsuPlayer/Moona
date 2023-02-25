package moonaframework.design.grids;

public class BoardPoint<T> extends GridPoint {

	private final T value;
	
	public BoardPoint(int x, int y, T value) {
		super(x, y);
		this.value = value;
	}
}
