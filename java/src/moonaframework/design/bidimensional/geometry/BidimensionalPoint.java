package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Movable2DType;

public sealed interface BidimensionalPoint<T extends Number> extends BidimensionalFixedPoint<T>, BidimensionalPosition<T>, Movable2DType<T> permits BidimensionalPoint.IntegralPoint2D, BidimensionalPoint.Point2D, BidimensionalPoint.DoublePoint2D {

	public non-sealed static class IntegralPoint2D extends IntegralPosition2D implements BidimensionalPoint<Integer>, IntegralMovable2D {
		
		public @Override IntegralPoint2D clone() {
			return new IntegralPoint2D(x, y);
		}
		
		public IntegralPoint2D(int x, int y) {
			super(x, y);
		}
		public IntegralPoint2D(Integer x, Integer y) {
			super(x.intValue(), y.intValue());
		}
		public IntegralPoint2D() {
			super(0, 0);
		}
	}
	
	public non-sealed static class Point2D extends Position2D implements BidimensionalPoint<Float>, Movable2D {
		
		public @Override Point2D clone() {
			return new Point2D(x, y);
		}
		
		public Point2D(float x, float y) {
			super(x, y);
		}
		public Point2D(Float x, Float y) {
			super(x.floatValue(), y.floatValue());
		}
		public Point2D() {
			super(0, 0);
		}
	}

	public non-sealed static class DoublePoint2D extends DoublePosition2D implements BidimensionalPoint<Double>, DoubleMovable2D {
		
		public @Override DoublePoint2D clone() {
			return new DoublePoint2D(x, y);
		}
		
		public DoublePoint2D(double x, double y) {
			super(x, y);
		}
		public DoublePoint2D(Double x, Double y) {
			super(x.doubleValue(), y.doubleValue());
		}
		public DoublePoint2D() {
			super(0, 0);
		}
	}
}