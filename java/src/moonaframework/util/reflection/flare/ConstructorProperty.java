package moonaframework.util.reflection.flare;

import java.util.ArrayList;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.ReflectionNotFoundException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Constructor;
import moonaframework.util.reflection.ExistingGeneric;
import moonaframework.util.reflection.ExistingModifier;
import moonaframework.util.reflection.ExistingParameter;
import moonaframework.util.reflection.Generic;
import moonaframework.util.reflection.Modifier;
import moonaframework.util.reflection.Parameter;
import moonaframework.util.reflection.Reflection;

public class ConstructorProperty extends Flare<Reflection<?>> {

	private final Constructor target;
	
	public @Override Constructor getTarget() {
		return this.target;
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
	
	private int parameterCount = 0;
	
	public int parameterCount() {
		return this.parameterCount;
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
	public List<Generic> getTypeArguments() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		final List<Generic> list = new ArrayList<>();
		for (int i = parameterCount; i < super.value.size(); i++) {
			list.add((Generic) super.value.get(i));
		}
		return list;
	}
	
	public Generic getTypeArgument(int index) throws IllegalArgumentException, MoonaHandlingException {
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
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " RawType "
					+ "References, index " + index + " is out of range.");
		}
		
		return (Generic) super.value.get(1+parameterCount+index);
	}
	public Generic getTypeArgument(String name) throws ReflectionNotFoundException, NullArgumentException, MoonaHandlingException {
		if (name == null) {
			throw new NullArgumentException("The field's name can't be null.");
		}
		
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		for (int i = 0; i < typeArgumentsCount; i++) {
			Generic g = getTypeArgument(i);
			if (g.getName().equals(name)) {
				return g;
			}
		}
		throw new ReflectionNotFoundException("There is no generic named " + name + " in this ConstructorProperty.");
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof ConstructorProperty cp) ? this.getTarget().equals(cp.getTarget()) : false;
	}
	
	public @Override String toString() {
		return (!super.hasGenerated) ? "Non-generated Flare" : "ConstructorProperty of " + target;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		java.lang.reflect.Constructor<?> method = target.evaluate();
		
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
	
	public ConstructorProperty(Constructor target) throws NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("ConstructorProperty cannot be extracted from a null Constructor Reference.");
		}
		this.target = target;
	}
}
