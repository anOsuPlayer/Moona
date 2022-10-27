package moonaFramework;

import moonaFramework.essentials.Container;
import moonaFramework.reflection.Property;
import moonaFramework.util.IshMap;

public final class Mirror implements Container<Property<?>> {

	final IshMap<Property<?>, Long> reflections = new IshMap<>();
	
	private int totalReflections = 0;
	
	@Override
	public final void add(Property<?> p) throws NullPointerException, MoonaHandlingException {
		if (p == null) {
			throw new NullPointerException("The Property you're trying to add is null.");
		}
		if (reflections.has(p, p.id())) {
			throw new MoonaHandlingException("The Property is already contained in this Mirror.");
		}
		reflections.add(p, p.id());
		totalReflections++;
	}
	
	@Override
	public final void remove(Property<?> p) throws NullPointerException, MoonaHandlingException {
		if (p == null) {
			throw new NullPointerException("The Property you're trying to remove is null.");
		}
		if (!reflections.has(p, p.id())) {
			throw new MoonaHandlingException("The Property is not contained in this Mirror.");
		}
		reflections.remove(p, p.id());
		totalReflections--;
	}
	
	@Override
	public Property<?> get(long id) {
		return reflections.valueOf(id);
	}
	
	@Override
	public int elementCount() {
		return totalReflections;
	}
	
	public Mirror() {
		
	}
}
