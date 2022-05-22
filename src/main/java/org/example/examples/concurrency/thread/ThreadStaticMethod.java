package org.example.examples.concurrency.thread;


import java.util.Date;

/**
 * 线程静态方法演示
 */
public class ThreadStaticMethod {

    public static void main(String[] args) throws InterruptedException {

        // sleep 方法
        System.out.println(new Date());
        Thread.sleep(5000);
        System.out.println(new Date());

        // currentThread 方法，获取当前执行线程
       Thread currentThread = Thread.currentThread();

        System.out.println("Thread Name: " + currentThread.getName());
        System.out.println("Priority: " + currentThread.getPriority());
        System.out.println("Daemon: " + currentThread.isDaemon());
        System.out.println("UncaughtExceptionHandler: " + currentThread.getUncaughtExceptionHandler());
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
