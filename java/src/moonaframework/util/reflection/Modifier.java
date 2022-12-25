package moonaframework.util.reflection;

import java.lang.reflect.Executable;
import java.lang.reflect.Member;

import moonaframework.util.exceptions.NullArgumentException;

public final class Modifier extends Reflection<Integer> {

	private final Reference<? extends Member> target;
	
	public @Override final Reference<? extends Member> getTarget() {
		return this.target;
	}
	
	public @Override final void reflect() {
		super.value = target.evaluate().getModifiers();
	}
	
	protected @Override final Integer evaluate() {
		return super.evaluate();
	}
	
	public boolean isPublic() {
		return java.lang.reflect.Modifier.isPublic(super.value);
	}
	public boolean isProtected() {
		return java.lang.reflect.Modifier.isPublic(super.value);
	}
	public boolean isPackage() {
		return !isPublic() && !isProtected() && !isPrivate();
	}
	public boolean isPrivate() {
		return java.lang.reflect.Modifier.isPublic(super.value);
	}
	
	public boolean isStatic() {
		return java.lang.reflect.Modifier.isStatic(super.value);
	}
	public boolean isFinal() {
		return java.lang.reflect.Modifier.isFinal(super.value);
	}
	public boolean isConstant() {
		return isStatic() && isFinal();
	}
	public boolean isAbstract() {
		return java.lang.reflect.Modifier.isAbstract(super.value);
	}
	
	public boolean isTransient() {
		return java.lang.reflect.Modifier.isTransient(super.value);
	}
	public boolean isVolatile() {
		return java.lang.reflect.Modifier.isVolatile(super.value);
	}
	public boolean isSynchronized() {
		return java.lang.reflect.Modifier.isSynchronized(super.value);
	}
	public boolean isNative() {
		return java.lang.reflect.Modifier.isNative(super.value);
	}
	public boolean isStrict() {
		return java.lang.reflect.Modifier.isStrict(super.value);
	}
	
	public Modifier(Reference<? extends Member> ref) throws NullArgumentException {
		if (ref == null) {
			throw new NullArgumentException("Cannot build a Modifier Reflection over a null Reference.");
		}
		this.target = ref;
	}
	
	protected Modifier(Reference<? extends Member> source, int modifiers) throws IllegalArgumentException {
		this(source);
		if (modifiers < 0) {
			throw new NullArgumentException("The value which states the modifiers cannot be null.");
		}
		super.value = modifiers;
	}
}
