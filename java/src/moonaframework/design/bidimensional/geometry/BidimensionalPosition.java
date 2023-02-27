package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Positional2DType;

public interface BidimensionalPosition<T extends Number> extends Positional2DType<T> {

	public static class IntegralPosition2D implements BidimensionalPosition<Integer>, IntegralPositional2D {
		
		protected int x;
		
		public @Override int getX() {
			return this.x;
		}
		
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
	
	public static class Position2D implements BidimensionalPosition<Float>, Positional2D {
		
		protected float x;
		
		public @Override float getX() {
			return this.x;
		}
		
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

	public static class DoublePosition2D implements BidimensionalPosition<Double>, DoublePositional2D {
		
		protected double x;
		
		public @Override double getX() {
			return this.x;
		}
		
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
