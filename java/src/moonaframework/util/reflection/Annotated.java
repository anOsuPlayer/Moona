package moonaframework.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;

import moonaframework.util.annotations.Deadlined;
import moonaframework.util.exceptions.NullArgumentException;

public abstract class Annotated extends AbstractReflection<Boolean> {
	
	public static final Class<?>[] NO_ARGS = new Class<?>[0];
	
	public @Override abstract void reflect();
	
	public abstract ElementType getType();
	
	public @Override abstract Object getTarget();
	
	public abstract Class<? extends Annotation> getAnnotation();
}
