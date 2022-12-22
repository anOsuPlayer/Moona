package moonaframework.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import moonaframework.util.exceptions.NullArgumentException;

public class Annotated extends AbstractReflection<Annotation[]> {

	private final Reference<? extends AnnotatedElement> target;
	
	public @Override final Reference<? extends AnnotatedElement> getTarget() {
		return this.target;
	}
	
	public @Override final void reflect() {
		super.value = target.evaluate().getAnnotations();
	}
	
	public final boolean isAnnotatedWith(Class<? extends Annotation> annot) {
		for (Annotation a : super.value) {
			if (a.annotationType().equals(annot)) {
				return true;
			}
		}
		return false;
	}
	
	public Annotated(Reference<? extends AnnotatedElement> ref) throws NullArgumentException {
		if (ref == null) {
			throw new NullArgumentException("Cannot build an Annotated over a null Reference.");
		}
		this.target = ref;
	}
	public Annotated(Class<?> clazz) throws NullArgumentException {
		this(new Reference.Type(clazz));
	}
}
