package moonaframework.util.reflection.flare;

import java.lang.reflect.AnnotatedElement;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Annotation;
import moonaframework.util.reflection.Reference;

public final class Annotated extends Flare<Annotation<?>> {
	
	private final Reference<? extends AnnotatedElement> target;
	
	public @Override Reference<? extends AnnotatedElement> getTarget() {
		return this.target;
	}
	
	public List<Annotation<?>> getAnnotations() throws UndefinedReflectionException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		return super.value;
	}
	
	@SuppressWarnings("unchecked")
	public <A extends java.lang.annotation.Annotation> Annotation<A> getAnnotation(Class<A> annot) throws UndefinedReflectionException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		for (Annotation<?> ann : super.value) {
			if (ann.evaluate().annotationType().equals(annot)) {
				return (Annotation<A>) ann;
			}
		}
		
		return null;
	}
	public <A extends java.lang.annotation.Annotation> A evaluateAnnotation(Class<A> annot) throws UndefinedReflectionException {
		return getAnnotation(annot) == null ? null : getAnnotation(annot).evaluate();
	}
	
	public boolean hasAnnotation(Class<? extends java.lang.annotation.Annotation> annot) throws UndefinedReflectionException {
		return target.evaluate().isAnnotationPresent(annot);
	}
	
	public int annotationCount() throws UndefinedReflectionException {
		if (!super.hasGenerated) {
			reflect();
		}
		
		return super.value.size();
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Annotated ann) ? this.getTarget().equals(ann.getTarget()) : false;
	}
	
	public @Override String toString() throws MoonaHandlingException {
		return (!super.hasGenerated) ? "Non-generated Flare" : "Annotated of " + target;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		AnnotatedElement ann = target.evaluate();
		
		strictContext.enable();
		
		for (java.lang.annotation.Annotation annot : ann.getDeclaredAnnotations()) {
			super.value.add(new Annotation<>(target, annot));
		}
		
		strictContext.disable();
		
		super.reflect();
	}
	
	public Annotated(Reference<? extends AnnotatedElement> target) throws NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("An Annotated cannot be extracted from a null Reference.");
		}
		this.target = target;
		
		super.mirrorInteraction(); super.valueExtraction();
	}
}
