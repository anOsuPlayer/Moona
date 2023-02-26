package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Movable2DType;
import moonaframework.design.bidimensional.Positional2DType.DoublePositional2D;
import moonaframework.design.bidimensional.Positional2DType.IntegralPositional2D;
import moonaframework.design.bidimensional.Positional2DType.Positional2D;
import moonaframework.util.exception.NullArgumentException;

public abstract class BidimensionalPoint<T extends Number> extends BidimensionalFixedPoint<T> implements Movable2DType<T> {

	public BidimensionalPoint(T x, T y) throws NullArgumentException {
		if (x == null || y == null) {
			throw new NullArgumentException("The Point's coordinates cannot be null.");
		}
		setWrappedX(x); setWrappedX(y);
	}
	protected BidimensionalPoint() {
		
	}
	
	public class IntegralPoint2D extends IntegralFixedPoint2D implements IntegralMovable2D {
		
		public @Override void setX(int x) {
			this.x = x;
		}
		
		public @Override void setY(int y) {
			this.y = y;
		}
		
		public IntegralPoint2D(int x, int y) {
			super(x, y);
		}
		public IntegralPoint2D() {
			this(0, 0);
		}
	}
	
	public class Point2D extends FixedPoint2D implements Movable2D {
		
		public @Override void setX(float x) {
			this.x = x;
		}
		
		public @Override void setY(float y) {
			this.y = y;
		}
		
		public Point2D(float x, float y) {
			super(x, y);
		}
		public Point2D() {
			this(0, 0);
		}
	}
	
	public class DoublePoint2D extends DoubleFixedPoint2D implements DoubleMovable2D {
		
		public @Override void setX(double x) {
			this.x = x;
		}
		
		public @Override void setY(double y) {
			this.y = y;
		}
		
		public DoublePoint2D(double x, double y) {
			super(x, y);
		}
		public DoublePoint2D() {
			this(0, 0);
		}
	}
}
