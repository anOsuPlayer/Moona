package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Positional2DType;
import moonaframework.design.bidimensional.geometry.BidimensionalPosition.DoublePosition2D;
import moonaframework.design.bidimensional.geometry.BidimensionalPosition.IntegralPosition2D;
import moonaframework.design.bidimensional.geometry.BidimensionalPosition.Position2D;

public sealed interface BidimensionalFixedPosition<T extends Number> extends Positional2DType<T> permits BidimensionalPosition<T>, BidimensionalFixedPoint<T>, BidimensionalFixedPosition.IntegralFixedPosition2D, BidimensionalFixedPosition.FixedPosition2D, BidimensionalFixedPosition.DoubleFixedPosition2D {
	
	public non-sealed static class IntegralFixedPosition2D implements BidimensionalFixedPosition<Integer>, IntegralPositional2D {
		
		protected int x;
		
		public @Override int getX() {
			return this.x;
		}
		
		protected int y;
		
		public @Override int getY() {
			return this.y;
		}
		
		public IntegralPosition2D unfixed() {
			return new IntegralPosition2D(x, y);
		}
		
		public @Override String toString() {
			return "[ " + x + "; " + y + " ]";
		}
		
		public @Override boolean equals(Object o) {
			return (o instanceof IntegralPositional2D pos) ? pos.getX() == this.x && pos.getY() == this.y : false;
		}
		
		public @Override IntegralFixedPosition2D clone() {
			return new IntegralFixedPosition2D(x, y);
		}
		
		public IntegralFixedPosition2D(int x, int y) {
			this.x = x; this.y = y;
		}
		public IntegralFixedPosition2D(Integer x, Integer y) {
			this(x.intValue(), y.intValue());
		}
		public IntegralFixedPosition2D() {
			this(0, 0);
		}
	}
	
	public non-sealed static class FixedPosition2D implements BidimensionalFixedPosition<Float>, Positional2D {
		
		protected float x;
		
		public @Override float getX() {
			return this.x;
		}
		
		protected float y;
		
		public @Override float getY() {
			return this.y;
		}
		
		public Position2D unfixed() {
			return new Position2D(x, y);
		}
		
		public @Override String toString() {
			return "[ " + x + "; " + y + " ]";
		}
		
		public @Override boolean equals(Object o) {
			return (o instanceof Positional2D pos) ? pos.getX() == this.x && pos.getY() == this.y : false;
		}
		
		public @Override FixedPosition2D clone() {
			return new FixedPosition2D(x, y);
		}
		
		public FixedPosition2D(float x, float y) {
			this.x = x; this.y = y;
		}
		public FixedPosition2D(Float x, Float y) {
			this(x.floatValue(), y.floatValue());
		}
		public FixedPosition2D() {
			this(0, 0);
		}
	}
	
	public non-sealed static class DoubleFixedPosition2D implements BidimensionalFixedPosition<Double>, DoublePositional2D {
		
		protected double x;
		
		public @Override double getX() {
			return this.x;
		}
		
		protected double y;
		
		public @Override double getY() {
			return this.y;
		}
		
		public DoublePosition2D unfixed() {
			return new DoublePosition2D(x, y);
		}
		
		public @Override String toString() {
			return "[ " + x + "; " + y + " ]";
		}
		
		public @Override boolean equals(Object o) {
			return (o instanceof DoublePositional2D pos) ? pos.getX() == this.x && pos.getY() == this.y : false;
		}
		
		public @Override DoubleFixedPosition2D clone() {
			return new DoubleFixedPosition2D(x, y);
		}
		
		public DoubleFixedPosition2D(double x, double y) {
			this.x = x; this.y = y;
		}
		public DoubleFixedPosition2D(Double x, Double y) {
			this(x.doubleValue(), y.doubleValue());
		}
		public DoubleFixedPosition2D() {
			this(0, 0);
		}
	}
}
