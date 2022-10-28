package moonaFramework.reflection;

import java.lang.annotation.Annotation;

import moonaFramework.Moona;
import moonaFramework.MoonaHandlingException;
import moonaFramework.essentials.Natural;
import moonaFramework.essentials.Serial;
import moonaFramework.util.IshMap;

public class Mirror extends Moona {
	
	final IshMap<Reflection<?>, Long> reflections = new IshMap<>();
	
	private int totalReflections = 0;
	
	public final void add(Reflection<?> p) throws NullPointerException, MoonaHandlingException {
		if (p == null) {
			throw new NullPointerException("The Property you're trying to add is null.");
		}
		if (reflections.has(p, p.id())) {
			throw new MoonaHandlingException("The Property is already contained in this Mirror.");
		}
		reflections.add(p, p.id());
		totalReflections++;
	}
	
	public final void remove(Reflection<?> p) throws NullPointerException, MoonaHandlingException {
		if (p == null) {
			throw new NullPointerException("The Property you're trying to remove is null.");
		}
		if (!reflections.has(p, p.id())) {
			throw new MoonaHandlingException("The Property is not contained in this Mirror.");
		}
		reflections.remove(p, p.id());
		totalReflections--;
	}
	
	public Annotated[] getAnnotations(Class<?> type) {
		Annotated[] arr = new Annotated[annotationsOf(type)];
		int count = 0;
		for (Reflection<?> r : reflections.values()) {
			arr[count] = (r instanceof Annotated ann && ann.getTarget().equals(type)) ? ann : arr[count];
			count += (arr[count].equals(r)) ? 1 : 0;
		}
		return arr;
	}
	
	public Class<?>[] getAnnotated(Class<? extends Annotation> annotation) {
		Class<?>[] arr = new Class<?>[annotatedWith(annotation)];
		int count = 0;
		for (Reflection<?> r : reflections.values()) {
			arr[count] = (r instanceof Annotated ann && ann.getAnnotation().equals(annotation))
					? ann.getTarget() : null;
			count += (arr[count] != null) ? 1 : 0;
		}
		return arr;
	}
	
	public Reflection<?> get(long id) {
		return reflections.valueOf(id);
	}
	
	public int elementCount() {
		return totalReflections;
	}
	
	public int annotationsOf(Class<?> type) {
		int count = 0;
		for (Reflection<?> r : reflections.values()) {
			if (r instanceof Annotated at) {
				count += (at.getTarget().equals(type)) ? 1 : 0;
			}
		}
		return count;
	}
	
	public int annotatedWith(Class<? extends Annotation> annotation) {
		int count = 0;
		for (Reflection<?> r : reflections.values()) {
			if (r instanceof Annotated at) {
				count += (at.getAnnotation().equals(annotation)) ? 1 : 0;
			}
		}
		return count;
	}
	
	public boolean has(Reflection<?> p) {
		return reflections.has(p, p.id());
	}
	
	public Mirror() {
		
	}
}
