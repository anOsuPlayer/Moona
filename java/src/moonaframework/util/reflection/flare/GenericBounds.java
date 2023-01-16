package moonaframework.util.reflection.flare;

import java.lang.reflect.TypeVariable;
import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Generic;
import moonaframework.util.reflection.RawType;

public final class GenericBounds extends Flare<RawType> {

	private final Generic target;
	
	public @Override Generic getTarget() {
		return this.target;
	}
	
	public List<RawType> getBounds() throws MoonaHandlingException {
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
	public RawType getBound(int index) throws IllegalArgumentException {
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
			throw new IndexOutOfBoundsException("There are only " + super.value.size() + " RawType Reflections,"
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
	
	public @Override boolean equals(Object o) {
		return (o instanceof GenericBounds gb) ? this.getTarget().equals(gb.getTarget()) : false;
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
		
		String bounds = "";
		for (RawType rt : super.value) {
			bounds += rt.toString().substring(rt.toString().lastIndexOf('.')+1) + ", ";
		}
		
		bounds = (bounds.length() != 0) ? bounds.substring(0, bounds.length()-2) : bounds;
		
		return (!super.hasGenerated) ? "Non-generated Flare" : "GenericBounds of " + target + ", "
				+ ((super.value.size() == 0) ? "no bounds" : "bounds : " + bounds);
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		TypeVariable<?> generic = target.evaluate();
		
		strictContext.enable();
		
		java.lang.reflect.AnnotatedType[] bounds = generic.getAnnotatedBounds();
		for (int i = 0; i < bounds.length; i++) {
			super.value.add(new RawType(bounds[i]));
		}
		
		strictContext.disable();
		
		super.reflect();
	}
	
	public GenericBounds(Generic target) throws NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("GenericBounds cannot be extracted from a null Generic Reference.");
		}
		this.target = target;
		
		super.mirrorInteraction();
	}
}
