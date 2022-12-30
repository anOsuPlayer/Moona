package moonaframework.util.reflection;

import java.lang.reflect.Member;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UnresolvedReflectionException;

public final class Modifier extends Reflection<Integer> {

	private final Reference<? extends Member> target;
	
	public @Override Reference<? extends Member> getTarget() {
		return this.target;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Modifier mod) ?
				this.target.equals(mod.target)
				: false;
	}
	
	public @Override String toString() {
		if (target == null) {
			return "Non-generated Reflection.";
		}
		
		String mod = "";
		mod += isPublic() ? "public" : "";
		mod += isProtected() ? "protected" : "";
		mod += isPackage() ? "package" : "";
		mod += isPrivate() ? "private" : "";
		
		mod += isSynchronized() ? " synchronized" : "";
		
		mod += isStatic() ? " static" : "";
		
		mod += isAbstract() ? " abstract" : "";
		mod += isFinal() ? " final" : "";
		
		mod += isTransient() ? " transient" : "";
		mod += isVolatile() ? " volatile" : "";
		
		mod += isStrict() ? " strictfp" : "";
		
		mod += isNative() ? " native" : "";
		
		return mod;
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
	
	public @Override void reflect() {
		try {
			super.value = target.evaluate().getModifiers();
		}
		catch (UnresolvedReflectionException ure) {
			ure.printStackTrace();
		}
	}
	
	public @Override Integer evaluate() {
		if (super.value == null) {
			reflect();
		}
		
		Integer mod = -1;
		
		try {
			mod = super.evaluate();
		}
		catch (UnresolvedReflectionException ure) {
			ure.printStackTrace();
		}
		
		return mod;
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
			throw new NullArgumentException("The value which states the modifiers cannot be less than zero.");
		}
		super.value = modifiers;
	}
}
