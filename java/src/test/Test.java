package test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Stream;

import moonaFramework.*;
import moonaFramework.annotations.*;
import moonaFramework.event.*;
import moonaFramework.process.*;
import moonaFramework.util.*;

@SuppressWarnings("unused")
public class Test {
	
	static EventSpace es = new EventSpace();
	
	static AutoEvent a = new AutoEvent(10) {
		public void trigger() {
			System.out.println("AAAAAAA");
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		Moona.init();
	}
}
