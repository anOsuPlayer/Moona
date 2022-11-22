package moonaframework.base;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import moonaframework.util.collection.IshMap;
import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.reflection.Annotated;
import moonaframework.util.reflection.Reflection;

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
	
	private static boolean avoidable() {
		return totalReflections == 0;
	}
	private static boolean annotAvoidable() {
		return totalAnnotations == 0;
	}
	
	public static List<Reflection<?>> getReflections(Object target) throws NullArgumentException {
		if (avoidable()) { return null; }
		if (target == null) {
			throw new NullArgumentException("The target Object is null.");
		}
		List<Reflection<?>> refls = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl.getTarget().equals(target)) { refls.add(refl); }
		}
		return refls;
	}
	
	public static List<Annotated> getAnnotated(Object target) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (target == null) {
			throw new NullArgumentException("The target Object is null.");
		}
		List<Annotated> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated ann && ann.getTarget().equals(target)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated> getAnnotatedWith(Class<? extends Annotation> annot) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (annot == null) {
			throw new NullArgumentException("The given Annotation is null.");
		}
		List<Annotated> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated ann && ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	
	public static Annotated getAnnotated(Object target, Class<? extends Annotation> annot) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (target == null || annot == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		for (Reflection<?> refl : reflections.values()) {	
			if (refl instanceof Annotated ann && ann.getTarget().equals(target) &&
					ann.getAnnotation().equals(annot)) {
				return ann;
			}
		}
		return null;
	}
	
	public static List<Annotated.Type> getAnnotatedTypes(Class<?> type) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null) {
			throw new NullArgumentException("The target Class is null.");
		}
		List<Annotated.Type> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Type ann && ann.getTarget().equals(type)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	
	public static List<Annotated.Type> getAnnotatedTypesWith(Class<? extends Annotation> annot) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (annot == null) {
			throw new NullArgumentException("The given Annotation is null.");
		}
		List<Annotated.Type> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Type ann && ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	
	public static Annotated.Type getAnnotatedType(Class<?> type, Class<? extends Annotation> annot) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null || annot == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		for (Reflection<?> refl : reflections.values()) {	
			if (refl instanceof Annotated.Type ann && ann.getTarget().equals(type) &&
					ann.getAnnotation().equals(annot)) {
				return ann;
			}
		}
		return null;
	}
	
	public static List<Annotated.Constructor> getAnnotatedConstructors(Class<?> type) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null) {
			throw new NullArgumentException("The target Class is null.");
		}
		List<Annotated.Constructor> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Constructor ann && ann.getTarget().equals(type)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated.Constructor> getAnnotatedConstructors(Class<?> type, Class<?>...args) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null) {
			throw new NullArgumentException("The target Class is null.");
		}
		if (args == null || args.length == 0) {
			args = Annotated.NO_ARGS;
		}
		List<Annotated.Constructor> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Constructor ann && ann.getTarget().equals(type)
					&& ann.getArgs().equals(args)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	
	public static List<Annotated.Constructor> getAnnotatedConstructorsWith(Class<? extends Annotation> annot) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (annot == null) {
			throw new NullArgumentException("The target Class is null.");
		}
		List<Annotated.Constructor> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Constructor ann && ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated.Constructor> getAnnotatedConstructorsWith(Class<?> type, Class<? extends Annotation> annot) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null || annot == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		List<Annotated.Constructor> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Constructor ann && ann.getTarget().equals(type)
					&& ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated.Constructor> getAnnotatedConstructorsWith(Class<? extends Annotation> annot, Class<?>...args) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (annot == null) {
			throw new NullArgumentException("The target Annotation is null.");
		}
		if (args == null || args.length == 0) {
			args = Annotated.NO_ARGS;
		}
		List<Annotated.Constructor> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Constructor ann && ann.getTarget().equals(annot)
					&& ann.getArgs().equals(args)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	
	public static Annotated.Constructor getAnnotatedConstructor(Class<?> type, Class<? extends Annotation> annot, Class<?>...args) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null || annot == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		if (args == null || args.length == 0) {
			args = Annotated.NO_ARGS;
		}
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Constructor ann && ann.getTarget().equals(type)
					&& ann.getAnnotation().equals(annot) && ann.getArgs().equals(args)) {
				return ann;
			}
		}
		return null;
	}
	
	public static List<Annotated.Field> getAnnotatedFields(Class<?> type) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null) {
			throw new NullArgumentException("The target Class is null.");
		}
		List<Annotated.Field> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Field ann && ann.getTarget().equals(type)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated.Field> getAnnotatedFields(Class<?> type, String fieldName) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null || fieldName == null) {
			throw new NullArgumentException("The target Class is null.");
		}
		List<Annotated.Field> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Field ann && ann.getTarget().equals(type)
					&& ann.getName().equals(fieldName)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	
	public static List<Annotated.Field> getAnnotatedFieldsWith(Class<? extends Annotation> annot) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (annot == null) {
			throw new NullArgumentException("The given Annotation is null.");
		}
		List<Annotated.Field> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Field ann && ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated.Field> getAnnotatedFieldsWith(Class<?> type, Class<? extends Annotation> annot) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null || annot == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		List<Annotated.Field> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Field ann && ann.getTarget().equals(type)
					&& ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated.Field> getAnnotatedFieldsWith(Class<? extends Annotation> annot, String fieldName) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (annot == null || fieldName == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		List<Annotated.Field> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Field ann && ann.getAnnotation().equals(annot)
					&& ann.getName().equals(fieldName)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	
	public static Annotated.Field getAnnotatedField(Class<?> type, Class<? extends Annotation> annot, String fieldName) {
		if (annotAvoidable()) { return null; }
		if (type == null || annot == null || fieldName == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		for (Reflection<?> refl : reflections.values()) {	
			if (refl instanceof Annotated.Field ann && ann.getTarget().equals(type) &&
					ann.getAnnotation().equals(annot) && ann.getName().equals(fieldName)) {
				return ann;
			}
		}
		return null;
	}
	
	public static List<Annotated.Method> getAnnotatedMethods(Class<?> type) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null) {
			throw new NullArgumentException("The target Class is null.");
		}
		List<Annotated.Method> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Method ann && ann.getTarget().equals(type)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethods(Class<?> type, String methodName) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null || methodName == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		List<Annotated.Method> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Method ann && ann.getTarget().equals(type)
					&& ann.getName().equals(methodName)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethods(Class<?> type, Class<?>...args) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null) {
			throw new NullArgumentException("The target Class is null.");
		}
		if (args == null || args.length == 0) {
			args = Annotated.NO_ARGS;
		}
		List<Annotated.Method> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Method ann && ann.getTarget().equals(type)
					&& ann.getArgs().equals(args)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethods(Class<?> type, String methodName, Class<?>...args) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null || methodName == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		if (args == null || args.length == 0) {
			args = Annotated.NO_ARGS;
		}
		List<Annotated.Method> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Method ann && ann.getTarget().equals(type)
					&& ann.getName().equals(methodName) && ann.getArgs().equals(args)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	
	public static List<Annotated.Method> getAnnotatedMethodsWith(Class<? extends Annotation> annot) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (annot == null) {
			throw new NullArgumentException("The given Annotation is null.");
		}
		List<Annotated.Method> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Method ann && ann.getAnnotation().equals(annot)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethodsWith(Class<?> type, Class<? extends Annotation> annot) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null || annot == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		List<Annotated.Method> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Method ann && ann.getAnnotation().equals(annot)
					&& ann.getTarget().equals(type)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethodsWith(Class<?> type, Class<? extends Annotation> annot, String methodName) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null || annot == null || methodName == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		List<Annotated.Method> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Method ann && ann.getAnnotation().equals(annot)
					&& ann.getTarget().equals(type) && ann.getName().equals(methodName)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethodsWith(Class<?> type, Class<? extends Annotation> annot, Class<?>...args) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null || annot == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		if (args == null || args.length == 0) {
			args = Annotated.NO_ARGS;
		}
		List<Annotated.Method> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Method ann && ann.getAnnotation().equals(annot)
					&& ann.getTarget().equals(type) && ann.getArgs().equals(args)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	public static List<Annotated.Method> getAnnotatedMethodsWith(Class<? extends Annotation> annot, String methodName, Class<?>...args) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (annot == null || methodName == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		if (args == null || args.length == 0) {
			args = Annotated.NO_ARGS;
		}
		List<Annotated.Method> annots = new ArrayList<>();
		for (Reflection<?> refl : reflections.values()) {
			if (refl instanceof Annotated.Method ann && ann.getAnnotation().equals(annot)
					&& ann.getName().equals(methodName) && ann.getArgs().equals(args)) {
				annots.add(ann);
			}
		}
		return annots;
	}
	
	public static Annotated.Method getAnnotatedMethod(Class<?> type, Class<? extends Annotation> annot, String methodName, Class<?>...args) throws NullArgumentException {
		if (annotAvoidable()) { return null; }
		if (type == null || annot == null || methodName == null) {
			throw new NullArgumentException("Null arguments are not allowed.");
		}
		if (args == null || args.length == 0) {
			args = Annotated.NO_ARGS;
		}
		for (Reflection<?> refl : reflections.values()) {	
			if (refl instanceof Annotated.Method ann && ann.getTarget().equals(type)
					&& ann.getAnnotation().equals(annot) && ann.getName().equals(methodName)
					&& ann.getArgs().equals(args)) {
				return ann;
			}
		}
		return null;
	}
	
	public static int reflectionsOf(Object target) throws NullArgumentException {
		if (avoidable()) { return 0; }
		if (target == null) {
			throw new NullArgumentException("The target Object is null.");
		}
		int total = 0;
		for (Reflection<?> refl : reflections.values()) {
			total += (refl.getTarget().equals(target)) ? 1 : 0;
		}
		return total;
	}
	public static int annotatedWith(Class<? extends Annotation> annot) throws NullArgumentException {
		if (annotAvoidable()) { return 0; }
		if (annot == null) {
			throw new NullArgumentException("The given Annotation is null.");
		}
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
		if (annotAvoidable()) { return 0; }
		int total = 0;
		for (Reflection<?> refl : reflections.values()) {
			total += (refl instanceof Annotated.Type ann) ? 1 : 0;
		}
		return total;
	}
	public static int annotatedConstructors() {
		if (annotAvoidable()) { return 0; }
		int total = 0;
		for (Reflection<?> refl : reflections.values()) {
			total += (refl instanceof Annotated.Constructor ann) ? 1 : 0;
		}
		return total;
	}
	public static int annotatedFields() {
		if (annotAvoidable()) { return 0; }
		int total = 0;
		for (Reflection<?> refl : reflections.values()) {
			total += (refl instanceof Annotated.Field ann) ? 1 : 0;
		}
		return total;
	}
	public static int annotatedMethods() {
		if (annotAvoidable()) { return 0; }
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
