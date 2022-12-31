package moonaframework.util.reflection.flare;

import java.lang.reflect.AnnotatedElement;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Annotation;
import moonaframework.util.reflection.Reference;

public final class Annotated extends Flare<Annotation> {
	
	private final Reference<? extends AnnotatedElement> source;
	
	public @Override Reference<? extends AnnotatedElement> getTarget() {
		return this.source;
	}
	
	public List<Annotation> getAnnotations() throws MoonaHandlingException {
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
	public Annotation getAnnotation(int index) throws IndexOutOfBoundsException, MoonaHandlingException {
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
		if (index >= super.value.size()) {
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " Annotation"
					+ "References, index " + index + " is out of range.");
		}
		
		return super.value.get(index);
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
		for (Annotation ann : super.value) {
			annots += ann.toString().substring(ann.toString().lastIndexOf('.')+1) + ", ";
		}
		
		annots = (annots.length() != 0) ? annots.substring(0, annots.length()-2) : annots;
		
		return (source == null) ? "Non-generated Flare" : "Annotated of " + source + ", "
				+ ((super.value.size() == 0) ? "no annotations" : "annotations : " + annots);
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		AnnotatedElement ann = source.evaluate();
		
		strictContext.enable();
		
		for (java.lang.annotation.Annotation annot : ann.getAnnotations()) {
			super.value.add(new Annotation(annot));
		}
		
		strictContext.disable();
		
		super.reflect();
	}
	
	public Annotated(Reference<? extends AnnotatedElement> source) throws NullArgumentException {
		if (source == null) {
			throw new NullArgumentException("An Annotated cannot be extracted from a null Reference.");
		}
		this.source = source;
	}
	public Annotated(Class<? extends java.lang.annotation.Annotation> clazz) {
		this(new Annotation(clazz));
	}
}
