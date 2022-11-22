package moonaframework.util.collection;

public class Duo<V1, V2> extends FinalDuo<V1, V2> {

	public void setValue1(V1 value1) {
		super.value1 = value1;
	}
	
	public void setValue2(V2 value2) {
		super.value2 = value2;
	}
	
	public Duo() {
		super();
	}
	public Duo(V1 value1, V2 value2) {
		super(value1, value2);
	}
}
