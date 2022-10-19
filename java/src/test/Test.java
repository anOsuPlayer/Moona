package test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.stream.Stream;

import moonaFramework.*;
import moonaFramework.annotations.*;
import moonaFramework.event.AbstractEvent;
import moonaFramework.event.Event;
import moonaFramework.event.EventMode;
import moonaFramework.event.EventSpace;
import moonaFramework.process.*;
import moonaFramework.util.Condition;

@SuppressWarnings("unused")
public class Test {
	
	static EventSpace e = new EventSpace();
	
	static Event ev = new AbstractEvent(EventMode.REPEAT.setIterations(3)) {
		public void onTrigger() {
			System.out.println("AAAAAAA");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	public static void main(String[] args) {
		Moona.init();
		
		Moona.start(e);
		e.add(ev);
	}
}
