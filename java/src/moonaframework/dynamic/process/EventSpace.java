package moonaframework.dynamic.process;

import moonaframework.dynamic.Processor;
import moonaframework.dynamic.event.Event;
import moonaframework.dynamic.event.EventMode;
import moonaframework.dynamic.event.ModalEvent;

public class EventSpace extends EventPlace {
	
	protected @Override void whenEmpty() {
		if (eventCount() == 0) {
			Processor.terminate(this);
		}
	}
	
	public @Override void update() {
		flush();
		
		for (Event e : events) {
			if (e instanceof ModalEvent me) {
				if (me.getMode().equals(EventMode.ONCE)) {
					toRemove.add(e);
				}
				if (me.getMode().equals(EventMode.REPEAT)) {
					if (me.getIterations() - 1 == 0) {
						toRemove.add(e);
					}
					me.setIterations(me.getIterations()-1);
				}
				if (me.getMode().equals(EventMode.UNTIL) && me.getCondition() != null) {
					if (!me.getCondition().verify()) {
						toRemove.add(me);
					}
				}
			}
			else {
				toRemove.add(e);
			}
			e.trigger();
		}
	}
	
	public EventSpace(Event...es) {
		super(es);
	}
	public EventSpace() {
		super();
	}
}
