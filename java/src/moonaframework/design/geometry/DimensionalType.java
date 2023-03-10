package moonaframework.design.geometry;

import moonaframework.design.geometry.ResizableType.DoubleResizable;
import moonaframework.design.geometry.ResizableType.IntegralResizable;
import moonaframework.design.geometry.ResizableType.Resizable;

public interface DimensionalType<T extends Number> extends GeometricalEntity, Cloneable {

	T getWrappedWidth();
	
	void applyWidth(ResizableType<T> res);
	
	T getWrappedHeight();
	
	void applyHeight(ResizableType<T> res);
	
	T getWrappedDepth();
	
	void applyDepth(ResizableType<T> res);
	
	public static interface IntegralDimensional extends DimensionalType<Integer> {
		
		int getWidth();
		
		default @Override Integer getWrappedWidth() {
			return Integer.valueOf(getWidth());
		}
		default @Override void applyWidth(ResizableType<Integer> res) {
			if (res instanceof IntegralResizable realres) {
				realres.setWidth(getWidth());
			}
			else {
				res.setWidth(getWrappedWidth());
			}
		}
		
		int getHeight();
		
		default @Override Integer getWrappedHeight() {
			return Integer.valueOf(getHeight());
		}
		default @Override void applyHeight(ResizableType<Integer> res) {
			if (res instanceof IntegralResizable realres) {
				realres.setHeight(getHeight());
			}
			else {
				res.setHeight(getWrappedHeight());
			}
		}
		
		int getDepth();
		
		default @Override Integer getWrappedDepth() {
			return Integer.valueOf(getDepth());
		}
		default @Override void applyDepth(ResizableType<Integer> res) {
			if (res instanceof IntegralResizable realres) {
				realres.setDepth(getDepth());
			}
			else {
				res.setDepth(getWrappedDepth());
			}
		}
		
		default int[] toArray() {
			return switch (this.getOrder()) {
				case MONODIMENSIONAL: yield new int[] {getWidth()};
				case BIDIMENSIONAL: yield new int[] {getWidth(), getHeight()};
				case TRIDIMENSIONAL: yield new int[] {getWidth(), getHeight(), getDepth()};
			};
		}
	}
	
	public static interface Dimensional extends DimensionalType<Float> {
		
		float getWidth();
		
		default @Override Float getWrappedWidth() {
			return Float.valueOf(getWidth());
		}
		default @Override void applyWidth(ResizableType<Float> res) {
			if (res instanceof Resizable realres) {
				realres.setWidth(getWidth());
			}
			else {
				res.setWidth(getWrappedWidth());
			}
		}
		
		float getHeight();
		
		default @Override Float getWrappedHeight() {
			return Float.valueOf(getHeight());
		}
		default @Override void applyHeight(ResizableType<Float> res) {
			if (res instanceof Resizable realres) {
				realres.setHeight(getHeight());
			}
			else {
				res.setHeight(getWrappedHeight());
			}
		}
		
		float getDepth();
		
		default @Override Float getWrappedDepth() {
			return Float.valueOf(getDepth());
		}
		default @Override void applyDepth(ResizableType<Float> res) {
			if (res instanceof Resizable realres) {
				realres.setDepth(getDepth());
			}
			else {
				res.setDepth(getWrappedDepth());
			}
		}
		
		default float[] toArray() {
			return switch (this.getOrder()) {
				case MONODIMENSIONAL: yield new float[] {getWidth()};
				case BIDIMENSIONAL: yield new float[] {getWidth(), getHeight()};
				case TRIDIMENSIONAL: yield new float[] {getWidth(), getHeight(), getDepth()};
			};
		}
	}
	
	public static interface DoubleDimensional extends DimensionalType<Double> {
		
		double getWidth();
		
		default @Override Double getWrappedWidth() {
			return Double.valueOf(getWidth());
		}
		default @Override void applyWidth(ResizableType<Double> res) {
			if (res instanceof DoubleResizable realres) {
				realres.setWidth(getWidth());
			}
			else {
				res.setWidth(getWrappedWidth());
			}
		}
		
		double getHeight();
		
		default @Override Double getWrappedHeight() {
			return Double.valueOf(getHeight());
		}
		default @Override void applyHeight(ResizableType<Double> res) {
			if (res instanceof DoubleResizable realres) {
				realres.setHeight(getHeight());
			}
			else {
				res.setHeight(getWrappedHeight());
			}
		}
		
		double getDepth();
		
		default @Override Double getWrappedDepth() {
			return Double.valueOf(getDepth());
		}
		default @Override void applyDepth(ResizableType<Double> res) {
			if (res instanceof DoubleResizable realres) {
				realres.setDepth(getDepth());
			}
			else {
				res.setDepth(getWrappedDepth());
			}
		}
		
		default double[] toArray() {
			return switch (this.getOrder()) {
				case MONODIMENSIONAL: yield new double[] {getWidth()};
				case BIDIMENSIONAL: yield new double[] {getWidth(), getHeight()};
				case TRIDIMENSIONAL: yield new double[] {getWidth(), getHeight(), getDepth()};
			};
		}
	}
}
