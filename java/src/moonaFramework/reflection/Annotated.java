package moonaFramework.reflection;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Annotated extends Reflection<Boolean> {

	private final Object target;
	
	private final Class<? extends Annotation> annotation;
	
	private final ElementType location;
	
	public final void reflect() {
		super.value = switch (location) {
			case ANNOTATION_TYPE:
				if (target instanceof Class<?> c) {
					yield (c.isAnnotation()) ? c.getAnnotation(annotation) != null
							? true : false : false;
				}
				yield false;
			case CONSTRUCTOR:
				for (Constructor<?> con : (target instanceof Class<?> c) ?
						c.getDeclaredConstructors() : target.getClass().getDeclaredConstructors()) {
					
					if (con.getAnnotation(annotation) != null) {
						yield true;
					}
				}
				yield false;
			case FIELD:
				for (Field f : (target instanceof Class<?> c) ? c.getDeclaredFields()
						: target.getClass().getDeclaredFields()) {
					
					if (f.getAnnotation(annotation) != null) {
						yield true;
					}
				}
				yield false;
			case LOCAL_VARIABLE:
				yield false;
			case METHOD:
				yield false;
			case PARAMETER:
				yield false;
			case RECORD_COMPONENT:
				yield false;
			case TYPE:
				yield false;
			case TYPE_USE:
				yield false;
			default:
				yield false;
		};
	}
	
	public Annotated(Object target, Class<? extends Annotation> annotation, ElementType location) throws NullPointerException {
		super();
		if (target == null || annotation == null || location == null) {
			throw new NullPointerException("Null parameters are not allowed for this Constructor.");
		}
		this.target = target;
		this.annotation = annotation;
		this.location = location;
	}
}
