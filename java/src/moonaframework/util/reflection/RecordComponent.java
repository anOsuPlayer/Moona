package moonaframework.util.reflection;

import moonaframework.base.MoonaHandlingException;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.filters.Indexed;
import moonaframework.util.reflection.filters.Nominal;

public sealed class RecordComponent extends Reference<java.lang.reflect.RecordComponent> implements Nominal, Indexed permits ExistingRecordComponent {

	private final Class<? extends Record> clazz;
	
	public Class<? extends Record> getRecord() {
		return this.clazz;
	}
	public @Override Class<? extends Record> getTarget() {
		return this.clazz;
	}
	
	private String name;
	
	public @Override String getName() {
		return this.name;
	}
	
	private int index = -1;
	
	public @Override int getIndex() throws MoonaHandlingException {
		if (this.index == -1) {
			try {
				reflect();
			}
			catch (UndefinedReflectionException ure) {
				throw new MoonaHandlingException("");
			}
		}
		
		return this.index;
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof RecordComponent rc) ?
				this.clazz.equals(rc.clazz) && this.name.equals(rc.name)
				: false;
	}
	
	public @Override String toString() {
		return (clazz == null) ? "Non-generated Reflection" : "RecordComponent " + name + " of " + clazz;
	}
	
	public @Override void reflect() throws UndefinedReflectionException, IndexOutOfBoundsException {
		java.lang.reflect.RecordComponent[] comps = clazz.getRecordComponents();
		
		if (this.index == -1) {
			for (int i = 0; i < comps.length; i++) {
				if (comps[i].getName().equals(name)) {
					super.value = comps[i];
					this.index = i;
					return;
				}
			}
		}
		else {
			if (index >= comps.length) {
				throw new IndexOutOfBoundsException("Only " + comps.length + " record components are available.");
			}
			
			super.value = comps[index];
			this.name = comps[index].getName();
			return;
		}
		
		throw new UndefinedReflectionException("No RecordComponent References could be generated from the"
				+ " given arguments.");
	}
	
	public RecordComponent(Class<? extends Record> clazz, String name) throws IllegalArgumentException, NullArgumentException {
		if (clazz == null || name == null) {
			throw new NullArgumentException("Cannot build a RecordComponent Reference over a null class"
					+ " or a null record component name.");
		}
		this.clazz = clazz; this.name = name;
		
		super.mirrorInteraction();
	}
	public RecordComponent(Class<? extends Record> clazz, int index) throws IllegalArgumentException, NullArgumentException {
		if (clazz == null) {
			throw new NullArgumentException("Cannot build a RecordComponent Reference over a null Type"
					+ " Reference.");
		}
		if (index < 0) {
			throw new IllegalArgumentException("RecordComponent's index cannot be less than zero.");
		}
		this.clazz = clazz; this.index = index;
		
		super.mirrorInteraction(); super.valueExtraction();
	}
}
