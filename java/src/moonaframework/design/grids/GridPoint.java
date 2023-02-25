package moonaframework.design.grids;

import moonaframework.design.bidimensional.Movable2D;

public class GridPoint implements Movable2D<Long> {

	private long x;
	
	public @Override Long getX() {
		return Long.valueOf(x);
	}
	public @Override void setX(Long x) {
		this.x = x.longValue();
	}
	
	private long y;
	
	public @Override Long getY() {
		return Long.valueOf(x);
	}
	public @Override void setY(Long y) {
		this.y = y.longValue();
	}
	
	public GridPoint() {
		this.x = 0; this.y = 0;
	}
	public GridPoint(long x, long y) {
		this.x = x; this.y = y;
	}
}
