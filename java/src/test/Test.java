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
	
	static EventPlace e = new EventPlace();
	
	static Action a = new Action(EventMode.ONCE) {
		public void trigger() {
			System.out.println("AAAAAAA");
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		Moona.init();
		
		e.add(a);
		Moona.start(e);
	}
}
