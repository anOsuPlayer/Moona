package moonaframework.design.geometry;

public enum DimensionalOrder {

	MONODIMENSIONAL(1, "Monodimensional Entity"),
	
	BIDIMENSIONAL(2, "Bidimensional Entity"),
	
	TRIDIMENSIONAL(3, "Tridimensional Entity");
	
	private final int index;
	
	private final String message;
	
	public @Override String toString() {
		return this.message;
	}
	
	static DimensionalOrder fromCode(int code) {
		for (DimensionalOrder d : DimensionalOrder.values()) {
			if (d.index == code) { return d; }
		}
		return null;
	}
	
	private DimensionalOrder(int index, String message) {
		this.index = index; this.message = message;
	}
}
