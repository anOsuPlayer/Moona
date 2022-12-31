package moonaframework.util.reflection.flare;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Field;
import moonaframework.util.reflection.Modifier;
import moonaframework.util.reflection.Reflection;

public class FieldProperty extends Flare<Reflection<?>> {

	private final Field source;
	
	public @Override Field getTarget() {
		return this.source;
	}
	
	public Modifier getModifiers() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (Modifier) super.value.get(0);
	}
	
	public @Override String toString() {
		return (source == null) ? "Non-generated Flare" : "FieldProperty of " + source;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		java.lang.reflect.Field field = source.evaluate();
		
		strictContext.enable();
		
		super.value.add(new Modifier(source));
		
		strictContext.disable();
		
		super.reflect();
	}
	
	public FieldProperty(Field source) throws NullArgumentException {
		if (source == null) {
			throw new NullArgumentException("FieldProperties cannot be extracted from a null Field Reference.");
		}
		this.source = source;
	}
}
