package moonaframework.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.List;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UnresolvedReflectionException;

public final class Annotated extends Reflection<List<Annotation>> {

	private final Reference<? extends AnnotatedElement> target;
	
	public @Override Reference<? extends AnnotatedElement> getTarget() {
		return this.target;
	}
	
	public @Override void reflect() {
		try {
			super.value = Arrays.asList(target.evaluate().getAnnotations());
		}
		catch (UnresolvedReflectionException ure) {
			ure.printStackTrace();
		}
	}
	
	public @Override List<Annotation> evaluate() {
		if (super.value == null) {
			reflect();
		}

		List<Annotation> annots = List.of();
		
		try {
			annots = super.evaluate();
		}
		catch (UnresolvedReflectionException ure) {
			ure.printStackTrace();
		}
		
		return annots;
	}
	
	public boolean isAnnotatedWith(Class<? extends Annotation> annot) {
		if (super.value == null) {
			reflect();
		}
		for (Annotation a : super.value) {
			if (a.annotationType().equals(annot)) {
				return true;
			}
		}
		return false;
	}
	public int annotationCount() {
		if (super.value == null) {
			reflect();
		}
		return super.value.size();
	}
	
	public Annotated(Reference<? extends AnnotatedElement> ref) throws NullArgumentException {
		if (ref == null) {
			throw new NullArgumentException("Cannot build an Annotated Reflection over a null Reference.");
		}
		this.target = ref;
	}
	public Annotated(Class<?> clazz) throws NullArgumentException {
		this(new Type(clazz));
	}
}
