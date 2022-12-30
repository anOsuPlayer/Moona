package moonaframework.util.reflection.beacon;

import java.util.ArrayList;
import java.util.List;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UnresolvedReflectionException;
import moonaframework.util.reflection.Method;
import moonaframework.util.reflection.Modifier;
import moonaframework.util.reflection.Parameter;
import moonaframework.util.reflection.Reflection;

public class MethodProperty extends Beacon<Reflection<?>> {

	private final Method source;
	
	public @Override Method getTarget() {
		return this.source;
	}
	
	public Modifier getModifiers() {
		if (!super.hasGenerated) {
			reflect();
		}
		return (Modifier) super.value.get(0);
	}
	
	public List<Parameter> getParameters() {
		if (!super.hasGenerated) {
			reflect();
		}
		final List<Parameter> list = new ArrayList<>();
		for (int i = 1; i < super.value.size(); i++) {
			list.add((Parameter) super.value.get(i));
		}
		return list;
	}
	public Parameter getParameter(int index) throws IllegalArgumentException {
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index + 1 >= super.value.size()) {
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " Parameter "
					+ "References, index " + index + " is out of range.");
		}
		if (!super.hasGenerated) {
			reflect();
		}
		return (Parameter) super.value.get(index+1);
	}
	
	public @Override List<Reflection<?>> evaluate() {
		if (!super.hasGenerated) {
			reflect();
		}
		return super.value;
	}
	
	public @Override void reflect() {
		try {
			java.lang.reflect.Method method = source.evaluate();
			
			strictContext.enable();
			
			super.value.add(new Modifier(source, method.getModifiers()));
			
			java.lang.reflect.Parameter[] params = method.getParameters();
			for (int i = 0; i < params.length; i++) {
				super.value.add(new Parameter(source, i, params[i]));
			}
			
			strictContext.disable();
			
			super.reflect();
		}
		catch (UnresolvedReflectionException ure) {
			ure.printStackTrace();
		}
	}
	
	public MethodProperty(Method source) throws NullArgumentException {
		if (source == null) {
			throw new NullArgumentException("MethodProperties cannot be extracted from a null Method Reference.");
		}
		this.source = source;
	}
}
