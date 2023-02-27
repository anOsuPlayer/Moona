package moonaframework.design.bidimensional;

import moonaframework.design.monodimensional.Adjustable1DType;

public interface Adjustable2DType<T extends Number> extends Spatial2DType<T>, Movable2DType<T>, Resizable2DType<T>, Adjustable1DType<T> {

	public static interface IntegralAdjustable2D extends Adjustable2DType<Integer>, IntegralSpatial2D, IntegralMovable2D, IntegralResizable2D, IntegralAdjustable1D {
		
	}
	
	public static interface Adjustable2D extends Adjustable2DType<Float>, Spatial2D, Movable2D, Resizable2D, Adjustable1D {
		
	}
	
	public static interface DoubleAdjustable2D extends Adjustable2DType<Double>, DoubleSpatial2D, DoubleMovable2D, DoubleResizable2D, DoubleAdjustable1D {
		
	}
}
