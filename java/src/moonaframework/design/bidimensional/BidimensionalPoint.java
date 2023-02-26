package moonaframework.design.bidimensional;

public abstract sealed class BidimensionalPoint<P extends Number> implements Movable2D<P> permits BidimensionalPoint.IntPoint2D, BidimensionalPoint.Point2D, BidimensionalPoint.PrecisePoint2D {

	public static non-sealed class IntPoint2D extends BidimensionalPoint<Integer> implements IntMovable2D {
		
		private int x;
		
		public @Override int getX() {
			return this.x;
		}
		public @Override void setX(int x) {
			this.x = x;
		}
		
		private int y;
		
		public @Override int getY() {
			return this.y;
		}
		public @Override void setY(int y) {
			this.y = y;
		}
		
		public IntPoint2D(int x, int y) {
			this.x = x; this.y = y;
		}
		public IntPoint2D() {
			this(0, 0);
		}
	}
	
	public static non-sealed class Point2D extends BidimensionalPoint<Float> implements FloatMovable2D {
		
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
	
	public static non-sealed class PrecisePoint2D extends BidimensionalPoint<Double> implements DoubleMovable2D {
		
		private double x;
		
		public @Override double getX() {
			return this.x;
		}
		public @Override void setX(double x) {
			this.x = x;
		}
		
		private double y;
		
		public @Override double getY() {
			return this.y;
		}
		public @Override void setY(double y) {
			this.y = y;
		}
		
		public PrecisePoint2D(double x, double y) {
			this.x = x; this.y = y;
		}
		public PrecisePoint2D() {
			this(0, 0);
		}
	}
	
	public BidimensionalPoint(P x, P y) {
		this.changeX(x); this.changeY(y);
	}
	protected BidimensionalPoint() {
		
	}
}
