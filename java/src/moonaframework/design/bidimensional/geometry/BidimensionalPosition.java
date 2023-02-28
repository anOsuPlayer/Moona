package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Positional2DType;
import moonaframework.design.monodimensional.geometry.MonodimensionalPosition;

public interface BidimensionalPosition<T extends Number> extends MonodimensionalPosition<T>, Positional2DType<T> {

	public static class IntegralPosition2D extends IntegralPosition1D implements BidimensionalPosition<Integer>, IntegralPositional2D {
		
		protected int y;
		
		public @Override int getY() {
			return this.y;
		}
		
		public @Override String toString() {
			return "[ " + x + "; " + y + " ]";
		}
		
		public @Override boolean equals(Object o) {
			return (o instanceof IntegralPositional2D pos) ? pos.getX() == x && pos.getY() == y :
				(o instanceof Positional2DType<?> postype) ? postype.getWrappedX().intValue() == x &&
				postype.getWrappedY().intValue() == y : false;
		}
		
		public @Override IntegralPosition2D clone() {
			return new IntegralPosition2D(x, y);
		}
		
		public IntegralPosition2D(int x, int y) {
			this.x = x; this.y = y;
		}
		public IntegralPosition2D(Integer x, Integer y) {
			this(x.intValue(), y.intValue());
		}
		public IntegralPosition2D(IntegralPositional2D pos) {
			this(pos.getX(), pos.getY());
		}
		public IntegralPosition2D(Positional2DType<?> pos) {
			this(pos.getWrappedX().intValue(), pos.getWrappedY().intValue());
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
		
		public @Override String toString() {
			return "[ " + x + "; " + y + " ]";
		}
		
		public @Override boolean equals(Object o) {
			return (o instanceof Positional2D pos) ? pos.getX() == x && pos.getY() == y :
				(o instanceof Positional2DType<?> postype) ? postype.getWrappedX().floatValue() == x &&
				postype.getWrappedY().floatValue() == y : false;
		}
		
		public @Override Position2D clone() {
			return new Position2D(x, y);
		}
		
		public Position2D(float x, float y) {
			this.x = x; this.y = y;
		}
		public Position2D(Float x, Float y) {
			this(x.floatValue(), y.floatValue());
		}
		public Position2D(Positional2D pos) {
			this(pos.getX(), pos.getY());
		}
		public Position2D(Positional2DType<?> pos) {
			this(pos.getWrappedX().floatValue(), pos.getWrappedY().floatValue());
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
		
		public @Override String toString() {
			return "[ " + x + "; " + y + " ]";
		}
		
		public @Override boolean equals(Object o) {
			return (o instanceof DoublePositional2D pos) ? pos.getX() == x && pos.getY() == y :
				(o instanceof Positional2DType<?> postype) ? postype.getWrappedX().doubleValue() == x &&
				postype.getWrappedY().doubleValue() == y : false;
		}
		
		public @Override DoublePosition2D clone() {
			return new DoublePosition2D(x, y);
		}
		
		public DoublePosition2D(double x, double y) {
			this.x = x; this.y = y;
		}
		public DoublePosition2D(Double x, Double y) {
			this(x.doubleValue(), y.doubleValue());
		}
		public DoublePosition2D(DoublePositional2D pos) {
			this(pos.getX(), pos.getY());
		}
		public DoublePosition2D(Positional2DType<?> pos) {
			this(pos.getWrappedX().doubleValue(), pos.getWrappedY().doubleValue());
		}
		public DoublePosition2D() {
			this(0, 0);
		}
	}
}
