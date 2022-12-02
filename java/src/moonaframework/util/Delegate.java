package moonaframework.util;

import java.util.function.Supplier;

import moonaframework.base.Satellite;
import moonaframework.util.annotations.Functional;
import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.function.Property;

public @Functional class Delegate<T> implements Property<T>, Satellite<Supplier<T>> {
	
	private Supplier<T> method;
	
	public @Override Supplier<T> translate() {
		return method;
	}
	
	public void watch(Supplier<T> source) throws NullArgumentException {
		if (source == null) {
			throw new NullArgumentException("A Delegate cannot be bound to a null method.");
		}
		this.method = source;
	}
	public @Override T evaluate() {
		return method.get();
	}
	
	public Delegate(Supplier<T> source) throws NullArgumentException {
		watch(source);
	}
}