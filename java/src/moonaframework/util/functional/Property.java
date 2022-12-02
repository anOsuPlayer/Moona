package moonaframework.util.functional;

import java.util.function.Supplier;
import moonaframework.base.Satellite;

public @FunctionalInterface interface Property<T> extends Satellite<Supplier<T>> {

	default @Override Supplier<T> translate() {
		return () -> evaluate();
	}
	
	T evaluate();
}
