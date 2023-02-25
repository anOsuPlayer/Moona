package moonaframework.design.grids;

import moonaframework.design.bidimensional.Movable2D;

public class GridPoint implements Movable2D<Integer> {

	private int x;
	
	public @Override Integer getX() {
		return Integer.valueOf(x);
	}
	public @Override void setX(Integer x) {
		this.x = x.intValue();
	}
	
	private int y;
	
	public @Override Integer getY() {
		return Integer.valueOf(x);
	}
	public @Override void setY(Integer y) {
		this.y = y.intValue();
	}
	
	public GridPoint() {
		this.x = 0; this.y = 0;
	}
	public GridPoint(int x, int y) {
		this.x = x; this.y = y;
	}
}
