package moonaframework.design.tridimensional;

import moonaframework.design.bidimensional.Positional2DType;

public interface Positional3DType<T extends Number> extends Positional2DType<T> {

	T getWrappedZ();
	
	public static interface IntegralPositional3D extends Positional3DType<Integer>, IntegralPositional2D {
		
		default @Override Integer getWrappedZ() {
			return Integer.valueOf(getZ());
		}
		
		int getZ();
	}
	
	public static interface Positional3D extends Positional3DType<Float>, Positional2D {
		
		default @Override Float getWrappedZ() {
			return Float.valueOf(getZ());
		}
		
		float getZ();
	}

	public static interface DoublePositional3D extends Positional3DType<Double>, DoublePositional2D {
		
		default @Override Double getWrappedZ() {
			return Double.valueOf(getZ());
		}
		
		double getZ();
	}
}
