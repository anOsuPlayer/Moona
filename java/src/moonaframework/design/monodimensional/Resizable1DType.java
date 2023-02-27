package moonaframework.design.monodimensional;

import moonaframework.design.monodimensional.Dimensional1DType.Dimensional1D;
import moonaframework.design.monodimensional.Dimensional1DType.DoubleDimensional1D;
import moonaframework.design.monodimensional.Dimensional1DType.IntegralDimensional1D;

public interface Resizable1DType<T extends Number> extends Positional1DType<T> {

	void setWidth(T width);
	
	void resizeX(T dWidth);
	
	public static interface IntegralResizable1D extends Resizable1DType<Integer>, IntegralDimensional1D {
		
		default @Override void setWidth(Integer width) {
			setWidth(width.intValue());
		}
		
		void setWidth(int width);
		
		default @Override void resizeX(Integer dWidth) {
			resizeX(dWidth.intValue());
		}
		
		void resizeX(int dWidth);
	}
	
	public static interface Resizable1D extends Resizable1DType<Float>, Dimensional1D {
		
		default @Override void setWidth(Float width) {
			setWidth(width.floatValue());
		}
		
		void setWidth(float width);
		
		default @Override void resizeX(Float dWidth) {
			resizeX(dWidth.floatValue());
		}
		
		void resizeX(float dWidth);
	}
	
	public static interface DoubleResizable1D extends Resizable1DType<Double>, DoubleDimensional1D {
		
		default @Override void setWidth(Double width) {
			setWidth(width.doubleValue());
		}
		
		void setWidth(double dWidth);
		
		default @Override void resizeX(Double dWidth) {
			resizeX(dWidth.doubleValue());
		}
		
		void resizeX(double dWidth);
	}
}
