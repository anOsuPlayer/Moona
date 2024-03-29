package moonaframework.util.reflection.flare;

import java.util.ArrayList;
import java.util.List;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.ReflectionNotFoundException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.ExistingGeneric;
import moonaframework.util.reflection.ExistingModifier;
import moonaframework.util.reflection.ExistingParameter;
import moonaframework.util.reflection.Generic;
import moonaframework.util.reflection.Method;
import moonaframework.util.reflection.Modifier;
import moonaframework.util.reflection.Parameter;
import moonaframework.util.reflection.Reflection;
import moonaframework.util.reflection.Type;

public class MethodProperty extends Flare<Reflection<?>> {

	private final Method target;
	
	public @Override Method getTarget() {
		return this.target;
	}
	
	public Modifier getModifiers() throws UndefinedReflectionException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		return (Modifier) super.value.get(0);
	}
	
	private int parameterCount = 0;
	
	public int parameterCount() {
		return this.parameterCount;
	}
	public List<Parameter> getParameters() throws UndefinedReflectionException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		final List<Parameter> list = new ArrayList<>();
		for (int i = 1; i < super.value.size(); i++) {
			list.add((Parameter) super.value.get(i));
		}
		
		return list;
	}
	
	public Parameter getParameter(int index) throws UndefinedReflectionException, IllegalArgumentException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index >= parameterCount) {
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " Parameter "
					+ "References, index " + index + " is out of range.");
		}
		
		return (Parameter) super.value.get(index+1);
	}
	
	private int typeArgumentsCount = 0;
	
	public int typeArgumentsCount() {
		return this.typeArgumentsCount;
	}
	public List<Generic> getTypeArguments() throws UndefinedReflectionException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		final List<Generic> list = new ArrayList<>();
		for (int i = parameterCount; i < super.value.size(); i++) {
			list.add((Generic) super.value.get(i));
		}
		return list;
	}
	
	public Generic getTypeArgument(int index) throws UndefinedReflectionException, IllegalArgumentException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index + 1 >= super.value.size()) {
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " RawType "
					+ "References, index " + index + " is out of range.");
		}
		
		return (Generic) super.value.get(1+parameterCount+index);
	}
	public Generic getTypeArgument(String name) throws UndefinedReflectionException, ReflectionNotFoundException, NullArgumentException {
		if (name == null) {
			throw new NullArgumentException("The field's name can't be null.");
		}
		
		if (!super.hasGenerated) {
			reflect();
		}
		
		for (int i = 0; i < typeArgumentsCount; i++) {
			Generic g = getTypeArgument(i);
			if (g.getName().equals(name)) {
				return g;
			}
		}
		throw new ReflectionNotFoundException("There is no generic named " + name + " in this MethodProperty.");
	}
	
	public Class<?> getReturnType() throws UndefinedReflectionException {
		return target.evaluate().getReturnType();
	}
	public Type getReturnTypeReference() throws UndefinedReflectionException {
		return new Type(getReturnType());
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof MethodProperty mp) ? this.getTarget().equals(mp.getTarget()) : false;
	}
	
	public @Override String toString() {
		return (!super.hasGenerated) ? "Non-generated Flare" : "MethodProperty of " + target;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		java.lang.reflect.Method method = target.evaluate();
		
		strictContext.enable();
		
		super.value.add(new ExistingModifier(target, method.getModifiers()));
		
		java.lang.reflect.Parameter[] params = method.getParameters();
		for (int i = 0; i < params.length; i++) {
			super.value.add(new ExistingParameter(target, i, params[i]));
			parameterCount++;
		}
		
		for (java.lang.reflect.TypeVariable<?> tv : method.getTypeParameters()) {
			super.value.add(new ExistingGeneric(target, tv.getName(), tv));
			typeArgumentsCount++;
		}
		
		strictContext.disable();
		
		super.reflect();
	}
	
	public MethodProperty(Method target) throws NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("MethodProperties cannot be extracted from a null Method Reference.");
		}
		this.target = target;
		
		super.mirrorInteraction(); super.valueExtraction();
	}
}
