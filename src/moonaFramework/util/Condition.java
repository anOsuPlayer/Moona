package moonaFramework.util;

public class Condition implements Conditional {

	private boolean value;
	
	public boolean verify() {
		return value;
	}
	public void reverse() {
		this.value = !value;
	}
	public void setValue(boolean value) {
		this.value = value;
	}
	
	public Conditional opposite() {
		return new Condition(!value);
	}
	
	public Condition(boolean value) {
		this.value = value;
	}
	public Condition() {
		this.value = false;
	}
}
