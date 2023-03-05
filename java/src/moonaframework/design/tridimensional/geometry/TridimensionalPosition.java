package moonaframework.design.tridimensional.geometry;

import moonaframework.design.bidimensional.Positional2DType;
import moonaframework.design.bidimensional.Positional2DType.IntegralPositional2D;
import moonaframework.design.bidimensional.geometry.BidimensionalPosition;
import moonaframework.design.bidimensional.geometry.BidimensionalPosition.IntegralPosition2D;
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
			return (o instanceof IntegralPositional2D pos) ? pos.getX() == x && pos.getY() == y :
				(o instanceof Positional2DType<?> postype) ? postype.getWrappedX().intValue() == x &&
				postype.getWrappedY().intValue() == y : false;
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
}
