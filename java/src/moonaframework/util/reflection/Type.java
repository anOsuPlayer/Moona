package moonaframework.util.reflection;

import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.reflection.cluster.TypeContent;

public final class Type extends Reference<Class<?>> {
	
	private final Class<?> clazz;
	
	public @Override String toString() {
		return clazz.getName();
	}
	
	public @Override void reflect() {
		super.value = this.clazz;
	}
	
	private TypeContent tc;
	
	public @Override TypeContent derive() {
		if (tc == null) {
			tc = new TypeContent(this);
		}
		return tc;
	}
	
	public Type(Class<?> clazz) throws NullArgumentException {
		if (clazz == null) {
			throw new NullArgumentException("Cannot generate a Type Reference over a null class.");
		}
		this.clazz = clazz;
	}
}