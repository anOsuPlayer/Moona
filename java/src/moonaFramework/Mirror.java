package moonaFramework;

import moonaFramework.basics.Serial;
import moonaFramework.reflection.Reflection;
import moonaFramework.util.IshMap;

public final class Mirror {
	
	static final IshMap<Reflection<?>, Long> reflections = new IshMap<>();
	
	private static int totalReflections = 0;
	
	public static void add(Reflection<?> r) throws NullPointerException, MoonaHandlingException {
		if (r == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (reflections.has(r, r.id())) {
			throw new MoonaHandlingException("This Reflection already belongs to Moona.");
		}
		addReflection(r);
	}
	static void addReflection(Reflection<?> r) {
		totalReflections++;
		
		reflections.add(r, r.id());
	}
	
	public static void remove(Reflection<?> r) throws NullPointerException, MoonaHandlingException {
		if (r == null) {
			throw new NullPointerException("You cannot remove a null element from Moona.");
		}
		if (!reflections.has(r, r.id())) {
			throw new MoonaHandlingException("This Reflection is not present in Moona.");
		}
		removeReflection(r);
	}
	static void removeReflection(Reflection<?> r) {
		totalReflections--;
		
		reflections.remove(r, r.id());
	}
	
	static void fullLoad() {
		reflections.forEachValue((r) -> r.reflect());
	}
	
	public static void wipe() {
		Moona.checkOn();
		Reflection<?>[] refls = new Reflection<?>[totalReflections];
		for (int i = 0, c = 0; i < reflections.size(); i++) {
			if (refls.length != 0) {
				refls[c++] = reflections.getValue(i);
			}
		}
		for (Reflection<?> r : refls) {
			removeReflection(r);
		}
	}
	
	public static Reflection<?> get(long id) {
		return isReflection(id) ? reflections.valueOf(id) : null;
	}
	
	public static boolean isReflection(Serial s) {
		return s instanceof Reflection<?>;
	}
	public static boolean isReflection(long id) {
		return reflections.valueOf(id) instanceof Reflection<?>;
	}
	
	public static boolean contains(Serial s) {
		return s instanceof Reflection<?> r ? reflections.has(r, r.id()) : false;
	}
	public static boolean has(long id) {
		return reflections.hasKey(id);
	}
	public static boolean has(Reflection<?> r) {
		return reflections.has(r, r.id());
	}
	
	public static int totalReflections() {
		return totalReflections;
	}
	
	private Mirror() {
		
	}
}
