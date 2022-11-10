package moonaFramework.dynamic;

import moonaFramework.util.annotations.Deadlined;
import moonaFramework.util.condition.Condition;

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
	void imposeReverse() {
		super.reverse();
	}
	
	public Status(boolean value) {
		super(value);
	}
	public Status() {
		super(false);
	}
}
