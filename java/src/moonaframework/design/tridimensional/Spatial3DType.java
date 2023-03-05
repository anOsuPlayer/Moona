package moonaframework.design.tridimensional;

import moonaframework.design.bidimensional.Dimensional2DType;
import moonaframework.design.bidimensional.Spatial2DType;
import moonaframework.design.tridimensional.Dimensional3DType.Dimensional3D;
import moonaframework.design.tridimensional.Dimensional3DType.DoubleDimensional3D;
import moonaframework.design.tridimensional.Dimensional3DType.IntegralDimensional3D;

public interface Spatial3DType<T extends Number> extends Spatial2DType<T>, Positional3DType<T>, Dimensional2DType<T> {

	public static interface IntegralSpatial3D extends Spatial3DType<Integer>, IntegralPositional3D, IntegralDimensional3D {
		
	}
	
	public static interface Spatial3D extends Spatial3DType<Float>, Positional3D, Dimensional3D {
		
	}

	public static interface DoubleSpatial3D extends Spatial3DType<Double>, DoublePositional3D, DoubleDimensional3D {
		
	}
}
