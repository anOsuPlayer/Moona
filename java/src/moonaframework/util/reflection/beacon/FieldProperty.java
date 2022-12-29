package moonaframework.util.reflection.beacon;

import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.reflection.Field;
import moonaframework.util.reflection.Modifier;
import moonaframework.util.reflection.Reflection;

public class FieldProperty extends Beacon<Reflection<?>> {

	private final Field source;
	
	public @Override Field getTarget() {
		return this.source;
	}
	
	public Modifier getModifiers() {
		if (!super.hasGenerated) {
			reflect();
		}
		return (Modifier) super.value.get(0);
	}
	
	public @Override void reflect() {
		java.lang.reflect.Field field = source.evaluate();
		
		super.value.add(new Modifier(source));
		
		super.reflect();
	}
	
	public FieldProperty(Field source) throws NullArgumentException {
		if (source == null) {
			throw new NullArgumentException("FieldProperties cannot be extracted from a null Field Reference.");
		}
		this.source = source;
	}
}
