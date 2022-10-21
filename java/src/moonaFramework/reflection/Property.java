package moonaFramework.reflection;

public sealed interface Property<T> permits Reflection<T> {

	T evaluate();
}
