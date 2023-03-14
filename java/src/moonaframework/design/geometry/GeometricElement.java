package moonaframework.design.geometry;

public interface GeometricElement<T extends Number> {

	DimensionalOrder order();
	
	T[] toWrappedArray();
	
	public static interface IntegralGeometricElement extends GeometricElement<Integer> {
		
		default @Override Integer[] toWrappedArray() {
			int[] arr = toArray(); Integer[] r = new Integer[arr.length];
			for (int i = 0; i < arr.length; i++) {
				r[i] = Integer.valueOf(arr[i]);
			}
			return r;
		}
		
		int[] toArray();
		
		default @Override DimensionalOrder order() {
			return DimensionalOrder.fromCode(toArray().length);
		}
	}
	
	public static interface FloatGeometricElement extends GeometricElement<Float> {
		
		default @Override Float[] toWrappedArray() {
			float[] arr = toArray(); Float[] r = new Float[arr.length];
			for (int i = 0; i < arr.length; i++) {
				r[i] = Float.valueOf(arr[i]);
			}
			return r;
		}
		
		float[] toArray();
		
		default @Override DimensionalOrder order() {
			return DimensionalOrder.fromCode(toArray().length);
		}
	}

	public static interface DoubleGeometricElement extends GeometricElement<Double> {
		
		default @Override Double[] toWrappedArray() {
			double[] arr = toArray(); Double[] r = new Double[arr.length];
			for (int i = 0; i < arr.length; i++) {
				r[i] = Double.valueOf(arr[i]);
			}
			return r;
		}
		
		double[] toArray();
		
		default @Override DimensionalOrder order() {
			return DimensionalOrder.fromCode(toArray().length);
		}
	}
}