package moonaframework.util.reflection.beacon;

import java.lang.reflect.TypeVariable;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UnresolvedReflectionException;
import moonaframework.util.reflection.Generic;
import moonaframework.util.reflection.Type;

public final class GenericBounds extends Beacon<Type> {

	private final Generic source;
	
	public @Override Generic getTarget() {
		return this.source;
	}
	
	public int boundsCount() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UnresolvedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with unresolved Reflections.", ure);
			}
		}
		return super.value.size();
	}
	
	public @Override void reflect() throws UnresolvedReflectionException {
		TypeVariable<?> generic = source.evaluate();
		
		strictContext.enable();
		
		java.lang.reflect.Type[] bounds = generic.getBounds();
		for (int i = 0; i < bounds.length; i++) {
			super.value.add(new Type((Class<?>) bounds[i]));
		}
		
		strictContext.disable();
		
		super.reflect();
	}
	
	public GenericBounds(Generic source) throws NullArgumentException {
		if (source == null) {
			throw new NullArgumentException("GenericBounds cannot be extracted from a null Generic Reference.");
		}
		this.source = source;
	}
}
