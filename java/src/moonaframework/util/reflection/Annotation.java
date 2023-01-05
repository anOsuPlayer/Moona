package moonaframework.util.reflection;

import java.lang.reflect.AnnotatedElement;

import moonaframework.util.annotation.Deadlined;
import moonaframework.util.exception.NullArgumentException;

public final class Annotation<A extends java.lang.annotation.Annotation> extends Reflection<A> {
	
	private final Reference<? extends AnnotatedElement> target;
	
	public @Override Reference<? extends AnnotatedElement> getTarget() {
		return this.target;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Annotation<?> ann) ?
				this.value.equals(ann.value)
				: false;
	}
	
	public @Override String toString() {
		return (value == null) ? "Non-generated Reflection" : "Annotation " + value.toString();
	}
	
	public @Deadlined void reflect() {
		
	}
	
	public @Override A evaluate() {
		return super.value;
	}
	
	public Annotation(Reference<? extends AnnotatedElement> target, A annot) throws NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("Cannot generate an Annotation Reference over a null class.");
		}
		if (annot == null) {
			throw new NullArgumentException("A null java.lang.annotation.Annotation cannot be accepted.");
		}
		this.target = target; super.value = annot;
	}
}
