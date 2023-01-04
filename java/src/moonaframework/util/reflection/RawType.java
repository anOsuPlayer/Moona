package moonaframework.util.reflection;

import moonaframework.util.annotation.Deadlined;
import moonaframework.util.exception.NullArgumentException;

public final class RawType extends Reference<java.lang.reflect.AnnotatedType> {

	public @Override boolean equals(Object o) {
		return (o instanceof RawType pt) ?
				this.value.equals(pt.value)
				: false;
	}
	
	public @Override String toString() {
		return (value == null) ? "Non-generated Reflection" : "RawType " + value.getType().getTypeName();
	}
	
	public @Override java.lang.reflect.AnnotatedType getTarget() {
		return super.value;
	}
	
	public @Deadlined void reflect() {
		
	}
	
	public @Override java.lang.reflect.AnnotatedType evaluate() {
		return super.value;
	}
	
	public RawType(java.lang.reflect.AnnotatedType type) throws NullArgumentException {
		if (type == null) {
			throw new NullArgumentException("A null java.lang.reflect.AnnotatedType cannot be accepted.");
		}
		super.value = type;
	}
}
