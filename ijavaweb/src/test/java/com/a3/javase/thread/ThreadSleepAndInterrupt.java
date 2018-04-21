package com.a3.javase.thread;

public class ThreadSleepAndInterrupt implements Runnable {

	public void run() {
		try {
			System.out.println("在 run()方法中 - 这个线程休眠 10 秒");
			Thread.sleep(10000);
			System.out.println("在 run()方法中休眠结束 - 继续运行");
		} catch (InterruptedException x) {
			System.out.println("在 run()方法中 - 中断线程");
			return;
		}
		System.out.println("在 run()方法中 - 休眠之后继续完成");
		System.out.println("在 run()方法中 - 正常退出");
	}

}
