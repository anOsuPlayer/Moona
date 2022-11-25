package moonaframework.util;

import moonaframework.util.annotations.Deadlined;
import moonaframework.util.function.Property;

public class Status<T> implements Property<T> {

	private T value;
	
	public T evaluate() {
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
