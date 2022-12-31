package moonaframework.util.reflection.flare;

import java.lang.reflect.TypeVariable;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Generic;
import moonaframework.util.reflection.Type;

public final class GenericBounds extends Flare<Type> {

	private final Generic source;
	
	public @Override Generic getTarget() {
		return this.source;
	}
	
	public int boundsCount() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return super.value.size();
	}
	
	public @Override String toString() {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		return (source == null) ? "Non-generated Flare" : "GenericBounds of " + source + ", "
				+ ((super.value.size() == 0) ? "no bounds" : "bounds : " + super.value.toString());
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
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
