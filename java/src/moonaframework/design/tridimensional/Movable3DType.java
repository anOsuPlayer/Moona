package moonaframework.design.tridimensional;

import moonaframework.design.bidimensional.Movable2DType;
import moonaframework.design.monodimensional.Positional1DType;

public interface Movable3DType<T extends Number> extends Positional3DType<T>, Movable2DType<T> {

	void setZ(T z) throws NullPointerException;
	
	void moveZ(T dz) throws NullPointerException;
	
	default void asZ(Positional1DType<T> pos) throws NullPointerException {
		setZ(pos.getWrappedX());
	}
	
	public static interface IntegralMovable3D extends Movable3DType<Integer>, IntegralPositional3D, IntegralMovable2D {
		
		default @Override void setZ(Integer z) throws NullPointerException {
			setZ(z.intValue());
		}
		
		void setZ(int z);
		
		default @Override void moveZ(Integer dz) throws NullPointerException {
			setZ(getZ() + dz.intValue());
		}
		default void moveZ(int dz) {
			setZ(getZ() + dz);
		}
		
		default @Override void asZ(Positional1DType<Integer> pos) throws NullPointerException {
			if (pos instanceof IntegralPositional1D specific) {
				setZ(specific.getX());
			}
			else {
				setZ(pos.getWrappedX());
			}
		}
	}
	
	public static interface Movable3D extends Movable3DType<Float>, Positional3D, Movable2D {
		
		default @Override void setZ(Float z) throws NullPointerException {
			setZ(z.floatValue());
		}
		
		void setZ(float z);
		
		default @Override void moveZ(Float dz) throws NullPointerException {
			setZ(getZ() + dz.floatValue());
		}
		default void moveZ(float dz) {
			setZ(getZ() + dz);
		}
		
		default @Override void asZ(Positional1DType<Float> pos) throws NullPointerException {
			if (pos instanceof Positional1D specific) {
				setZ(specific.getX());
			}
			else {
				setZ(pos.getWrappedX());
			}
		}
	}
	
	public static interface DoubleMovable3D extends Movable3DType<Double>, DoublePositional3D, DoubleMovable2D {
		
		default @Override void setZ(Double z) throws NullPointerException {
			setZ(z.intValue());
		}
		
		void setZ(double z);
		
		default @Override void moveZ(Double dz) throws NullPointerException {
			setZ(getZ() + dz.intValue());
		}
		default void moveZ(double dz) {
			setZ(getZ() + dz);
		}
		
		default @Override void asZ(Positional1DType<Double> pos) throws NullPointerException {
			if (pos instanceof DoublePositional1D specific) {
				setZ(specific.getX());
			}
			else {
				setZ(pos.getWrappedX());
			}
		}
	}
}
