package moonaframework.design.bidimensional;

public interface Adjustable2DType<T extends Number> extends Spatial2DType<T>, Movable2DType<T>, Resizable2DType<T> {

	public interface IntegralAdjustable2D extends Adjustable2DType<Integer>, IntegralSpatial2D, IntegralMovable2D, IntegralResizable2D {
		
	}
	
	public interface Adjustable2D extends Adjustable2DType<Float>, Spatial2D, Movable2D, Resizable2D {
		
	}
	
	public interface DoubleAdjustable2D extends Adjustable2DType<Double>, DoubleSpatial2D, DoubleMovable2D, DoubleResizable2D {
		
	}
}
