package moonaframework.util.reflection.cluster;

import java.util.List;

import moonaframework.util.annotation.Deadlined;
import moonaframework.util.reflection.Reflection;

public abstract class Cluster<R extends Reflection<?>> extends Reflection<List<R>> {

	public static final Cluster<Reflection<?>> EMPTY_CLUSTER = new Cluster<>() {
		public @Deadlined Object getTarget() {
			return null;
		}
		
		public @Deadlined List<Reflection<?>> evaluate() {
			return List.of();
		}
	};
	
	public @Override abstract Object getTarget();
	
	public @Override void reflect() {
		for (R refl : super.value) {
			refl.reflect();
		}
	}
	
	public @Override List<R> evaluate() {
		return super.evaluate();
	}
	
	protected Cluster() {
		
	}
}
