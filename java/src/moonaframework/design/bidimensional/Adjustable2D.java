package moonaframework.design.bidimensional;

public sealed interface Adjustable2D<P extends Number> extends Spatial2D<P>, Movable2D<P>, Resizable2D<P> permits Adjustable2D.IntAdjustable2D, Adjustable2D.LongAdjustable2D, Adjustable2D.FloatAdjustable2D, Adjustable2D.DoubleAdjustable2D {

	public non-sealed interface IntAdjustable2D extends Adjustable2D<Integer>, IntSpatial2D, IntMovable2D, IntResizable2D {
		
	}
	
	public non-sealed interface LongAdjustable2D extends Adjustable2D<Long>, LongSpatial2D, LongMovable2D, LongResizable2D {
		
	}
	
	public non-sealed interface FloatAdjustable2D extends Adjustable2D<Float>, FloatSpatial2D, FloatMovable2D, FloatPositional2D {
		
	}
	
	public non-sealed interface DoubleAdjustable2D extends Adjustable2D<Double>, DoubleSpatial2D, DoubleMovable2D, DoublePositional2D {
		
	}
}
