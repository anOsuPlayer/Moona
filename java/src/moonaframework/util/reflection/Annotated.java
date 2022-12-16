package moonaframework.util.reflection;

import java.lang.annotation.Annotation;

import moonaframework.util.exceptions.NullArgumentException;

public sealed abstract class Annotated extends AbstractReflection<Boolean> permits Annotated.Method {

	public static final class Method extends Annotated {
		
		private final Class<? extends Annotation> annot;
		
		public @Override Class<? extends Annotation> getAnnotation() {
			return this.annot;
		}
		
		private final Reference.Method target;
		
		public @Override Reference.Method getTarget() {
			return this.target;
		}
		
		public @Override void reflect() {
			super.value = target.evaluate().isAnnotationPresent(annot);
		}
		
		public Method(Reference.Method target, Class<? extends Annotation> annot) throws NullArgumentException {
			if (target == null || annot == null) {
				throw new NullArgumentException("The target method and the Annotation cannot be null.");
			}
			this.target = target; this.annot = annot;
		}
	}
	
	public abstract Class<? extends Annotation> getAnnotation();
	
	public @Override abstract Reference getTarget();
	
	public @Override abstract void reflect();
	
	private Annotated() {
		
	}
}
