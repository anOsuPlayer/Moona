package moonaframework.util.reflection;

import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.reflection.Modifier.Modifiers;

public class Modifier extends AbstractReflection<Modifiers> {

	public final class Modifiers {
		
		private final int mod;
		
		public boolean isPublic() {
			return java.lang.reflect.Modifier.isPublic(mod);
		}
		public boolean isProtected() {
			return java.lang.reflect.Modifier.isProtected(mod);
		}
		public boolean isDefault() {
			return !isPublic() && !isProtected() && !isPrivate();
		}
		public boolean isPrivate() {
			return java.lang.reflect.Modifier.isProtected(mod);
		}
		
		public boolean isStatic() {
			return java.lang.reflect.Modifier.isStatic(mod);
		}
		public boolean isFinal() {
			return java.lang.reflect.Modifier.isFinal(mod);
		}
		public boolean isAbstract() {
			return java.lang.reflect.Modifier.isAbstract(mod);
		}
		
		public boolean isSynchronized() {
			return java.lang.reflect.Modifier.isSynchronized(mod);
		}
		public boolean isTransient() {
			return java.lang.reflect.Modifier.isTransient(mod);
		}
		public boolean isVolatile() {
			return java.lang.reflect.Modifier.isVolatile(mod);
		}
		
		public boolean isNative() {
			return java.lang.reflect.Modifier.isNative(mod);
		}
		public boolean isStrct() {
			return java.lang.reflect.Modifier.isStrict(mod);
		}
		
		protected Modifiers(int mod) {
			this.mod = mod;
		}
	}
	
	private final Reference target;
	
	public @Override Reference getTarget() {
		return this.target;
	}
	
	public @Override void reflect() {
		super.value = new Modifiers(((java.lang.reflect.Member) target.evaluate()).getModifiers());
	}
	
	public Modifier(Reference ref) throws IllegalArgumentException, NullArgumentException {
		if (ref == null) {
			throw new NullArgumentException("The given Reference cannot be null.");
		}
		if (!(ref instanceof Modifiable)) {
			throw new IllegalArgumentException("The given Reference does not allow any modifier.");
		}
		this.target = ref;
	}
}
