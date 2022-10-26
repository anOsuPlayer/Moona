package moonaFramework.reflection;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Annotated extends Reflection<Boolean> {
	
	private final Object target;
	private final Class<?> host;
	
	private final ElementType element;
	
	private final Class<? extends Annotation> annotation;
	
	private final Class<?>[] args;
	
	private final String name;
	
	public void reflect() {
		super.value = switch (element) {
			case ANNOTATION_TYPE:
				yield (host.isAnnotation()) ? host.getAnnotation(annotation) != null : false;
			case CONSTRUCTOR:
				for (Constructor<?> con : host.getConstructors()) {
					if (args != null && con.getParameterTypes().equals(args) &&
							con.getAnnotation(annotation) != null) {
						
						yield true;
					}
					else if (con.getAnnotation(annotation) != null) {
						yield true;
					}
				}
				yield false;
			case FIELD:
				for (Field f : host.getFields()) {
					try {
						if (target != null && name != null && f.getName().equals(name)
								&& f.getAnnotation(annotation) != null && f.get(host).equals(target)) {
							yield true;
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				yield false;
			case LOCAL_VARIABLE:
				yield false;
			case METHOD:
				yield false;
			case MODULE:
				yield false;
			case PACKAGE:
				yield false;
			case PARAMETER:
				yield false;
			case RECORD_COMPONENT:
				yield false;
			case TYPE:
				yield host.getAnnotation(annotation) != null;
			case TYPE_PARAMETER:
				yield false;
			case TYPE_USE:
				yield false;
			default:
				yield false;
		};
	}
	
	public Annotated(Object target, Class<?> host, Class<? extends Annotation> annotation, ElementType element,
			String name, Class<?>...args) {
		this.target = target; this.host = host;
		this.annotation = annotation; this.element = element;
		this.name = name; this.args = args;
	}
	
	public Annotated(Object target, Class<? extends Annotation> annotation, ElementType element) {
		this.target = target; this.host = (target instanceof Class<?> c) ? c : target.getClass();
		this.annotation = annotation; this.element = element;
		this.name = null; this.args = null;
	}
	
	public Annotated(Class<? extends Annotation> target, Class<? extends Annotation> annotation) {
		this(target, target, annotation, ElementType.ANNOTATION_TYPE, null, new Class<?>[0]);
	}
}
