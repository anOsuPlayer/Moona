package moonaframework.util.reflection.flare;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.ReflectionNotFoundException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Constructor;
import moonaframework.util.reflection.ExistingGeneric;
import moonaframework.util.reflection.Field;
import moonaframework.util.reflection.Generic;
import moonaframework.util.reflection.Method;
import moonaframework.util.reflection.Reference;
import moonaframework.util.reflection.Type;

public final class TypeContent extends Flare<Reference<? extends AnnotatedElement>> {

	protected final Type target;
	
	public @Override Type getTarget() {
		return this.target;
	}
	
	private int methodCount = 0;
	
	public int methodCount() {
		return this.methodCount;
	}
	
	public List<Method> getMethods() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		final List<Method> list = new ArrayList<>();
		for (int i = 0; i < methodCount; i++) {
			list.add((Method) super.value.get(i));
		}
		return list;
	}
	
	public Method getMethod(int index) throws MoonaHandlingException, IndexOutOfBoundsException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index >= methodCount) {
			throw new IndexOutOfBoundsException("There are only " + methodCount + " Method References, index "
					+ index + " is out of range.");
		}
		
		return (Method) super.value.get(index);
	}
	public Method getMethod(String name, Class<?>...args) throws MoonaHandlingException, ReflectionNotFoundException, NullArgumentException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		if (name == null) {
			throw new NullArgumentException("The method's name can't be null.");
		}
		
		for (int i = 0; i < methodCount; i++) {
			Method m = getMethod(i);
			if (m.getName().equals(name) && Arrays.equals(m.getParameterTypes(), args)) {
				return m;
			}
		}
		throw new ReflectionNotFoundException("There is no method named " + name + " having those arguments"
				+ " in this TypeContent.");
	}
	
	private int constructorCount = 0;
	
	public int constructorCount() {
		return this.constructorCount;
	}
	public List<Constructor> getConstructors() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		final List<Constructor> list = new ArrayList<>();
		for (int i = methodCount; i < methodCount+constructorCount; i++) {
			list.add((Constructor) super.value.get(i));
		}
		return list;
	}
	
	public Constructor getConstructor(int index) throws MoonaHandlingException, IndexOutOfBoundsException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index >= constructorCount) {
			throw new IndexOutOfBoundsException("There are only " + constructorCount + " Constructor References,"
					+ "index " + index + " is out of range.");
		}
		
		return (Constructor) super.value.get(methodCount+index);
	}
	public Constructor getConstructor(Class<?>...args) throws MoonaHandlingException, ReflectionNotFoundException, NullArgumentException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		if (args == null) {
			throw new NullArgumentException("The constructor's arguments can't be null.");
		}
		
		for (int i = 0; i < methodCount; i++) {
			Constructor con = getConstructor(i);
			if (Arrays.equals(con.getParameterTypes(), args)) {
				return con;
			}
		}
		throw new ReflectionNotFoundException("There is no constructor having such parameters in this" 
				+ " TypeContent.");
	}
	
	private int fieldCount = 0;
	
	public int fieldCount() {
		return this.fieldCount;
	}
	public List<Field> getFields() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		final List<Field> list = new ArrayList<>();
		for (int i = constructorCount+methodCount; i < constructorCount+methodCount+fieldCount; i++) {
			list.add((Field) super.value.get(i));
		}
		return list;
	}
	
	public Field getField(int index) throws MoonaHandlingException, IndexOutOfBoundsException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index >= fieldCount) {
			throw new IndexOutOfBoundsException("There are only " + fieldCount + " Field References, index "
					+ index + " is out of range.");
		}
		
		return (Field) super.value.get(methodCount+constructorCount+index);
	}
	public Field getField(String name) throws MoonaHandlingException, ReflectionNotFoundException, NullArgumentException {
		if (name == null) {
			throw new NullArgumentException("The field's name can't be null.");
		}
		
		if (!super.hasGenerated) {
			reflect();
		}
		
		for (int i = 0; i < fieldCount; i++) {
			Field f = getField(i);
			if (f.getName().equals(name)) {
				return f;
			}
		}
		throw new ReflectionNotFoundException("There is no field named " + name + " in this TypeContent.");
	}
	
	private int typeArgumentsCount = 0;
	
	public int typeArgumentsCount() {
		return this.typeArgumentsCount;
	}
	public List<Generic> getTypeArguments() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		final List<Generic> list = new ArrayList<>();
		for (int i = constructorCount+methodCount+fieldCount; i < totalReflections(); i++) {
			list.add((Generic) super.value.get(i));
		}
		return list;
	}
	
	public Generic getTypeArgument(int index) throws MoonaHandlingException, IndexOutOfBoundsException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index >= typeArgumentsCount) {
			throw new IndexOutOfBoundsException("There are only " + typeArgumentsCount + " Generic References, index "
					+ index + " is out of range.");
		}
		
		return (Generic) super.value.get(methodCount+constructorCount+fieldCount+index);
	}
	public Generic getTypeArgument(String name) throws MoonaHandlingException, ReflectionNotFoundException, NullArgumentException{
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
		throw new ReflectionNotFoundException("There is no generic named " + name + " in this TypeContent.");
	}
	
	public int totalReflections() {
		return methodCount + constructorCount + fieldCount + typeArgumentsCount;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof TypeContent tc) ? this.getTarget().equals(tc.getTarget()) : false;
	}
	
	public @Override String toString() {
		return (!super.hasGenerated) ? "Non-generated Flare" : "TypeContent of " + target;
	}
	
	public @Override void reflect() throws MoonaHandlingException {
		Class<?> clazz = target.evaluate();
		
		strictContext.enable();
		
		for (java.lang.reflect.Method m : clazz.getDeclaredMethods()) {
			super.value.add(new Method(m));
			methodCount++;
		}
		
		for (java.lang.reflect.Constructor<?> con : clazz.getDeclaredConstructors()) {
			super.value.add(new Constructor(con));
			constructorCount++;
		}
		
		for (java.lang.reflect.Field f : clazz.getDeclaredFields()) {
			super.value.add(new Field(f));
			fieldCount++;
		}
		
		for (java.lang.reflect.TypeVariable<?> tv : clazz.getTypeParameters()) {
			super.value.add(new ExistingGeneric(target, tv.getName(), tv));
			typeArgumentsCount++;
		}
		
		strictContext.disable();
		
		try {
			super.reflect();
		}
		catch (UndefinedReflectionException ure) {
			throw new MoonaHandlingException("This should not happen.");
		}
	}
	
	public TypeContent(Type target) throws NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("TypeContent cannot be extracted from a null Type Reference.");
		}
		this.target = target;
		
		super.mirrorInteraction(); super.valueExtraction();
	}
	public TypeContent(Class<?> target) throws NullArgumentException {
		this(new Type(target));
	}
}
