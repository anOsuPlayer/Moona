package moonaFramework.dynamic.process;

import moonaFramework.dynamic.Processor;
import moonaFramework.dynamic.event.Event;
import moonaFramework.dynamic.event.EventMode;
import moonaFramework.dynamic.event.ModalEvent;

public class EventSpace extends EventPlace {
	
	@Override
	protected void whenEmpty() {
		if (eventCount() == 0) {
			Processor.terminate(this);
		}
	}
	
	@Override
	public void update() {
		flush();
		
		for (Event e : events.values()) {
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
