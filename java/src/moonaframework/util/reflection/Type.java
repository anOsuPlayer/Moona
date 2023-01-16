package moonaframework.util.reflection;

import moonaframework.util.annotation.Deadlined;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.reflection.flare.TypeContent;

public final class Type extends Reference<Class<?>> {
	
	@SuppressWarnings("unchecked")
	public <T> T[] getArrayType(int length) {
		return (T[]) java.lang.reflect.Array.newInstance(super.value, length);
	}
	public Class<?> getComponentType() {
		return super.value.getComponentType();
	}
	public Class<?> getBasicComponentType() {
		Class<?> clazz = super.value;
		while (clazz.isArray()) {
			clazz = clazz.componentType();
		}
		return clazz;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Type t) ?
				super.value.equals(t.value)
				: false;
	}
	
	public @Override String toString() {
		return (value == null) ? "Non-generated Reflection" : "Type " + value.getName();
	}
	
	public @Override Class<?> getTarget() {
		return super.value;
	}
	
	public @Deadlined void reflect() {
		
	}
	
	public @Override Class<?> evaluate() {
		return super.value;
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
		super.value = clazz;
		
		super.mirrorInteraction();
	}
}