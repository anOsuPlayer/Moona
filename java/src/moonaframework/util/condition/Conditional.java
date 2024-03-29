package moonaframework.util.condition;

import java.util.function.Supplier;

import moonaframework.util.annotation.Functional;
import moonaframework.util.functional.Translatable;

public @Functional interface Conditional extends Translatable<Supplier<Boolean>> {
	
	default @Override Supplier<Boolean> translate() {
		return () -> verify();
	}

	boolean verify();
	
	void reverse();
	
	void setValue(boolean value);
	
	Conditional opposite();
}
