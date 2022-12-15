package moonaframework.util.reflection;

import moonaframework.base.Nature;
import moonaframework.base.Serial;
import moonaframework.util.functional.Property;

public interface Reflection<T> extends Property<T>, Serial {
	
	default @Override Nature nature() {
		return Nature.REFLECTION;
	}
	
	@Override long id();
	
	Object getTarget();
	
	void reflect();
	
	@Override T evaluate();
}
