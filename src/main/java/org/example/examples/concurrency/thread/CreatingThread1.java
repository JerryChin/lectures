package org.example.examples.concurrency.thread;

/**
 * 创建线程的两种方式
 */
public class CreatingThread1 {

    public static void main(String[] args) {

        // 第一种方式
        Thread thread1 = new Thread(new MyRunnable());
        thread1.start();

        // 第一种方式
        Thread thread2 = new MyThread();
        thread2.start();
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello world from MyRunnable!");
        }
    }

    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello world from MyThread!");
        }
    }
}
