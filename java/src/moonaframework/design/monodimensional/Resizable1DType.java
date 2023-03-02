package moonaframework.design.monodimensional;

import moonaframework.design.monodimensional.geometry.MonodimensionalBounds;

public interface Resizable1DType<T extends Number> extends Dimensional1DType<T> {

	void setWidth(T width) throws NullPointerException;
	
	void resizeWidth(T dWidth) throws NullPointerException;
	
	void setSize(Dimensional1DType<T> dim);
	
	void setBounds(MonodimensionalBounds<T> bounds);
	
	public static interface IntegralResizable1D extends Resizable1DType<Integer>, IntegralDimensional1D {
		
		default @Override void setWidth(Integer width) throws NullPointerException {
			setWidth(width.intValue());
		}
		
		void setWidth(int width);
		
		default @Override void resizeWidth(Integer dWidth) throws NullPointerException {
			resizeWidth(dWidth.intValue());
		}
		default void resizeWidth(int dWidth) {
			setWidth(getWidth() + dWidth);
		}
		
		default @Override void setSize(Dimensional1DType<Integer> dim) {
			if (dim instanceof IntegralDimensional1D actual) {
				setWidth(actual.getWidth());
			}
			else {
				setWidth(dim.getWrappedWidth());
			}
		}
		default @Override void setBounds(MonodimensionalBounds<Integer> bounds) {
			if (bounds instanceof IntegralDimensional1D actual) {
				setWidth(actual.getWidth() * ((getWidth() < 0) ? -1 : 1));
			}
			else {
				setWidth(bounds.getWrappedWidth() * ((getWidth() < 0) ? -1 : 1));
			}
		}
	}
	
	public static interface Resizable1D extends Resizable1DType<Float>, Dimensional1D {
		
		default @Override void setWidth(Float width) throws NullPointerException {
			setWidth(width.floatValue());
		}
		
		void setWidth(float width);
		
		default @Override void resizeWidth(Float dWidth) throws NullPointerException {
			resizeWidth(dWidth.floatValue());
		}
		default void resizeWidth(float dWidth) {
			setWidth(getWidth() + dWidth);
		}
		
		default @Override void setSize(Dimensional1DType<Float> dim) {
			if (dim instanceof Dimensional1D actual) {
				setWidth(actual.getWidth());
			}
			else {
				setWidth(dim.getWrappedWidth());
			}
		}
		default @Override void setBounds(MonodimensionalBounds<Float> bounds) {
			if (bounds instanceof Dimensional1D actual) {
				setWidth(actual.getWidth() * ((getWidth() < 0) ? -1 : 1));
			}
			else {
				setWidth(bounds.getWrappedWidth() * ((getWidth() < 0) ? -1 : 1));
			}
		}
	}
	
	public static interface DoubleResizable1D extends Resizable1DType<Double>, DoubleDimensional1D {
		
		default @Override void setWidth(Double width) throws NullPointerException {
			setWidth(width.doubleValue());
		}
		
		void setWidth(double dWidth);
		
		default @Override void resizeWidth(Double dWidth) throws NullPointerException {
			resizeWidth(dWidth.doubleValue());
		}
		default void resizeWidth(double dWidth) {
			setWidth(getWidth() + dWidth);
		}
		
		default @Override void setSize(Dimensional1DType<Double> dim) {
			if (dim instanceof DoubleDimensional1D actual) {
				setWidth(actual.getWidth());
			}
			else {
				setWidth(dim.getWrappedWidth());
			}
		}
		default @Override void setBounds(MonodimensionalBounds<Double> bounds) {
			if (bounds instanceof DoubleDimensional1D actual) {
				setWidth(actual.getWidth() * ((getWidth() < 0) ? -1 : 1));
			}
			else {
				setWidth(bounds.getWrappedWidth() * ((getWidth() < 0) ? -1 : 1));
			}
		}
	}
}
