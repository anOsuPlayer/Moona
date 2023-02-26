package moonaframework.design.bidimensional;

import moonaframework.design.bidimensional.Dimensional2DType.Dimensional2D;
import moonaframework.design.bidimensional.Dimensional2DType.DoubleDimensional2D;
import moonaframework.design.bidimensional.Dimensional2DType.IntegralDimensional2D;

public interface Resizable2DType<T extends Number> extends Positional2DType<T> {

	void setWrappedWidth(T x);
	
	void setWrappedHeight(T y);
	
	public interface IntegralResizable2D extends Resizable2DType<Integer>, IntegralDimensional2D {
		
		default @Override void setWrappedWidth(Integer x) {
			setWidth(x.intValue());
		}
		
		void setWidth(int x);
		
		default @Override void setWrappedHeight(Integer y) {
			setHeight(y.intValue());
		}
		
		void setHeight(int y);
	}
	
	public interface Resizable2D extends Resizable2DType<Float>, Dimensional2D {
		
		default @Override void setWrappedWidth(Float x) {
			setWidth(x.floatValue());
		}
		
		void setWidth(float x);
		
		default @Override void setWrappedHeight(Float y) {
			setHeight(y.floatValue());
		}
		
		void setHeight(float y);
	}
	
	public interface DoubleResizable2D extends Resizable2DType<Double>, DoubleDimensional2D {
		
		default @Override void setWrappedWidth(Double x) {
			setWidth(x.doubleValue());
		}
		
		void setWidth(double x);
		
		default @Override void setWrappedHeight(Double y) {
			setHeight(y.doubleValue());
		}
		
		void setHeight(double y);
	}
}