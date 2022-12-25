package moonaframework.util.condition;

import java.util.function.Supplier;

import moonaframework.util.annotation.Functional;
import moonaframework.util.functional.Satellite;

public @Functional interface Conditional extends Satellite<Supplier<Boolean>> {
	
	default @Override Supplier<Boolean> translate() {
		return () -> verify();
	}

	boolean verify();
	
	void reverse();
	
	void setValue(boolean value);
	
	Conditional opposite();
}
