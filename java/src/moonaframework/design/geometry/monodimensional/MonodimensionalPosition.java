package moonaframework.design.geometry.monodimensional;

import moonaframework.design.geometry.Coordinate;
import moonaframework.design.geometry.Size;
import moonaframework.design.geometry.bidimensional.Dimensional2DType.IntegralDimensional2D;
import moonaframework.design.geometry.bidimensional.Positional2DType.DoublePositional2D;
import moonaframework.design.geometry.bidimensional.Positional2DType.IntegralPositional2D;
import moonaframework.design.geometry.bidimensional.Positional2DType.Positional2D;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.IntegralDimensional3D;
import moonaframework.design.geometry.tridimensional.Positional3DType.DoublePositional3D;
import moonaframework.design.geometry.tridimensional.Positional3DType.IntegralPositional3D;
import moonaframework.design.geometry.tridimensional.Positional3DType.Positional3D;

public interface MonodimensionalPosition<T extends Number> extends Positional1DType<T>, Cloneable {
	
	public static class IntegralPosition1D implements MonodimensionalPosition<Integer>, IntegralPositional1D {
		
		protected int x;
		
		public @Override int getX() {
			return this.x;
		}
		
		public @Override IntegralPosition1D getPosition() {
			return new IntegralPosition1D(x);
		}

		public @Override String toString() {
			return "[ " + x + " ]";
		}
		
		public @Override boolean equals(Object o) {
			return (o instanceof IntegralPositional1D pos) ? pos.getX() == x :
				(o instanceof Positional1DType<?> postype) ? postype.getWrappedX().intValue() == x :
				false;
		}
		
		public @Override IntegralPosition1D clone() {
			return new IntegralPosition1D(x);
		}
		
		public IntegralPosition1D(int x) {
			this.x = x;
		}
		public IntegralPosition1D(Integer x) {
			this(x.intValue());
		}
		public IntegralPosition1D(IntegralPositional3D pos, Coordinate x) throws NullPointerException {
			switch (x) {
				case X -> this.x = pos.getX();
				case Y -> this.x = pos.getY();
				case Z -> this.x = pos.getZ();
			}
		}
		public IntegralPosition1D(IntegralPositional2D pos, Coordinate x) throws NullPointerException, IllegalArgumentException {
			switch (x) {
				case X -> this.x = pos.getX();
				case Y -> this.x = pos.getY();
				case Z -> throw new IllegalArgumentException("Bidimensional Elements do not extend on the Z axis.");
			}
		}
		public IntegralPosition1D(IntegralPositional1D pos) {
			this(pos.getX());
		}
		public IntegralPosition1D(Positional1DType<?> pos) {
			this(pos.getWrappedX().intValue());
		}
		public IntegralPosition1D() {
			this.x = 0;
		}
	}
	
	public static class Position1D implements MonodimensionalPosition<Float>, Positional1D {
		
		protected float x;
		
		public @Override float getX() {
			return this.x;
		}
		
		public @Override Position1D getPosition() {
			return new Position1D(x);
		}
		
		public @Override String toString() {
			return "[ " + x + " ]";
		}
		
		public @Override boolean equals(Object o) {
			return (o instanceof Positional1D pos) ? pos.getX() == x :
				(o instanceof Positional1DType<?> postype) ? postype.getWrappedX().floatValue() == x :
				false;
		}
		
		public @Override Position1D clone() {
			return new Position1D(x);
		}
		
		public Position1D(float x) {
			this.x = x;
		}
		public Position1D(Float x) {
			this(x.floatValue());
		}
		public Position1D(Positional3D pos, Coordinate x) throws NullPointerException {
			switch (x) {
				case X -> this.x = pos.getX();
				case Y -> this.x = pos.getY();
				case Z -> this.x = pos.getZ();
			}
		}
		public Position1D(Positional2D pos, Coordinate x) throws NullPointerException, IllegalArgumentException {
			switch (x) {
				case X -> this.x = pos.getX();
				case Y -> this.x = pos.getY();
				case Z -> throw new IllegalArgumentException("Bidimensional Elements do not extend on the Z axis.");
			}
		}
		public Position1D(Positional1D pos) {
			this(pos.getX());
		}
		public Position1D(Positional1DType<?> pos) {
			this(pos.getWrappedX().floatValue());
		}
		public Position1D() {
			this.x = 0;
		}
	}
	
	public static class DoublePosition1D implements MonodimensionalPosition<Double>, DoublePositional1D {
		
		protected double x;
		
		public @Override double getX() {
			return this.x;
		}
		
		public @Override DoublePosition1D getPosition() {
			return new DoublePosition1D(x);
		}
		
		public @Override String toString() {
			return "[ " + x + " ]";
		}
		
		public @Override boolean equals(Object o) {
			return (o instanceof DoublePositional1D pos) ? pos.getX() == x :
				(o instanceof Positional1DType<?> postype) ? postype.getWrappedX().doubleValue() == x :
				false;
		}
		
		public @Override DoublePosition1D clone() {
			return new DoublePosition1D(x);
		}
		
		public DoublePosition1D(double x) {
			this.x = x;
		}
		public DoublePosition1D(Double x) {
			this(x.doubleValue());
		}
		public DoublePosition1D(DoublePositional3D pos, Coordinate x) throws NullPointerException {
			switch (x) {
				case X -> this.x = pos.getX();
				case Y -> this.x = pos.getY();
				case Z -> this.x = pos.getZ();
			}
		}
		public DoublePosition1D(DoublePositional2D pos, Coordinate x) throws NullPointerException, IllegalArgumentException {
			switch (x) {
				case X -> this.x = pos.getX();
				case Y -> this.x = pos.getY();
				case Z -> throw new IllegalArgumentException("Bidimensional Elements do not extend on the Z axis.");
			}
		}
		public DoublePosition1D(DoublePositional1D pos) {
			this(pos.getX());
		}
		public DoublePosition1D(Positional1DType<?> pos) {
			this(pos.getWrappedX().doubleValue());
		}
		public DoublePosition1D() {
			this.x = 0;
		}
	}
}
