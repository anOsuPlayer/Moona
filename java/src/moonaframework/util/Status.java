package moonaframework.util;

import moonaframework.util.annotation.Deadlined;
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
	
	public boolean isValue(T t) {
		return this.value.equals(t);
	}
	public @Override boolean equals(Object o) {
		return (o instanceof Status<?> s) ? s.evaluate().equals(o) : false;
	}
	
	protected Status(T value) {
		setValue(value);
	}
	protected Status() {
		
	}
}
