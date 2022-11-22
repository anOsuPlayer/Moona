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

import moonaFramework.*;
import moonaFramework.base.Agent;
import moonaFramework.base.Mirror;
import moonaFramework.base.Moona;
import moonaFramework.dynamic.CompositeProcess;
import moonaFramework.dynamic.Handler;
import moonaFramework.dynamic.Processor;
import moonaFramework.dynamic.event.*;
import moonaFramework.dynamic.process.*;
import moonaFramework.dynamic.process.Process;
import moonaFramework.util.Benchmark;
import moonaFramework.util.annotations.*;
import moonaFramework.util.collection.*;
import moonaFramework.util.function.Mold;
import moonaFramework.util.function.Snippet;
import moonaFramework.util.reflection.*;
import moonaFramework.util.time.*;

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
	}
}
