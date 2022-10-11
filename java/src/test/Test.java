package test;

import java.util.ArrayList;
import java.util.List;

import moonaFramework.Benchmark;
import moonaFramework.Moona;
import moonaFramework.ProcessCondition;
import moonaFramework.Status;
import moonaFramework.process.*;
import moonaFramework.util.Condition;

@SuppressWarnings("unused")
public class Test {
	
	static Task t = new Task() {
		public void initialize() {
			System.out.println("CCCCCCCCC");
		}
		public void update() {
			System.out.println("AAAAAAAAA");
		}
		public void end() {
			System.out.println("BBBBBBBBB");
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		Moona.Init();
		
		Moona.Start(t);
		
		Thread.sleep(1000);
		
		Moona.Interrupt(t);
	}
}
