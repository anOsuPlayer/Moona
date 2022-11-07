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
import moonaFramework.process.ProcessBuilder;

@SuppressWarnings("unused")
public class Test {
	
	public static void main(String[] args) {
		Moona.init();
		
		Task t = ProcessBuilder.fromRunnable(() -> {
			System.out.println("AAAAA");
		});
		
		Processor.start(t);
	}
}
