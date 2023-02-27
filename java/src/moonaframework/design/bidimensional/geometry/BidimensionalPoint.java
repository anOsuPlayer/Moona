package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Movable2DType;

public interface BidimensionalPoint<T extends Number> extends BidimensionalPosition<T>, Movable2DType<T> {

	public static class IntegralPoint2D extends IntegralPosition2D implements BidimensionalPoint<Integer>, IntegralMovable2D {
		
		public @Override void setX(int x) {
			this.x = x;
		}
		
		public @Override void setY(int y) {
			this.y = y;
		}
		
		public IntegralPoint2D(int x, int y) {
			super(x, y);
		}
		public IntegralPoint2D(Integer x, Integer y) {
			super(x, y);
		}
		public IntegralPoint2D() {
			super(0, 0);
		}
	}
	
	public static class Point2D extends Position2D implements BidimensionalPoint<Float>, Movable2D {
		
		public @Override void setX(float x) {
			this.x = x;
		}
		
		public @Override void setY(float y) {
			this.y = y;
		}
		
		public Point2D(float x, float y) {
			super(x, y);
		}
		public Point2D(Float x, Float y) {
			super(x, y);
		}
		public Point2D() {
			super(0, 0);
		}
	}

	public static class DoublePoint2D extends DoublePosition2D implements BidimensionalPoint<Double>, DoubleMovable2D {
		
		public @Override void setX(double x) {
			this.x = x;
		}
		
		public @Override void setY(double y) {
			this.y = y;
		}
		
		public DoublePoint2D(double x, double y) {
			super(x, y);
		}
		public DoublePoint2D(Double x, Double y) {
			super(x, y);
		}
		public DoublePoint2D() {
			super(0, 0);
		}
	}
}
