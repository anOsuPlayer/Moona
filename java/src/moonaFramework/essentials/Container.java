package moonaFramework.essentials;

import moonaFramework.MoonaHandlingException;

public interface Container<E extends Serial> extends Serial {
	
	@Override
	long id();
	@Override
	int nature();
	
	void add(E element) throws NullPointerException, MoonaHandlingException;
	
	void remove(E element) throws NullPointerException, MoonaHandlingException;
	
	int elementCount();
	
	E get(long id);
	
	boolean has(E element);
}
