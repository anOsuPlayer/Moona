package moonaFramework.relation;

/**
 * Bindables allow for a more flexible approach to the relation between Attached and host: using Bindables
 * you have a built-in way to deatach or change the object host.
 * 
 * @author MasterZEr0
 * @param <T> : The type of the host.
 */
public interface Bindable<T> extends Attached<T> {
	
	@Override
	T getHost();
	
	/**
	 * Switches the current host with another one passed as argument to this method. Setting the host to null
	 * is equivalent to calling the {@link #unbind()} method.
	 * 
	 * @param host : The new host.
	 */
	void setHost(T host);

	/**
	 * Sets the current host to null. This way, the Bindable will lack an host until further reassignment.
	 * Please note that, when a Bindable lacks a host, you should take some null-safety precautions in order
	 * to not make a mess.
	 */
	void unbind();
}
