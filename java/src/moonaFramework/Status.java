package moonaFramework;

import moonaFramework.util.Condition;

public class Status extends Condition {

	public void setValue(boolean value) {
	}
	void imposeSet(boolean value) {
		super.setValue(value);
	}
	
	public void reverse() {
	}
	void imposeReverse(boolean value) {
		super.reverse();
	}
	
	public Status(boolean value) {
		super(value);
	}
	public Status() {
		super(false);
	}
}
