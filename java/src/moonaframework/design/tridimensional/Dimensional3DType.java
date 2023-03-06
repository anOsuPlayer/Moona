package moonaframework.design.tridimensional;

import moonaframework.design.bidimensional.Dimensional2DType;
import moonaframework.design.tridimensional.geometry.TridimensionalBounds;
import moonaframework.design.tridimensional.geometry.TridimensionalBounds.Bounds3D;
import moonaframework.design.tridimensional.geometry.TridimensionalBounds.DoubleBounds3D;
import moonaframework.design.tridimensional.geometry.TridimensionalBounds.IntegralBounds3D;
import moonaframework.design.tridimensional.geometry.TridimensionalSize;
import moonaframework.design.tridimensional.geometry.TridimensionalSize.DoubleSize3D;
import moonaframework.design.tridimensional.geometry.TridimensionalSize.IntegralSize3D;
import moonaframework.design.tridimensional.geometry.TridimensionalSize.Size3D;

public interface Dimensional3DType<T extends Number> extends Dimensional2DType<T> {

	T getWrappedDepth();
	
	@Override TridimensionalSize<T> getSize();
	
	@Override TridimensionalBounds<T> getBounds();
	
	public static interface IntegralDimensional3D extends Dimensional3DType<Integer>, IntegralDimensional2D {
		
		default @Override Integer getWrappedDepth() {
			return Integer.valueOf(getDepth());
		}
		
		int getDepth();
		
		default @Override TridimensionalSize<Integer> getSize() {
			return new IntegralSize3D(getWidth(), getHeight(), getDepth());
		}
		default @Override TridimensionalBounds<Integer> getBounds() {
			return new IntegralBounds3D(this);
		}
	}
	
	public static interface Dimensional3D extends Dimensional3DType<Float>, Dimensional2D {
		
		default @Override Float getWrappedDepth() {
			return Float.valueOf(getDepth());
		}
		
		float getDepth();
		
		default @Override TridimensionalSize<Float> getSize() {
			return new Size3D(getWidth(), getHeight(), getDepth());
		}
		default @Override TridimensionalBounds<Float> getBounds() {
			return new Bounds3D(this);
		}
	}

	public static interface DoubleDimensional3D extends Dimensional3DType<Double>, DoubleDimensional2D {
		
		default @Override Double getWrappedDepth() {
			return Double.valueOf(getDepth());
		}
		
		double getDepth();
		
		default @Override TridimensionalSize<Double> getSize() {
			return new DoubleSize3D(getWidth(), getHeight(), getDepth());
		}
		default @Override TridimensionalBounds<Double> getBounds() {
			return new DoubleBounds3D(this);
		}
	}
}
