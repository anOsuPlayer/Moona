package moonaframework.util.reflection;

import moonaframework.util.exception.NullArgumentException;

public final class PureType extends Reflection<java.lang.reflect.Type> {

	private final java.lang.reflect.Type target;
	
	public @Override java.lang.reflect.Type getTarget() {
		return this.target;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof PureType pt) ?
				this.target.equals(pt.target)
				: false;
	}
	
	public @Override String toString() {
		return (target == null) ? "Non-generated Reflection" : "PureType " + target.getTypeName();
	}
	
	public @Override void reflect() {
		super.value = this.target;
	}
	
	public PureType(java.lang.reflect.Type target) throws NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("A null java.lang.reflect.Type cannot be accepted.");
		}
		this.target = target;
	}
}
