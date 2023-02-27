package moonaframework.design.monodimensional;

public interface Adjustable1DType<T extends Number> extends Spatial1DType<T>, Movable1DType<T>, Resizable1DType<T> {

	public static interface IntegralAdjustable1D extends Adjustable1DType<Integer>, IntegralSpatial1D, IntegralMovable1D, IntegralResizable1D {
		
	}
	
	public static interface Adjustable1D extends Adjustable1DType<Float>, Spatial1D, Movable1D, Resizable1D {
		
	}

	public static interface DoubleAdjustable1D extends Adjustable1DType<Double>, DoubleSpatial1D, DoubleMovable1D, DoubleResizable1D {
		
	}
}
