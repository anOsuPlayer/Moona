package moonaframework.util.functional;

import java.util.function.Function;

public @FunctionalInterface interface Mold<T, S> extends Satellite<Function<T, S>> {

	default @Override Function<T, S> translate() {
		return (T t) -> cast(t);
	}
	
	S cast(T s);
}
