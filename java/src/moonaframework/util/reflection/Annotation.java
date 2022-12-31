package moonaframework.util.reflection;

import moonaframework.util.exception.NullArgumentException;

public final class Annotation extends Reference<Class<? extends java.lang.annotation.Annotation>> {

	private final Class<? extends java.lang.annotation.Annotation> annot;
	
	public @Override boolean equals(Object o) {
		return (o instanceof Annotation ann) ?
				this.annot.equals(ann.annot)
				: false;
	}
	
	public @Override String toString() {
		return (annot == null) ? "Non-generated Reflection" : "Annotation " + annot.getName();
	}
	
	public @Override void reflect() {
		super.value = this.annot;
	}
	
	public @Override Class<? extends java.lang.annotation.Annotation> evaluate() {
		return this.annot;
	}
	
	public Annotation(Class<? extends java.lang.annotation.Annotation> annot) throws NullArgumentException {
		if (annot == null) {
			throw new NullArgumentException("Cannot generate a Annotation Reference over a null class.");
		}
		this.annot = annot;
	}
	public Annotation(java.lang.annotation.Annotation annot) throws NullArgumentException {
		if (annot == null) {
			throw new NullArgumentException("A null java.lang.annotation.Annotation cannot be accepted.");
		}
		this.annot = annot.annotationType();
	}
}
