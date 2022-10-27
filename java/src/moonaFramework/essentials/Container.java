package moonaFramework.essentials;

public interface Container<E extends Serial> {

	void add(E element);
	
	void remove(E element);
	
	int elementCount();
	
	E get(long id);
}
