package org.example.examples.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.example.examples.util.Printer.print;

/**
 * synchronized 关键字 —— synchronized 方法
 */
public class SynchronizedMethod {

    public static void main(String[] args) {
        runSafeVersion();
//        runUnsafeVersion();
    }
    private static void runUnsafeVersion() {
        ExecutorService service = Executors.newCachedThreadPool();
        UnsafeCounter unsafeCounter = new UnsafeCounter();

        for (int i = 0;  i <  10; i++) {
            service.submit(new Runner(unsafeCounter));
        }
    }

    private static void runSafeVersion() {
        ExecutorService service = Executors.newCachedThreadPool();
        SafeCounter safeCounter = new SafeCounter();


        for (int i = 0;  i <  10; i++) {
            service.submit(new Runner(safeCounter));
        }
    }

    private static class Runner implements Runnable {
        private final EvenCounter counter;

        private Runner(EvenCounter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            while (counter.get() < 1000_0000) {
                counter.increment();

                long value = counter.get();
                if(value % 2 != 0) {
                    print("发生异常！ " + value);
                    return;
                }
            }

            print("执行完成");
        }
    }

    private interface EvenCounter {
        void increment();
        long get();
    }

    private static class SafeCounter implements EvenCounter {
        private long counter;

        public synchronized void increment() {
            counter += 1;
            counter += 1;
        }

        public synchronized long get() {
            return counter;
        }
    }

    private static class UnsafeCounter implements EvenCounter {
        private long counter;

        public void increment() {
            counter += 1;
            counter += 1;
        }

        public long get() {
            return counter;
        }
    }
}
