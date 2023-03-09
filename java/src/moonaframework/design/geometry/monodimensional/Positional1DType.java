package moonaframework.design.geometry.monodimensional;

import moonaframework.design.geometry.monodimensional.MonodimensionalPosition.DoublePosition1D;
import moonaframework.design.geometry.monodimensional.MonodimensionalPosition.IntegralPosition1D;
import moonaframework.design.geometry.monodimensional.MonodimensionalPosition.Position1D;

public interface Positional1DType<T extends Number> {

	T getWrappedX();
	
	MonodimensionalPosition<T> getPosition();
	
	public static interface IntegralPositional1D extends Positional1DType<Integer> {
		
		default @Override Integer getWrappedX() {
			return Integer.valueOf(getX());
		}
		
		int getX();
		
		default @Override MonodimensionalPosition<Integer> getPosition() {
			return new IntegralPosition1D(getX());
		}
	}
	
	public static interface Positional1D extends Positional1DType<Float> {
		
		default @Override Float getWrappedX() {
			return Float.valueOf(getX());
		}
		
		float getX();
		
		default @Override MonodimensionalPosition<Float> getPosition() {
			return new Position1D(getX());
		}
	}

	public static interface DoublePositional1D extends Positional1DType<Double> {
		
		default @Override Double getWrappedX() {
			return Double.valueOf(getX());
		}
		
		default @Override MonodimensionalPosition<Double> getPosition() {
			return new DoublePosition1D(getX());
		}
		
		double getX();
	}
}
