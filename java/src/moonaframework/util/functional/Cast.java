package moonaframework.util.functional;

import java.util.function.Function;

public @FunctionalInterface interface Cast<T> extends Mold<Object, T> {
	
	default Function<Object, T> translate() {
		return (Object o) -> cast(o);
	}
	
	@Override T cast(Object object);
}
