package moonaframework.util.reflection;

import java.lang.reflect.AnnotatedElement;

import moonaframework.util.exceptions.NullArgumentException;

public sealed abstract class Reference<T extends AnnotatedElement> extends AbstractReflection<T> permits Reference.Type, Reference.Method, Reference.Constructor, Reference.Field {

	public static final class Type extends Reference<Class<?>> {
		
		private final Class<?> clazz;
		
		public @Override final Class<?> getTarget() {
			return super.value;
		}
		
		public @Override final void reflect() {
			super.value = this.clazz;
		}
		
		public Type(Class<?> clazz) throws NullArgumentException {
			if (clazz == null) {
				throw new NullArgumentException("Cannot generate a Type Reference over a null class.");
			}
			this.clazz = clazz;
		}
	}
	
	public static final class Constructor extends Reference<java.lang.reflect.Constructor<?>> {
		
		private final Class<?> clazz;
		
		private final Class<?>[] args;
		
		public @Override final java.lang.reflect.Constructor<?> getTarget() {
			return super.value;
		}
		
		public @Override final void reflect() throws UnresolvedReflectionException {
			for (java.lang.reflect.Constructor<?> m : clazz.getDeclaredConstructors()) {
				if ((args.equals(Mirror.NO_ARGS)) ? m.getParameterTypes().length == 0 : m.getParameterTypes().equals(args)) {
					super.value = m;
					return;
				}
			}
			throw new UnresolvedReflectionException("No Constructor References could be generated from the given arguments.");
		}
		
		public Constructor(Class<?> clazz, Class<?>...args) throws IllegalArgumentException, NullArgumentException {
			if (clazz == null) {
				throw new NullArgumentException("Cannot build a Constructor Reference with a null class.");
			}
			this.clazz = clazz; this.args = args;
		}
		public Constructor(Class<?> clazz) {
			this(clazz, Mirror.NO_ARGS);
		}
	}
	
	public static final class Method extends Reference<java.lang.reflect.Method> {
		
		private final Class<?> clazz;
		
		private final String name;
		
		private final Class<?>[] args;
		
		public @Override final java.lang.reflect.Method getTarget() {
			return super.value;
		}
		
		public @Override final void reflect() throws UnresolvedReflectionException {
			for (java.lang.reflect.Method m : clazz.getDeclaredMethods()) {
				if (m.getName().equals(name) && (args.equals(Mirror.NO_ARGS)) ? true : m.getParameterTypes().equals(args)) {
					super.value = m;
					return;
				}
			}
			throw new UnresolvedReflectionException("No Method References could be generated from the given arguments.");
		}
		
		public Method(Class<?> clazz, String name, Class<?>...args) throws IllegalArgumentException, NullArgumentException {
			if (clazz == null || name == null) {
				throw new NullArgumentException("Cannot build a Method Reference with a null class or a null"
						+ " method name.");
			}
			this.clazz = clazz; this.name = name; this.args = args;
		}
		public Method(Class<?> clazz, String name) {
			this(clazz, name, Mirror.NO_ARGS);
		}
	}
	
	public static final class Field extends Reference<java.lang.reflect.Field> {
		
		private final Class<?> clazz;
		
		private final String name;
		
		public @Override final java.lang.reflect.Field getTarget() {
			return (java.lang.reflect.Field) super.value;
		}
		
		public @Override final void reflect() throws UnresolvedReflectionException {
			for (java.lang.reflect.Field m : clazz.getDeclaredFields()) {
				if (m.getName().equals(name)) {
					super.value = m;
					return;
				}
			}
			throw new UnresolvedReflectionException("No Field References could be generated from the given arguments.");
		}
		
		public Field(Class<?> clazz, String name) throws IllegalArgumentException, NullArgumentException {
			if (clazz == null || name == null) {
				throw new NullArgumentException("Cannot build a Field Reference with a null class or a null"
						+ " field name.");
			}
			this.clazz = clazz; this.name = name;
		}
	}
	
	public @Override abstract AnnotatedElement getTarget();
	
	public @Override abstract void reflect();
	
	private Reference() {
		super();
	}
}
