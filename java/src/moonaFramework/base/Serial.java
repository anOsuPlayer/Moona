package moonaFramework.base;

/**
 * Serials aim to extend the distinction between objects stated by the Natural Interface. 
 * <br><br>
 * Since serial elements are required to be distinguished from each other, they also need to be told apart
 * in a more general way, this is because this interface extends Natural.
 * <br><br>
 * The only method which this interface contains is the .id() method: the thing that tells apart different
 * serials is a long number returned by this method. This given long number is also the thing which can make
 * every serial object recallable by the Moona Class.
 * 
 * @author MasterZEr0
 */
public interface Serial {

	/**
	 * According to the definition of this Interface, every single Serial is required to have a different id.
	 * Since IDs cannot be arbitrarily assigned to objects, often you'll find them stored inside a private
	 * long field in every subclass.
	 * 
	 * @return The ID of this serial.
	 */
	long id();
}
