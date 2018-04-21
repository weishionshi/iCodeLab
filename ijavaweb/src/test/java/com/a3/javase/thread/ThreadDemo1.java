package com.a3.javase.thread;

public class ThreadDemo1 implements Runnable {

	public void run() {
		String str = new String();
		int i = 0;
		for (int x = 0; x < 10; x++) {
			System.out.println(Thread.currentThread().getName() + " ---->> " + i++);
		}
	}

}
