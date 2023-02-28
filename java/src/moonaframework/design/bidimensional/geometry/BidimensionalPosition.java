package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Positional2DType;
import moonaframework.design.bidimensional.geometry.BidimensionalPoint.DoublePoint2D;
import moonaframework.design.monodimensional.geometry.MonodimensionalPosition;

public interface BidimensionalPosition<T extends Number> extends MonodimensionalPosition<T>, Positional2DType<T> {

	public static class IntegralPosition2D extends IntegralPosition1D implements BidimensionalPosition<Integer>, IntegralPositional2D {
		
		protected int y;
		
		public @Override int getY() {
			return this.y;
		}
		
		public IntegralPosition2D(int x, int y) {
			this.x = x; this.y = y;
		}
		public IntegralPosition2D(Integer x, Integer y) {
			this(x.intValue(), y.intValue());
		}
		public IntegralPosition2D() {
			this(0, 0);
		}
	}
	
	public static class Position2D extends Position1D implements BidimensionalPosition<Float>, Positional2D {
		
		protected float y;
		
		public @Override float getY() {
			return this.y;
		}
		
		public Position2D(float x, float y) {
			this.x = x; this.y = y;
		}
		public Position2D(Float x, Float y) {
			this(x.floatValue(), y.floatValue());
		}
		public Position2D() {
			this(0, 0);
		}
	}

	public static class DoublePosition2D extends DoublePosition1D implements BidimensionalPosition<Double>, DoublePositional2D {
		
		protected double y;
		
		public @Override double getY() {
			return this.y;
		}
		
		public DoublePosition2D(double x, double y) {
			this.x = x; this.y = y;
		}
		public DoublePosition2D(Double x, Double y) {
			this(x.doubleValue(), y.doubleValue());
		}
		public DoublePosition2D() {
			this(0, 0);
		}
	}
}
