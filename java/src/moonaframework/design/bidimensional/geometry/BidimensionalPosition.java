package moonaframework.design.bidimensional.geometry;

import moonaframework.design.bidimensional.Movable2DType;

public sealed interface BidimensionalPosition<T extends Number> extends BidimensionalFixedPosition<T>, Movable2DType<T> permits BidimensionalPoint<T>, BidimensionalPosition.IntegralPosition2D, BidimensionalPosition.Position2D, BidimensionalPosition.DoublePosition2D {

	BidimensionalFixedPosition<T> fixed();
	
	public non-sealed static class IntegralPosition2D extends IntegralFixedPosition2D implements BidimensionalPosition<Integer>, IntegralMovable2D {
		
		public @Override void setX(int x) {
			this.x = x;
		}
		
		public @Override void setY(int y) {
			this.y = y;
		}
		
		public IntegralFixedPosition2D fixed() {
			return new IntegralFixedPosition2D(x, y);
		}
		
		public @Override IntegralPosition2D clone() {
			return new IntegralPosition2D(x, y);
		}
		
		public IntegralPosition2D(int x, int y) {
			setX(x); setY(y);
		}
		public IntegralPosition2D() {
			this(0, 0);
		}
	}
	
	public non-sealed static class Position2D extends FixedPosition2D implements BidimensionalPosition<Float>, Movable2D {
		
		public @Override void setX(float x) {
			this.x = x;
		}

		public @Override void setY(float y) {
			this.y = y;
		}
		
		public FixedPosition2D fixed() {
			return new FixedPosition2D(x, y);
		}
		
		public @Override Position2D clone() {
			return new Position2D(x, y);
		}
		
		public Position2D(float x, float y) {
			setX(x); setY(y);
		}
		public Position2D() {
			this(0, 0);
		}
	}
	
	public non-sealed static class DoublePosition2D extends DoubleFixedPosition2D implements BidimensionalPosition<Double>, DoubleMovable2D {

		public @Override void setX(double x) {
			this.x = x;
		}
		
		public @Override void setY(double y) {
			this.y = y;
		}
		
		public DoubleFixedPosition2D fixed() {
			return new DoubleFixedPosition2D(x, y);
		}
		
		public @Override DoublePosition2D clone() {
			return new DoublePosition2D(x, y);
		}
		
		public DoublePosition2D(double x, double y) {
			setX(x); setY(y);
		}
		public DoublePosition2D() {
			this(0, 0);
		}
	}
}
