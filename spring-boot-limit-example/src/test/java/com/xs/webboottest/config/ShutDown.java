package com.xs.webboottest.config;

public class ShutDown {
	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(10000);
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}).start();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("钩子被触发");
			}
		}));

	}

}
