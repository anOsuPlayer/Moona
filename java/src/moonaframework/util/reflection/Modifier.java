package moonaframework.util.reflection;

import java.lang.reflect.Member;

import moonaframework.util.exception.NullArgumentException;

public final class Modifier extends Reflection<Integer> {

	private final Reference<? extends Member> target;
	
	public @Override Reference<? extends Member> getTarget() {
		return this.target;
	}
	
	public @Override void reflect() {
		super.value = target.evaluate().getModifiers();
	}
	
	public @Override Integer evaluate() {
		if (super.value == null) {
			reflect();
		}
		return super.evaluate();
	}
	
	public boolean isPublic() {
		if (super.value == null) {
			reflect();
		}
		return (super.value & java.lang.reflect.Modifier.PUBLIC) != 0;
	}
	public boolean isProtected() {
		if (super.value == null) {
			reflect();
		}
		return (super.value & java.lang.reflect.Modifier.PROTECTED) != 0;
	}
	public boolean isPackage() {
		return !isPublic() && !isProtected() && !isPrivate();
	}
	public boolean isPrivate() {
		if (super.value == null) {
			reflect();
		}
		return (super.value & java.lang.reflect.Modifier.PRIVATE) != 0;
	}
	
	public boolean isStatic() {
		if (super.value == null) {
			reflect();
		}
		return (super.value & java.lang.reflect.Modifier.STATIC) != 0;
	}
	public boolean isFinal() {
		if (super.value == null) {
			reflect();
		}
		return (super.value & java.lang.reflect.Modifier.FINAL) != 0;
	}
	public boolean isConstant() {
		return isStatic() && isFinal();
	}
	public boolean isAbstract() {
		if (super.value == null) {
			reflect();
		}
		return (super.value & java.lang.reflect.Modifier.ABSTRACT) != 0;
	}
	
	public boolean isTransient() {
		if (super.value == null) {
			reflect();
		}
		return (super.value & java.lang.reflect.Modifier.TRANSIENT) != 0;
	}
	public boolean isVolatile() {
		if (super.value == null) {
			reflect();
		}
		return (super.value & java.lang.reflect.Modifier.VOLATILE) != 0;
	}
	public boolean isSynchronized() {
		if (super.value == null) {
			reflect();
		}
		return (super.value & java.lang.reflect.Modifier.SYNCHRONIZED) != 0;
	}
	public boolean isNative() {
		if (super.value == null) {
			reflect();
		}
		return (super.value & java.lang.reflect.Modifier.NATIVE) != 0;
	}
	public boolean isStrict() {
		if (super.value == null) {
			reflect();
		}
		return (super.value & java.lang.reflect.Modifier.STRICT) != 0;
	}
	
	public Modifier(Reference<? extends Member> ref) throws NullArgumentException {
		if (ref == null) {
			throw new NullArgumentException("Cannot build a Modifier Reflection over a null Reference.");
		}
		this.target = ref;
	}
	
	public Modifier(Reference<? extends Member> source, int modifiers) throws IllegalArgumentException {
		this(source);
		if (modifiers < 0) {
			throw new NullArgumentException("The value which states the modifiers cannot be null.");
		}
		super.value = modifiers;
	}
}
