package moonaframework.util.reflection;

import java.lang.reflect.AnnotatedElement;

import moonaframework.base.Mirror;
import moonaframework.util.Namespace;
import moonaframework.util.exceptions.NullArgumentException;

public sealed abstract class Reference extends AbstractReflection<AnnotatedElement> implements Namespace permits Reference.Method {
	
	public static final class Method extends Reference {
		
		public @Override java.lang.reflect.Method getTarget() {
			return (java.lang.reflect.Method) super.value;
		}
		
		public boolean equals(Reference.Method ref) {
			return clazz.equals(ref.clazz) && name.equals(ref.name) && args.equals(ref.args);
		}
		
		private final Class<?> clazz;
		
		private final String name;
		
		private final Class<?>[] args;
		
		public @Override final void reflect() throws UnresolvedReflectionException {
			for (java.lang.reflect.Method m : clazz.getDeclaredMethods()) {
				if (m.getName().equals(name) && (args.equals(Mirror.NO_ARGS)) ? true : args.equals(m.getParameterTypes())) {
					super.value = m;
					return;
				}
			}
			throw new UnresolvedReflectionException("There is no method " + name + " in class " + clazz.getName() + ".");
		}
		
		public Method(Class<?> clazz, String name, Class<?>[] args) throws NullArgumentException {
			if (clazz == null || name == null) {
				throw new NullArgumentException("The declaring class and the method's name cannot be null.");
			}
			this.clazz = clazz; this.name = name; this.args = (args == null) ? Mirror.NO_ARGS : args;
		}
		public Method(Class<?> clazz, String name) throws NullArgumentException {
			this(clazz, name, Mirror.NO_ARGS);
		}
	}
	
	public @Override abstract AnnotatedElement getTarget();
	
	public @Override abstract void reflect();
	
	private static final Class<?>[] namespace = new Class<?>[] { Reference.Method.class };
	
	public @Override final Class<?>[] getClasses() {
		return namespace;
	}
	
	private Reference() {
		
	}
}
