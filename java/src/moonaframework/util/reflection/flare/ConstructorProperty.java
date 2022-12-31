package moonaframework.util.reflection.flare;

import java.util.ArrayList;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Constructor;
import moonaframework.util.reflection.Modifier;
import moonaframework.util.reflection.Parameter;
import moonaframework.util.reflection.Reflection;

public class ConstructorProperty extends Flare<Reflection<?>> {

	private final Constructor source;
	
	public @Override Constructor getTarget() {
		return this.source;
	}
	
	public Modifier getModifiers() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (Modifier) super.value.get(0);
	}
	
	public List<Parameter> getParameters() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		final List<Parameter> list = new ArrayList<>();
		for (int i = 1; i < super.value.size(); i++) {
			list.add((Parameter) super.value.get(i));
		}
		return list;
	}
	public Parameter getParameter(int index) throws IllegalArgumentException, MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index + 1 >= super.value.size()) {
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " Parameter "
					+ "References, index " + index + " is out of range.");
		}
		
		return (Parameter) super.value.get(index+1);
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		java.lang.reflect.Constructor<?> method = source.evaluate();
		
		strictContext.enable();
		
		super.value.add((Modifier) new Modifier_(source, method.getModifiers()));
		
		java.lang.reflect.Parameter[] params = method.getParameters();
		for (int i = 0; i < params.length; i++) {
			super.value.add((Parameter) new Parameter_(source, i, params[i]));
		}
		
		strictContext.disable();
		
		super.reflect();
	}
	
	public ConstructorProperty(Constructor source) throws NullArgumentException {
		if (source == null) {
			throw new NullArgumentException("MethodProperties cannot be extracted from a null Method Reference.");
		}
		this.source = source;
	}
}
