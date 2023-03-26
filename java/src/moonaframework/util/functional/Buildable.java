package moonaframework.util.functional;

public @FunctionalInterface interface Buildable<T> {

	Buildable<T> build(T source);
}
