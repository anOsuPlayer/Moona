package moonaFramework.util.conditions;

public class Condition implements Conditional {

	private boolean value;
	
	@Override
	public boolean verify() {
		return value;
	}
	@Override
	public void reverse() {
		this.value = !value;
	}
	@Override
	public void setValue(boolean value) {
		this.value = value;
	}
	
	@Override
	public Conditional opposite() {
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
