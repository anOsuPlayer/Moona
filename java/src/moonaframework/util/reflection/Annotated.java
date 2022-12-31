package moonaframework.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;

public final class Annotated extends Reflection<List<Annotation>> {

	private final Reference<? extends AnnotatedElement> target;
	
	public @Override Reference<? extends AnnotatedElement> getTarget() {
		return this.target;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		super.value = Arrays.asList(target.evaluate().getAnnotations());
	}
	
	public @Override List<Annotation> evaluate() throws UndefinedReflectionException {
		if (super.value == null) {
			reflect();
		}
		return super.evaluate();
	}
	
	public boolean isAnnotatedWith(Class<? extends Annotation> annot) throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		for (Annotation a : super.value) {
			if (a.annotationType().equals(annot)) {
				return true;
			}
		}
		return false;
	}
	public int annotationCount() throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
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
