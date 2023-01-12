package moonaframework.util.reflection.flare;

import java.util.List;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.ReflectionNotFoundException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.ExistingRecordComponent;
import moonaframework.util.reflection.RecordComponent;
import moonaframework.util.reflection.Type;

public final class RecordContent extends Flare<RecordComponent> {
	
	private final Type target;
	
	public @Override Type getTarget() {
		return this.target;
	}
	
	public int componentCount() {
		return super.value.size();
	}
	public List<RecordComponent> getRecordComponents() throws MoonaHandlingException {
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
	
	public RecordComponent getRecordComponent(int index) throws IllegalArgumentException, MoonaHandlingException {
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
			throw new IllegalArgumentException("There are only " + (super.value.size()-1) + " RecordComponent "
					+ "References, index " + index + " is out of range.");
		}
		
		return super.value.get(index);
	}
	public RecordComponent getRecordComponent(String name) throws NullArgumentException, MoonaHandlingException, ReflectionNotFoundException {
		if (!super.hasGenerated) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("Unable to operate with undefined Reflections.", ure);
			}
		}
		
		if (name == null) {
			throw new NullArgumentException("The record component's name cannot be null.");
		}
		
		for (RecordComponent rc : super.value) {
			if (rc.getName().equals(name)) {
				return rc;
			}
		}
		
		throw new ReflectionNotFoundException("There is no record component named " + name + " in this"
				+ " RecordContent.");
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof RecordContent rc) ?
				this.target.equals(rc.target)
				: false;
	}
	
	public @Override String toString() {
		return (!super.hasGenerated) ? "Non-generated Flare." : "RecordContent of " + target;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		@SuppressWarnings("unchecked") Class<? extends Record> clazz = (Class<? extends Record>) target.evaluate();
		
		strictContext.enable();
		
		for (java.lang.reflect.RecordComponent rc : clazz.getRecordComponents()) {
			super.value.add(new ExistingRecordComponent(clazz, rc.getName(), rc));
		}
		
		strictContext.disable();
		
		super.reflect();
	}

	public RecordContent(Type target) throws IllegalArgumentException, NullArgumentException {
		if (target == null) {
			throw new NullArgumentException("SealedProfilers cannot be extracted from a null Type Reference.");
		}
		if (!target.evaluate().isRecord()) {
			throw new IllegalArgumentException("RecordContents can only be built from Type References that"
					+ " target records.");
		}
		this.target = target;
	}
}
