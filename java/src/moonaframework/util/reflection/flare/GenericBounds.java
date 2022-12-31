package moonaframework.util.reflection.flare;

import java.lang.reflect.TypeVariable;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Generic;
import moonaframework.util.reflection.PureType;

public final class GenericBounds extends Flare<PureType> {

	private final Generic source;
	
	public @Override Generic getTarget() {
		return this.source;
	}
	
	public List<PureType> getBounds() throws MoonaHandlingException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}

		return super.value;
	}
	public PureType getBound(int index) throws IllegalArgumentException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		if (index < 0) {
			throw new IllegalArgumentException("Negative indexes are not allowed.");
		}
		if (index >= super.value.size()) {
			throw new IndexOutOfBoundsException("There are only " + super.value.size() + " PureType Reflections,"
					+ " index " + index + " is out of range.");
		}
		
		return super.value.get(index);
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
			super.value.add(new PureType(bounds[i]));
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
