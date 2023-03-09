package moonaframework.design.geometry.bidimensional;

import moonaframework.design.geometry.Coordinate;
import moonaframework.design.geometry.monodimensional.MonodimensionalPoint;
import moonaframework.design.geometry.tridimensional.Positional3DType.DoublePositional3D;
import moonaframework.design.geometry.tridimensional.Positional3DType.IntegralPositional3D;
import moonaframework.design.geometry.tridimensional.Positional3DType.Positional3D;

public interface BidimensionalPoint<T extends Number> extends BidimensionalPosition<T>, Movable2DType<T>, MonodimensionalPoint<T> {

	public static class IntegralPoint2D extends IntegralPosition2D implements BidimensionalPoint<Integer>, IntegralMovable2D {
		
		public @Override void setX(int x) {
			this.x = x;
		}
		
		public @Override void setY(int y) {
			this.y = y;
		}
		
		public @Override String toString() {
			return "( " + x + "; " + y + " )";
		}
		
		public @Override IntegralPoint2D clone() {
			return new IntegralPoint2D(x, y);
		}
		
		public IntegralPoint2D(int x, int y) {
			super(x, y);
		}
		public IntegralPoint2D(Integer x, Integer y) throws NullPointerException {
			super(x, y);
		}
		public IntegralPoint2D(IntegralPositional3D pos, Coordinate x, Coordinate y) throws NullPointerException {
			super(pos, x, y);
		}
		public IntegralPoint2D(IntegralPositional2D pos) throws NullPointerException {
			super(pos);
		}
		public IntegralPoint2D(Positional2DType<?> pos) throws NullPointerException {
			super(pos);
		}
		public IntegralPoint2D() {
			super(0, 0);
		}
	}
	
	public static class Point2D extends Position2D implements BidimensionalPoint<Float>, Movable2D {
		
		public @Override void setX(float x) {
			this.x = x;
		}
		
		public @Override void setY(float y) {
			this.y = y;
		}
		
		public @Override String toString() {
			return "( " + x + "; " + y + " )";
		}
		
		public @Override Point2D clone() {
			return new Point2D(x, y);
		}
		
		public Point2D(float x, float y) {
			super(x, y);
		}
		public Point2D(Float x, Float y) throws NullPointerException {
			super(x, y);
		}
		public Point2D(Positional3D pos, Coordinate x, Coordinate y) throws NullPointerException {
			super(pos, x, y);
		}
		public Point2D(Positional2D pos) throws NullPointerException {
			super(pos);
		}
		public Point2D(Positional2DType<?> pos) throws NullPointerException {
			super(pos);
		}
		public Point2D() {
			super(0, 0);
		}
	}

	public static class DoublePoint2D extends DoublePosition2D implements BidimensionalPoint<Double>, DoubleMovable2D {
		
		public @Override void setX(double x) {
			this.x = x;
		}
		
		public @Override void setY(double y) {
			this.y = y;
		}
		
		public @Override String toString() {
			return "( " + x + "; " + y + " )";
		}
		
		public @Override DoublePoint2D clone() {
			return new DoublePoint2D(x, y);
		}
		
		public DoublePoint2D(double x, double y) {
			super(x, y);
		}
		public DoublePoint2D(Double x, Double y) throws NullPointerException {
			super(x, y);
		}
		public DoublePoint2D(DoublePositional3D pos, Coordinate x, Coordinate y) throws NullPointerException {
			super(pos, x, y);
		}
		public DoublePoint2D(DoublePositional2D pos) throws NullPointerException {
			super(pos);
		}
		public DoublePoint2D(Positional2DType<?> pos) throws NullPointerException {
			super(pos);
		}
		public DoublePoint2D() {
			super(0, 0);
		}
	}
}
