package moonaframework.util.reflection;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import moonaframework.base.Moona;
import moonaframework.base.MoonaHandlingException;
import moonaframework.base.MoonaObject;
import moonaframework.dynamic.process.Process;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.filters.Argumental;
import moonaframework.util.reflection.filters.ClassElement;
import moonaframework.util.reflection.filters.Indexed;
import moonaframework.util.reflection.filters.Nominal;
import moonaframework.util.reflection.flare.Annotated;
import moonaframework.util.reflection.flare.Flare;

public final class Mirror {
	
	private static final List<Reflection<?>> reflections = new ArrayList<>();
	
	private static final List<Reflection<?>> queue = new ArrayList<>();
	
	static void queue(Reflection<?> refl) throws NullArgumentException {
		if (refl == null) {
			throw new NullArgumentException("You cannot queue null Reflections.");
		}
		queue.add(refl);
	}
	
	private static int totalReflections = 0;
	
	private static int undefinedReflections = 0;
	
	private static int totalFlares = 0;
	
	public static final Class<?>[] NO_ARGS = new Class<?>[0];
	
	public static void add(Reflection<?> refl) throws MoonaHandlingException, NullArgumentException {
		if (refl == null) {
			throw new NullArgumentException("You cannot add null elements to Moona.");
		}
		addReflection(refl);
	}
	public static void add(Reflection<?>...reflections) throws MoonaHandlingException, NullArgumentException {
		for (Reflection<?> refl : reflections) {
			add(refl);
		}
	}
	static void addReflection(Reflection<?> refl) throws MoonaHandlingException {
		if (!reflections.contains(refl)) {
			totalReflections++;
			totalFlares += (refl instanceof Flare<?>) ? 1 : 0;
			
			reflections.add(refl);
		}
	}
	
	public static void remove(Reflection<?> refl) throws MoonaHandlingException, NullArgumentException {
		if (refl == null) {
			throw new NullArgumentException("You cannot add null elements to Moona.");
		}
		removeReflection(refl);
	}
	public static void remove(Reflection<?>...reflections) throws MoonaHandlingException, NullArgumentException {
		for (Reflection<?> refl : reflections) {
			remove(refl);
		}
	}
	static void removeReflection(Reflection<?> refl) throws MoonaHandlingException {
		if (reflections.contains(refl)) {
			totalReflections--;
			totalFlares -= (refl instanceof Flare<?>) ? 1 : 0;
			
			reflections.remove(refl);
		}
	}
	
	public static void loadReflections() throws MoonaHandlingException {
		if (Moona.unsafeReflectionLoading.evaluate()) {
			reflections.forEach((refl) -> {
				if (refl.safeEval() == null) {
					undefinedReflections++;
				}
			});
		}
		else {
			reflections.forEach((refl) -> {
				try {
					refl.evaluate();
				}
				catch (UndefinedReflectionException ure) {
					throw new MoonaHandlingException("Undefined Reflections.", ure);
				}
			});
		}
		if (!queue.isEmpty()) {
			for (Reflection<?> refl : queue) {
				if (!reflections.contains(refl)) {
					addReflection(refl);
				}
			}
			queue.clear();
			loadReflections();
		}
	}
	
	@SuppressWarnings("unchecked")
	static <T> void askMirror(Reflection<T> refl) {
		for (Reflection<?> r : reflections) {
			if (r.equals(refl)) {
				refl.value = (T) r.value;
			}
		}
	}
	
	public static final class MirrorFilter<R extends Reflection<?>> {
		
		final List<R> filtered;
		
		public List<R> getResults() {
			return this.filtered;
		}
		public R getResult(int index) throws IndexOutOfBoundsException {
			return (filtered.isEmpty()) ? null : filtered.get(index);
		}
		
		public int resultsCount() {
			return filtered.size();
		}
		
		public MirrorFilter<R> filter(Predicate<? super R> condition) throws NullArgumentException {
			if (condition == null) {
				throw new NullArgumentException("Cannot filter this MirrorFilter with a null Predicate.");
			}
			filtered.removeIf(condition.negate());
			return this;
		}
		
		public MirrorFilter<R> filterByType(Class<?> clazz) {
			filtered.removeIf((R r) -> { return !r.safeEval().equals(clazz); });
			return this;
		}
		
		public MirrorFilter<R> filterByTarget(Object target) {
			filtered.removeIf((R r) -> { return !r.getTarget().equals(target); });
			return this;
		}
		
		public MirrorFilter<R> filterByName(String name) {
			filtered.removeIf((R r) -> { return !(r instanceof Nominal n && n.getName().equals(name)); });
			return this;
		}
		
		public MirrorFilter<R> filterByClass(Class<?> clazz) {
			filtered.removeIf((R r) -> { return !(r instanceof ClassElement ce && ce.getDeclaringClass().equals(clazz)); });
			return this;
		}
		
		public MirrorFilter<R> filterByArguments(Class<?>[] args) {
			filtered.removeIf((R r) -> { return !(r instanceof Argumental a && a.getParameterTypes().equals(args)); });
			return this;
		}
		
		public MirrorFilter<R> filterByIndex(int index) {
			filtered.removeIf((R r) -> { return !(r instanceof Indexed i && i.getIndex() == index); });
			return this;
		}
		
		MirrorFilter() {
			this.filtered = new ArrayList<>();
		}
	}
	
	public static List<Reflection<?>> getReflectionsOf(Object target) {
		final List<Reflection<?>> refls = new ArrayList<>();
		for (Reflection<?> r : reflections) {
			if (r.getTarget().equals(target)) { refls.add(r); }
		}
		return refls;
	}
	
