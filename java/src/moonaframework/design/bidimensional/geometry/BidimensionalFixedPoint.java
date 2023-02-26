package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Positional2DType;

public abstract class BidimensionalFixedPoint<T extends Number> implements Positional2DType<T> {

	protected BidimensionalFixedPoint(T x, T y) {
		
	}
	protected BidimensionalFixedPoint() {
		
	}
	
	public class IntegralFixedPoint2D extends BidimensionalFixedPoint<Integer> implements IntegralPositional2D {
		
		protected int x;
		
		public @Override int getX() {
			return this.x;
		}
		
		protected int y;
		
		public @Override int getY() {
			return this.y;
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
	
	public class FixedPoint2D extends BidimensionalFixedPoint<Float> implements Positional2D {
		
		protected float x;
		
		public @Override float getX() {
			return this.x;
		}
		
		protected float y;
		
		public @Override float getY() {
			return this.y;
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
	
	public class DoubleFixedPoint2D extends BidimensionalFixedPoint<Double> implements DoublePositional2D {
		
		protected double x;
		
		public @Override double getX() {
			return this.x;
		}
		
		protected double y;
		
		public @Override double getY() {
			return this.y;
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
