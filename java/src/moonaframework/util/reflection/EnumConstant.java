package moonaframework.util.reflection;

import moonaframework.util.annotation.Deadlined;
import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.reflection.flare.Annotated;
import moonaframework.util.reflection.flare.TypeContent;

public final class EnumConstant<E extends Enum<E>> extends Reflection<E> implements Derivable {

	private final Class<E> target;
	
	public @Override Class<E> getTarget() {
		return this.target;
	}

	public String getName() {
		return String.valueOf(super.value);
	}
	
	public @Override boolean equals(Object o) {
		return (o instanceof EnumConstant<?> en) ?
				this.target.equals(en.target) && this.value.equals(en.value)
				: false;
	}
	
	public @Override String toString() {
		return (target == null) ? "Non-generated Reflection" : "Enum Constant " + super.value + " of Enum "
				+ target.getName();
	}
	
	public @Deadlined void reflect() {
		
	}
	
	public @Override E evaluate() {
		return super.value;
	}
	
	private Annotated annots;
	
	public @Override Annotated getAnnotated() {
		if (annots == null) {
			annots = new Annotated(new EnumField(target, super.value.toString()));
		}
		return annots;
	}
	
	private TypeContent tc;
	
	public @Override TypeContent derive() {
		if (tc == null) {
			tc = new TypeContent(super.value.getClass());
		}
		return tc;
	}
	
	public EnumConstant(E en) throws NullArgumentException {
		if (en == null) {
			throw new NullArgumentException("Cannot generate an EnumConstant Reference over a null enum.");
		}
		super.value = en; this.target = super.value.getDeclaringClass();
		
		super.mirrorInteraction(); super.valueExtraction();
	}
}
