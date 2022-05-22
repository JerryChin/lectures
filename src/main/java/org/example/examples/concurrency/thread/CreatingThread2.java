package org.example.examples.concurrency.thread;


/**
 * 创建线程时可以指定的参数
 */
public class CreatingThread2 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {

            Thread currentThread = Thread.currentThread();
            System.out.println("Thread Name: " + currentThread.getName());
            System.out.println("Priority: " + currentThread.getPriority());
            System.out.println("Daemon: " + currentThread.isDaemon());
            System.out.println("UncaughtExceptionHandler: " + currentThread.getUncaughtExceptionHandler());

            throw new RuntimeException("故意抛出未处理异常，应该被捕捉。");
        });

        thread1.setName("My Thread");
        thread1.setPriority(5);
        thread1.setDaemon(false);
        thread1.setUncaughtExceptionHandler((t, e) -> System.err.println("caught exception!" + e));
        thread1.start();
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
