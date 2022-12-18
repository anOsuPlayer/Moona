package moonaframework.util.reflection;

import java.lang.annotation.Annotation;
import moonaframework.util.exceptions.NullArgumentException;

public class Annotated extends AbstractReflection<Boolean> {
	
	private final Class<? extends Annotation> annot;
	
	public Class<? extends Annotation> getAnnotation() {
		return this.annot;
	}
	
	private final Reference target;
	
	public @Override Reference getTarget() {
		return this.target;
	}
	
	public @Override void reflect() {
		super.value = target.evaluate().isAnnotationPresent(annot);
	}
	
	public Annotated(Reference ref, Class<? extends Annotation> annot) throws NullArgumentException {
		if (ref == null || annot == null) {
			throw new NullArgumentException("The Reference and the Annotation cannot be null.");
		}
		this.target = ref; this.annot = annot;
	}
	public Annotated(Class<?> target, Class<? extends Annotation> annot) throws NullArgumentException {
		this(new Reference.Type(target), annot);
	}
}
