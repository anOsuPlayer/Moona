package moonaframework.base;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import moonaframework.util.annotations.Timeless;
import moonaframework.util.annotations.Unique;
import moonaframework.util.collection.IshMap;
import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.reflection.Annotated;
import moonaframework.util.reflection.Reflection;
import moonaframework.dynamic.process.Process;

public final class Mirror {
	
	static final IshMap<Reflection<?>, Long> reflections = new IshMap<>();
	
	private static int totalReflections = 0;
	
	private static int totalAnnotations = 0;
	
	public static void add(Reflection<?> r) throws NullArgumentException, MoonaHandlingException {
		if (r == null) {
			throw new NullArgumentException("You cannot add null elements to Moona.");
		}
		if (reflections.hasKey(r.id())) {
			throw new MoonaHandlingException("This Reflection already belongs to Moona.");
		}
		addReflection(r);
	}
	static void addReflection(Reflection<?> r) {
		totalReflections++;
		totalAnnotations += (r instanceof Annotated) ? 1 : 0;
		
		reflections.add(r, r.id());
	}
	
	public static void remove(Reflection<?> r) throws NullArgumentException, MoonaHandlingException {
		if (r == null) {
			throw new NullArgumentException("You cannot remove a null element from Moona.");
		}
		if (!reflections.hasKey(r.id())) {
			throw new MoonaHandlingException("This Reflection is not present in Moona.");
		}
		removeReflection(r);
	}
	static void removeReflection(Reflection<?> r) {
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
	
	public static Reflection<?> get(long id) {
		return reflections.valueOf(id);
	}
	
	public static boolean isReflection(long id) {
		return reflections.hasKey(id);
	}
	public static boolean isReflection(Serial s) {
		return s instanceof Reflection<?>;
	}
	
	public static boolean contains(Serial s) {
		return s instanceof Reflection<?> r ? has(r.id()) : false;
	}
	public static boolean has(long id) {
		return reflections.hasKey(id);
	}
	public static boolean has(Reflection<?> r) {
		return has(r.id());
	}
	
	public static int totalReflections() {
		return totalReflections;
	}
	
	private Mirror() {
		
	}
}
