package moonaframework.util.function;

import java.util.function.Supplier;
import moonaframework.base.Satellite;

public interface Property<T> extends Satellite<Supplier<T>> {

	default @Override Supplier<T> translate() {
		return () -> evaluate();
	}
	
	T evaluate();
}
