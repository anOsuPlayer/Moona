package moonaframework.util.reflection;

import java.lang.reflect.AnnotatedElement;

import moonaframework.util.annotation.Deadlined;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.reflection.flare.Annotated;
import moonaframework.util.reflection.flare.Flare;

public final class Annotation<A extends java.lang.annotation.Annotation> extends Reflection<A> implements Derivable {
	
	private final Reference<? extends AnnotatedElement> target;
	
	public @Override Reference<? extends AnnotatedElement> getTarget() {
		return this.target;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Annotation<?> ann) ?
				this.target.equals(ann.target) && this.value.equals(ann.value)
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
	
	private Annotated annots;
	
	public final Annotated getAnnotated() {
		if (annots == null) {
			annots = new Annotated(new Type(super.value.annotationType()));
		}
		return annots;
	}
	
	public @Deadlined Flare<?> derive() {
		return Flare.EMPTY_FLARE;
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
