package moonaFramework.base;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import moonaFramework.util.collection.IshMap;
import moonaFramework.util.reflection.Annotated;
import moonaFramework.util.reflection.Reflection;

public final class Mirror {
	
	static final IshMap<Reflection<?>, Long> reflections = new IshMap<>();
	
	private static int totalReflections = 0;
	
	private static int totalAnnotations = 0;
	
	public static void add(Reflection<?> r) throws NullPointerException, MoonaHandlingException {
		if (r == null) {
			throw new NullPointerException("You cannot add null elements to Moona.");
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
	
	public static void remove(Reflection<?> r) throws NullPointerException, MoonaHandlingException {
		if (r == null) {
			throw new NullPointerException("You cannot remove a null element from Moona.");
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
		reflections.forEachValue((r) -> r.reflect());
	}
	
	public static List<Reflection<?>> getReflections(Object target) {
		List<Reflection<?>> refls = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl.getTarget().equals(target)) { refls.add(refl); }
		});
		return refls;
	}
	
	public static List<Annotated> getAnnotated(Object target) {
		List<Annotated> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated ann && ann.getTarget().equals(target)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated> getAnnotatedWith(Class<? extends Annotation> annot) {
		List<Annotated> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated ann && ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	
	public static Annotated getAnnotated(Object target, Class<? extends Annotation> annot) {
		for (Reflection<?> refl : reflections.values()) {	
			if (refl instanceof Annotated ann && ann.getTarget().equals(target) &&
					ann.getAnnotation().equals(annot)) {
				return ann;
			}
		}
		return null;
	}
	
	public static List<Annotated.Type> getAnnotatedTypes(Class<?> type) {
		List<Annotated.Type> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Type ann && ann.getTarget().equals(type)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	
	public static List<Annotated.Type> getAnnotatedTypesWith(Class<? extends Annotation> annot) {
		List<Annotated.Type> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Type ann && ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	
	public static Annotated.Type getAnnotatedType(Class<?> type, Class<? extends Annotation> annot) {
		for (Reflection<?> refl : reflections.values()) {	
			if (refl instanceof Annotated.Type ann && ann.getTarget().equals(type) &&
					ann.getAnnotation().equals(annot)) {
				return ann;
			}
		}
		return null;
	}
	
	public static List<Annotated.Constructor> getAnnotatedConstructors(Class<?> type) {
		List<Annotated.Constructor> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Constructor ann && ann.getTarget().equals(type)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated.Constructor> getAnnotatedConstructors(Class<?> type, Class<?>...args) {
		List<Annotated.Constructor> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Constructor ann && ann.getTarget().equals(type)
					&& ann.getArgs().equals(args)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	
	public static List<Annotated.Constructor> getAnnotatedConstructorsWith(Class<? extends Annotation> annot) {
		List<Annotated.Constructor> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Constructor ann && ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated.Constructor> getAnnotatedConstructorsWith(Class<?> type, Class<? extends Annotation> annot) {
		List<Annotated.Constructor> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Constructor ann && ann.getTarget().equals(type)
					&& ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated.Constructor> getAnnotatedConstructorsWith(Class<? extends Annotation> annot, Class<?>...args) {
		List<Annotated.Constructor> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Constructor ann && ann.getTarget().equals(annot)
					&& ann.getArgs().equals(args)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	
	public static Annotated.Constructor getAnnotatedConstructor(Class<?> type, Class<? extends Annotation> annot, Class<?>...args) {
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Constructor ann && ann.getTarget().equals(type)
					&& ann.getAnnotation().equals(annot) && ann.getArgs().equals(args)) {
				return ann;
			}
		}
		return null;
	}
	
	public static List<Annotated.Field> getAnnotatedFields(Class<?> type) {
		List<Annotated.Field> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Field ann && ann.getTarget().equals(type)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated.Field> getAnnotatedFields(Class<?> type, String fieldName) {
		List<Annotated.Field> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Field ann && ann.getTarget().equals(type)
					&& ann.getName().equals(fieldName)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	
	public static List<Annotated.Field> getAnnotatedFieldsWith(Class<? extends Annotation> annot) {
		List<Annotated.Field> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Field ann && ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated.Field> getAnnotatedFieldsWith(Class<?> type, Class<? extends Annotation> annot) {
		List<Annotated.Field> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Field ann && ann.getTarget().equals(type)
					&& ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated.Field> getAnnotatedFieldsWith(Class<? extends Annotation> annot, String fieldName) {
		List<Annotated.Field> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Field ann && ann.getAnnotation().equals(annot)
					&& ann.getName().equals(fieldName)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	
	public static Annotated.Field getAnnotatedMethod(Class<?> type, Class<? extends Annotation> annot, String fieldName) {
		for (Reflection<?> refl : reflections.values()) {	
			if (refl instanceof Annotated.Field ann && ann.getTarget().equals(type) &&
					ann.getAnnotation().equals(annot) && ann.getName().equals(fieldName)) {
				return ann;
			}
		}
		return null;
	}
	
	public static List<Annotated.Method> getAnnotatedMethods(Class<?> type) {
		List<Annotated.Method> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Method ann && ann.getTarget().equals(type)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethods(Class<?> type, String methodName) {
		List<Annotated.Method> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Method ann && ann.getTarget().equals(type)
					&& ann.getName().equals(methodName)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethods(Class<?> type, Class<?>...args) {
		List<Annotated.Method> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Method ann && ann.getTarget().equals(type)
					&& ann.getArgs().equals(args)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethods(Class<?> type, String methodName, Class<?>...args) {
		List<Annotated.Method> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Method ann && ann.getTarget().equals(type)
					&& ann.getName().equals(methodName) && ann.getArgs().equals(args)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	
	public static List<Annotated.Method> getAnnotatedMethodsWith(Class<? extends Annotation> annot) {
		List<Annotated.Method> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Method ann && ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethodsWith(Class<?> type, Class<? extends Annotation> annot) {
		List<Annotated.Method> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Method ann && ann.getAnnotation().equals(annot)
					&& ann.getTarget().equals(type)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethodsWith(Class<?> type, Class<? extends Annotation> annot, String methodName) {
		List<Annotated.Method> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Method ann && ann.getAnnotation().equals(annot)
					&& ann.getTarget().equals(type) && ann.getName().equals(methodName)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethodsWith(Class<?> type, Class<? extends Annotation> annot, Class<?>...args) {
		List<Annotated.Method> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Method ann && ann.getAnnotation().equals(annot)
					&& ann.getTarget().equals(type) && ann.getArgs().equals(args)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethodsWith(Class<? extends Annotation> annot, String methodName, Class<?>...args) {
		List<Annotated.Method> annots = new ArrayList<>();
		reflections.forEachValue((refl) -> {
			if (refl instanceof Annotated.Method ann && ann.getAnnotation().equals(annot)
					&& ann.getName().equals(methodName) && ann.getArgs().equals(args)) {
				annots.add(ann);
			}
		});
		return annots;
	}
	
	public static Annotated.Method getAnnotatedMethodsWith(Class<?> type, Class<? extends Annotation> annot, String methodName, Class<?>...args) {
		for (Reflection<?> refl : reflections.values()) {	
			if (refl instanceof Annotated.Method ann && ann.getTarget().equals(type)
					&& ann.getAnnotation().equals(annot) && ann.getName().equals(methodName)
					&& ann.getArgs().equals(args)) {
				return ann;
			}
		}
		return null;
	}
	
	public static int reflectionsOf(Object target) {
		int total = 0;
		for (Reflection<?> refl : reflections.values()) {
			total += (refl.getTarget().equals(target)) ? 1 : 0;
		}
		return total;
	}
	public static int annotatedWith(Class<? extends Annotation> annot) {
		int total = 0;
		for (Reflection<?> refl : reflections.values()) {
			total += (refl instanceof Annotated ann && ann.getAnnotation().equals(annot)) ? 1 : 0;
		}
		return total;
	}
	
	public static int totalAnnotations() {
		return totalAnnotations;
	}
	public static int annotatedTypes() {
		int total = 0;
		for (Reflection<?> refl : reflections.values()) {
			total += (refl instanceof Annotated.Type ann) ? 1 : 0;
		}
		return total;
	}
	public static int annotatedConstructors() {
		int total = 0;
		for (Reflection<?> refl : reflections.values()) {
			total += (refl instanceof Annotated.Constructor ann) ? 1 : 0;
		}
		return total;
	}
	public static int annotatedFields() {
		int total = 0;
		for (Reflection<?> refl : reflections.values()) {
			total += (refl instanceof Annotated.Field ann) ? 1 : 0;
		}
		return total;
	}
	public static int annotatedMethods() {
		int total = 0;
		for (Reflection<?> refl : reflections.values()) {
			total += (refl instanceof Annotated.Method ann) ? 1 : 0;
		}
		return total;
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
