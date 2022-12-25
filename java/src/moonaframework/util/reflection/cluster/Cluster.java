package moonaframework.util.reflection.cluster;

import java.util.List;
import moonaframework.util.reflection.Reflection;

public interface Cluster<R extends Reflection<?>> {

	Object getSource();
	
	List<R> getReflections();
}
