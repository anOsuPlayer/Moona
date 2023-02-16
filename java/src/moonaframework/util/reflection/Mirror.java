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
		
		public R evaluate() {
			return filtered.get(0);
		}
		
		public MirrorFilter<R> filter(Predicate<? super R> condition) {
			filtered.removeIf(condition);
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
			for (R r : filtered) {
				if (!(r instanceof Indexed i && i.getIndex() == index)) {
					filtered.remove(r);
				}
			}
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
	
	public static MirrorFilter<Method> filterMethods() {
		MirrorFilter<Method> methods = new MirrorFilter<>();
		for (Reflection<?> r : reflections) {
			if (r instanceof Method m) { methods.filtered.add(m); }
		}
		return methods;
	}
	
	public static Annotated getProcessInitializer(Process p) {
		for (Reflection<?> r : reflections) {
			if (r instanceof Method m && m.getName().equals("initialize")) { return m.getAnnotated(); }
		}
		return null;
	}
	public static Annotated getProcessEnder(Process p) {
		for (Reflection<?> r : reflections) {
			if (r instanceof Method m && m.getName().equals("end")) { return m.getAnnotated(); }
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