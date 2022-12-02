package moonaframework.util.functional;

import java.util.function.Supplier;
import moonaframework.base.Satellite;

public @FunctionalInterface interface Packable<E> extends Satellite<Supplier<E[]>> {

	default @Override Supplier<E[]> translate() {
		return () -> unpack();
	}
	
	E[] unpack();
}
