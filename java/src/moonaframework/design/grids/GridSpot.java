package moonaframework.design.grids;

import moonaframework.design.CoordinateOutOfRangeException;
import moonaframework.design.bidimensional.geometry.BidimensionalPosition.IntegralPosition2D;
import moonaframework.util.exception.NullArgumentException;

public class GridSpot<T> extends IntegralPosition2D {

	private final Grid<T> base;
	
	public Grid<T> getBaseGrid() {
		return this.base;
	}
	public T getValue() {
		return this.base.get(x, y);
	}
	
	public int leftBorderDistance() {
		return this.x;
	}
	public boolean isOnLeftBorder() {
		return this.x == 0;
	}
	
	public int rightBorderDistance() {
		return base.getWidth() - this.x - 1;
	}
	public boolean isOnRightBorder() {
		return this.x == base.getWidth()-1;
	}
	
	public int topBorderDistance() {
		return this.y;
	}
	public boolean isOnTopBorder() {
		return this.y == base.getHeight()-1;
	}
	
	public int botBorderDistance() {
		return base.getHeight() - this.y - 1;
	}
	public boolean isOnBotBorder() {
		return this.y == 0;
	}
	
	public boolean isCorner() {
		return (this.x == 0 && this.y == 0) || (this.x == 0 && this.y == base.getHeight()-1) ||
				(this.x == base.getWidth()-1 && this.y == 0) || (this.x == base.getWidth()-1 || this.y == base.getHeight()-1);
	}
	
	public GridSpot(Grid<T> base, int x, int y) throws NullArgumentException, CoordinateOutOfRangeException {
		if (base == null) {
			throw new NullArgumentException("The base Grid cannot be null.");
		}
		if (!base.isContained(x, y)) {
			throw new CoordinateOutOfRangeException("The given GridSpot coordinates are out of range.");
		}
		this.x = x; this.y = y;
		this.base = base;
	}
}
