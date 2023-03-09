package moonaframework.design.geometry.tridimensional;

import moonaframework.design.geometry.bidimensional.BidimensionalPoint;
import moonaframework.design.geometry.tridimensional.Movable3DType.DoubleMovable3D;
import moonaframework.design.geometry.tridimensional.Movable3DType.IntegralMovable3D;
import moonaframework.design.geometry.tridimensional.Movable3DType.Movable3D;

public interface TridimensionalPoint<T extends Number> extends TridimensionalPosition<T>, BidimensionalPoint<T> {
	
	public static class IntegralPoint3D extends IntegralPosition3D implements TridimensionalPoint<Integer>, IntegralMovable3D {
		
		public @Override void setX(int x) {
			this.x = x;
		}
		
		public @Override void setY(int y) {
			this.y = y;
		}
		
		public @Override void setZ(int z) {
			this.z = z;
		}
		
		public @Override String toString() {
			return "( " + x + "; " + y + "; " + z + " )";
		}
		
		public @Override IntegralPoint3D clone() {
			return new IntegralPoint3D(x, y, z);
		}
		
		public IntegralPoint3D(int x, int y, int z) {
			super(x, y, z);
		}
		public IntegralPoint3D(Integer x, Integer y, Integer z) throws NullPointerException {
			super(x, y, z);
		}
		public IntegralPoint3D(IntegralPositional3D pos) throws NullPointerException {
			super(pos);
		}
		public IntegralPoint3D(Positional3DType<?> pos) throws NullPointerException {
			super(pos);
		}
		public IntegralPoint3D() {
			super(0, 0, 0);
		}
	}
	
	public static class Point3D extends Position3D implements TridimensionalPoint<Float>, Movable3D {
		
		public @Override void setX(float x) {
			this.x = x;
		}
		
		public @Override void setY(float y) {
			this.y = y;
		}
		
		public @Override void setZ(float z) {
			this.z = z;
		}
		
		public @Override String toString() {
			return "( " + x + "; " + y + "; " + z + " )";
		}
		
		public @Override Point3D clone() {
			return new Point3D(x, y, z);
		}
		
		public Point3D(float x, float y, float z) {
			super(x, y, z);
		}
		public Point3D(Integer x, Integer y, Integer z) throws NullPointerException {
			super(x, y, z);
		}
		public Point3D(Positional3D pos) throws NullPointerException {
			super(pos);
		}
		public Point3D(Positional3DType<?> pos) throws NullPointerException {
			super(pos);
		}
		public Point3D() {
			super(0, 0, 0);
		}
	}
	
	public static class DoublePoint3D extends DoublePosition3D implements TridimensionalPoint<Double>, DoubleMovable3D {
		
		public @Override void setX(double x) {
			this.x = x;
		}
		
		public @Override void setY(double y) {
			this.y = y;
		}
		
		public @Override void setZ(double z) {
			this.z = z;
		}
		
		public @Override String toString() {
			return "( " + x + "; " + y + "; " + z + " )";
		}
		
		public @Override DoublePoint3D clone() {
			return new DoublePoint3D(x, y, z);
		}
		
		public DoublePoint3D(double x, double y, double z) {
			super(x, y, z);
		}
		public DoublePoint3D(Integer x, Integer y, Integer z) throws NullPointerException {
			super(x, y, z);
		}
		public DoublePoint3D(DoublePositional3D pos) throws NullPointerException {
			super(pos);
		}
		public DoublePoint3D(Positional3DType<?> pos) throws NullPointerException {
			super(pos);
		}
		public DoublePoint3D() {
			super(0, 0, 0);
		}
	}
}
