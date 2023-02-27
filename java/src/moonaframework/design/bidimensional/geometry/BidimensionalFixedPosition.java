package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Positional2DType;
import moonaframework.design.bidimensional.geometry.BidimensionalPoint.DoublePoint2D;
import moonaframework.design.bidimensional.geometry.BidimensionalPoint.IntegralPoint2D;
import moonaframework.design.bidimensional.geometry.BidimensionalPoint.Point2D;

public sealed abstract class BidimensionalFixedPoint<T extends Number> implements Positional2DType<T> permits BidimensionalPoint<T>, BidimensionalFixedPoint.IntegralFixedPoint2D, BidimensionalFixedPoint.FixedPoint2D, BidimensionalFixedPoint.DoubleFixedPoint2D {

	protected abstract BidimensionalPoint<T> unfixed();
	
	protected BidimensionalFixedPoint(T x, T y) {
		
	}
	protected BidimensionalFixedPoint() {
		
	}
	
	public non-sealed static class IntegralFixedPoint2D extends BidimensionalFixedPoint<Integer> implements IntegralPositional2D {
		
		protected int x;
		
		public @Override int getX() {
			return this.x;
		}
		
		protected int y;
		
		public @Override int getY() {
			return this.y;
		}
		
		public IntegralPoint2D unfixed() {
			return new IntegralPoint2D(x, y);
		}
		
		public IntegralFixedPoint2D(int x, int y) {
			this.x = x; this.y = y;
		}
		public IntegralFixedPoint2D(Integer x, Integer y) {
			this(x.intValue(), y.intValue());
		}
		public IntegralFixedPoint2D() {
			this(0, 0);
		}
	}
	
	public non-sealed static class FixedPoint2D extends BidimensionalFixedPoint<Float> implements Positional2D {
		
		protected float x;
		
		public @Override float getX() {
			return this.x;
		}
		
		protected float y;
		
		public @Override float getY() {
			return this.y;
		}
		
		public Point2D unfixed() {
			return new Point2D(x, y);
		}
		
		public FixedPoint2D(float x, float y) {
			this.x = x; this.y = y;
		}
		public FixedPoint2D(Float x, Float y) {
			this(x.floatValue(), y.floatValue());
		}
		public FixedPoint2D() {
			this(0, 0);
		}
	}
	
	public non-sealed static class DoubleFixedPoint2D extends BidimensionalFixedPoint<Double> implements DoublePositional2D {
		
		protected double x;
		
		public @Override double getX() {
			return this.x;
		}
		
		protected double y;
		
		public @Override double getY() {
			return this.y;
		}
		
		public DoublePoint2D unfixed() {
			return new DoublePoint2D(x, y);
		}
		
		public DoubleFixedPoint2D(double x, double y) {
			this.x = x; this.y = y;
		}
		public DoubleFixedPoint2D(Double x, Double y) {
			this(x.doubleValue(), y.doubleValue());
		}
		public DoubleFixedPoint2D() {
			this(0, 0);
		}
	}
}
