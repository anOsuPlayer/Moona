package moonaFramework.reflection;

import moonaFramework.essentials.Serial;

public sealed interface Property<T> extends Serial permits Reflection<T> {

	@Override
	long id();
	@Override
	int nature();
	
	T evaluate();
}
