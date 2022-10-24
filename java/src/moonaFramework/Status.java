package moonaFramework;

import moonaFramework.annotations.Deadlined;
import moonaFramework.util.Condition;

public class Status extends Condition {

	@Deadlined
	public void setValue(boolean value) {
	}
	void imposeSet(boolean value) {
		super.setValue(value);
	}
	
	@Deadlined
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
