package moonaframework.util.reflection.flare;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.ReflectionNotFoundException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Constructor;
import moonaframework.util.reflection.Field;
import moonaframework.util.reflection.Method;
import moonaframework.util.reflection.Reference;
import moonaframework.util.reflection.Type;

public final class TypeContent extends Flare<Reference<? extends AnnotatedElement>> {

	private final Type source;
	
	public @Override Type getTarget() {
		return this.source;
	}
	
	private int methodCount = 0;
	
	public int methodCount() {
		return this.methodCount;
	}
	public List<Method> getMethods() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		final List<Method> list = new ArrayList<>();
		for (int i = 0; i < methodCount; i++) {
			list.add((Method) super.value.get(i));
		}
		return list;
	}
	
	public Method getMethod(int index) throws IndexOutOfBoundsException, MoonaHandlingException {
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
		if (index >= methodCount) {
			throw new IndexOutOfBoundsException("There are only " + methodCount + " Method References, index "
					+ index + " is out of range.");
		}
		
		return (Method) super.value.get(index);
	}
	public Method getMethod(String name) throws ReflectionNotFoundException, NullArgumentException, MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		if (name == null) {
			throw new NullArgumentException("The method's name can't be null.");
		}
		
		for (int i = 0; i < methodCount; i++) {
			Method m = getMethod(i);
			if (m.getName().equals(name)) {
				return m;
			}
		}
		throw new ReflectionNotFoundException("There is no method named " + name + " in this TypeContent.");
	}
	
	private int constructorCount = 0;
	
	public int constructorCount() {
		return this.fieldCount;
	}
	public List<Constructor> getConstructors() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		final List<Constructor> list = new ArrayList<>();
		for (int i = methodCount; i < methodCount+constructorCount; i++) {
			list.add((Constructor) super.value.get(i));
		}
		return list;
	}
	
	public Constructor getConstructor(int index) throws IndexOutOfBoundsException, MoonaHandlingException {
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
		if (index >= methodCount) {
			throw new IndexOutOfBoundsException("There are only " + constructorCount + " Constructor References,"
					+ "index " + index + " is out of range.");
		}
		
		return (Constructor) super.value.get(methodCount+index);
	}
	
	private int fieldCount = 0;
	
	public int fieldCount() {
		return this.fieldCount;
	}
	public List<Field> getFields() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		final List<Field> list = new ArrayList<>();
		for (int i = constructorCount+methodCount; i < totalReflections(); i++) {
			list.add((Field) super.value.get(i));
		}
		return list;
	}
	
	public Field getField(int index) throws IndexOutOfBoundsException, MoonaHandlingException {
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
		if (index >= methodCount) {
			throw new IndexOutOfBoundsException("There are only " + fieldCount + " Field References, index "
					+ index + " is out of range.");
		}
		
		return (Field) super.value.get(methodCount+constructorCount+index);
	}
	public Field getField(String name) throws ReflectionNotFoundException, NullArgumentException, MoonaHandlingException {
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
		for (int i = 0; i < methodCount; i++) {
			Field f = getField(i);
			if (f.getName().equals(name)) {
				return f;
			}
		}
		throw new ReflectionNotFoundException("There is no field named " + name + " in this TypeContent.");
	}
	
	public int totalReflections() {
		return methodCount + constructorCount + fieldCount;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		Class<?> clazz = source.evaluate();
		
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
		
		strictContext.disable();
		
		super.reflect();
	}
	
	public TypeContent(Type source) throws NullArgumentException {
		if (source == null) {
			throw new NullArgumentException("TypeContent cannot be extracted from a null Type Reference.");
		}
		this.source = source;
	}
	public TypeContent(Class<?> source) throws NullArgumentException {
		this.source = new Type(source);
	}
}