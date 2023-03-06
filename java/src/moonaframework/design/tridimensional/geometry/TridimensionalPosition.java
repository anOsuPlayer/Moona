package moonaframework.design.tridimensional.geometry;

import moonaframework.design.bidimensional.geometry.BidimensionalPosition;
import moonaframework.design.tridimensional.Positional3DType;

public interface TridimensionalPosition<T extends Number> extends BidimensionalPosition<T>, Positional3DType<T> {

	public static class IntegralPosition3D implements TridimensionalPosition<Integer>, IntegralPositional3D {
		
		protected int x;
		
		public @Override int getX() {
			return this.x;
		}
		
		protected int y;
		
		public @Override int getY() {
			return this.y;
		}
		
		protected int z;
		
		public @Override int getZ() {
			return this.z;
		}
		
		public @Override IntegralPosition3D getPosition() {
			return new IntegralPosition3D(x, y, z);
		}
		
		public @Override String toString() {
			return "[ " + x + "; " + y + "; " + z + " ]";
		}
		
		public @Override boolean equals(Object o) throws NullPointerException {
			return (o instanceof IntegralPositional3D pos) ? pos.getX() == x && pos.getY() == y && pos.getZ() == z :
				(o instanceof Positional3DType<?> postype) ? postype.getWrappedX().intValue() == x &&
				postype.getWrappedY().intValue() == y && postype.getWrappedZ().intValue() == z : false;
		}
		
		public @Override IntegralPosition3D clone() {
			return new IntegralPosition3D(x, y, z);
		}
		
		public IntegralPosition3D(int x, int y, int z) {
			this.x = x; this.y = y; this.z = z;
		}
		public IntegralPosition3D(Integer x, Integer y, Integer z) throws NullPointerException {
			this(x.intValue(), y.intValue(), z.intValue());
		}
		public IntegralPosition3D(IntegralPositional3D pos) throws NullPointerException {
			this(pos.getX(), pos.getY(), pos.getZ());
		}
		public IntegralPosition3D(Positional3DType<?> pos) throws NullPointerException {
			this(pos.getWrappedX().intValue(), pos.getWrappedY().intValue(), pos.getWrappedZ().intValue());
		}
		public IntegralPosition3D() {
			this(0, 0, 0);
		}
	}
	
	public static class Position3D implements TridimensionalPosition<Float>, Positional3D {
		
		protected float x;
		
		public @Override float getX() {
			return this.x;
		}
		
		protected float y;
		
		public @Override float getY() {
			return this.y;
		}
		
		protected float z;
		
		public @Override float getZ() {
			return this.z;
		}
		
		public @Override Position3D getPosition() {
			return new Position3D(x, y, z);
		}
		
		public @Override String toString() {
			return "[ " + x + "; " + y + "; " + z + " ]";
		}
		
		public @Override boolean equals(Object o) throws NullPointerException {
			return (o instanceof Positional3D pos) ? pos.getX() == x && pos.getY() == y && pos.getZ() == z :
				(o instanceof Positional3DType<?> postype) ? postype.getWrappedX().floatValue() == x &&
				postype.getWrappedY().floatValue() == y && postype.getWrappedZ().floatValue() == z : false;
		}
		
		public @Override Position3D clone() {
			return new Position3D(x, y, z);
		}
		
		public Position3D(float x, float y, float z) {
			this.x = x; this.y = y; this.z = z;
		}
		public Position3D(Float x, Float y, Float z) throws NullPointerException {
			this(x.floatValue(), y.floatValue(), z.floatValue());
		}
		public Position3D(Positional3D pos) throws NullPointerException {
			this(pos.getX(), pos.getY(), pos.getZ());
		}
		public Position3D(Positional3DType<?> pos) throws NullPointerException {
			this(pos.getWrappedX().floatValue(), pos.getWrappedY().floatValue(), pos.getWrappedZ().floatValue());
		}
		public Position3D() {
			this(0, 0, 0);
		}
	}
	
	public static class DoublePosition3D implements TridimensionalPosition<Double>, DoublePositional3D {
		
		protected double x;
		
		public @Override double getX() {
			return this.x;
		}
		
		protected double y;
		
		public @Override double getY() {
			return this.y;
		}
		
		protected double z;
		
		public @Override double getZ() {
			return this.z;
		}
		
		public @Override DoublePosition3D getPosition() {
			return new DoublePosition3D(x, y, z);
		}
		
		public @Override String toString() {
			return "[ " + x + "; " + y + "; " + z + " ]";
		}
		
		public @Override boolean equals(Object o) throws NullPointerException {
			return (o instanceof DoublePositional3D pos) ? pos.getX() == x && pos.getY() == y && pos.getZ() == z :
				(o instanceof Positional3DType<?> postype) ? postype.getWrappedX().doubleValue() == x &&
				postype.getWrappedY().doubleValue() == y && postype.getWrappedZ().doubleValue() == z : false;
		}
		
		public @Override DoublePosition3D clone() {
			return new DoublePosition3D(x, y, z);
		}
		
		public DoublePosition3D(double x, double y, double z) {
			this.x = x; this.y = y; this.z = z;
		}
		public DoublePosition3D(Double x, Double y, Double z) throws NullPointerException {
			this(x.doubleValue(), y.doubleValue(), z.doubleValue());
		}
		public DoublePosition3D(DoublePositional3D pos) throws NullPointerException {
			this(pos.getX(), pos.getY(), pos.getZ());
		}
		public DoublePosition3D(Positional3DType<?> pos) throws NullPointerException {
			this(pos.getWrappedX().doubleValue(), pos.getWrappedY().doubleValue(), pos.getWrappedZ().doubleValue());
		}
		public DoublePosition3D() {
			this(0, 0, 0);
		}
	}
}
