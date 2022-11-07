package moonaFramework.util.collection;

public abstract class FinalDuo<V1, V2> {

	protected V1 value1;
	public V1 getValue1() {
		return value1;
	}
	
	protected V2 value2;
	public V2 getValue2() {
		return value2;
	}
	
	protected FinalDuo() {
		
	}
	public FinalDuo(V1 value1, V2 value2) {
		this.value1 = value1; this.value2 = value2;
	}
}
