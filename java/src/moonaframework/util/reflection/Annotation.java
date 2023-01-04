package moonaframework.util.reflection;

import moonaframework.util.annotation.Deadlined;
import moonaframework.util.exception.NullArgumentException;

public final class Annotation extends Reference<Class<? extends java.lang.annotation.Annotation>> {
	
	public @Override boolean equals(Object o) {
		return (o instanceof Annotation ann) ?
				this.value.equals(ann.value)
				: false;
	}
	
	public @Override String toString() {
		return (value == null) ? "Non-generated Reflection" : "Annotation " + value.getName();
	}
	
	public @Deadlined void reflect() {
		
	}
	
	public @Override Class<? extends java.lang.annotation.Annotation> evaluate() {
		return super.value;
	}
	
	public Annotation(Class<? extends java.lang.annotation.Annotation> annot) throws NullArgumentException {
		if (annot == null) {
			throw new NullArgumentException("Cannot generate a Annotation Reference over a null class.");
		}
		super.value = annot;
	}
	public Annotation(java.lang.annotation.Annotation annot) throws NullArgumentException {
		if (annot == null) {
			throw new NullArgumentException("A null java.lang.annotation.Annotation cannot be accepted.");
		}
		super.value = annot.annotationType();
	}
}
