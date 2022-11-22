package moonaframework.util.condition;

public interface Conditional {

	boolean verify();
	
	void reverse();
	
	void setValue(boolean value);
	
	Conditional opposite();
}
