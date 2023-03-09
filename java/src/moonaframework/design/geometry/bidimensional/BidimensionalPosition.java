package moonaframework.design.geometry.bidimensional;

import moonaframework.design.geometry.Coordinate;
import moonaframework.design.geometry.monodimensional.MonodimensionalPosition;
import moonaframework.design.geometry.tridimensional.Positional3DType.DoublePositional3D;
import moonaframework.design.geometry.tridimensional.Positional3DType.IntegralPositional3D;
import moonaframework.design.geometry.tridimensional.Positional3DType.Positional3D;
import moonaframework.design.geometry.tridimensional.TridimensionalPosition.DoublePosition3D;

public interface BidimensionalPosition<T extends Number> extends MonodimensionalPosition<T>, Positional2DType<T> {

	public static class IntegralPosition2D implements BidimensionalPosition<Integer>, IntegralPositional2D {
		
		protected int x;
		
		public @Override int getX() {
			return this.x;
		}
		
		protected int y;
		
		public @Override int getY() {
			return this.y;
		}
		
		public @Override IntegralPosition2D getPosition() {
			return new IntegralPosition2D(x, y);
		}
		
		public @Override String toString() {
			return "[ " + x + "; " + y + " ]";
		}
		
		public @Override boolean equals(Object o) throws NullPointerException {
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
		public IntegralPosition2D(Integer x, Integer y) throws NullPointerException {
			this(x.intValue(), y.intValue());
		}
		public IntegralPosition2D(IntegralPositional3D pos, Coordinate x, Coordinate y) throws NullPointerException {
			switch (x) {
				case X -> this.x = pos.getX();
				case Y -> this.x = pos.getY();
				case Z -> this.x = pos.getZ();
			}
			switch (y) {
				case X -> this.y = pos.getX();
				case Y -> this.y = pos.getY();
				case Z -> this.y = pos.getZ();
			}
		}
		public IntegralPosition2D(IntegralPositional2D pos) throws NullPointerException {
			this(pos.getX(), pos.getY());
		}
		public IntegralPosition2D(Positional2DType<?> pos) throws NullPointerException {
			this(pos.getWrappedX().intValue(), pos.getWrappedY().intValue());
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
		
		public @Override Position2D getPosition() {
			return new Position2D(x, y);
		}
		
		public @Override String toString() {
			return "[ " + x + "; " + y + " ]";
		}
		
		public @Override boolean equals(Object o) throws NullPointerException {
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
		public Position2D(Float x, Float y) throws NullPointerException {
			this(x.floatValue(), y.floatValue());
		}
		public Position2D(Positional3D pos, Coordinate x, Coordinate y) throws NullPointerException {
			switch (x) {
				case X -> this.x = pos.getX();
				case Y -> this.x = pos.getY();
				case Z -> this.x = pos.getZ();
			}
			switch (y) {
				case X -> this.y = pos.getX();
				case Y -> this.y = pos.getY();
				case Z -> this.y = pos.getZ();
			}
		}
		public Position2D(Positional2D pos) throws NullPointerException {
			this(pos.getX(), pos.getY());
		}
		public Position2D(Positional2DType<?> pos) throws NullPointerException {
			this(pos.getWrappedX().floatValue(), pos.getWrappedY().floatValue());
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
		
		public @Override String toString() {
			return "[ " + x + "; " + y + " ]";
		}
		
		public @Override DoublePosition2D getPosition() {
			return new DoublePosition2D(x, y);
		}
		
		public @Override boolean equals(Object o) throws NullPointerException {
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
		public DoublePosition2D(Double x, Double y) throws NullPointerException {
			this(x.doubleValue(), y.doubleValue());
		}
		public DoublePosition2D(DoublePositional3D pos, Coordinate x, Coordinate y) throws NullPointerException {
			switch (x) {
				case X -> this.x = pos.getX();
				case Y -> this.x = pos.getY();
				case Z -> this.x = pos.getZ();
			}
			switch (y) {
				case X -> this.y = pos.getX();
				case Y -> this.y = pos.getY();
				case Z -> this.y = pos.getZ();
			}
		}
		public DoublePosition2D(DoublePositional2D pos) throws NullPointerException {
			this(pos.getX(), pos.getY());
		}
		public DoublePosition2D(Positional2DType<?> pos) throws NullPointerException {
			this(pos.getWrappedX().doubleValue(), pos.getWrappedY().doubleValue());
		}
		public DoublePosition2D() {
			this(0, 0);
		}
	}
}
