package moonaframework.util.reflection;

import java.lang.reflect.AnnotatedElement;

import moonaframework.util.annotation.Deadlined;
import moonaframework.util.reflection.beacon.Beacon;

public abstract sealed class Reference<T extends AnnotatedElement> extends Reflection<T> permits Type, Constructor, Method, Field, Parameter {
	
	public @Override final AnnotatedElement getTarget() {
		return super.value;
	}
	
	public @Override abstract void reflect();
	
	public @Deadlined Beacon<?> derive() {
		return Beacon.EMPTY_BEACON;
	}
	
	protected Reference() {
		super();
	}
}
