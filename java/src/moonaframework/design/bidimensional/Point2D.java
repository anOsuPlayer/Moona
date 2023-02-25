package moonaframework.design.bidimensional;

public class Point2D implements Movable2D<Float> {

	private float x;
	
	public @Override Float getX() {
		return Float.valueOf(x);
	}
	public @Override void setX(Float x) {
		this.x = x.floatValue();
	}
	
	private float y;
	
	public @Override Float getY() {
		return Float.valueOf(x);
	}
	public @Override void setY(Float y) {
		this.y = y.floatValue();
	}
	
	public Point2D() {
		this.x = 0.0f; this.y = 0.0f;
	}
	public Point2D(float x, float y) {
		this.x = x; this.y = y;
	}
}
