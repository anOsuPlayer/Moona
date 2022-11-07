package moonaFramework.util.conditions;

public interface Conditional {

	boolean verify();
	
	void reverse();
	
	void setValue(boolean value);
	
	Conditional opposite();
}
