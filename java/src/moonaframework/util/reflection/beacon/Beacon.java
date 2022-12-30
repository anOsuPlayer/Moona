package moonaframework.util.reflection.beacon;

import java.util.ArrayList;
import java.util.List;

import moonaframework.base.Moona;
import moonaframework.util.annotation.Deadlined;
import moonaframework.util.exception.UnresolvedReflectionException;
import moonaframework.util.reflection.Mirror;
import moonaframework.util.reflection.Reflection;

public abstract class Beacon<R extends Reflection<?>> extends Reflection<List<R>> {

	public static final Beacon<Reflection<?>> EMPTY_BEACON = new Beacon<>() {
		
		public @Override String toString() {
			return "Empty Beacon";
		}
		
		private static final Object PLACEHOLDER = new Object();
		
		public @Deadlined Object getTarget() {
			return PLACEHOLDER;
		}
		
		{
			if (Moona.autoReflections.evaluate()) {
				Mirror.add(this);
			}
		}
	};
	
	public @Override final boolean equals(Object o) {
		return (o instanceof Beacon<?> beacon) ?
				this.getTarget().equals(beacon.getTarget())
				: false;
	}
	
	public @Override abstract Object getTarget();
	
	protected boolean hasGenerated = false;
	
	public @Override void reflect() throws UnresolvedReflectionException {
		for (R refl : super.value) {
			refl.evaluate();
		}
		hasGenerated = true;
	}
	
	public @Override List<R> evaluate() throws UnresolvedReflectionException {
		if (!hasGenerated) {
			reflect();
		}
		return super.value;
	}
	
	protected Beacon() {
		super(); super.value = new ArrayList<>();
	}
}
