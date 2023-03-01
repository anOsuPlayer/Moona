package moonaframework.design.bidimensional;

import moonaframework.design.bidimensional.Positional2DType.IntegralPositional2D;
import moonaframework.design.bidimensional.geometry.BidimensionalBounds;
import moonaframework.design.monodimensional.Dimensional1DType;
import moonaframework.design.monodimensional.Positional1DType;
import moonaframework.design.monodimensional.Resizable1DType;
import moonaframework.design.monodimensional.geometry.MonodimensionalBounds;

public interface Resizable2DType<T extends Number> extends Dimensional2DType<T>, Resizable1DType<T> {
	
	void setHeight(T height) throws NullPointerException;
	
	void resizeHeight(T dHeight) throws NullPointerException;
	
	public static interface IntegralResizable2D extends Resizable2DType<Integer>, IntegralDimensional2D, IntegralResizable1D {
		
		default @Override void setHeight(Integer height) throws NullPointerException {
			setHeight(height.intValue());
		}
		
		void setHeight(int height);
		
		default @Override void resizeHeight(Integer dHeight) throws NullPointerException {
			resizeHeight(dHeight.intValue());
		}
		default void resizeHeight(int dHeight) {
			setHeight(getHeight() + dHeight);
		}
		
		default @Override void setSize(Dimensional1DType<Integer> dim) {
			if (dim instanceof IntegralDimensional2D actual) {
				setWidth((int) actual.getWidth()); setHeight(actual.getHeight());
			}
			else if (dim instanceof Dimensional2DType<Integer> general) {
				setWidth(general.getWrappedWidth()); setHeight(general.getWrappedHeight());
			}
			else {
				setWidth(dim.getWrappedWidth());
			}
		}
		default @Override void setBounds(MonodimensionalBounds<Integer> dim) {
			if (dim instanceof IntegralDimensional2D actual) {
				setWidth((int) actual.getWidth() * ((getWidth() < 0) ? -1 : 1)); setHeight(actual.getHeight()
						* ((getHeight() < 0) ? -1 : 1));
			}
			else if (dim instanceof BidimensionalBounds<Integer> general) {
				setWidth(general.getWrappedWidth() * ((getWidth() < 0) ? -1 : 1)); setHeight(general.getWrappedHeight()
						* ((getHeight() < 0) ? -1 : 1));
			}
			else {
				setWidth(dim.getWrappedWidth() * ((getWidth() < 0) ? -1 : 1));
			}
		}
	}
	
	public static interface Resizable2D extends Resizable2DType<Float>, Dimensional2D, Resizable1D {
		
		default @Override void setHeight(Float height) throws NullPointerException {
			setHeight(height.floatValue());
		}
		
		void setHeight(float height);
		
		default @Override void resizeHeight(Float dHeight) throws NullPointerException {
			resizeHeight(dHeight.floatValue());
		}
		default void resizeHeight(float dHeight) {
			setHeight(getHeight() + dHeight);
		}
		
		default @Override void setSize(Dimensional1DType<Float> dim) {
			if (dim instanceof Dimensional2D actual) {
				setWidth((float) actual.getWidth()); setHeight(actual.getHeight());
			}
			else if (dim instanceof Dimensional2DType<Float> general) {
				setWidth(general.getWrappedWidth()); setHeight(general.getWrappedHeight());
			}
			else {
				setWidth(dim.getWrappedWidth());
			}
		}
		default @Override void setBounds(MonodimensionalBounds<Float> dim) {
			if (dim instanceof Dimensional2D actual) {
				setWidth((float) actual.getWidth() * ((getWidth() < 0) ? -1 : 1)); setHeight(actual.getHeight()
						* ((getHeight() < 0) ? -1 : 1));
			}
			else if (dim instanceof BidimensionalBounds<Float> general) {
				setWidth(general.getWrappedWidth() * ((getWidth() < 0) ? -1 : 1)); setHeight(general.getWrappedHeight()
						* ((getHeight() < 0) ? -1 : 1));
			}
			else {
				setWidth(dim.getWrappedWidth() * ((getWidth() < 0) ? -1 : 1));
			}
		}
	}
	
	public static interface DoubleResizable2D extends Resizable2DType<Double>, DoubleDimensional2D, DoubleResizable1D {
		
		default @Override void setHeight(Double height) throws NullPointerException {
			setHeight(height.doubleValue());
		}
		
		void setHeight(double height);
		
		default @Override void resizeHeight(Double dHeight) throws NullPointerException {
			resizeHeight(dHeight.doubleValue());
		}
		default void resizeHeight(double dHeight) {
			setHeight(getHeight() + dHeight);
		}
		
		default @Override void setSize(Dimensional1DType<Double> dim) {
			if (dim instanceof DoubleDimensional2D actual) {
				setWidth((double) actual.getWidth()); setHeight(actual.getHeight());
			}
			else if (dim instanceof Dimensional2DType<Double> general) {
				setWidth(general.getWrappedWidth()); setHeight(general.getWrappedHeight());
			}
			else {
				setWidth(dim.getWrappedWidth());
			}
		}
		default @Override void setBounds(MonodimensionalBounds<Double> dim) {
			if (dim instanceof DoubleDimensional2D actual) {
				setWidth((double) actual.getWidth() * ((getWidth() < 0) ? -1 : 1)); setHeight(actual.getHeight()
						* ((getHeight() < 0) ? -1 : 1));
			}
			else if (dim instanceof BidimensionalBounds<Double> general) {
				setWidth(general.getWrappedWidth() * ((getWidth() < 0) ? -1 : 1)); setHeight(general.getWrappedHeight()
						* ((getHeight() < 0) ? -1 : 1));
			}
			else {
				setWidth(dim.getWrappedWidth() * ((getWidth() < 0) ? -1 : 1));
			}
		}
	}
}