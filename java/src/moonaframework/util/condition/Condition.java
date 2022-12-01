package moonaframework.util.condition;

public class Condition implements Conditional {

	private boolean value;
	
	public @Override boolean verify() {
		return value;
	}
	public @Override void reverse() {
		this.value = !value;
	}
	public @Override void setValue(boolean value) {
		this.value = value;
	}
	
	public @Override Conditional opposite() {
		return new Condition(!value);
	}
	
	public Condition(Conditional c) {
		this.value = c.verify();
	}
	public Condition(boolean value) {
		this.value = value;
	}
	public Condition() {
		this.value = false;
	}
}
