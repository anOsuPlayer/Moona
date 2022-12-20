package moonaframework.util.reflection;

import java.lang.annotation.Annotation;
import moonaframework.util.exceptions.NullArgumentException;

public final class Annotated extends AbstractReflection<Boolean> {
	
	private final Class<? extends Annotation>[] annots;
	
	public Class<? extends Annotation>[] getAnnotations() {
		return this.annots;
	}
	
	private final Reference target;
	
	public @Override Reference getTarget() {
		return this.target;
	}
	
	public @Override void reflect() {
		super.value = true;
		for (Class<? extends Annotation> ann : annots) {
			super.value &= target.evaluate().isAnnotationPresent(ann);
		}
	}
	
	public @SafeVarargs Annotated(Reference ref, Class<? extends Annotation>...annots) throws NullArgumentException {
		if (ref == null || annots == null) {
			throw new NullArgumentException("The Reference and the Annotation cannot be null.");
		}
		this.target = ref; this.annots = annots;
	}
	public @SafeVarargs Annotated(Class<?> target, Class<? extends Annotation>...annots) throws NullArgumentException {
		this(new Reference.Type(target), annots);
	}
}
