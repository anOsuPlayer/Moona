package moonaframework.design.geometry.bidimensional;

import moonaframework.design.geometry.monodimensional.Spatial1DType;

public interface Spatial2DType<T extends Number> extends Positional2DType<T>, Dimensional2DType<T>, Spatial1DType<T> {

	public static interface IntegralSpatial2D extends Spatial2DType<Integer>, IntegralPositional2D, IntegralDimensional2D, IntegralSpatial1D {
		
	}
	
	public static interface Spatial2D extends Spatial2DType<Float>, Positional2D, Dimensional2D, Spatial1D {
		
	}
	
	public static interface DoubleSpatial2D extends Spatial2DType<Double>, DoublePositional2D, DoubleDimensional2D, DoubleSpatial1D {
		
	}
}
