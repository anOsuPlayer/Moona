package moonaframework.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;

import moonaframework.util.annotations.Deadlined;
import moonaframework.util.exceptions.NullArgumentException;

public abstract class Annotated extends Reflection<Boolean> {
	
	public static final class Type extends Annotated {
		
		private final Class<?> target;
		@Override
		public Class<?> getTarget() {
			return target;
		}
		
		private final Class<? extends Annotation> annotation;
		@Override
		public Class<? extends Annotation> getAnnotation() {
			return annotation;
		}
		
		@Deadlined
		public String getName() {
			return "";
		}
		
		@Deadlined
		public Class<?>[] getArgs() {
			return NO_ARGS;
		}
		
		@Override
		public void reflect() {
			if (getType().equals(ElementType.TYPE_USE)) {
				super.value = new Field(getTarget().getEnclosingClass(), annotation).evaluate();
				return;
			}
			super.value = target.isAnnotationPresent(annotation);
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
		@Override
		public Class<?> getTarget() {
			return target;
		}
		
		private final Class<? extends Annotation> annotation;
		@Override
		public Class<? extends Annotation> getAnnotation() {
			return annotation;
		}
		
		@Deadlined
		public String getName() {
			return "";
		}
		
		private Class<?>[] args;
		@Override
		public Class<?>[] getArgs() {
			return args;
		}
		
		@Override
		public void reflect() {
			for (java.lang.reflect.Constructor<?> con : target.getDeclaredConstructors()) {
				if (args != null) {
					Class<?>[] params = con.getParameterTypes();
					params = (params.length == 0) ? Annotated.NO_ARGS : params;
					super.value = (params.equals(args) && con.isAnnotationPresent(annotation));
				}
				else {
					super.value = con.isAnnotationPresent(annotation);
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
		@Override
		public Class<?> getTarget() {
			return target;
		}
		
		private final Class<? extends Annotation> annotation;
		@Override
		public Class<? extends Annotation> getAnnotation() {
			return annotation;
		}
		
		private String fieldName;
		@Override
		public String getName() {
			return fieldName;
		}
		
		@Deadlined
		public Class<?>[] getArgs() {
			return NO_ARGS;
		}
		
		@Override
		public void reflect() {
			for (java.lang.reflect.Field f : target.getDeclaredFields()) {
				if (fieldName != null) {
					super.value = (f.getName().equals(fieldName) && f.isAnnotationPresent(annotation));
				}
				else {
					super.value = f.isAnnotationPresent(annotation);
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
		@Override
		public Class<?> getTarget() {
			return target;
		}
		
		private final Class<? extends Annotation> annotation;
		@Override
		public Class<? extends Annotation> getAnnotation() {
			return annotation;
		}
		
		private String methodName;
		@Override
		public String getName() {
			return methodName;
		}
		
		private Class<?>[] args;
		@Override
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
							&& m.isAnnotationPresent(annotation);
				}
				else if (args != null) {
					Class<?>[] params = m.getParameterTypes();
					params = (params.length == 0) ? Annotated.NO_ARGS : params;
					super.value = params.equals(args) && m.isAnnotationPresent(annotation);
					this.methodName = m.getName();
				}
				else if (methodName != null) {
					super.value = m.getName().equals(methodName) && m.isAnnotationPresent(annotation);
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