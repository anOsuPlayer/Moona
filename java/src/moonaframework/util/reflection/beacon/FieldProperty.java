package moonaframework.util.reflection.beacon;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UnresolvedReflectionException;
import moonaframework.util.reflection.Field;
import moonaframework.util.reflection.Modifier;
import moonaframework.util.reflection.Reflection;

public class FieldProperty extends Beacon<Reflection<?>> {

	private final Field source;
	
	public @Override Field getTarget() {
		return this.source;
	}
	
	public Modifier getModifiers() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UnresolvedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with unresolved Reflections.", ure);
			}
		}
		return (Modifier) super.value.get(0);
	}
	
	public @Override void reflect() throws UnresolvedReflectionException {
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
