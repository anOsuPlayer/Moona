package moonaframework.design.bidimensional;

public sealed interface Spatial2DType<T extends Number> extends Positional2DType<T>, Dimensional2DType<T> permits Adjustable2DType<T>, Spatial2DType.IntegralSpatial2D, Spatial2DType.Spatial2D, Spatial2DType.DoubleSpatial2D {

	public non-sealed static interface IntegralSpatial2D extends Spatial2DType<Integer>, IntegralPositional2D, IntegralDimensional2D {
		
	}
	
	public non-sealed static interface Spatial2D extends Spatial2DType<Float>, Positional2D, Dimensional2D {
		
	}
	
	public non-sealed static interface DoubleSpatial2D extends Spatial2DType<Double>, DoublePositional2D, DoubleDimensional2D {
		
	}
}
