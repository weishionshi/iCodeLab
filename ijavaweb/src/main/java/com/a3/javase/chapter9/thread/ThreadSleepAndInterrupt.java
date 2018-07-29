package com.a3.javase.chapter9.thread;

public class ThreadSleepAndInterrupt implements Runnable {

	public void run() {
		try {
			System.out.println("鍦� run()鏂规硶涓� - 杩欎釜绾跨▼浼戠湢 10 绉�");
			Thread.sleep(10000);
			System.out.println("鍦� run()鏂规硶涓紤鐪犵粨鏉� - 缁х画杩愯");
		} catch (InterruptedException x) {
			System.out.println("鍦� run()鏂规硶涓� - 涓柇绾跨▼");
			return;
		}
		System.out.println("鍦� run()鏂规硶涓� - 浼戠湢涔嬪悗缁х画瀹屾垚");
		System.out.println("鍦� run()鏂规硶涓� - 姝ｅ父閫�鍑�");
	}

}
