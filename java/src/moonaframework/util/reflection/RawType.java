package moonaframework.util.reflection;

import java.lang.reflect.AnnotatedType;

import moonaframework.util.annotation.Deadlined;
import moonaframework.util.exception.NullArgumentException;

public final class RawType extends Reference<AnnotatedType> {
	
	public @Override boolean equals(Object o) {
		return (o instanceof RawType rt) ?
				this.value.equals(rt.value)
				: false;
	}
	
	public @Override String toString() {
		return (value == null) ? "Non-generated Reflection" : "RawType " + value.getType().getTypeName();
	}
	
	public @Override AnnotatedType getTarget() {
		return super.value;
	}
	
	public @Deadlined void reflect() {
		
	}
	
	public @Override AnnotatedType evaluate() {
		return super.value;
	}
	
	public RawType(AnnotatedType type) throws NullArgumentException {
		if (type == null) {
			throw new NullArgumentException("A null java.lang.reflect.AnnotatedType cannot be accepted.");
		}
		super.value = type;
		
		super.mirrorInteraction();
	}
}
