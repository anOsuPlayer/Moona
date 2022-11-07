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
import moonaFramework.base.Moona;
import moonaFramework.base.Processor;
import moonaFramework.dynamic.Creator;
import moonaFramework.dynamic.event.*;
import moonaFramework.dynamic.process.*;
import moonaFramework.util.Benchmark;
import moonaFramework.util.annotations.*;
import moonaFramework.util.collection.*;
import moonaFramework.util.function.Cast;
import moonaFramework.util.function.Snippet;
import moonaFramework.util.reflection.*;
import moonaFramework.util.time.*;

@SuppressWarnings("unused")
public class Test {
	
	static int a = 12;
	
	static Daemon d = Creator.buildDaemon(() -> { System.out.println("a"); });
	
	static Cast<Daemon, Worm> caster = new Cast<>() {
		public Worm cast(Daemon object) {
			return new Worm() {
				@Override
				public void update() {
					object.update();
				}
			};
		}
	};
	
	public static void main(String[] args) {
		Moona.init();
		
		System.out.println(Benchmark.TIME(() -> {
			Worm w = caster.cast(d);
		}));
	}
}
