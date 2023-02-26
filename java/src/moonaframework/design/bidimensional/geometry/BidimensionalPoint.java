package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Movable2DType;
import moonaframework.design.bidimensional.Positional2DType.DoublePositional2D;
import moonaframework.design.bidimensional.Positional2DType.IntegralPositional2D;
import moonaframework.design.bidimensional.Positional2DType.Positional2D;
import moonaframework.util.exception.NullArgumentException;

public sealed abstract class BidimensionalPoint<T extends Number> extends BidimensionalFixedPoint<T> implements Movable2DType<T> permits BidimensionalPoint.IntegralPoint2D, BidimensionalPoint.Point2D, BidimensionalPoint.DoublePoint2D {

	public abstract BidimensionalFixedPoint<T> fixed();
	
	protected BidimensionalPoint<T> unfixed() {
		return this;
	}
	
	public BidimensionalPoint(T x, T y) throws NullArgumentException {
		if (x == null || y == null) {
			throw new NullArgumentException("The Point's coordinates cannot be null.");
		}
		setWrappedX(x); setWrappedX(y);
	}
	protected BidimensionalPoint() {
		
	}
	
	public non-sealed static class IntegralPoint2D extends BidimensionalPoint<Integer> implements IntegralMovable2D {
		
		protected int x;
		
		public @Override int getX() {
			return this.x;
		}
		public @Override void setX(int x) {
			this.x = x;
		}
		
		protected int y;
		
		public @Override int getY() {
			return this.y;
		}
		public @Override void setY(int y) {
			this.y = y;
		}
		
		public IntegralFixedPoint2D fixed() {
			return new IntegralFixedPoint2D(x, y);
		}
		
		public IntegralPoint2D(int x, int y) {
			super(x, y);
		}
		public IntegralPoint2D() {
			this(0, 0);
		}
	}
	
	public non-sealed static class Point2D extends BidimensionalPoint<Float> implements Movable2D {
		
		protected float x;
		
		public @Override float getX() {
			return this.x;
		}
		public @Override void setX(float x) {
			this.x = x;
		}
		
		protected float y;
		
		public @Override float getY() {
			return this.y;
		}
		public @Override void setY(float y) {
			this.y = y;
		}
		
		public FixedPoint2D fixed() {
			return new FixedPoint2D(x, y);
		}
		
		public Point2D(float x, float y) {
			super(x, y);
		}
		public Point2D() {
			this(0, 0);
		}
	}
	
	public non-sealed static class DoublePoint2D extends BidimensionalPoint<Double> implements DoubleMovable2D {
		
		protected double x;
		
		public @Override double getX() {
			return this.x;
		}
		public @Override void setX(double x) {
			this.x = x;
		}
		
		protected double y;
		
		public @Override double getY() {
			return this.y;
		}
		public @Override void setY(double y) {
			this.y = y;
		}
		
		public DoubleFixedPoint2D fixed() {
			return new DoubleFixedPoint2D(x, y);
		}
		
		public DoublePoint2D(double x, double y) {
			super(x, y);
		}
		public DoublePoint2D() {
			this(0, 0);
		}
	}
}
