package test;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import moonaFramework.*;
import moonaFramework.annotations.*;
import moonaFramework.basics.Serial;
import moonaFramework.event.*;
import moonaFramework.process.*;
import moonaFramework.process.Process;
import moonaFramework.reflection.*;
import moonaFramework.time.*;
import moonaFramework.util.*;

@SuppressWarnings("unused")
public class Test {
	
	static Task t = new Task() {
		public void onPause() {
			System.out.println("BBBBBBBBBB");
		}
		public void update() {
			System.out.println("A");
		}
	};
	static Task t2 = new Task() {
		public void onPause() {
			System.out.println("AAAAAAAAAA");
		}
		public void update() {
			System.out.println("B");
		}
	};
	
	static Synchronizer s = new Synchronizer(new Timer(10l), t, t2);
	
	public static void main(String[] args) {
		Moona.init();
		
		Processor.start(s);
		
		Benchmark.WAIT(61);
		
		Processor.spark(s);
	}
}
