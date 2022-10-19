package test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.stream.Stream;

import moonaFramework.*;
import moonaFramework.annotations.*;
import moonaFramework.event.*;
import moonaFramework.process.*;
import moonaFramework.util.*;

@SuppressWarnings("unused")
public class Test {
	
	static EventDaemon e = new EventDaemon();
	
	static Event ev = new AbstractEvent(4) {
		public void onTrigger() {
			System.out.println("AAAAAAA");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		Moona.init();
		
		Moona.start(e);
		e.add(ev);
	}
}
