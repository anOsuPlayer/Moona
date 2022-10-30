package moonaFramework;

import moonaFramework.basics.Serial;
import moonaFramework.reflection.Reflection;

public final class Mirror extends Core {
	
	private static int totalReflections = 0;
	
	public static void add(Reflection<?> r) throws NullPointerException, MoonaHandlingException {
		if (r == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
		}
		if (elements.has(r, r.id())) {
			throw new MoonaHandlingException("This Reflection already belongs to Moona.");
		}
		addReflection(r);
	}
	static void addReflection(Reflection<?> r) {
		totalReflections++;
		
		elements.add(r, r.id());
	}
	
	public static void remove(Reflection<?> r) throws NullPointerException, MoonaHandlingException {
		if (r == null) {
			throw new NullPointerException("You cannot remove a null element from Moona.");
		}
		if (!elements.has(r, r.id())) {
			throw new MoonaHandlingException("This Reflection is not present in Moona.");
		}
		removeReflection(r);
	}
	static void removeReflection(Reflection<?> r) {
		totalReflections--;
		
		elements.remove(r, r.id());
	}
	
	static void fullLoad() {
		elements.forEachValue((s) -> {
			if (s instanceof Reflection<?> r) { r.reflect(); }
		});
	}
	
	public static void wipe() {
		Moona.checkOn();
		Reflection<?>[] refls = new Reflection<?>[totalReflections];
		for (int i = 0, c = 0; i < elements.size(); i++) {
			if (elements.getValue(i) instanceof Reflection<?> r && refls.length > 0) {
				refls[c++] = r;
			}
		}
		for (Reflection<?> r : refls) {
			removeReflection(r);
		}
	}
	
	public static Reflection<?> get(long id) {
		return isReflection(id) ? (Reflection<?>) elements.valueOf(id) : null;
	}
	
	public static boolean isReflection(Serial s) {
		return s instanceof Reflection<?>;
	}
	public static boolean isReflection(long id) {
		return elements.valueOf(id) instanceof Reflection<?>;
	}
	
	public static int totalReflections() {
		return totalReflections;
	}
	
	private Mirror() {
		
	}
}
