package moonaframework.util.reflection.cluster;

import java.util.ArrayList;
import java.util.List;

import moonaframework.util.annotation.Deadlined;
import moonaframework.util.reflection.Reflection;

public abstract class Cluster<R extends Reflection<?>> extends Reflection<List<R>> {

	public static final Cluster<Reflection<?>> EMPTY_CLUSTER = new Cluster<>() {
		public @Deadlined Object getTarget() {
			return null;
		}
	};
	
	public @Override abstract Object getTarget();
	
	private boolean hasGenerated = false;
	
	public @Override void reflect() {
		for (R refl : super.value) {
			refl.evaluate();
		}
		hasGenerated = true;
	}
	
	public @Override final List<R> evaluate() {
		if (!hasGenerated) {
			reflect();
		}
		return super.value;
	}
	
	protected Cluster() {
		super(); super.value = new ArrayList<>();
	}
}
