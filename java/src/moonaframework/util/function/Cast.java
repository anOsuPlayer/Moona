package moonaframework.util.function;

import java.util.function.Function;

public interface Cast<T> extends Mold<Object, T> {
	
	default Function<Object, T> translate() {
		return (Object o) -> cast(o);
	}
	
	@Override
	T cast(Object object);
}
