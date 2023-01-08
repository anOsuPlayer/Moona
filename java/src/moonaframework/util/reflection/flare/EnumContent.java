package moonaframework.util.reflection.flare;

import moonaframework.util.exception.NullArgumentException;
import moonaframework.util.exception.UndefinedReflectionException;
import moonaframework.util.reflection.Enumeration;

public final class EnumContent extends TypeContent {

	private int enumFieldCount = 0;
	
	public int enumFieldCount() {
		return this.enumFieldCount;
	}
	
	
	
	public @Override boolean equals(Object o) {
		return (o instanceof EnumContent ec) ? this.getTarget().equals(ec.getTarget()) : false;
	}
	
	public @Override String toString() {
		return (target == null) ? "Non-generated Flare" : "EnumContent of " + target;
	}
	
	public @Override void reflect() throws UndefinedReflectionException {
		super.reflect();
		
		
	}
	
	public EnumContent(Enumeration source) throws NullArgumentException {
		super(source);
	}
	public EnumContent(Class<? extends Enum<?>> source) throws NullArgumentException {
		super(source);
	}
}
