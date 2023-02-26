package moonaframework.design.bidimensional;

public sealed interface Spatial2D<P extends Number> extends Positional2D<P>, Dimensional2D<P> permits Spatial2D.IntSpatial2D, Spatial2D.LongSpatial2D, Spatial2D.FloatSpatial2D, Spatial2D.DoubleSpatial2D, Adjustable2D<P> {

	public non-sealed interface IntSpatial2D extends Spatial2D<Integer>, IntPositional2D, IntDimensional2D {
		
	}
	
	public non-sealed interface LongSpatial2D extends Spatial2D<Long>, LongPositional2D, LongDimensional2D {
		
	}
	
	public non-sealed interface FloatSpatial2D extends Spatial2D<Float>, FloatPositional2D, FloatDimensional2D {
		
	}
	
	public non-sealed interface DoubleSpatial2D extends Spatial2D<Double>, DoublePositional2D, DoubleDimensional2D {
		
	}
}
