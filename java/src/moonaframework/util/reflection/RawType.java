package moonaframework.util.reflection;

import moonaframework.util.exception.NullArgumentException;

public final class RawType extends Reference<java.lang.reflect.AnnotatedType> {

	private final java.lang.reflect.AnnotatedType target;

	public @Override boolean equals(Object o) {
		return (o instanceof RawType pt) ?
				this.target.equals(pt.target)
				: false;
	}
	
	public @Override String toString() {
		return (target == null) ? "Non-generated Reflection" : "RawType " + target.getType().getTypeName();
	}
	
	public @Override void reflect() {
		super.value = this.target;
	}
	
	public RawType(java.lang.reflect.AnnotatedType target) throws NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("A null java.lang.reflect.Type cannot be accepted.");
		}
		this.target = target;
	}
}
