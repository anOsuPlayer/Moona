package moonaframework.util.reflection;

import java.lang.reflect.Member;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;

public sealed class Modifier extends Reflection<Integer> permits ExistingModifier {

	private final Reference<? extends Member> target;
	
	public @Override Reference<? extends Member> getTarget() {
		return this.target;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof Modifier mod) ?
				this.target.equals(mod.target)
				: false;
	}
	
	public boolean isPublic() throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (super.value & java.lang.reflect.Modifier.PUBLIC) != 0;
	}
	public boolean isProtected() throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (super.value & java.lang.reflect.Modifier.PROTECTED) != 0;
	}
	public boolean isPackage() throws MoonaHandlingException {
		return !isPublic() && !isProtected() && !isPrivate();
	}
	public boolean isPrivate() throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (super.value & java.lang.reflect.Modifier.PRIVATE) != 0;
	}
	
	public boolean isStatic() throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (super.value & java.lang.reflect.Modifier.STATIC) != 0;
	}
	public boolean isFinal() throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (super.value & java.lang.reflect.Modifier.FINAL) != 0;
	}
	public boolean isConstant() throws MoonaHandlingException {
		return isStatic() && isFinal();
	}
	public boolean isAbstract() throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (super.value & java.lang.reflect.Modifier.ABSTRACT) != 0;
	}
	
	public boolean isTransient() throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (super.value & java.lang.reflect.Modifier.TRANSIENT) != 0;
	}
	public boolean isVolatile() throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (super.value & java.lang.reflect.Modifier.VOLATILE) != 0;
	}
	public boolean isSynchronized() throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (super.value & java.lang.reflect.Modifier.SYNCHRONIZED) != 0;
	}
	public boolean isNative() throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (super.value & java.lang.reflect.Modifier.NATIVE) != 0;
	}
	public boolean isStrict() throws MoonaHandlingException {
		if (super.value == null) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (super.value & java.lang.reflect.Modifier.STRICT) != 0;
	}
	
	public @Override String toString() {
		if (target == null) {
			return "Non-generated Reflection.";
		}
		
		String mod = "Modifier of " + target + " : ";
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
	
	public @Override void reflect() throws UndefinedReflectionException {
		super.value = target.evaluate().getModifiers();
	}
	
	public Modifier(Reference<? extends Member> ref) throws NullArgumentException {
		if (ref == null) {
			throw new NullArgumentException("Cannot build a Modifier Reflection over a null Reference.");
		}
		this.target = ref;
		
		super.mirrorInteraction();
	}
}
