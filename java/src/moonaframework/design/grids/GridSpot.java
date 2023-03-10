package moonaframework.design.grids;

import moonaframework.design.geometry.CoordinateOutOfRangeException;
import moonaframework.design.geometry.PositionType.IntegralPosition;
import moonaframework.util.exception.NullArgumentException;

public class GridSpot<T> extends IntegralPosition {

	private final Grid<T> base;
	
	public Grid<T> getBaseGrid() {
		return this.base;
	}
	public T getValue() {
		return this.base.get(pos[0], pos[1]);
	}
	
	public int leftBorderDistance() {
		return this.pos[0];
	}
	public boolean isOnLeftBorder() {
		return this.pos[0] == 0;
	}
	
	public int rightBorderDistance() {
		return base.getWidth() - this.pos[0] - 1;
	}
	public boolean isOnRightBorder() {
		return this.pos[0] == base.getWidth()-1;
	}
	
	public int topBorderDistance() {
		return this.pos[1];
	}
	public boolean isOnTopBorder() {
		return this.pos[1] == base.getHeight()-1;
	}
	
	public int botBorderDistance() {
		return base.getHeight() - this.pos[1] - 1;
	}
	public boolean isOnBotBorder() {
		return this.pos[1] == 0;
	}
	
	public boolean isCorner() {
		return (this.pos[0] == 0 && this.pos[1] == 0) || (this.pos[0] == 0 && this.pos[1] == base.getHeight()-1) ||
				(this.pos[0] == base.getWidth()-1 && this.pos[1] == 0) || (this.pos[0] == base.getWidth()-1 || this.pos[1] == base.getHeight()-1);
	}
	
	public GridSpot(Grid<T> base, int x, int y) throws NullArgumentException, CoordinateOutOfRangeException {
		super(x, y);
		if (base == null) {
			throw new NullArgumentException("The base Grid cannot be null.");
		}
		if (!base.isContained(x, y)) {
			throw new CoordinateOutOfRangeException("The given GridSpot coordinates are out of range.");
		}
		this.base = base;
	}
}
