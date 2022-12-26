package moonaframework.util.reflection;

import java.lang.reflect.AnnotatedElement;

import moonaframework.util.annotation.Deadlined;
import moonaframework.util.reflection.cluster.Cluster;

public abstract sealed class Reference<T extends AnnotatedElement> extends Reflection<T> permits Type, Constructor, Method, Field, Parameter {
	
	public @Override final AnnotatedElement getTarget() {
		return super.value;
	}
	
	public @Override abstract void reflect();
	
	public @Override T evaluate() {
		if (super.value == null) {
			reflect();
		}
		return super.evaluate();
	}
	
	public @Deadlined Cluster<?> derive() {
		return Cluster.EMPTY_CLUSTER;
	}
	
	protected Reference() {
		super();
	}
}
