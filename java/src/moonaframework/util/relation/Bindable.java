package moonaframework.util.relation;

public interface Bindable<T> extends Attached<T> {
	
	@Override T getHost();
	
	void setHost(T host);

	void unbind();
}
