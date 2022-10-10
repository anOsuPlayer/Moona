package test;

import java.util.ArrayList;
import java.util.List;

import moonaFramework.Benchmark;
import moonaFramework.Moona;
import moonaFramework.ProcessCondition;
import moonaFramework.process.*;

@SuppressWarnings("unused")
public class Test {
	
	static Daemon d = new Daemon() {
		public void update() {
			System.out.println("CCCCCCCCC");
		}
	};
	
	static Task t = new Task() {
		public void update() {
			System.out.println("AAAAAAAAA");
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		Moona.Init();
		
		Moona.Start(d);
		Thread.sleep(1000);
		Moona.Terminate(t);
		
		
	}
}
