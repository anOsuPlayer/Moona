package moonaframework.util.reflection.beacon;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.reflection.Constructor;
import moonaframework.util.reflection.Field;
import moonaframework.util.reflection.Method;
import moonaframework.util.reflection.Reference;
import moonaframework.util.reflection.Type;

public final class TypeContent extends Beacon<Reference<? extends AnnotatedElement>> {

	private final Type source;
	
	public @Override Type getTarget() {
		return this.source;
	}
	
	private int methodCount = 0;
	
	public int methodCount() {
		return this.methodCount;
	}
	public List<Method> getMethods() {
		if (!super.hasGenerated) {
			reflect();
		}
		final List<Method> list = new ArrayList<>();
		for (int i = 0; i < methodCount; i++) {
			list.add((Method) super.value.get(i));
		}
		return list;
	}
	
	public Method getMethod(int index) throws IndexOutOfBoundsException {
		if (!super.hasGenerated) {
			reflect();
		}
		if (index >= methodCount) {
			throw new IndexOutOfBoundsException("There are only " + methodCount + " Method References, index "
					+ index + " is out of range.");
		}
		return (Method) super.value.get(index);
	}
	
	private int constructorCount = 0;
	
	public int constructorCount() {
		return this.fieldCount;
	}
	public List<Constructor> getConstructors() {
		if (!super.hasGenerated) {
			reflect();
		}
		final List<Constructor> list = new ArrayList<>();
		for (int i = methodCount; i < methodCount+constructorCount; i++) {
			list.add((Constructor) super.value.get(i));
		}
		return list;
	}
	
	public Constructor getConstructor(int index) throws IndexOutOfBoundsException {
		if (!super.hasGenerated) {
			reflect();
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
	public List<Field> getFields() {
		if (!super.hasGenerated) {
			reflect();
		}
		final List<Field> list = new ArrayList<>();
		for (int i = constructorCount+methodCount; i < totalReflections(); i++) {
			list.add((Field) super.value.get(i));
		}
		return list;
	}
	
	public Field getField(int index) throws IndexOutOfBoundsException {
		if (!super.hasGenerated) {
			reflect();
		}
		if (index >= methodCount) {
			throw new IndexOutOfBoundsException("There are only " + fieldCount + " Field References, index "
					+ index + " is out of range.");
		}
		return (Field) super.value.get(methodCount+constructorCount+index);
	}
	
	public int totalReflections() {
		return methodCount + constructorCount + fieldCount;
	}
	
	public @Override void reflect() {
		 Class<?> clazz = source.evaluate();
		 
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
