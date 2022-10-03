package moonaFramework.relation;

public interface Bindable<T> extends Attached<T> {
	
	void setHost(T host);
	
	void unbind();
}
