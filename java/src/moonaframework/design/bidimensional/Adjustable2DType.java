package moonaframework.design.bidimensional;

public sealed interface Adjustable2DType<T extends Number> extends Spatial2DType<T>, Movable2DType<T>, Resizable2DType<T> permits Adjustable2DType.IntegralAdjustable2D, Adjustable2DType.Adjustable2D, Adjustable2DType.DoubleAdjustable2D {

	public non-sealed static interface IntegralAdjustable2D extends Adjustable2DType<Integer>, IntegralSpatial2D, IntegralMovable2D, IntegralResizable2D {
		
	}
	
	public non-sealed static interface Adjustable2D extends Adjustable2DType<Float>, Spatial2D, Movable2D, Resizable2D {
		
	}
	
	public non-sealed static interface DoubleAdjustable2D extends Adjustable2DType<Double>, DoubleSpatial2D, DoubleMovable2D, DoubleResizable2D {
		
	}
}
