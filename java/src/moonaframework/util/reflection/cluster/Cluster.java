package moonaframework.util.reflection.cluster;

import java.util.List;

import moonaframework.util.annotation.Deadlined;
import moonaframework.util.reflection.Reflection;

public interface Cluster<R extends Reflection<?>> {

	Cluster<Reflection<?>> EMPTY_CLUSTER = new Cluster<>() {
		public @Deadlined Object getSource() { return null; }
		public @Deadlined List<Reflection<?>> getReflections() { return List.of(); }
	};
	
	Object getSource();
	
	List<R> getReflections();
}
