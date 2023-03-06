package moonaframework.design.tridimensional;

import moonaframework.design.bidimensional.Positional2DType;
import moonaframework.design.tridimensional.geometry.TridimensionalPosition;
import moonaframework.design.tridimensional.geometry.TridimensionalPosition.DoublePosition3D;
import moonaframework.design.tridimensional.geometry.TridimensionalPosition.IntegralPosition3D;
import moonaframework.design.tridimensional.geometry.TridimensionalPosition.Position3D;

public interface Positional3DType<T extends Number> extends Positional2DType<T> {

	T getWrappedZ();
	
	@Override TridimensionalPosition<T> getPosition();
	
	public static interface IntegralPositional3D extends Positional3DType<Integer>, IntegralPositional2D {
		
		default @Override Integer getWrappedZ() {
			return Integer.valueOf(getZ());
		}
		
		int getZ();
		
		default @Override TridimensionalPosition<Integer> getPosition() {
			return new IntegralPosition3D(getX(), getY(), getZ());
		}
	}
	
	public static interface Positional3D extends Positional3DType<Float>, Positional2D {
		
		default @Override Float getWrappedZ() {
			return Float.valueOf(getZ());
		}
		
		float getZ();
		
		default @Override TridimensionalPosition<Float> getPosition() {
			return new Position3D(getX(), getY(), getZ());
		}
	}

	public static interface DoublePositional3D extends Positional3DType<Double>, DoublePositional2D {
		
		default @Override Double getWrappedZ() {
			return Double.valueOf(getZ());
		}
		
		double getZ();
		
		default @Override TridimensionalPosition<Double> getPosition() {
			return new DoublePosition3D(getX(), getY(), getZ());
		}
	}
}
