package moonaframework.util.reflection;

import java.lang.reflect.AnnotatedElement;

import moonaframework.base.Moona;
import moonaframework.util.annotation.Deadlined;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.beacon.Beacon;

public abstract sealed class Reference<T extends AnnotatedElement> extends Reflection<T>
	permits Type, Constructor, Method, Field, Parameter, Generic {
	
	public @Override final T getTarget() {
		return super.value;
	}
	
	public @Override abstract void reflect() throws UndefinedReflectionException;
	
	public @Deadlined Beacon<?> derive() {
		return Beacon.EMPTY_BEACON;
	}
	
	protected Reference() {
		super();
		
		if (Moona.autoReflections.evaluate() || strictContext.evaluate()) {
			derive();
		}
	}
}
