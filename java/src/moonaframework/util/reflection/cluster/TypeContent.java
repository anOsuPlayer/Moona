package moonaframework.util.reflection.cluster;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

import moonaframework.util.exceptions.NullArgumentException;
import moonaframework.util.reflection.Reference;
import moonaframework.util.reflection.Type;

public final class TypeContent extends Cluster<Reference<? extends AnnotatedElement>> {

	private final Type source;
	
	public Type getTarget() {
		return this.source;
	}
	
	private final List<Reference<? extends AnnotatedElement>> references;
	
	public List<Reference<? extends AnnotatedElement>> getReflections() {
		return this.references;
	}
	
	public TypeContent(Type source) throws NullArgumentException {
		if (source == null) {
			throw new NullArgumentException("TypeContent cannot be extracted from a null Type Reference.");
		}
		this.source = source; this.references = new ArrayList<>();
	}
}
