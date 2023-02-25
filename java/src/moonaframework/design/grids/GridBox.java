package moonaframework.design.grids;

import moonaframework.design.bidimensional.Positional2D;
import moonaframework.util.exception.NullArgumentException;

public class GridBox<T> implements Positional2D<Integer> {
	
	private final int x;
	
	public @Override Integer getX() {
		return Integer.valueOf(x);
	}
	
	private final int y;
	
	public @Override Integer getY() {
		return Integer.valueOf(y);
	}
	
	private final Grid<T> base;
	
	public Grid<T> getBaseGrid() {
		return this.base;
	}
	public T getValue() {
		return this.base.valueAt(x, y);
	}
	
	public GridBox(Grid<T> base, int x, int y) throws NullArgumentException {
		if (base == null) {
			throw new NullArgumentException("The base Grid cannot be null.");
		}
		
		this.x = x; this.y = y;
		this.base = base;
	}
}
