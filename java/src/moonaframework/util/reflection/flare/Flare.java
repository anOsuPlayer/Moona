package moonaframework.util.reflection.flare;

import java.util.ArrayList;
import java.util.List;

import moonaframework.base.Moona;
import moonaframework.util.annotation.Deadlined;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Mirror;
import moonaframework.util.reflection.Reference;
import moonaframework.util.reflection.Reflection;
import moonaframework.util.reflection.Type;

public abstract class Flare<R extends Reflection<?>> extends Reflection<List<R>> {

	public static final Flare<Reflection<?>> EMPTY_FLARE = new Flare<>() {
		
		public @Override String toString() {
			return "Empty Flare";
		}
		
		private static final Type PLACEHOLDER = new Type(Flare.class);
		
		public @Deadlined Type getTarget() {
			return PLACEHOLDER;
		}
		
		{
			super.mirrorInteraction();
		}
	};
	
	public @Override boolean equals(Object o) {
		return (o instanceof Flare<?> flare) ? this.getTarget().equals(flare.getTarget()) : false;
	}
	
	public @Override abstract Reference<?> getTarget();
	
	protected boolean hasGenerated = false;
	
	public @Override void reflect() throws UndefinedReflectionException {
		for (R refl : super.value) {
			refl.evaluate();
		}
		hasGenerated = true;
	}
	
	public @Override List<R> evaluate() throws UndefinedReflectionException {
		if (!hasGenerated) {
			reflect();
		}
		return super.value;
	}
	
	protected Flare() {
		super(); super.value = new ArrayList<>();
	}
}
