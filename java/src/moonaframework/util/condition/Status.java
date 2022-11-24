package moonaframework.util.condition;

import moonaframework.util.annotations.Deadlined;

public class Status<T> {

	private T value;
	
	public T getValue() {
		return this.value;
	}
	
	protected void setValue(T value) {
		this.value = value;
	}
	
	@Deadlined
	protected void reverse() {
	}
	
	protected Status(T value) {
		setValue(value);
	}
}
