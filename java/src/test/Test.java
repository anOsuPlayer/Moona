package test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.stream.Stream;

import moonaFramework.*;
import moonaFramework.annotations.*;
import moonaFramework.process.*;
import moonaFramework.util.Condition;

@SuppressWarnings("unused")
public class Test {
	
	static Phase p = new Phase();
	
	static Task t = new Task() {
		public void update() {
			System.out.println("AAAAAAAAA");
		}
	};
	
	static Devil d = new Devil(p) {
		public void update() {
			System.out.println("BBBBBBBB");
		}
	};
	
	public static void main(String[] args) {
		Moona.init();
		
		p.start(t);
	}
}
