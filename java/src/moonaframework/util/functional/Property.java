package moonaframework.util.functional;

import java.util.function.Supplier;

public @FunctionalInterface interface Property<T> extends Translatable<Supplier<T>> {

	default @Override Supplier<T> translate() {
		return () -> evaluate();
	}
	
	T evaluate();
}
