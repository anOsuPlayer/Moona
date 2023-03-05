package moonaframework.design.tridimensional;

import moonaframework.design.bidimensional.Dimensional2DType;
import moonaframework.design.bidimensional.Dimensional2DType.IntegralDimensional2D;

public interface Dimensional3DType<T extends Number> extends Dimensional2DType<T> {

	T getWrappedDepth();
	
	public static interface IntegralDimensional3D extends Dimensional3DType<Integer>, IntegralDimensional2D {
		
		default @Override Integer getWrappedDepth() {
			return Integer.valueOf(getDepth());
		}
		
		int getDepth();
	}
	
	public static interface Dimensional3D extends Dimensional3DType<Float>, Dimensional2D {
		
		default @Override Float getWrappedDepth() {
			return Float.valueOf(getDepth());
		}
		
		float getDepth();
	}

	public static interface DoubleDimensional3D extends Dimensional3DType<Double>, DoubleDimensional2D {
		
		default @Override Double getWrappedDepth() {
			return Double.valueOf(getDepth());
		}
		
		double getDepth();
	}
}
