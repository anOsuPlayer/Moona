package moonaFramework.util;

public interface Mold<T, S> {

	T cast(S s);
}
