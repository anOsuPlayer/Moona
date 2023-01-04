package moonaframework.util.reflection;

import java.lang.reflect.AnnotatedElement;

import moonaframework.base.Moona;
import moonaframework.util.annotation.Deadlined;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.flare.Annotated;
import moonaframework.util.reflection.flare.Flare;

public abstract sealed class Reference<T extends AnnotatedElement> extends Reflection<T>
		permits Type, Constructor, Method, Field, Parameter, Generic, RawType, Annotation {
	
	public @Override abstract Object getTarget();
	
	public @Override abstract void reflect() throws UndefinedReflectionException;
	
	private Annotated annots;
	
	public final Annotated getAnnotated() {
		if (annots == null) {
			annots = new Annotated(this);
		}
		return annots;
	}
	
	public @Deadlined Flare<?> derive() {
		return Flare.EMPTY_FLARE;
	}
	
	protected Reference() {
		super();
		
		if (Moona.autoReflections.evaluate() || strictContext.evaluate()) {
			derive();
			getAnnotated();
		}
	}
}
