package moonaframework.util;

import moonaframework.util.annotations.Deadlined;
import moonaframework.util.functional.Property;

public class Status<T> implements Property<T> {

	private T value;
	
	public @Override T evaluate() {
		return this.value;
	}
	
	protected void setValue(T value) {
		this.value = value;
	}
	
	protected @Deadlined void reverse() {
		
	}
	
	protected Status(T value) {
		setValue(value);
	}
}
