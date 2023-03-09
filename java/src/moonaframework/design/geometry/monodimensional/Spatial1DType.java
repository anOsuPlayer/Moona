package moonaframework.design.geometry.monodimensional;

import moonaframework.design.geometry.monodimensional.Movable1DType.IntegralMovable1D;

public interface Spatial1DType<T extends Number> extends Positional1DType<T>, Dimensional1DType<T> {

	public static interface IntegralSpatial1D extends Spatial1DType<Integer>, IntegralPositional1D, IntegralMovable1D {
		
	}
	
	public static interface Spatial1D extends Spatial1DType<Float>, Positional1D, Dimensional1D {
		
	}

	public static interface DoubleSpatial1D extends Spatial1DType<Double>, DoublePositional1D, DoubleDimensional1D {
		
	}
}
