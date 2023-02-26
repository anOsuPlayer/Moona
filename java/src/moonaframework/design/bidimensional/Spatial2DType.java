package moonaframework.design.bidimensional;

public interface Spatial2DType<T extends Number> extends Positional2DType<T>, Dimensional2DType<T> {

	public interface IntegralSpatial2D extends Spatial2DType<Integer>, IntegralPositional2D, IntegralDimensional2D {
		
	}
	
	public interface Spatial2D extends Spatial2DType<Float>, Positional2D, Dimensional2D {
		
	}
	
	public interface DoubleSpatial2D extends Spatial2DType<Double>, DoublePositional2D, DoubleDimensional2D {
		
	}
}
