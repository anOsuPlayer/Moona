package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Positional2DType;

public sealed interface BidimensionalFixedPoint<T extends Number> extends BidimensionalFixedPosition<T>, Positional2DType<T> permits BidimensionalPoint<T>, BidimensionalFixedPoint.IntegralFixedPoint2D, BidimensionalFixedPoint.FixedPoint2D, BidimensionalFixedPoint.DoubleFixedPoint2D {

	public non-sealed static class IntegralFixedPoint2D extends IntegralFixedPosition2D implements BidimensionalFixedPoint<Integer>, IntegralPositional2D {
		
		public @Override IntegralFixedPoint2D clone() {
			return new IntegralFixedPoint2D(x, y);
		}
		
		public IntegralFixedPoint2D(int x, int y) {
			super(x, y);
		}
		public IntegralFixedPoint2D(Integer x, Integer y) {
			super(x.intValue(), y.intValue());
		}
		public IntegralFixedPoint2D() {
			super(0, 0);
		}
	}
	
	public non-sealed static class FixedPoint2D extends FixedPosition2D implements BidimensionalFixedPoint<Float>, Positional2D {
		
		public @Override FixedPoint2D clone() {
			return new FixedPoint2D(x, y);
		}
		
		public FixedPoint2D(float x, float y) {
			super(x, y);
		}
		public FixedPoint2D(Float x, Float y) {
			super(x.floatValue(), y.floatValue());
		}
		public FixedPoint2D() {
			super(0, 0);
		}
	}

	public non-sealed static class DoubleFixedPoint2D extends DoubleFixedPosition2D implements BidimensionalFixedPoint<Double>, DoublePositional2D {
		
		public @Override DoubleFixedPoint2D clone() {
			return new DoubleFixedPoint2D(x, y);
		}
		
		public DoubleFixedPoint2D(double x, double y) {
			super(x, y);
		}
		public DoubleFixedPoint2D(Double x, Double y) {
			super(x.doubleValue(), y.doubleValue());
		}
		public DoubleFixedPoint2D() {
			super(0, 0);
		}
	}
}
