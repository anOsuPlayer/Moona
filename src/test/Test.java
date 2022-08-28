package test;

import moonaFramework.Moona;
import moonaFramework.process.AbstractProcess;

@SuppressWarnings("unused")
public class Test {

	public static void main(String[] args) {
		AbstractProcess a = new AbstractProcess() {
			
			@Override
			public void update() {
				System.out.println("AAAAAAA");
			}
			
			@Override
			public void initialize() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public long id() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void end() {
				// TODO Auto-generated method stub
				
			}
		};
		
		Moona.Await(a);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Moona.Unlock(a);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Moona.Spark(a);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Moona.Spark(a);
	}
}
