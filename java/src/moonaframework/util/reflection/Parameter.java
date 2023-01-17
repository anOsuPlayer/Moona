package moonaframework.util.reflection;

import java.lang.reflect.Executable;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;

public sealed class Parameter extends Reference<java.lang.reflect.Parameter> permits ExistingParameter {

	private final Reference<? extends Executable> target;
	
	public Reference<? extends Executable> getTarget() {
		return this.target;
	}
	public Class<?> getType() {
		return super.value.getType();
	}
	
	private final int index;
	
	public int getIndex() {
		return this.index;
	}
	
	public boolean isVarArgs() throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return super.value.isVarArgs();
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Parameter param) ?
				this.target.equals(param.target) && this.index == param.index
				: false;
	}
	
	public @Override String toString() {
		return (target == null) ? "Non-generated Reflection" : "Parameter n." + (index+1) + " of " + target;
	}
	
	public @Override void reflect() throws UndefinedReflectionException, IndexOutOfBoundsException {
		java.lang.reflect.Parameter[] params = target.evaluate().getParameters();
		
		if (index >= params.length) {
			throw new IndexOutOfBoundsException("Only " + params.length + " parameters are available.");
		}
		
		if (index < params.length) {
			super.value = params[index];
			return;
		}
		
		throw new UndefinedReflectionException("Parameter n." + index + " could not be evaluated from the given"
				+ " Reference.");
	}
	
	public Parameter(Reference<? extends Executable> target, int index) throws IllegalArgumentException, NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("Cannot generate a Parameter Reference over a null Reference.");
		}
		if (index < 0) {
			throw new IllegalArgumentException("Parameter's index cannot be less than zero.");
		}
		this.target = target; this.index = index;
		
		super.mirrorInteraction(); super.valueExtraction();
	}
}
