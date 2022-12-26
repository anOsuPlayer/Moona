package moonaframework.util.reflection.cluster;

import java.lang.reflect.AnnotatedElement;

import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.reflection.Constructor;
import moonaframework.util.reflection.Field;
import moonaframework.util.reflection.Method;
import moonaframework.util.reflection.Reference;
import moonaframework.util.reflection.Type;

public final class TypeContent extends Cluster<Reference<? extends AnnotatedElement>> {

	private final Type source;
	
	public Type getTarget() {
		return this.source;
	}
	
	public @Override void reflect() {
		 Class<?> clazz = source.evaluate();
		 
		 for (java.lang.reflect.Method m : clazz.getDeclaredMethods()) {
			 Method newRef = new Method(m);
			 super.value.add(newRef);
		 }
		 
		 for (java.lang.reflect.Field f : clazz.getDeclaredFields()) {
			 Field newRef = new Field(f);
			 super.value.add(newRef);
		 }
		 
		 for (java.lang.reflect.Constructor<?> con : clazz.getDeclaredConstructors()) {
			 Constructor newRef = new Constructor(con);
			 super.value.add(newRef);
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
