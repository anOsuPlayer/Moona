package moonaframework.design.grids;

import moonaframework.design.bidimensional.Positional2D;
import moonaframework.util.exception.CoordinateOutOfRangeException;
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
		return this.base.get(x, y);
	}
	
	public GridBox(Grid<T> base, int x, int y) throws NullArgumentException, CoordinateOutOfRangeException {
		if (base == null) {
			throw new NullArgumentException("The base Grid cannot be null.");
		}
		if (!base.isContained(x, y)) {
			throw new CoordinateOutOfRangeException("The given point coordinates are out of range.");
		}
		this.x = x; this.y = y;
		this.base = base;
	}
}
