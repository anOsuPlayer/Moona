package moonaframework.util.function;

import java.util.function.Function;
import moonaframework.base.Satellite;

public interface Mold<T, S> extends Satellite<Function<T, S>> {

	default Function<T, S> translate() {
		return (T t) -> cast(t);
	}
	
	S cast(T s);
}
