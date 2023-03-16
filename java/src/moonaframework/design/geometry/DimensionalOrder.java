package moonaframework.design.geometry;

public enum DimensionalOrder {

	MONODIMENSIONAL(1, "Monodimensional Entity"),
	
	BIDIMENSIONAL(2, "Bidimensional Entity"),
	
	TRIDIMENSIONAL(3, "Tridimensional Entity");
	
	private final int number;
	
	public final int number() {
		return this.number;
	}
	
	private final String message;
	
	public @Override String toString() {
		return this.message;
	}
	
	public static DimensionalOrder fromCode(int code) {
		for (DimensionalOrder d : DimensionalOrder.values()) {
			if (d.number == code) { return d; }
		}
		return null;
	}
	
	public boolean satisfies(DimensionalOrder dim) {
		return dim.number <= number;
	}
	
	private DimensionalOrder(int index, String message) {
		this.number = index; this.message = message;
	}
}
