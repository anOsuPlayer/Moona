package moonaframework.design.geometry.tridimensional;

import moonaframework.design.geometry.bidimensional.Resizable2DType;
import moonaframework.design.geometry.monodimensional.Dimensional1DType;

public interface Resizable3DType<T extends Number> extends Dimensional3DType<T>, Resizable2DType<T> {

	void setDepth(T depth) throws NullPointerException;
	
	void resizeDepth(T dDepth) throws NullPointerException;
	
	default void asDepth(Dimensional1DType<T> dim) throws NullPointerException {
		setDepth(getWrappedDepth());
	}
	
	public static interface IntegralResizable3D extends Resizable3DType<Integer>, IntegralDimensional3D, IntegralResizable2D {
		
		default @Override void setDepth(Integer depth) throws NullPointerException {
			setDepth(depth.intValue());
		}
		
		void setDepth(int depth);
		
		default @Override void resizeDepth(Integer dDepth) throws NullPointerException {
			setDepth(getDepth() + dDepth.intValue());
		}
		default void resizeDepth(int dDepth) {
			setDepth(getDepth() + dDepth);
		}
		
		default @Override void asDepth(Dimensional1DType<Integer> dim) throws NullPointerException {
			if (dim instanceof IntegralDimensional1D specific) {
				setDepth(specific.getWidth());
			}
			else {
				setDepth(dim.getWrappedWidth());
			}
		}
	}
	
	public static interface Resizable3D extends Resizable3DType<Float>, Dimensional3D, Resizable2D {
		
		default @Override void setDepth(Float depth) throws NullPointerException {
			setDepth(depth.intValue());
		}
		
		void setDepth(float depth);
		
		default @Override void resizeDepth(Float dDepth) throws NullPointerException {
			setDepth(getDepth() + dDepth.intValue());
		}
		default void resizeDepth(float dDepth) {
			setDepth(getDepth() + dDepth);
		}
		
		default @Override void asDepth(Dimensional1DType<Float> dim) throws NullPointerException {
			if (dim instanceof Dimensional1D specific) {
				setDepth(specific.getWidth());
			}
			else {
				setDepth(dim.getWrappedWidth());
			}
		}
	}
	
	public static interface DoubleResizable3D extends Resizable3DType<Double>, DoubleDimensional3D, DoubleResizable2D {
		
		default @Override void setDepth(Double depth) throws NullPointerException {
			setDepth(depth.intValue());
		}
		
		void setDepth(double depth);
		
		default @Override void resizeDepth(Double dDepth) throws NullPointerException {
			setDepth(getDepth() + dDepth.intValue());
		}
		default void resizeDepth(double dDepth) {
			setDepth(getDepth() + dDepth);
		}
		
		default @Override void asDepth(Dimensional1DType<Double> dim) throws NullPointerException {
			if (dim instanceof DoubleDimensional1D specific) {
				setDepth(specific.getWidth());
			}
			else {
				setDepth(dim.getWrappedWidth());
			}
		}
	}
}
