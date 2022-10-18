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

	static Task d = new Task() {
		public void initialize() {
			
		}
		public void update() {
			System.out.println("AAAAAAAAA");
		}
	};
	
	static Worm w = new Worm() {
		public void update() {
			System.out.println("BBBBBBBBB");
		}
	};
	
	public static void main(String[] args) {
		Moona.init();
	}
}
