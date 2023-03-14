package moonaframework.design.geometry;

import moonaframework.design.geometry.ResizableType.DoubleResizable;
import moonaframework.design.geometry.ResizableType.IntegralResizable;
import moonaframework.design.geometry.ResizableType.Resizable;

public interface DimensionalType<T extends Number> extends Cloneable, GeometricElement<T> {

	T getWrappedWidth();
	
	void applyWidth(ResizableType<T> res) throws NullPointerException;
	
	T getWrappedHeight() throws LowerDimensionalOrderException;
	
	void applyHeight(ResizableType<T> res) throws NullPointerException, LowerDimensionalOrderException;
	
	T getWrappedDepth() throws LowerDimensionalOrderException;
	
	void applyDepth(ResizableType<T> res) throws NullPointerException, LowerDimensionalOrderException;
	
	public static interface IntegralDimensional extends DimensionalType<Integer>, IntegralGeometricElement {
		
		int getWidth();
		
		default @Override Integer getWrappedWidth() {
			return Integer.valueOf(getWidth());
		}
		default @Override void applyWidth(ResizableType<Integer> res) throws NullPointerException {
			if (res instanceof IntegralResizable realres) {
				realres.setWidth(getWidth());
			}
			else {
				res.setWidth(getWrappedWidth());
			}
		}
		
		int getHeight() throws LowerDimensionalOrderException;
		
		default @Override Integer getWrappedHeight() throws LowerDimensionalOrderException {
			return Integer.valueOf(getHeight());
		}
		default @Override void applyHeight(ResizableType<Integer> res) throws NullPointerException, LowerDimensionalOrderException {
			if (res instanceof IntegralResizable realres) {
				realres.setHeight(getHeight());
			}
			else {
				res.setHeight(getWrappedHeight());
			}
		}
		
		int getDepth() throws LowerDimensionalOrderException;
		
		default @Override Integer getWrappedDepth() throws LowerDimensionalOrderException {
			return Integer.valueOf(getDepth());
		}
		default @Override void applyDepth(ResizableType<Integer> res) throws NullPointerException, LowerDimensionalOrderException {
			if (res instanceof IntegralResizable realres) {
				realres.setDepth(getDepth());
			}
			else {
				res.setDepth(getWrappedDepth());
			}
		}
		
		default @Override int[] toArray() {
			return switch (order()) {
				case MONODIMENSIONAL: yield new int[] {getWidth()};
				case BIDIMENSIONAL: yield new int[] {getWidth(), getHeight()};
				case TRIDIMENSIONAL: yield new int[] {getWidth(), getHeight(), getDepth()};
			};
		}
	}
	
	public static interface Dimensional extends DimensionalType<Float>, FloatGeometricElement {
		
		float getWidth();
		
		default @Override Float getWrappedWidth() {
			return Float.valueOf(getWidth());
		}
		default @Override void applyWidth(ResizableType<Float> res) throws NullPointerException {
			if (res instanceof Resizable realres) {
				realres.setWidth(getWidth());
			}
			else {
				res.setWidth(getWrappedWidth());
			}
		}
		
		float getHeight() throws LowerDimensionalOrderException;
		
		default @Override Float getWrappedHeight() throws LowerDimensionalOrderException {
			return Float.valueOf(getHeight());
		}
		default @Override void applyHeight(ResizableType<Float> res) throws NullPointerException, LowerDimensionalOrderException {
			if (res instanceof Resizable realres) {
				realres.setHeight(getHeight());
			}
			else {
				res.setHeight(getWrappedHeight());
			}
		}
		
		float getDepth() throws LowerDimensionalOrderException;
		
		default @Override Float getWrappedDepth() throws LowerDimensionalOrderException {
			return Float.valueOf(getDepth());
		}
		default @Override void applyDepth(ResizableType<Float> res) throws NullPointerException, LowerDimensionalOrderException {
			if (res instanceof Resizable realres) {
				realres.setDepth(getDepth());
			}
			else {
				res.setDepth(getWrappedDepth());
			}
		}
		
		default @Override float[] toArray() {
			return switch (order()) {
				case MONODIMENSIONAL: yield new float[] {getWidth()};
				case BIDIMENSIONAL: yield new float[] {getWidth(), getHeight()};
				case TRIDIMENSIONAL: yield new float[] {getWidth(), getHeight(), getDepth()};
			};
		}
	}
	
	public static interface DoubleDimensional extends DimensionalType<Double>, DoubleGeometricElement {
		
		double getWidth();
		
		default @Override Double getWrappedWidth() {
			return Double.valueOf(getWidth());
		}
		default @Override void applyWidth(ResizableType<Double> res) throws NullPointerException {
			if (res instanceof DoubleResizable realres) {
				realres.setWidth(getWidth());
			}
			else {
				res.setWidth(getWrappedWidth());
			}
		}
		
		double getHeight() throws LowerDimensionalOrderException;
		
		default @Override Double getWrappedHeight() throws LowerDimensionalOrderException {
			return Double.valueOf(getHeight());
		}
		default @Override void applyHeight(ResizableType<Double> res) throws NullPointerException, LowerDimensionalOrderException {
			if (res instanceof DoubleResizable realres) {
				realres.setHeight(getHeight());
			}
			else {
				res.setHeight(getWrappedHeight());
			}
		}
		
		double getDepth() throws LowerDimensionalOrderException;
		
		default @Override Double getWrappedDepth() throws LowerDimensionalOrderException{
			return Double.valueOf(getDepth());
		}
		default @Override void applyDepth(ResizableType<Double> res) throws NullPointerException, LowerDimensionalOrderException {
			if (res instanceof DoubleResizable realres) {
				realres.setDepth(getDepth());
			}
			else {
				res.setDepth(getWrappedDepth());
			}
		}
		
		default @Override double[] toArray() {
			return switch (order()) {
				case MONODIMENSIONAL: yield new double[] {getWidth()};
				case BIDIMENSIONAL: yield new double[] {getWidth(), getHeight()};
				case TRIDIMENSIONAL: yield new double[] {getWidth(), getHeight(), getDepth()};
			};
		}
	}
}
