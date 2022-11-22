package moonaframework.util.relation;

/**
 * The Attached Interface is the most basic relation object in the whole framework. This type only contains
 * one abstract method, .getHost() method allows a forced connection to an object of type T (forced
 * connection = the initial host object cannot be changed).
 * 
 * @author MasterZEr0
 * @param <T> : The type of the host.
 */
public interface Attached<T> {

	/**
	 * This method is used by subclasses to return a host. A host is nothing but an object of type T which
	 * a certain Attached depends from (usually it's stored in some subclass' field).
	 * 
	 * @return The host of type T.
	 */
	T getHost();
}
