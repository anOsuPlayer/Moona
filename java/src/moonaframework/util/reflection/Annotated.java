package moonaframework.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;

import moonaframework.util.annotations.Deadlined;
import moonaframework.util.exceptions.NullArgumentException;

public abstract class Annotated extends Reflection<Boolean> {
	
	public static final class Type extends Annotated {
		
		private final Class<?> target;
		public Class<?> getTarget() {
			return target;
		}
		
		private final Class<? extends Annotation> annotation;
		public Class<? extends Annotation> getAnnotation() {
			return annotation;
		}
		
		@Deadlined
		public String getName() {
			return null;
		}
		
		@Deadlined
		public Class<?>[] getArgs() {
			return null;
		}
		
		@Override
		public void reflect() {
			super.value = target.getAnnotation(annotation) != null;
		}
		
		@Override
		public ElementType getType() {
			if (target.isAnnotation()) { return ElementType.ANNOTATION_TYPE; }
			if (target.isAnonymousClass()) { return ElementType.TYPE_USE; }
			return ElementType.TYPE;
		}
		
		public Type(Class<?> target, Class<? extends Annotation> annotation) throws NullArgumentException {
			if (target == null || annotation == null) {
				throw new NullArgumentException("Null parameters are not allowed.");
			}
			this.target = target;
			this.annotation = annotation;
		}
	}
	
	public static final class Constructor extends Annotated {
		
		private final Class<?> target;
		public Class<?> getTarget() {
			return target;
		}
		
		private final Class<? extends Annotation> annotation;
		public Class<? extends Annotation> getAnnotation() {
			return annotation;
		}
		
		@Deadlined
		public String getName() {
			return null;
		}
		
		private Class<?>[] args;
		public Class<?>[] getArgs() {
			return args;
		}
		
		@Override
		public void reflect() {
			for (java.lang.reflect.Constructor<?> con : target.getDeclaredConstructors()) {
				if (args != null) {
					Class<?>[] params = con.getParameterTypes();
					params = (params.length == 0) ? Annotated.NO_ARGS : params;
					super.value = (params.equals(args) && con.getAnnotation(annotation) != null);
				}
				else {
					super.value = con.getAnnotation(annotation) != null;
					this.args = NO_ARGS;
				}
				if (super.value) { return; }
			}
			super.value = false;
		}
		
		@Override
		public ElementType getType() {
			return ElementType.CONSTRUCTOR;
		}
		
		public Constructor(Object target, Class<? extends Annotation> annotation, Class<?>[] args) 
				throws NullArgumentException {
			if (target == null || annotation == null) {
				throw new NullArgumentException("Null parameters are not allowed.");
			}
			this.target = (target instanceof Class<?> c) ? c : target.getClass();
			this.annotation = annotation;
			this.args = args;
		}
		public Constructor(Object target, Class<? extends Annotation> annotation) throws NullArgumentException {
			this(target, annotation, null);
		}
	}
	
	public static final class Field extends Annotated {
		
		private final Class<?> target;
		public Class<?> getTarget() {
			return target;
		}
		
		private final Class<? extends Annotation> annotation;
		public Class<? extends Annotation> getAnnotation() {
			return annotation;
		}
		
		private String fieldName;
		public String getName() {
			return fieldName;
		}
		
		@Deadlined
		public Class<?>[] getArgs() {
			return null;
		}
		
		@Override
		public void reflect() {
			for (java.lang.reflect.Field f : target.getDeclaredFields()) {
				if (fieldName != null) {
					super.value = (f.getName().equals(fieldName) && f.getAnnotation(annotation) != null);
				}
				else {
					super.value = f.getAnnotation(annotation) != null;
					this.fieldName = f.getName();
				}
				if (super.value) { return; }
			}
			super.value = false;
		}
		
		@Override
		public ElementType getType() {
			return ElementType.FIELD;
		}
		
		public Field(Object target, Class<? extends Annotation> annotation, String fieldName)
				throws NullArgumentException {
			if (target == null || annotation == null) {
				throw new NullArgumentException("Null parameters are not allowed.");
			}
			this.target = (target instanceof Class<?> c) ? c : target.getClass();
			this.annotation = annotation;
			this.fieldName = fieldName;
		}
		public Field(Object target, Class<? extends Annotation> annotation) throws NullArgumentException {
			this(target, annotation, null);
		}
	}
	
	public static final class Method extends Annotated {
		
		private final Class<?> target;
		public Class<?> getTarget() {
			return target;
		}
		
		private final Class<? extends Annotation> annotation;
		public Class<? extends Annotation> getAnnotation() {
			return annotation;
		}
		
		private String methodName;
		public String getName() {
			return methodName;
		}
		
		private Class<?>[] args;
		public Class<?>[] getArgs() {
			return args;
		}
		
		@Override
		public void reflect() {
			for (java.lang.reflect.Method m : target.getDeclaredMethods()) {
				if (args != null && methodName != null) {
					Class<?>[] params = m.getParameterTypes();
					params = (params.length == 0) ? Annotated.NO_ARGS : params;
					super.value = m.getName().equals(methodName) && params.equals(args)
							&& m.getAnnotation(annotation) != null;
				}
				else if (args != null) {
					Class<?>[] params = m.getParameterTypes();
					params = (params.length == 0) ? Annotated.NO_ARGS : params;
					super.value = params.equals(args) && m.getAnnotation(annotation) != null;
					this.methodName = m.getName();
				}
				else if (methodName != null) {
					super.value = m.getName().equals(methodName) && m.getAnnotation(annotation) != null;
					this.args = NO_ARGS;
				}
				if (super.value) { return; }
			}
			super.value = false;
		}
		
		@Override
		public ElementType getType() {
			return ElementType.METHOD;
		}
		
		public Method(Class<?> target, Class<? extends Annotation> annotation, String methodName,
				Class<?>[] args) throws NullArgumentException {
			if (target == null || annotation == null) {
				throw new NullArgumentException("Null parameters are not allowed.");
			}
			this.target = target;
			this.annotation = annotation;
			this.methodName = methodName;
			this.args = args;
		}
		public Method(Class<?> target, Class<? extends Annotation> annotation, String methodName)
				throws NullArgumentException {
			this(target, annotation, methodName, null);
		}
		public Method(Class<?> target, Class<? extends Annotation> annotation, Class<?>[] args)
				throws NullArgumentException {
			this(target, annotation, null, args);
		}
		public Method(Class<?> target, Class<? extends Annotation> annotation) throws NullArgumentException {
			this(target, annotation, null, null);
		}
	}
	
	public static final Class<?>[] NO_ARGS = new Class<?>[0];
	
	public abstract void reflect();
	
	public abstract ElementType getType();
	
	public abstract Class<?> getTarget();
	public abstract Class<? extends Annotation> getAnnotation();
	public abstract String getName();
	public abstract Class<?>[] getArgs();
	
	private Annotated() {
		
	}
}
