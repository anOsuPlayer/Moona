package moonaframework.util.reflection;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

import moonaframework.base.Mirror;
import moonaframework.util.Namespace;
import moonaframework.util.exceptions.NullArgumentException;

public sealed abstract class Reference extends AbstractReflection<AnnotatedElement> implements Namespace permits Reference.Type, Reference.Constructor, Reference.Method, Reference.Field {
	
	public static final class Type extends Reference {
		
		private final Class<?> clazz;
		
		public @Override java.lang.reflect.Method getTarget() {
			return (java.lang.reflect.Method) super.value;
		}
		
		public boolean equals(Reference.Type ref) throws NullArgumentException {
			if (ref == null) {
				throw new NullArgumentException("The Reference to compare cannot be null.");
			}
			return clazz.equals(ref.clazz);
		}
		
		public @Override final void reflect() {
			super.value = clazz;
		}
		
		public Type(Class<?> clazz) throws NullArgumentException {
			if (clazz == null) {
				throw new NullArgumentException("The Type cannot be null.");
			}
			this.clazz = clazz;
		}
	}
	
	public static final class Constructor extends Reference {
		
		private final Class<?> clazz;
		
		private final Class<?>[] args;
		
		public @Override java.lang.reflect.Constructor<?> getTarget() {
			return (java.lang.reflect.Constructor<?>) super.value;
		}
		
		public boolean equals(Reference.Constructor ref) throws NullArgumentException {
			if (ref == null) {
				throw new NullArgumentException("The Reference to compare cannot be null.");
			}
			return clazz.equals(ref.clazz) && args.equals(ref.args);
		}
		
		public @Override final void reflect() throws UnresolvedReflectionException {
			Class<?>[] params;
			for (java.lang.reflect.Constructor<?> m : clazz.getDeclaredConstructors()) {
				params = m.getParameterTypes();
				if ((args.equals(Mirror.NO_ARGS)) ? params.length == 0 : Arrays.equals(params, args)) {
					super.value = m;
					return;
				}
			}
			throw new UnresolvedReflectionException("There is no Constructor in Class " + clazz.getName()
					+ " accepting those parameters.");
		}
		
		public Constructor(Class<?> clazz, Class<?>...args) throws NullArgumentException {
			if (clazz == null) {
				throw new NullArgumentException("The declaring Class cannot be null.");
			}
			this.clazz = clazz; this.args = (args == null) ? Mirror.NO_ARGS : args;
		}
		public Constructor(Class<?> clazz) throws NullArgumentException {
			this(clazz, Mirror.NO_ARGS);
		}
	}
	
	public static final class Method extends Reference {
		
		private final Class<?> clazz;
		
		private final String name;
		
		private final Class<?>[] args;
		
		public @Override java.lang.reflect.Method getTarget() {
			return (java.lang.reflect.Method) super.value;
		}
		
		public boolean equals(Reference.Method ref) throws NullArgumentException {
			if (ref == null) {
				throw new NullArgumentException("The Reference to compare cannot be null.");
			}
			return clazz.equals(ref.clazz) && name.equals(ref.name) && args.equals(ref.args);
		}
		
		public @Override final void reflect() throws UnresolvedReflectionException {
			for (java.lang.reflect.Method m : clazz.getDeclaredMethods()) {
				if (m.getName().equals(name) && (args.equals(Mirror.NO_ARGS)) ? true : Arrays.equals(args, m.getParameterTypes())) {
					super.value = m;
					return;
				}
			}
			throw new UnresolvedReflectionException("There is no Method " + name + " in Class " + clazz.getName() + ".");
		}
		
		public Method(Class<?> clazz, String name, Class<?>...args) throws NullArgumentException {
			if (clazz == null || name == null) {
				throw new NullArgumentException("The declaring Class and the Method's name cannot be null.");
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
		
		public boolean equals(Reference.Method ref) throws NullArgumentException {
			if (ref == null) {
				throw new NullArgumentException("The Reference to compare cannot be null.");
			}
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
	
	private static final Class<?>[] namespace = new Class<?>[] { Reference.Type.class, Reference.Constructor.class,
		Reference.Method.class, Reference.Field.class };
	
	public @Override final Class<?>[] getClasses() {
		return namespace;
	}
	
	private Reference() {
		
	}
}
