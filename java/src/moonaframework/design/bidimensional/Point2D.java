package moonaframework.design.bidimensional;

import moonaframework.design.bidimensional.Movable2D.FloatMovable2D;

public class Point2D implements FloatMovable2D {

	private float x;
	
	public @Override float getX() {
		return this.x;
	}
	public @Override void setX(float x) {
		this.x = x;
	}
	
	private float y;
	
	public @Override float getY() {
		return this.y;
	}
	public @Override void setY(float y) {
		this.y = y;
	}
	
	public Point2D(float x, float y) {
		this.x = x; this.y = y;
	}
	public Point2D() {
		this(0, 0);
	}
}
