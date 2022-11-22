package moonaframework.util.function;

public interface Mold<T, S> {

	T cast(S s);
}
