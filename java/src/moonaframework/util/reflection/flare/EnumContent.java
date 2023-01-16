package moonaframework.util.reflection.flare;

import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.ReflectionNotFoundException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.EnumConstant;
import moonaframework.util.reflection.Type;

public final class EnumContent<E extends Enum<E>> extends Flare<EnumConstant<E>> {

	private final Type target;
	
	public @Override Type getTarget() {
		return this.target;
	}
	
	public List<EnumConstant<E>> getEnumConstants() throws MoonaHandlingException {
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
	
	public EnumConstant<E> getEnumConstant(int index) throws IllegalArgumentException, MoonaHandlingException {
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
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " EnumConstant "
					+ "References, index " + index + " is out of range.");
		}
		
		return super.value.get(index);
	}
	public EnumConstant<E> getEnumConstant(String name) throws NullArgumentException, MoonaHandlingException, ReflectionNotFoundException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		if (name == null) {
			throw new NullArgumentException("The enum component's name cannot be null.");
		}
		
		for (EnumConstant<E> ec : super.value) {
			if (ec.getName().equals(name)) {
				return ec;
			}
		}
		
		throw new ReflectionNotFoundException("There is no enum constant named " + name + " in this"
				+ " EnumContent.");
	}
	
	public int enumConstsCount() throws MoonaHandlingException {
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
		return (o instanceof EnumContent<?> ec) ?
				this.target.equals(ec.target)
				: false;
	}
	
	public @Override String toString() {
		return (!super.hasGenerated) ? "Non-generated Flare" : "EnumContent of " + target; 
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		@SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) target.evaluate();
		
		strictContext.enable();
		
		for (E enconst : clazz.getEnumConstants()) {
			super.value.add(new EnumConstant<E>(enconst));
		}
		
		strictContext.disable();
		
		super.reflect();
	}
	
	public EnumContent(Type target) throws IllegalArgumentException, NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("EnumContents cannot be extracted from a null Type Reference.");
		}
		if (!target.evaluate().isEnum()) {
			throw new IllegalArgumentException("EnumContents can only be built from Type References that"
					+ " target enum classes.");
		}
		this.target = target;
		
		super.mirrorInteraction();
	}
}
