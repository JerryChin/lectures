package org.example.examples.concurrency.problems;

/**
 * 死锁演示
 */
public class DeadLock {

    public static final Object A = new Object();
    public static final Object B = new Object();

    public static void main( String[] args ) throws InterruptedException {

        // 先锁定 A 再锁定 B
        new Thread(() -> {
            synchronized (A) {
                System.err.println("A lock granted.");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (B) {
                    System.err.println("B lock granted.");
                }
            }
        }).start();

        // 先锁定 B 再锁定 A
        new Thread(() -> {
            synchronized (B) {
                System.err.println("B lock granted.");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (A) {
                    System.err.println("A lock granted.");
                }
            }
        }).start();

       Thread.sleep(1000000);
    }
}
