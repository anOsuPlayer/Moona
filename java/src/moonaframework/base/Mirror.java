package moonaframework.base;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import moonaframework.util.annotations.Timeless;
import moonaframework.util.annotations.Unique;
import moonaframework.util.collection.IshMap;
import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.reflection.Annotated;
import moonaframework.util.reflection.AbstractReflection;
import moonaframework.dynamic.process.Process;

public final class Mirror {
	
	static final IshMap<AbstractReflection<?>, Long> reflections = new IshMap<>();
	
	private static int totalReflections = 0;
	
	private static int totalAnnotations = 0;
	
	public static void add(AbstractReflection<?> r) throws NullArgumentException, MoonaHandlingException {
		if (r == null) {
			throw new NullArgumentException("You cannot add null elements to Moona.");
		}
		if (reflections.hasKey(r.id())) {
			throw new MoonaHandlingException("This Reflection already belongs to Moona.");
		}
		addReflection(r);
	}
	static void addReflection(AbstractReflection<?> r) {
		totalReflections++;
		totalAnnotations += (r instanceof Annotated) ? 1 : 0;
		
		reflections.add(r, r.id());
	}
	
	public static void remove(AbstractReflection<?> r) throws NullArgumentException, MoonaHandlingException {
		if (r == null) {
			throw new NullArgumentException("You cannot remove a null element from Moona.");
		}
		if (!reflections.hasKey(r.id())) {
			throw new MoonaHandlingException("This Reflection is not present in Moona.");
		}
		removeReflection(r);
	}
	static void removeReflection(AbstractReflection<?> r) {
		totalReflections--;
		totalAnnotations -= (r instanceof Annotated) ? 1 : 0;
		
		reflections.remove(r, r.id());
	}
	
	static void loadReflections() {
		reflections.forEachValue((refl) -> refl.reflect());
	}
	
	public static void wipe() {
		reflections.clear();
	}
	
	public static AbstractReflection<?> get(long id) {
		return reflections.valueOf(id);
	}
	
	public static boolean isReflection(long id) {
		return reflections.hasKey(id);
	}
	public static boolean isReflection(Serial s) {
		return s instanceof AbstractReflection<?>;
	}
	
	public static boolean contains(Serial s) {
		return s instanceof AbstractReflection<?> r ? has(r.id()) : false;
	}
	public static boolean has(long id) {
		return reflections.hasKey(id);
	}
	public static boolean has(AbstractReflection<?> r) {
		return has(r.id());
	}
	
	public static int totalReflections() {
		return totalReflections;
	}
	
	private Mirror() {
		
	}
}
