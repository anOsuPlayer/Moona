package moonaframework.util;

import java.util.function.Supplier;

import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.function.Property;

public class Delegate<T> implements Property<T> {

	private Supplier<T> method;
	
	public void watch(Supplier<T> source) throws NullArgumentException {
		if (source == null) {
			throw new NullArgumentException("A Delegate cannot be bound to a null method.");
		}
		this.method = source;
	}
	public T evaluate() {
		return method.get();
	}
	
	public Delegate(Supplier<T> source) throws NullArgumentException {
		watch(source);
	}
}
