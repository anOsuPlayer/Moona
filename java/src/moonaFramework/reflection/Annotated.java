package moonaFramework.reflection;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;

public abstract class Annotated extends Reflection<Boolean> {
	
	public static final class Type extends Annotated {
		
		private final Class<?> target;
		
		private final Class<? extends Annotation> annotation;
		
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
		
		public Type(Class<?> target, Class<? extends Annotation> annotation) throws NullPointerException {
			if (target == null || annotation == null) {
				throw new NullPointerException("Null parameters are not allowed.");
			}
			this.target = target;
			this.annotation = annotation;
		}
	}
	
	public static final class Constructor extends Annotated {
		
		private final Class<?> target;
		
		private final Class<? extends Annotation> annotation;
		
		private final Class<?>[] args;
		
		@Override
		public void reflect() {
			for (java.lang.reflect.Constructor<?> con : target.getDeclaredConstructors()) {
				if (args != null) {
					super.value = (con.getParameterTypes().equals(args) && con.getAnnotation(annotation) != null);
				}
				else {
					super.value = con.getAnnotation(annotation) != null;
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
				throws NullPointerException {
			if (target == null || annotation == null) {
				throw new NullPointerException("Null parameters are not allowed.");
			}
			this.target = (target instanceof Class<?> c) ? c : target.getClass();
			this.annotation = annotation;
			this.args = args;
		}
		public Constructor(Object target, Class<? extends Annotation> annotation) throws NullPointerException {
			this(target, annotation, null);
		}
	}
	
	public static final class Field extends Annotated {
		
		private final Class<?> target;
		
		private final Class<? extends Annotation> annotation;
		
		private final String fieldName;
		
		@Override
		public void reflect() {
			for (java.lang.reflect.Field f : target.getDeclaredFields()) {
				if (fieldName != null) {
					super.value = (f.getName().equals(fieldName) && f.getAnnotation(annotation) != null);
				}
				else {
					super.value = f.getAnnotation(annotation) != null;
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
				throws NullPointerException {
			if (target == null || annotation == null) {
				throw new NullPointerException("Null parameters are not allowed.");
			}
			this.target = (target instanceof Class<?> c) ? c : target.getClass();
			this.annotation = annotation;
			this.fieldName = fieldName;
		}
		public Field(Object target, Class<? extends Annotation> annotation) throws NullPointerException {
			this(target, annotation, null);
		}
	}
	
	public static final class Method extends Annotated {
		
		private final Class<?> target;
		
		private final Class<? extends Annotation> annotation;
		
		private final String methodName;
		
		private final Class<?>[] args;
		
		@Override
		public void reflect() {
			for (java.lang.reflect.Method m : target.getDeclaredMethods()) {
				if (args != null && methodName != null) {
					super.value = m.getName().equals(methodName) && m.getParameterTypes().equals(args)
							&& m.getAnnotation(annotation) != null;
				}
				else if (args != null) {
					super.value = m.getParameterTypes().equals(args) && m.getAnnotation(annotation) != null;
				}
				else if (methodName != null) {
					super.value = m.getName().equals(methodName) && m.getAnnotation(annotation) != null;
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
				Class<?>[] args) throws NullPointerException {
			if (target == null || annotation == null) {
				throw new NullPointerException("Null parameters are not allowed.");
			}
			this.target = target;
			this.annotation = annotation;
			this.methodName = methodName;
			this.args = args;
		}
		public Method(Class<?> target, Class<? extends Annotation> annotation, String methodName)
				throws NullPointerException {
			this(target, annotation, methodName, null);
		}
		public Method(Class<?> target, Class<? extends Annotation> annotation, Class<?>[] args)
				throws NullPointerException {
			this(target, annotation, null, args);
		}
		public Method(Class<?> target, Class<? extends Annotation> annotation) throws NullPointerException {
			this(target, annotation, null, null);
		}
	}
	
	public abstract void reflect();
	
	public abstract ElementType getType();
	
	private Annotated() {
		
	}
}
