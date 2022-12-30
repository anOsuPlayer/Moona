package moonaframework.util.reflection;

import java.lang.reflect.Executable;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UnresolvedReflectionException;

public final class Parameter extends Reference<java.lang.reflect.Parameter> {

	private final Reference<? extends Executable> source;
	
	public final Reference<? extends Executable> getSource() {
		return this.source;
	}
	public final Class<?> getType() {
		return super.value.getType();
	}
	
	private final int index;
	
	public final int getIndex() {
		return this.index;
	}
	
	public final boolean isVarArgs() {
		return super.value.isVarArgs();
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Parameter param) ?
				this.source.equals(param.source) && this.index == param.index
				: false;
	}
	
	public @Override String toString() {
		return (source == null) ? "Non-generated Reflection" : "Parameter n." + index + " of " + source;
	}
	
	public @Override final void reflect() throws UnresolvedReflectionException {
		java.lang.reflect.Parameter[] params = source.evaluate().getParameters();
		if (index < params.length) {
			super.value = params[index];
			return;
		}
		throw new UnresolvedReflectionException("Parameter n." + index + " could not be evaluated from the given"
				+ " Reference.");
	}
	
	public Parameter(Reference<? extends Executable> source, int index) throws IllegalArgumentException, NullArgumentException {
		if (source == null) {
			throw new NullArgumentException("Cannot generate a Parameter Reference over a null Reference.");
		}
		if (index < 0) {
			throw new IllegalArgumentException("Parameter's index cannot be less than zero.");
		}
		this.source = source; this.index = index;
	}
	
	public Parameter(Reference<? extends Executable> source, int index, java.lang.reflect.Parameter param) throws IllegalArgumentException {
		this(source, index);
		if (param == null) {
			throw new NullArgumentException("A null java.lang.reflect.Parameter cannot be accepted.");
		}
		super.value = param;
	}
}
