package moonaframework.util.reflection;

import moonaframework.base.MoonaHandlingException;
import moonaframework.base.Nature;
import moonaframework.base.Serial;
import moonaframework.util.collection.IshMap;
import moonaframework.util.exception.NullArgumentException;

public final class Mirror {
	
	private static final IshMap<Reflection<?>, Long> reflections = new IshMap<>();
	
	private static final IshMap<Reflection<?>, Long> queue = new IshMap<>();
	
	static void queue(Reflection<?> refl) throws NullArgumentException {
		if (refl == null) {
			throw new NullArgumentException("You cannot queue null Reflections.");
		}
		queue.add(refl, refl.id());
	}
	
	private static int totalReflections = 0;
	
	public static final Class<?>[] NO_ARGS = new Class<?>[0];
	
	public static void add(Reflection<?> refl) throws MoonaHandlingException, NullArgumentException {
		if (refl == null) {
			throw new NullArgumentException("You cannot add null elements to Moona.");
		}
		if (reflections.hasKey(refl.id())) {
			throw new MoonaHandlingException("This Reflection already belongs to Moona.");
		}
		if (!has(refl)) {
			addReflection(refl);
		}
	}
	public static void add(Reflection<?>...reflections) throws MoonaHandlingException, NullArgumentException {
		for (Reflection<?> refl : reflections) {
			add(refl);
		}
	}
	static void addReflection(Reflection<?> refl) {
		totalReflections++;
		
		reflections.add(refl, refl.id());
	}
	
	public static void remove(Reflection<?> refl) throws MoonaHandlingException, NullArgumentException {
		if (refl == null) {
			throw new NullArgumentException("You cannot remove null elements to Moona.");
		}
		if (!reflections.hasKey(refl.id())) {
			throw new MoonaHandlingException("This Reflection is not present in Moona.");
		}
		if (has(refl)) {
			removeReflection(refl);
		}
	}
	public static void remove(Reflection<?>...reflections) throws MoonaHandlingException, NullArgumentException {
		for (Reflection<?> refl : reflections) {
			remove(refl);
		}
	}
	static void removeReflection(Reflection<?> refl) {
		totalReflections--;
		
		reflections.remove(refl, refl.id());
	}
	
	public static void loadReflections() {
		reflections.forEachValue((refl) -> refl.evaluate());
		if (!queue.isEmpty()) {
			for (Reflection<?> refl : queue.values()) {
				if (!has(refl)) {
					addReflection(refl);
				}
			}
			queue.clear();
			loadReflections();
		}
	}
	
	
	
	public static void wipe() {
		reflections.clear();
	}
	
	public static Reflection<?> get(long id) {
		return isReflection(id) ? reflections.valueOf(id) : null;
	}
	
	public static boolean isReflection(long id) {
		return reflections.hasKey(id);
	}
	public static boolean isReflection(Serial s) {
		return s.nature() == Nature.REFLECTION;
	}
	
	public static boolean contains(Serial s) {
		return s instanceof Reflection<?> refl ? reflections.hasKey(refl.id()) : false;
	}
	public static boolean has(long id) {
		return reflections.hasKey(id);
	}
	public static boolean has(Reflection<?> refl) {
		for (Reflection<?> r : reflections.values()) {
			if (r.equals(refl)) { return true; };
		}
		return false;
	}
	
	public static int totalReflections() {
		return totalReflections;
	}
	
	private Mirror() {
		
	}
}
