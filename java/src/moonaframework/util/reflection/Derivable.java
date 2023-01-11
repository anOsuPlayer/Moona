package moonaframework.util.reflection;

import moonaframework.util.reflection.flare.Annotated;
import moonaframework.util.reflection.flare.Flare;

public interface Derivable {

	Flare<?> derive();
	
	Annotated getAnnotated();
}
