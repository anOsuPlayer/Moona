package moonaframework.design.bidimensional;

public interface Spatial2DType<T extends Number> extends Positional2DType<T>, Dimensional2DType<T> {

	public static interface IntegralSpatial2D extends Spatial2DType<Integer>, IntegralPositional2D, IntegralDimensional2D {
		
	}
	
	public static interface Spatial2D extends Spatial2DType<Float>, Positional2D, Dimensional2D {
		
	}
	
	public static interface DoubleSpatial2D extends Spatial2DType<Double>, DoublePositional2D, DoubleDimensional2D {
		
	}
}
