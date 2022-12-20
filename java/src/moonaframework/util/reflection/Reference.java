package moonaframework.util.reflection;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Executable;
import java.util.Arrays;

import moonaframework.util.Namespace;
import moonaframework.util.exceptions.NullArgumentException;

public sealed abstract class Reference extends AbstractReflection<AnnotatedElement> implements Namespace permits Reference.Type, Reference.Constructor, Reference.Method, Reference.Field, Reference.Parameter {
	
	public static final class Type extends Reference {
		
		private final Class<?> clazz;
		
		public @Override Class<?> getTarget() {
			return (Class<?>) super.value;
		}
		
		public boolean equals(Reference ref) throws NullArgumentException {
			if (ref == null) {
				throw new NullArgumentException("The Reference to compare cannot be null.");
			}
			return clazz.equals(((Reference.Type) ref).clazz);
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
	
	public static final class Constructor extends Reference implements Modifiable, Parameterized {
		
		private final Class<?> clazz;
		
		private final Class<?>[] args;
		
		public @Override java.lang.reflect.Constructor<?> getTarget() {
			return (java.lang.reflect.Constructor<?>) super.value;
		}
		
		public @Override boolean equals(Reference ref) throws NullArgumentException {
			if (ref == null) {
				throw new NullArgumentException("The Reference to compare cannot be null.");
			}
			return clazz.equals(((Reference.Constructor) ref).clazz) && args.equals(((Reference.Constructor) ref).args);
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
			throw new UnresolvedReflectionException("There is no constructor in class " + clazz.getName()
					+ " accepting those parameters.");
		}
		
		public Constructor(Class<?> clazz, Class<?>...args) throws NullArgumentException {
			if (clazz == null) {
				throw new NullArgumentException("The declaring class cannot be null.");
			}
			this.clazz = clazz; this.args = (args == null) ? Mirror.NO_ARGS : args;
		}
		public Constructor(Class<?> clazz) throws NullArgumentException {
			this(clazz, Mirror.NO_ARGS);
		}
	}
	
	public static final class Method extends Reference implements Modifiable, Parameterized {
		
		private final Class<?> clazz;
		
		private final String name;
		
		private final Class<?>[] args;
		
		public @Override java.lang.reflect.Method getTarget() {
			return (java.lang.reflect.Method) super.value;
		}
		
		public @Override boolean equals(Reference ref) throws NullArgumentException {
			if (ref == null) {
				throw new NullArgumentException("The Reference to compare cannot be null.");
			}
			return clazz.equals(((Reference.Method) ref).clazz) && name.equals(((Reference.Method) ref).name) 
					&& args.equals(((Reference.Method) ref).args);
		}
		
		public @Override final void reflect() throws UnresolvedReflectionException {
			for (java.lang.reflect.Method m : clazz.getDeclaredMethods()) {
				if (m.getName().equals(name) && (args.equals(Mirror.NO_ARGS)) ? true : Arrays.equals(args, m.getParameterTypes())) {
					super.value = m;
					return;
				}
			}
			throw new UnresolvedReflectionException("There is no method " + name + " in class " + clazz.getName() + ".");
		}
		
		public Method(Class<?> clazz, String name, Class<?>...args) throws IllegalArgumentException, NullArgumentException {
			if (clazz == null || name == null) {
				throw new NullArgumentException("The declaring class and the method's name cannot be null.");
			}
			if (name.equals("")) {
				throw new IllegalArgumentException("Empty strings cannot be accepted to generate a Method Reference.");
			}
			this.clazz = clazz; this.name = name; this.args = (args == null) ? Mirror.NO_ARGS : args;
		}
		public Method(Class<?> clazz, String name) throws NullArgumentException {
			this(clazz, name, Mirror.NO_ARGS);
		}
	}
	
	public static final class Field extends Reference implements Modifiable {
		
		private final Class<?> clazz;
		
		private final String name;
		
		public @Override java.lang.reflect.Field getTarget() {
			return (java.lang.reflect.Field) super.value;
		}
		
		public @Override boolean equals(Reference ref) throws NullArgumentException {
			if (ref == null) {
				throw new NullArgumentException("The Reference to compare cannot be null.");
			}
			return clazz.equals(((Reference.Method) ref).clazz) && name.equals(((Reference.Method) ref).name);
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
		
		public Field(Class<?> clazz, String name) throws IllegalArgumentException, NullArgumentException {
			if (clazz == null || name == null) {
				throw new NullArgumentException("The declaring class and the method's name cannot be null.");
			}
			if (name.equals("")) {
				throw new IllegalArgumentException("Empty strings cannot be accepted to generate a Field Reference.");
			}
			this.clazz = clazz; this.name = name;
		}
	}
	
	public static final class Parameter extends Reference {
		
		private final Parameterized ref;
		
		private final int argc;
		
		public @Override java.lang.reflect.Parameter getTarget() {
			return (java.lang.reflect.Parameter) super.value;
		}
		
		public @Override boolean equals(Reference otherRef) throws NullArgumentException {
			if (ref == null) {
				throw new NullArgumentException("The Reference to compare cannot be null.");
			}
			return ref.equals(((Reference.Parameter) otherRef).ref) && argc == ((Reference.Parameter) otherRef).argc;
		}
		
		public @Override final void reflect() throws UnresolvedReflectionException {
			java.lang.reflect.Parameter[] params = ((Executable) ref.evaluate()).getParameters();
			if (argc >= params.length) {
				throw new UnresolvedReflectionException("Parameter number " + argc + " could not be found in this Parameterized Reference.");
			}
			super.value = params[argc];
			return;
		}
		
		public Parameter(Parameterized ref, int argc) throws IllegalArgumentException, NullArgumentException {
			if (ref == null) {
				throw new NullArgumentException("The reference and the parameter's name cannot be null.");
			}
			if (argc < 0) {
				throw new IllegalArgumentException("Negative parameter count cannot be accepted to generate a"
						+ " Parameter Reference.");
			}
			this.ref = ref; this.argc = argc;
		}
	}
	
	public @Override abstract AnnotatedElement getTarget();
	
	public @Override abstract void reflect();
	
	public abstract boolean equals(Reference ref);
	
	private static final Class<?>[] namespace = new Class<?>[] { Reference.Type.class, Reference.Constructor.class,
		Reference.Method.class, Reference.Field.class, Reference.Parameter.class };
	
	public @Override final Class<?>[] getClasses() {
		return namespace;
	}
	
	private Reference() {
		
	}
}
