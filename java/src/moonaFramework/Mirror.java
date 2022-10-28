package moonaFramework;

import moonaFramework.essentials.Serial;
import moonaFramework.reflection.Reflection;

public class Mirror {
	
	private static int totalReflections = 0;
	
	public static void add(Reflection<?> r) throws NullPointerException, MoonaHandlingException {
		if (r == null) {
			throw new NullPointerException("The Property you're trying to add is null.");
		}
		if (Moona.elements.has(r, r.id())) {
			throw new MoonaHandlingException("The Property is already contained in this Mirror.");
		}
		addReflection(r);
	}
	static void addReflection(Reflection<?> r) {
		totalReflections++;
		
		Moona.elements.add(r, r.id());
	}
	
	public static void remove(Reflection<?> r) throws NullPointerException, MoonaHandlingException {
		if (r == null) {
			throw new NullPointerException("The Property you're trying to remove is null.");
		}
		if (!Moona.elements.has(r, r.id())) {
			throw new MoonaHandlingException("The Property is not contained in this Mirror.");
		}
		removeReflection(r);
	}
	static void removeReflection(Reflection<?> r) {
		totalReflections--;
		
		Moona.elements.remove(r, r.id());
	}
	
	public static Reflection<?> get(long id) {
		return isReflection(id) ? (Reflection<?>) Moona.elements.valueOf(id) : null;
	}
	
	public static boolean isReflection(Serial s) {
		return s instanceof Reflection<?>;
	}
	public static boolean isReflection(long id) {
		return Moona.elements.valueOf(id) instanceof Reflection<?>;
	}
	
	public static int totalReflections() {
		return totalReflections;
	}
	
	public Mirror() {
		
	}
}
