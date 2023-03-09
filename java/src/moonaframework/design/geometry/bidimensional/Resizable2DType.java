package moonaframework.design.geometry.bidimensional;

import moonaframework.design.geometry.monodimensional.Dimensional1DType;
import moonaframework.design.geometry.monodimensional.MonodimensionalBounds;
import moonaframework.design.geometry.monodimensional.Resizable1DType;

public interface Resizable2DType<T extends Number> extends Dimensional2DType<T>, Resizable1DType<T> {
	
	void setHeight(T height) throws NullPointerException;
	
	void resizeHeight(T dHeight) throws NullPointerException;
	
	default void asWidth(Dimensional1DType<T> dim) {
		setWidth(dim.getWrappedWidth());
	}
	
	default void asHeight(Dimensional1DType<T> dim) {
		setHeight(dim.getWrappedWidth());
	}
	
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
				setWidth(actual.getWidth()); setHeight(actual.getHeight());
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
				setWidth(actual.getWidth() * ((getWidth() < 0) ? -1 : 1)); setHeight(actual.getHeight()
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
		
		default @Override void asWidth(Dimensional1DType<Integer> pos) throws NullPointerException {
			if (pos instanceof IntegralDimensional1D specific) {
				setWidth(specific.getWidth());
			}
			else {
				setWidth(pos.getWrappedWidth());
			}
		}
		default @Override void asHeight(Dimensional1DType<Integer> pos) throws NullPointerException {
			if (pos instanceof IntegralDimensional1D specific) {
				setWidth(specific.getWidth());
			}
			else {
				setHeight(pos.getWrappedWidth());
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
				setWidth(actual.getWidth()); setHeight(actual.getHeight());
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
				setWidth(actual.getWidth() * ((getWidth() < 0) ? -1 : 1)); setHeight(actual.getHeight()
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
		
		default @Override void asWidth(Dimensional1DType<Float> pos) throws NullPointerException {
			if (pos instanceof Dimensional1D specific) {
				setWidth(specific.getWidth());
			}
			else {
				setWidth(pos.getWrappedWidth());
			}
		}
		default @Override void asHeight(Dimensional1DType<Float> pos) throws NullPointerException {
			if (pos instanceof Dimensional1D specific) {
				setWidth(specific.getWidth());
			}
			else {
				setHeight(pos.getWrappedWidth());
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
				setWidth(actual.getWidth()); setHeight(actual.getHeight());
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
				setWidth(actual.getWidth() * ((getWidth() < 0) ? -1 : 1)); setHeight(actual.getHeight()
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
		
		default @Override void asWidth(Dimensional1DType<Double> pos) throws NullPointerException {
			if (pos instanceof DoubleDimensional1D specific) {
				setWidth(specific.getWidth());
			}
			else {
				setWidth(pos.getWrappedWidth());
			}
		}
		default @Override void asHeight(Dimensional1DType<Double> pos) throws NullPointerException {
			if (pos instanceof DoubleDimensional1D specific) {
				setWidth(specific.getWidth());
			}
			else {
				setHeight(pos.getWrappedWidth());
			}
		}
	}
}