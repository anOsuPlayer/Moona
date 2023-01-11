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
	
	public List<Annotation<?>> getAnnotations() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		return super.value;
	}
	
	@SuppressWarnings("unchecked")
	public <A extends java.lang.annotation.Annotation> Annotation<A> getAnnotation(Class<A> annot) throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		for (Annotation<?> ann : super.value) {
			if (ann.evaluate().annotationType().equals(annot)) {
				return (Annotation<A>) ann;
			}
		}
		
		return null;
	}
	public <A extends java.lang.annotation.Annotation> A evaluateAnnotation(Class<A> annot) throws MoonaHandlingException {
		return getAnnotation(annot).evaluate();
	}
	
	public int annotationCount() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		return super.value.size();
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Annotated ann) ? this.getTarget().equals(ann.getTarget()) : false;
	}
	
	public @Override String toString() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		String annots = "";
		for (Annotation<?> ann : super.value) {
			annots += ann.toString().substring(ann.toString().lastIndexOf('.')+1) + ", ";
		}
		
		annots = (annots.length() != 0) ? annots.substring(0, annots.length()-2) : annots;
		
		return (!super.hasGenerated) ? "Non-generated Flare" : "Annotated of " + target + ", "
				+ ((super.value.size() == 0) ? "no annotations" : "annotations : " + annots);
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
	}
}
