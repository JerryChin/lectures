package org.example.examples;

/**
 * Hello world!
 *
 */
public class DeadLockApp {

    public static final Object A = new Object();
    public static final Object B = new Object();


    public static void main( String[] args ) throws InterruptedException {
       System.out.println( "Hello World!" );

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
