package moonaframework.util.reflection;

import java.lang.reflect.AnnotatedElement;

import moonaframework.base.Mirror;
import moonaframework.util.Namespace;
import moonaframework.util.exceptions.NullArgumentException;

public sealed abstract class Reference extends AbstractReflection<AnnotatedElement> implements Namespace
		permits Reference.Type, Reference.Method, Reference.Field {
	
	public static final class Type extends Reference {
		
		private final Class<?> clazz;
		
		public @Override java.lang.reflect.Method getTarget() {
			return (java.lang.reflect.Method) super.value;
		}
		
		public boolean equals(Reference.Type ref) {
			return clazz.equals(ref.clazz);
		}
		
		public @Override final void reflect() {
			super.value = clazz;
		}
		
		public Type(Class<?> clazz) throws NullArgumentException {
			if (clazz == null) {
				throw new NullArgumentException("The type cannot be null.");
			}
			this.clazz = clazz;
		}
	}
	
	public static final class Method extends Reference {
		
		private final Class<?> clazz;
		
		private final String name;
		
		private final Class<?>[] args;
		
		public @Override java.lang.reflect.Method getTarget() {
			return (java.lang.reflect.Method) super.value;
		}
		
		public boolean equals(Reference.Method ref) {
			return clazz.equals(ref.clazz) && name.equals(ref.name) && args.equals(ref.args);
		}
		
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
	
	public static final class Field extends Reference {
		
		private final Class<?> clazz;
		
		private final String name;
		
		public @Override java.lang.reflect.Field getTarget() {
			return (java.lang.reflect.Field) super.value;
		}
		
		public boolean equals(Reference.Method ref) {
			return clazz.equals(ref.clazz) && name.equals(ref.name);
		}
		
		public @Override final void reflect() throws UnresolvedReflectionException {
			for (java.lang.reflect.Field m : clazz.getDeclaredFields()) {
				if (m.getName().equals(name)) {
					super.value = m;
					return;
				}
			}
			throw new UnresolvedReflectionException("There is no field " + name + " in class " + clazz.getName() + ".");
		}
		
		public Field(Class<?> clazz, String name) throws NullArgumentException {
			if (clazz == null || name == null) {
				throw new NullArgumentException("The declaring class and the method's name cannot be null.");
			}
			this.clazz = clazz; this.name = name;
		}
	}
	
	public @Override abstract AnnotatedElement getTarget();
	
	public @Override abstract void reflect();
	
	private static final Class<?>[] namespace = new Class<?>[] { Reference.Type.class, Reference.Method.class,
		Reference.Field.class };
	
	public @Override final Class<?>[] getClasses() {
		return namespace;
	}
	
	private Reference() {
		
	}
}
