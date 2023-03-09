package moonaframework.design.geometry.tridimensional;

import moonaframework.design.geometry.bidimensional.Dimensional2DType;
import moonaframework.design.geometry.bidimensional.Spatial2DType;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.Dimensional3D;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.DoubleDimensional3D;
import moonaframework.design.geometry.tridimensional.Dimensional3DType.IntegralDimensional3D;

public interface Spatial3DType<T extends Number> extends Positional3DType<T>, Dimensional2DType<T>, Spatial2DType<T> {

	public static interface IntegralSpatial3D extends Spatial3DType<Integer>, IntegralPositional3D, IntegralDimensional3D {
		
	}
	
	public static interface Spatial3D extends Spatial3DType<Float>, Positional3D, Dimensional3D {
		
	}

	public static interface DoubleSpatial3D extends Spatial3DType<Double>, DoublePositional3D, DoubleDimensional3D {
		
	}
}
