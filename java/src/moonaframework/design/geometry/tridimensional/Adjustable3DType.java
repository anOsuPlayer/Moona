package moonaframework.design.geometry.tridimensional;

import moonaframework.design.geometry.bidimensional.Adjustable2DType;

public interface Adjustable3DType<T extends Number> extends Spatial3DType<T>, Movable3DType<T>, Resizable3DType<T>, Adjustable2DType<T> {

	public static interface IntegralAdjustable3D extends Adjustable3DType<Integer>, IntegralSpatial3D, IntegralMovable3D, IntegralResizable3D, IntegralAdjustable2D {
		
	}
	
	public static interface Adjustable3D extends Adjustable3DType<Float>, Spatial3D, Movable3D, Resizable3D, Adjustable2D {
		
	}
	
	public static interface DoubleAdjustable3D extends Adjustable3DType<Double>, DoubleSpatial3D, DoubleMovable3D, DoubleResizable3D, DoubleAdjustable2D {
		
	}
}