	public static MirrorFilter<Type> filterTypes() {
		MirrorFilter<Type> types = new MirrorFilter<>();
		for (Reflection<?> r : reflections) {
			if (r instanceof Type t) { types.filtered.add(t); }
		}
		return types;
	}
	public static MirrorFilter<RawType> filterRawTypes() {
		MirrorFilter<RawType> rawtypes = new MirrorFilter<>();
		for (Reflection<?> r : reflections) {
			if (r instanceof RawType rt) { rawtypes.filtered.add(rt); }
		}
		return rawtypes;
	}
	
	public static MirrorFilter<Method> filterMethods() {
		MirrorFilter<Method> methods = new MirrorFilter<>();
		for (Reflection<?> r : reflections) {
			if (r instanceof Method m) { methods.filtered.add(m); }
		}
		return methods;
	}
	public static MirrorFilter<Constructor> filterConstructors() {
		MirrorFilter<Constructor> cons = new MirrorFilter<>();
		for (Reflection<?> r : reflections) {
			if (r instanceof Constructor con) { cons.filtered.add(con); }
		}
		return cons;
	}
	public static MirrorFilter<Field> filterFields() {
		MirrorFilter<Field> fields = new MirrorFilter<>();
		for (Reflection<?> r : reflections) {
			if (r instanceof Field f) { fields.filtered.add(f); }
		}
		return fields;
	}
	public static MirrorFilter<Parameter> filterParameters() {
		MirrorFilter<Parameter> params = new MirrorFilter<>();
		for (Reflection<?> r : reflections) {
			if (r instanceof Parameter p) { params.filtered.add(p); }
		}
		return params;
	}
	public static MirrorFilter<Modifier> filterModifiers() {
		MirrorFilter<Modifier> mods = new MirrorFilter<>();
		for (Reflection<?> r : reflections) {
			if (r instanceof Modifier m) { mods.filtered.add(m); }
		}
		return mods;
	}
	public static MirrorFilter<Generic> filterGenerics() {
		MirrorFilter<Generic> generics = new MirrorFilter<>();
		for (Reflection<?> r : reflections) {
			if (r instanceof Generic g) { generics.filtered.add(g); }
		}
		return generics;
	}
	
	public static MirrorFilter<RecordComponent> filterRecordComponents() {
		MirrorFilter<RecordComponent> comps = new MirrorFilter<>();
		for (Reflection<?> r : reflections) {
			if (r instanceof RecordComponent comp) { comps.filtered.add(comp); }
		}
		return comps;
	}
	public static MirrorFilter<EnumConstant<?>> filterEnumConstants() {
		MirrorFilter<EnumConstant<?>> consts = new MirrorFilter<>();
		for (Reflection<?> r : reflections) {
			if (r instanceof EnumConstant<?> ec) { consts.filtered.add(ec); }
		}
		return consts;
	}
	
	public static MirrorFilter<Annotation<?>> filterAnnotations() {
		MirrorFilter<Annotation<?>> annots = new MirrorFilter<>();
		for (Reflection<?> r : reflections) {
			if (r instanceof Annotation<?> a) { annots.filtered.add(a); }
		}
		return annots;
	}
	
	public static Flare<?> getDerivedFlareOf(Reference<?> ref) {
		for (Reflection<?> r : reflections) {
			if (r instanceof Flare<?> fl && fl.getTarget().equals(ref)) {
				return fl;
			}
		}
		return null;
	}
	public static Flare<?> getDerivedFlareOfTarget(Object target) {
		for (Reflection<?> r : reflections) {
			if (r instanceof Flare<?> fl && fl.getTarget().getTarget().equals(target)) {
				return fl;
			}
		}
		return null;
	}
	
	public static Annotated getAnnotatedFlareOf(Reference<?> ref) {
		for (Reflection<?> r : reflections) {
			if (r instanceof Annotated annot && annot.getTarget().equals(ref)) {
				return annot;
			}
		}
		return null;
	}
	public static Annotated getAnnotatedFlareOfTarget(Object target) {
		for (Reflection<?> r : reflections) {
			if (r instanceof Annotated annot && annot.getTarget().getTarget().equals(target)) {
				return annot;
			}
		}
		return null;
	}
	
	public static Annotated getProcessInitializer(Process p) {
		for (Reflection<?> r : reflections) {
			if (r instanceof Method m && m.getName().equals("initialize") && m.getDeclaringClass().equals(p.getClass())) { return m.getAnnotated(); }
		}
		return null;
	}
	public static Annotated getProcessEnder(Process p) {
		for (Reflection<?> r : reflections) {
			if (r instanceof Method m && m.getName().equals("end") && m.getDeclaringClass().equals(p.getClass())) { return m.getAnnotated(); }
		}
		return null;
	}
	
	public static boolean isReflected(Reflection<?> refl) {
		return refl.value != null;
	}

	public static boolean isReflection(MoonaObject mo) {
		return mo instanceof Reflection<?>;
	}
	
	public static boolean contains(MoonaObject mo) {
		return mo instanceof Reflection<?> refl ? has(refl) : false;
	}
	public static boolean has(Reflection<?> refl) {
		return reflections.contains(refl) || queue.contains(refl);
	}
	
	public static int totalReflections() {
		return totalReflections + totalFlares;
	}
	
	public static int reflectionsCount() {
		return totalReflections;
	}
	public static int undefinedReflectionsCount() {
		return undefinedReflections;
	}
	public static int flaresCount() {
		return totalFlares;
	}
	
	private Mirror() {
		
	}
}