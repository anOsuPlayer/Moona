package moonaFramework.essentials;

import moonaFramework.MoonaHandlingException;

public interface Container<T extends Serial> extends Serial {
	
	@Override
	long id();
	@Override
	int nature();
	
	void add(T element) throws NullPointerException, MoonaHandlingException;
	
	void remove(T element) throws NullPointerException, MoonaHandlingException;
	
	int elementCount();
	
	T get(long id);
	
	boolean has(T element);
}
