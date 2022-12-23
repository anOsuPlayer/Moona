package moonaframework.util.reflection;

import java.lang.reflect.AnnotatedElement;

public abstract sealed class Reference<T extends AnnotatedElement> extends Reflection<T> permits Type, Method, Constructor, Field {
	
	protected @Override final AnnotatedElement getTarget() {
		return super.value;
	}
	
	public @Override abstract void reflect();
	
	public @Override T evaluate() {
		return super.evaluate();
	}
	
	Reference() {
		super();
	}
}
