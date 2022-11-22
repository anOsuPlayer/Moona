package test;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import moonaframework.*;
import moonaframework.base.Agent;
import moonaframework.base.Mirror;
import moonaframework.base.Moona;
import moonaframework.dynamic.CompositeProcess;
import moonaframework.dynamic.Handler;
import moonaframework.dynamic.Processor;
import moonaframework.dynamic.event.*;
import moonaframework.dynamic.process.*;
import moonaframework.util.Benchmark;
import moonaframework.util.annotations.*;
import moonaframework.util.collection.*;
import moonaframework.util.function.Mold;
import moonaframework.util.function.Snippet;
import moonaframework.util.reflection.*;
import moonaframework.util.time.*;

@SuppressWarnings("unused")
public class Test {
	
	@Unique
	static Task t = Handler.buildProcess(() -> { System.out.println("AAAAAAAAAAAAAa"); });
	
	static Task t2 = new Task() {
		public void update() {
			System.out.println("BBBBBBBB");
			t.getClock().sleep(500l);
		}
	};
	
	public static void main(String[] args) {
		Moona.init();
		
		Array<String> arr = new Array<>();
	}
}
